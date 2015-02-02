package pulsar.actor

import akka.actor.{ActorRef, Cancellable, ActorSystem, Actor}
import pulsar.action.{Kill, Reschedule, Schedule, Dispatch}
import pulsar.model.Task
/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Scheduler(system: ActorSystem, dispatcher: ActorRef) extends Actor{

  var scheduledTasks = Map.empty[String, (Task, Cancellable)]

  def receive = {
    case Schedule(task, delay, period) => {
      val cancellable = period match {
        case None => system.scheduler.scheduleOnce(delay, dispatcher, Dispatch(task))
        case Some(period) => system.scheduler.schedule(delay, period, dispatcher, Dispatch(task))
      }
      saveScheduledTask(task, cancellable)
    }
    case Reschedule(id, delay, period) => Option(scheduledTasks(id)).map(scheduledTask => {
      self ! Kill(id)
      self ! Schedule(scheduledTask._1, delay, period)
    })
    case Kill(id) => cancelTask(id)
  }

  def cancelTask(id: String) = Option(scheduledTasks(id)).map(_._2.cancel())

  def saveScheduledTask(task: Task, cancellable: Cancellable) = scheduledTasks += (task.id -> (task, cancellable))
}