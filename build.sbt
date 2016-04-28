addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0") // fot sbt-0.13.5 or higher

import play.PlayImport.PlayKeys._

name := "play-imagemagick"

version := "0.1.0"

scalaVersion := "2.11.1"

javacOptions ++= Seq("-source", "1.8")

crossScalaVersions := Seq("2.10.4", "2.11.1")

organization := "se.digiplant"

playPlugin := true

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

fork in Test := false

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "org.im4java" % "im4java" % "1.4.0",
  "se.digiplant" %% "play-res" % "1.1.1",
  "com.typesafe.play" %% "play" % play.core.PlayVersion.current % "provided",
  "com.typesafe.play" %% "play-test" % play.core.PlayVersion.current % "test"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
