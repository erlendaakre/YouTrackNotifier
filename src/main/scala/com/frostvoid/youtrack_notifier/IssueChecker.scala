package com.frostvoid.youtrack_notifier

import java.io.{BufferedReader, InputStreamReader}
import java.util.stream.Collectors

import akka.actor.{Actor, ActorLogging}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

/**
  * @author eaakre - 2017-06-21
  */
class IssueChecker(authToken: String) extends Actor with ActorLogging {
  log.debug(s"Actor created with auth $authToken")

  def receive: Receive = {
    case GetPage(url) => sender ! getPage(url)
  }

  private def getPage(url: String): PageResult = {
    log.debug(s"getting page $url")
    val httpClient = HttpClientBuilder.create().build()
    val req = new HttpGet(url)
    req.setHeader("Authorization", s"Bearer $authToken")
    req.setHeader("Accept", "application/json")

    import scala.collection.JavaConverters._
    val uglyShittyJavaArrayList = new BufferedReader(new InputStreamReader(httpClient.execute(req).getEntity.getContent)).lines().collect(Collectors.toList())
    PageResult(uglyShittyJavaArrayList.asScala.toList.mkString("\n"))
  }
}
