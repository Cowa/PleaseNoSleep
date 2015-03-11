import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import akka.actor.{ ActorRef, ActorSystem, Props, Actor, Inbox }
import scala.concurrent.duration._
import dispatch._
import Defaults._

object PleaseNoSleep extends App {
  val system = ActorSystem("PleaseNoSleep")
  val scheduler = QuartzSchedulerExtension(system)

  println("ok")
}
