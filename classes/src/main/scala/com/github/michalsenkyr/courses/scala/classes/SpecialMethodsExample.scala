package com.github.michalsenkyr.courses.scala.classes

/**
  * Example of special methods
  */
object SpecialMethodsExample extends App {

  class Test(name: String) {
    override def equals(other: Any): Boolean = {
      println(s"$this.equals($other)")
      false
    }

    def apply(param: String) = println(s"$this.apply($param)")

    def update(param: String, value: String) = println(s"$this.update($param, $value)")

    def field = {
      println(s"$this.field")
      "field"
    }

    def field_=(value: String) = println(s"$this.field_=($value)")

    def unapply(value: String): Option[String] = Some(s"$this.unapply($value)")

    //def unapplySeq(value: String): Option[Seq[String]] = Some(Seq(s"$this.unapplySeq($value)"))

    override def toString = name
  }

  val t1 = new Test("t1")
  val t2 = new Test("t2")

  print("t1 == t2  ===  ")
  t1 == t2

  print("t1(test)  ===  ")
  t1("test")

  print("t1(test) = value  ===  ")
  t1("test") = "value"

  print("t1.field = value  ===  ")
  t1.field = "value"

  val t1(param) = "value"
  println(s"val t1(param) = value; param  ===  $param")
}
