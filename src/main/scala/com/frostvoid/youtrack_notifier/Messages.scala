package com.frostvoid.youtrack_notifier

case class GetPage(url: String)
case class PageResult(body: String)
case object NewIssueNotification