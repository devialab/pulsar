package pulsar.command

import akka.util.ByteString

/**
 * @author Alberto J. Rubio
 */
case class RegisterCommand(socketId: ByteString, `type`: String) extends Command