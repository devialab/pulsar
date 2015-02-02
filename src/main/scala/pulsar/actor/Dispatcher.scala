package pulsar.actor

import akka.actor.{ActorRef, Actor}
import akka.util.ByteString
import grizzled.slf4j.Logging
import pulsar.action.Dispatch
import zeromq.Message

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Dispatcher extends Actor with Logging{

  var sockets = Map.empty[String, ActorRef]

  def receive = {
    case Dispatch(task) => Option(sockets(task.`type`)) match {
      case Some(socket) => socket ! Message(ByteString(task.`type`), ByteString(), task.payload)
      //TODO: Here we lost the dispatching of the task. Maybe we should try a bit latter?
      case None => error(s"Socket does not exists for task type ${task.`type`}")
    }
  }

}
