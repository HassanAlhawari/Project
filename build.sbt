name := """play-scala-seed"""
organization := "sbt run"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

herokuAppName in Compile := "thawing-mountain-98674"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "sbt run.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "sbt run.binders._"
