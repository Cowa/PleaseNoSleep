import dispatch._
import Defaults._
import akka.io.IO
import spray.can.{Http => HttpS}
import scala.util.Properties
import akka.actor.{Actor, ActorSystem, Props}
import org.slf4j.LoggerFactory
import scala.concurrent.duration._
import com.typesafe.scalalogging._
import spray.util._
import spray.http._

object PleaseNoSleep extends App {
  implicit val system = ActorSystem("system")
  val logger = Logger(LoggerFactory.getLogger("PleaseNoSleep"))
  val urlToPing: List[String] = Properties.envOrElse("URL_TO_PING", "").split(",").toList

  val handler = system.actorOf(Props[Handler], name = "handler")
  IO(HttpS) ! HttpS.Bind(handler, "0.0.0.0", port = Properties.envOrElse("PORT", "8080").toInt)

  system.scheduler.schedule(0 seconds, 5 seconds){(
    pingAll(urlToPing)
  )}

  /** Ping all URL to keep them awake
    *
    * @param urls List of every URL to ping
    */
  def pingAll(urls: List[String]): Unit = urls match {
    case h :: q => ping(h); pingAll(q)
    case Nil => Unit
  }

  /** Ping an URL to keep him awake
    *
    * @param toPing URL to ping
    */
  def ping(toPing: String): Unit = {
    val response = Http(url(toPing) OK as.String)
    logger.info("Ping " + toPing)
  }
}

class Handler extends Actor {
  def receive = {
    case _: HttpS.Connected => sender ! HttpS.Register(self)
    case _: HttpRequest => sender ! HttpResponse(entity = "Please, no sleep.")
  }
}
