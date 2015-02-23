package pulsar.command

import akka.util.ByteString
import zeromq.Message

/**
 * @author Alberto J. Rubio
 */

trait Command

object Command {
  def apply(message: Message): Command = {
    val id = message(0)
    val content = new String(message(1).toArray).toUpperCase.split(" ")
    content(0) match {
      case "REG" => RegisterSocket(id, content(1))
    }
  }
}
