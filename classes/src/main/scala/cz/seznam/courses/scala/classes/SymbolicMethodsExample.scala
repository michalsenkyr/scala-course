package cz.seznam.courses.scala.classes

import scala.language.postfixOps

/**
  * Example of symbolic methods (methods with symbolic names)
  */
object SymbolicMethodsExample extends App {

  class Test(name: String) {
    def +(other: Test) = new Test(s"$this.+($other)")

    def +:(other: Test) = new Test(s"$this.+:($other)")

    def *(other: Test) = new Test(s"$this.*($other)")

    def ! = new Test(s"$this.!")

    def unary_! = new Test(s"$this.unary_!")

    override def toString = name
  }

  val t1 = new Test("t1")
  val t2 = new Test("t2")
  val t3 = new Test("t3")

  println(s"t1 + t2 === ${t1 + t2}")
  println(s"t1 +: t2 === ${t1 +: t2}")
  println(s"t1 * t2 + t3 === ${t1 * t2 + t3}")
  println(s"t1 + t2 * t3 === ${t1 + t2 * t3}")
  println(s"t1! === ${t1!}")
  println(s"!t1 === ${!t1}")
}
