lazy val common = Seq(
  version := "1.0",
  scalaVersion := "2.12.0",
  organization := "cz.seznam.courses.scala"
)

lazy val variables = project.
  settings(common: _*).
  settings(
    name := "variables",
    description := "Variables example"
  )

lazy val classes = project.
  settings(common: _*).
  settings(
    name := "classes",
    description := "Classes example"
  )

lazy val patternMatching = project.
  settings(common: _*).
  settings(
    name := "pattern-matching",
    description := "Pattern matching example"
  )

lazy val collections = project.
  settings(common: _*).
  settings(
    name := "collections",
    description := "Collections example"
  )

lazy val forComprehensions = project.
  settings(common: _*).
  settings(
    name := "for-comprehensions",
    description := "For-comprehensions example"
  )

lazy val implicits = project.
  settings(common: _*).
  settings(
    name := "implicits",
    description := "Implicits example"
  )

lazy val duplicateFinder = project.
  settings(common: _*).
  settings(
    name := "duplicate-finder",
    description := "Duplicate file finder example application",
    fork in run := true,
    baseDirectory in run := project.base
  )

lazy val root = (project in file(".")).
  settings(common: _*).
  settings(
    name := "scala-course",
    description := "Scala course materials"
  ).
  aggregate(variables, classes, patternMatching, collections, forComprehensions, implicits, duplicateFinder)
