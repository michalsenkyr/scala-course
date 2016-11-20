package cz.seznam.courses.scala.classes

/**
  * Example of case classes
  */
object CaseClassExample extends App {
  case class Test(id: Int)

  val t1 = Test(1)
  println(s"t1  ===  $t1")

  println(s"t1.id  ===  ${t1.id}")

  val t2 = new Test(2)
  println(s"t2  ===  $t2")

  println(s"t1 == t2  ===  ${t1 == t2}")

  println(s"t1.copy(3)  ===  ${t1.copy(3)}")

  println(s"t1.copy(2) == t2  ===  ${t1.copy(2) == t2}")

  println(s"t1.productElement(0)  ===  ${t1.productElement(0)}")

  val Test(id) = t1
  println(s"val Test(id) = t1; id  ===  $id")
}
