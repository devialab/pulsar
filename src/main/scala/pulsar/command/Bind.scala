package pulsar.command

import akka.actor.ActorRef

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
case class Bind(socket: ActorRef)
