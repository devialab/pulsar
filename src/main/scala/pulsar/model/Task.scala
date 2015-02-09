package pulsar.model

import akka.util.ByteString

/**
 * @author Alexander De Leon
 *
 */
case class Task(id: String, `type`: String, payload: ByteString)