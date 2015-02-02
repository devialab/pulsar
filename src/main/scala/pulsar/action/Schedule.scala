package pulsar.action

import pulsar.model.Task

import scala.concurrent.duration.FiniteDuration

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
case class Schedule(task: Task, delay: FiniteDuration, period: Option[FiniteDuration] = None)
