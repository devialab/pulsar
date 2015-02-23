package pulsar.actor

import akka.actor.{Actor, Props}
import pulsar.action.Register
import pulsar.command.{RegisterSocket, Command}
import zeromq._


/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class EntryPoint extends Actor {

  val dispatcher = context.system.actorOf(Dispatcher.props)
  val scheduler = context.system.actorOf(Scheduler.props(dispatcher))

  val zmq = ZeroMQExtension(context.system)
  val socket = zmq.newSocket(SocketType.Router, Bind("tcp://*:6666"), Listener(self))

  override def receive: Receive = {
    case RegisterSocket(id, t) => dispatcher ! Register(t, context.system.actorOf(ClientConnection.props(socket, id)))
    case message: Message => self ! Command(message)
  }
}

object EntryPoint {
  def props = Props(new EntryPoint)
}
