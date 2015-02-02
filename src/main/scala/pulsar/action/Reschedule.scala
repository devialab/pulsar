package pulsar.action

import scala.concurrent.duration.FiniteDuration

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
case class Reschedule(id: String, delay: FiniteDuration, period: Option[FiniteDuration] = None)
