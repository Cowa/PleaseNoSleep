name := """PleaseNoSleep"""

version := "1.0.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.slf4j" % "slf4j-simple" % "1.7.10",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
)
