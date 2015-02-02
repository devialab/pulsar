package pulsar.model

import java.util.Date
import akka.util.ByteString

import scala.reflect.{ BeanProperty, BooleanBeanProperty }
import scala.concurrent.duration.FiniteDuration

/**
 * @author Alexander De Leon
 *
 */
case class Task(id: String, `type`: String, payload: ByteString)