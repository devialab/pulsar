package pulsar.actor

import akka.actor.ActorSystem
import akka.testkit._
import akka.util.ByteString
import org.scalatest.{BeforeAndAfter, Matchers, WordSpecLike}
import pulsar.action.{Dispatch, Kill, Reschedule, Schedule}
import pulsar.model.Task

import scala.concurrent.duration._

/**
 * @author Alexander De Leon <me@alexdeleon.name>
 */
class SchedulerTest extends TestKit(ActorSystem.create("SchedulerTest"))
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfter {

  var dispatcher: TestProbe = null
  var scheduler: TestActorRef[Scheduler] = null

  before {
    dispatcher = TestProbe()
    scheduler = TestActorRef(new Scheduler(dispatcher.ref))
  }

  "scheduler" should {
    "schedule new task" in {
      val testTask = Task("id", "test", ByteString("payload"))
      scheduler ! Schedule(testTask, 1 second)
      within(1 second, 1.2 second) {
        dispatcher.expectMsg(Dispatch(testTask))
      }
    }
    "delaying existing task" in {
      val testTask = Task("id", "test", ByteString("payload"))
      scheduler ! Schedule(testTask, 0.5 second)
      scheduler ! Reschedule("id", 1 second)
      within(1 second, 1.2 second) {
        dispatcher.expectMsg(Dispatch(testTask))
      }
    }
    "anticipate existing task" in {
      val testTask = Task("id", "test", ByteString("payload"))
      scheduler ! Schedule(testTask, 1 second)
      scheduler ! Reschedule("id", 0.5 second)
      within(0.5 second, 0.7 second) {
        dispatcher.expectMsg(Dispatch(testTask))
      }
    }
    "cancel existing task" in {
      val testTask = Task("id", "test", ByteString("payload"))
      scheduler ! Schedule(testTask, 1 second)
      scheduler ! Kill("id")
      dispatcher.expectNoMsg(1.2 seconds)
    }
  }
}
