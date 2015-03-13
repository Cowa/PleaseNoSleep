import dispatch._
import Defaults._
import akka.io.IO
import spray.can.{Http => HttpSpray}
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

  val handler = system.actorOf(Props[HttpHandler], name = "handler")

  // Set up a tiny HTTP server
  IO(HttpSpray) ! HttpSpray.Bind(handler, "0.0.0.0", port = Properties.envOrElse("PORT", "8080").toInt)

  // Set an Akka scheduler to ping all URL every 5 minutes
  system.scheduler.schedule(0 seconds, 5 minutes)(pingAll(urlToPing))

  /** Ping all URL to keep them awake
    *
    * @param urls List of every URL to ping
    */
  def pingAll(urls: List[String]): Unit = urls match {
    case h :: q => ping(h); pingAll(q)
    case Nil => Unit
  }

  /** Ping an URL
    *
    * @param toPing URL to ping
    */
  def ping(toPing: String): Unit = {
    val response = Http(url(toPing) OK as.String)
    logger.info("Ping " + toPing)
  }
}

class HttpHandler extends Actor {
  def receive = {
    case _: HttpSpray.Connected => sender ! HttpSpray.Register(self)
    case _: HttpRequest => sender ! HttpResponse(entity = "Please, no sleep.")
  }
}
