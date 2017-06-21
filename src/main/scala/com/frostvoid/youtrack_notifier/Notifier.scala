package com.frostvoid.youtrack_notifier

import akka.actor.Actor

/**
  * @author eaakre - 2017-06-21
  */
class Notifier extends Actor {

  def receive: Receive = {
    // TODO: support different notification methods and define in config
    case NewIssueNotification => linuxNotifySend()
  }

  def linuxNotifySend(): Unit = {
    import sys.process._
    Seq("notify-send", "-i", "face-sad", "-t", "2000", "There is a new issue in the support inbox!") !
  }
}
