package pulsar.actor

import akka.actor.{ActorRef, Actor}
import akka.util.ByteString
import grizzled.slf4j.Logging
import pulsar.action.{Register, Dispatch}
import zeromq.Message

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Dispatcher extends Actor with Logging{

  var listeners = Map.empty[String, ActorRef]

  def receive = {
    case Register(t, listener) => listeners += (t -> listener)
    case Dispatch(task) => Option(listeners(task.`type`)) match {
      case Some(listener) => listener ! task
      //TODO: Here we lost the dispatching of the task. Maybe we should try a bit latter?
      case None => error(s"Listener does not exists for task type ${task.`type`}")
    }
  }

}
