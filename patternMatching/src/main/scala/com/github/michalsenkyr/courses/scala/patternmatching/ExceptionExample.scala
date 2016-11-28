package com.github.michalsenkyr.courses.scala.patternmatching

/**
  * Example of pattern matching on exceptions
  */
object ExceptionExample extends App {
  try {
    throw new IllegalArgumentException("test")
  } catch {
    case e: IllegalArgumentException => println(s"Caught $e")
  }
}
