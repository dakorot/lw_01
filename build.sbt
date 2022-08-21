ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "3.1.2"

lazy val root = (project in file("."))
  .settings(
    name := "llww01",
    idePackagePrefix := Some("labw01"),
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
  )

