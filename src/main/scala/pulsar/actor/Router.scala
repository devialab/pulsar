package pulsar.actor

import akka.actor._
import pulsar.action.Register
import pulsar.command
import pulsar.command.{RegisterCommand}
import pulsar.factory.CommandFactory
import zeromq._


/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Router(pulsarDispatcher: ActorRef) extends Actor with ActorLogging {
  import context._

  override def receive: Receive = {
    case command.Bind(socket) => become(active(socket))
  }

  def active(socket: ActorRef): Receive = {
    case RegisterCommand(id, t) => pulsarDispatcher ! Register(t, actorOf(ClientConnection.props(socket, id)))
    case message: Message => self ! CommandFactory(message)
  }
}

object Router {
  def props(pulsarDispatcher: ActorRef) = Props(new Router(pulsarDispatcher))
}
