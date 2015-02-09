package pulsar.action

import akka.actor.ActorRef

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
case class Register(`type`: String, listener: ActorRef)
