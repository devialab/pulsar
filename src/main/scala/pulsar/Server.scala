package pulsar

import akka.actor.ActorSystem
import pulsar.actor.PulsarServer

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
object Server extends App {

  val system = ActorSystem("pulsar-server")

  system.actorOf(PulsarServer.props)

}
