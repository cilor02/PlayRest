import play.Project._

name := "hello-play-scala"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.2", 
  "org.webjars" % "bootstrap" % "2.3.1",
"xerces" % "xerces" % "2.4.0"
)

playScalaSettings
