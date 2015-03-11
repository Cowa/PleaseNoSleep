name := """PleaseNoSleep"""

version := "1.0.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.9",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.3.0-akka-2.3.x"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


fork in run := true
