package pulsar.client

import akka.util.ByteString
import rx.lang.scala.Observable

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
trait PulsarClient {

  def register(taskType: String): Observable[ByteString]

}
