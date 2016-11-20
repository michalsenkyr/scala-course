package cz.seznam.courses.scala.classes

/**
  * Example of tuples, functions and partial functions
  */
object SpecialClassesExample extends App {
  println("tuple = (1, test)")
  val tuple = (1, "test")
  println(s"tuple  ===  $tuple")
  println(s"tuple._1  ===  ${tuple._1}")
  println(s"tuple.swap  ===  ${tuple.swap}")

  println("f1 = (x: Int) => x + 1")
  val f1 = (x: Int) => x + 1
  println(s"f1(1)  ===  ${f1(1)}")

  println(s"f2 = (x: Int, y: String) => y + x")
  val f2 = (x: Int, y: String) => y + x
  println(s"f2.tupled(tuple)  ===  ${f2.tupled(tuple)}")
  println(s"f2.curried(1)(test)  ===  ${f2.curried(1)("test")}")

  println("pf: PartialFunction[Int, String] = { case 1 => one; case 2 => two }")
  val pf: PartialFunction[Int, String] = {
    case 1 => "one"
    case 2 => "two"
  }
  println(s"pf(1)  ===  ${pf(1)}")
  println(s"pf.isDefinedAt(1)  ===  ${pf.isDefinedAt(1)}")
  println(s"pf.isDefinedAt(3)  ===  ${pf.isDefinedAt(3)}")
  println(s"pf.applyOrElse[Int, String](3, _.toString)  ===  ${pf.applyOrElse[Int, String](3, _.toString)}")
  println(s"pf.orElse[Int, String] { case 3 => three }(3)  ===  ${pf.orElse[Int, String] { case 3 => "three" }(3)}")
  println(s"pf.lift(1)  ===  ${pf.lift(1)}")
  println(s"pf.lift(3)  ===  ${pf.lift(3)}")
}
