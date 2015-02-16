package pulsar

import akka.actor.{ActorRef, ActorSystem}
import pulsar.action.Register
import pulsar.actor.{Dispatcher, Scheduler}

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
object Pulsar {

  def apply(implicit system: ActorSystem) = new {
    val dispatcher = system.actorOf(Dispatcher.props)
    val scheduler = system.actorOf(Scheduler.props(dispatcher))

    def taskHandler(`type`: String, handler: ActorRef) = dispatcher ! Register(`type`, handler)

  }

}
