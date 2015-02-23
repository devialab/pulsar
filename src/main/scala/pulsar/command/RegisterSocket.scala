package pulsar.command

import akka.util.ByteString

/**
 * @author Alberto J. Rubio
 */
case class RegisterSocket(socketId: ByteString, `type`: String) extends Command