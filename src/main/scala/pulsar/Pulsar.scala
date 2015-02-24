package pulsar

import akka.actor.ActorSystem
import pulsar.actor.{Router}

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
object Pulsar extends App {

  val system = ActorSystem("pulsar")

  system.actorOf(Router.props)

}
