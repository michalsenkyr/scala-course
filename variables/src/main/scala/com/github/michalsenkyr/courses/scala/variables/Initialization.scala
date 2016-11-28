package com.github.michalsenkyr.courses.scala.variables

/**
  * Example of eager/lazy variable initialization
  */
object Initialization extends App {
  println("Declaring variables")

  val eagerVal = {
    println("Eager variable initialized")
    1
  }

  lazy val lazyVal = {
    println("Lazy variable initialized")
    2
  }

  println("Performing computation")

  val result = eagerVal + lazyVal

  println(s"Result = $result")
}
