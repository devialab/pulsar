package pulsar.actor

import akka.actor.{ActorLogging, Actor, ActorRef, Props}
import grizzled.slf4j.Logging
import pulsar.action.{Dispatch, Register}

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Dispatcher extends Actor with ActorLogging {

  var listeners = Map.empty[String, ActorRef]

  override def receive: Receive = {
    case Register(listenerType, listener) =>
      log.info(s"Registering listener $listenerType")
      listeners += (listenerType -> listener)

    case Dispatch(task) => listeners get task.`type` match {
      case Some(listener) => listener ! task
      //TODO: Here we lost the dispatching of the task. Maybe we should try a bit latter?
      case None => log.error(s"Listener does not exists for task type ${task.`type`}")
    }
  }
}

object Dispatcher {
  def props = Props[Dispatcher]
}
