package com.frostvoid.youtrack_notifier

import java.io.File
import java.net.URLEncoder

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}

/**
  * @author eaakre - 2017-06-21
  */
object YouTrackNotifier {
  val config: Config = ConfigFactory.parseFile(new File("YouTrackNotifier.conf"))
  val url: String = config.getString("url")
  val token: String = config.getString("token")
  val filter: String = config.getString("filter")

  final val system = ActorSystem("supportAlert")

  def main(args: Array[String]): Unit = {
    println("YouTrackNotifier starting...")
    println(s"url    : $url")
    println(s"token  : $token")
    println(s"filter : $filter")

    system.actorOf(Props[YouTrackNotifier])
  }
}

class YouTrackNotifier extends Actor {
  import YouTrackNotifier._

  private val youTrackActor = system.actorOf(Props(new IssueChecker(token)))
  private val notifierActor = system.actorOf(Props[Notifier])

  import system.dispatcher
  import scala.concurrent.duration._
  system.scheduler.schedule(1 seconds, 10 seconds) {
    youTrackActor ! GetPage(s"$url?filter=${URLEncoder.encode(filter, "UTF-8")}")
  }

  def receive: Receive = {
    case msg: PageResult => parseResult(msg)
  }

  private def parseResult(pageResult: PageResult): Unit = {
    if(pageResult.body == "{\"value\":\"Unauthorized\"}") println("ERROR: Unauthorized")

    if (pageResult.body.length > 25) notifierActor ! NewIssueNotification
  }
}
