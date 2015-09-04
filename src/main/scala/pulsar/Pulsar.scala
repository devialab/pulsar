package pulsar

import akka.actor.ActorSystem
import pulsar.actor.{Dispatcher, Router}
import zeromq.{Listener, Bind, ZeroMQExtension, SocketType}

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
object Pulsar extends App {

  val system = ActorSystem("pulsar")

  val pulsarDispatcher = system.actorOf(Dispatcher.props)
  val router = system.actorOf(Router.props(pulsarDispatcher))

  val zmq = ZeroMQExtension(system)
  val socket = zmq.newSocket(SocketType.Router, Bind(s"tcp://*:6666"), Listener(router))

  router ! command.Bind(socket)

}
