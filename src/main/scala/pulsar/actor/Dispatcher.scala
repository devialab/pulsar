package pulsar.actor

import akka.actor.{Actor, ActorRef, Props}
import grizzled.slf4j.Logging
import pulsar.action.{Dispatch, Register}

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Dispatcher extends Actor with Logging {

  var listeners = Map.empty[String, ActorRef]

  def receive = {
    case Register(listenerType, listener) => listeners += (listenerType -> listener)
    case Dispatch(task) => listeners get task.`type` match {
      case Some(listener) => listener ! task
      //TODO: Here we lost the dispatching of the task. Maybe we should try a bit latter?
      case None => error(s"Listener does not exists for task type ${task.`type`}")
    }
  }
}

object Dispatcher {
  def props = Props(new Dispatcher)
}
