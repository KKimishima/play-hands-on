name := """play-hands-on"""
organization := "com.github.kkimishima"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test

libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "com.typesafe.play" %% "anorm" % "2.5.3"
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.kkimishima.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.kkimishima.binders._"