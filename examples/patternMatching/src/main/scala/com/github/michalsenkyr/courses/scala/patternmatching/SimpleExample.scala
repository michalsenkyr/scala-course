package com.github.michalsenkyr.courses.scala.patternmatching

/**
  * Example of simple pattern matching
  */
object SimpleExample extends App {
  val test2 = "test2"

  def process(str: String) = str match {
    case "test1" => println("First match")
    case `test2` => println("Second match")
    case s if s.startsWith("test") => println("Third match")
    case _ => println("Fourth match")
  }

  process("test1")
  process("test2")
  process("test3")
  process("other")
}
