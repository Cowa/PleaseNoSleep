package com.github.cowa.pleasenosleep

import dispatch._
import Defaults._
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging._

object Pinger {
  val logger = Logger(LoggerFactory.getLogger("PleaseNoSleep"))

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
    logger.info("Ping " + toPing)

    Http(url(toPing) OK as.String)
  }
}
