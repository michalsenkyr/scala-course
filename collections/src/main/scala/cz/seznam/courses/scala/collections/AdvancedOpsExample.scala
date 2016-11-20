package cz.seznam.courses.scala.collections

/**
  * Examples of advanced operations on collections
  */
object AdvancedOpsExample extends App {
  print(s"Seq(1, 2, 3, 4, 5).foldLeft(2) { case (r, x) => r + x }  ===  ")
  println(Seq(1, 2, 3, 4, 5).foldLeft(2) { case (r, x) => r + x })

  print(s"Seq(1, 2, 3, 4, 5).reduceLeft(_ - _)  ===  ")
  println(Seq(1, 2, 3, 4, 5).reduceLeft(_ - _))

  println(s"Seq(1, 2, 3, 4, 5).grouped(3).toList  ===  ${Seq(1, 2, 3, 4, 5).grouped(3).toList}")
  println(s"Seq(1, 2, 3, 4, 5).sliding(3).toList  ===  ${Seq(1, 2, 3, 4, 5).sliding(3).toList}")
  println(s"Seq(1, 2, 3, 4, 5).patch(1, Seq(-1, -2), 3)  ===  ${Seq(1, 2, 3, 4, 5).patch(1, Seq(-1, -2), 3)}")
}
