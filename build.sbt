name := """PleaseNoSleep"""

version := "1.0.0"

scalaVersion := "2.11.5"

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-simple" % "1.7.10",
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "io.spray" %% "spray-servlet" % "1.3.2",
  "io.spray" %% "spray-routing" % "1.3.2",
  "io.spray" %% "spray-can" % "1.3.2"
)
