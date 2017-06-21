name := "SupportAlert"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.3"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.2"

mainClass := Some("com.frostvoid.youtrack_notifier.YouTrackNotifier")
