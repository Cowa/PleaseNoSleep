import dispatch._
import Defaults._
import scala.util.Properties
import akka.actor.ActorSystem
import org.slf4j.LoggerFactory
import scala.concurrent.duration._
import com.typesafe.scalalogging._

object PleaseNoSleep extends App {
  val system = ActorSystem("system")
  val logger = Logger(LoggerFactory.getLogger("PleaseNoSleep"))
  val urlToPing: List[String] = Properties.envOrElse("URL_TO_PING", "").split(",").toList

  system.scheduler.schedule(0 seconds, 5 minutes){(
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
