package pulsar.actor

import akka.actor.{Actor, ActorRef, Props}
import akka.util.ByteString
import pulsar.action.Dispatch
import zeromq.Message

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class ClientDelegate(socket: ActorRef, clientId: ByteString) extends Actor {

  override def receive: Receive = {
    case Dispatch(task) => socket ! Message(clientId, task.payload)
  }
}

object ClientDelegate {
  def props(socket: ActorRef, clientId: ByteString) = Props(new ClientDelegate(socket, clientId))
}
