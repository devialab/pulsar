package pulsar

import akka.actor.ActorSystem
import pulsar.actor.EntryPoint

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
object Pulsar extends App {

  val system = ActorSystem("pulsar")

  system.actorOf(EntryPoint.props)

}
