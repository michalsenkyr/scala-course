package cz.seznam.courses.scala.collections

import scala.collection.mutable

/**
  * Basic usage of collections example
  */
object BasicsExample extends App {
  println(s"List(a, b)(1)  ===  ${List("a", "b")(1)}")
  println(s"Array(a, b).length  ===  ${Array("a", "b").length}")
  println(s"Set(a, b) & Set(b, c)  ===  ${Set("a", "b") & Set("b", "c")}")
  println(s"Map(a -> 1, b -> 2)(b)  ===  ${Map("a" -> 1, "b" -> 2)("b")}")
  println(s"mutable.HashSet(a, b) += c  ===  ${mutable.HashSet("a", "b") += "c"}")
}
