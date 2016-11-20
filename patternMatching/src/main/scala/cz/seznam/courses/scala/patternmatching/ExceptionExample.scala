package cz.seznam.courses.scala.patternmatching

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
