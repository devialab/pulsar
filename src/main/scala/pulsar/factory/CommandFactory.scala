package pulsar.factory

import pulsar.command.{Command, RegisterCommand}
import zeromq.Message

  /**
   * @author Alberto J. Rubio
   */
  object CommandFactory  {
    def apply(message: Message): Command = {
      val id = message(0)
      val content = new String(message(1).toArray).toUpperCase.split(" ")
      content(0) match {
        case cmd: String if cmd.equals("REG") => RegisterCommand(id, content(1))
      }
    }
  }
