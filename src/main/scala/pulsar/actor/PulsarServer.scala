package pulsar.actor

import akka.actor.{Actor, Props}
import akka.util.ByteString
import pulsar.Pulsar
import zeromq._


/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class PulsarServer extends Actor {
  val pulsar = Pulsar(context.system)

  val zmq = ZeroMQExtension(context.system)
  val socket = zmq.newSocket(SocketType.Router, Bind("tcp://*:6666"), Listener(self))

  override def receive: Receive = {
    case RegisterCommand(id, t) =>
      pulsar taskHandler (t, context.system.actorOf(ClientDelegate.props(socket, id)))
    case message: Message =>
      self ! Command(message)
  }
}

object Command {
  def apply(message: Message): Command = {
    val id = message(0)
    val content = new String(message(1).toArray).toUpperCase.split(" ")
    content(0) match {
      case "REG" => RegisterCommand(id, content(1))
    }
  }
}

abstract class Command
case class RegisterCommand(socketId: ByteString, `type`: String) extends Command


object PulsarServer {
  def props = Props(new PulsarServer)
}
