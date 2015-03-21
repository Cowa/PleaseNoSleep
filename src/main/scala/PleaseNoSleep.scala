package com.github.cowa.pleasenosleep

import akka.io.IO
import spray.util._
import spray.http._
import spray.can.Http
import scala.util.Properties
import akka.actor.{Actor, ActorSystem, Props}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object PleaseNoSleep extends App {
  implicit val system = ActorSystem("system")
  val urlToPing: List[String] = Properties.envOrElse("URL_TO_PING", "").split(",").toList

  val handler = system.actorOf(Props[HttpHandler], name = "handler")

  // Set up a tiny HTTP server
  IO(Http) ! Http.Bind(handler, "0.0.0.0", port = Properties.envOrElse("PORT", "8080").toInt)

  // Set an Akka scheduler to ping all URL every 5 minutes
  system.scheduler.schedule(0 seconds, 5 minutes)(Pinger.pingAll(urlToPing))
}

class HttpHandler extends Actor {
  def receive = {
    case _: Http.Connected => sender ! Http.Register(self)
    case _: HttpRequest => sender ! HttpResponse(entity = "Please, no sleep.")
  }
}
