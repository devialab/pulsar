package pulsar.actor

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import akka.util.ByteString
import org.scalatest.{BeforeAndAfter, Matchers, WordSpecLike}
import pulsar.action._
import pulsar.model.Task

import scala.concurrent.duration._

/**
 * @author Alberto J. Rubio
 */
class DispatcherTest extends TestKit(ActorSystem.create("DispatcherTest"))
with ImplicitSender
with WordSpecLike
with Matchers
with BeforeAndAfter {

  var listener: TestProbe = null
  var dispatcher: TestActorRef[Dispatcher] = null
  var scheduler: TestActorRef[Scheduler] = null

  val testTask = Task("id", "test", ByteString("payload"))

  before {
    listener = TestProbe()
    dispatcher = TestActorRef(new Dispatcher())
    scheduler = TestActorRef(new Scheduler(dispatcher))
  }

  "dispatcher" should {
    "register new listener and schedule new task" in {
      dispatcher ! Register("test", listener.ref)
      scheduler ! Schedule(testTask, 1 second)
      within(1 second, 1.2 second) {
        listener.expectMsg(testTask)
      }
    }
    "schedule new task without listener" in {
      scheduler ! Schedule(testTask, 1 second)
      listener.expectNoMsg(1.5 second)
    }
  }
}
