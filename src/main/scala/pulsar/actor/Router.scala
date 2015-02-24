package pulsar.actor

import akka.actor.{ActorRef, Actor, Props}
import pulsar.action.Register
import pulsar.command.{RegisterCommand}
import pulsar.factory.CommandFactory
import zeromq._


/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class Router(dispatcher: ActorRef) extends Actor {

  val zmq = ZeroMQExtension(context.system)
  val socket = zmq.newSocket(SocketType.Router, Bind("tcp://*:6666"), Listener(self))

  override def receive: Receive = {
    case RegisterCommand(id, t) => dispatcher ! Register(t, context.system.actorOf(ClientConnection.props(socket, id)))
    case message: Message => self ! CommandFactory(message)
  }
}

object Router {
  def props(dispatcher: ActorRef) = Props(new Router(dispatcher))
}
