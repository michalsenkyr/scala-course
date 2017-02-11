package com.github.michalsenkyr.courses.scala.collections

/**
  * Transformation operations examples
  */
object TransformOpsExample extends App {
  println(s"Seq(1, 2, 3) ++ Seq(1, 2)  ===  ${Seq(1, 2, 3) ++ Seq(1, 2)}")
  println(s"Set(1, 2, 3) -- Set(1, 2)  ===  ${Set(1, 2, 3) -- Set(1, 2)}")
  println(s"Set(1, 2) & Set(2, 3)  ===  ${Set(1, 2) & Set(2, 3)}")
  println(s"Set(1, 2) | Set(2, 3)  ===  ${Set(1, 2) | Set(2, 3)}")
  println(s"Seq(1, 2, 3).map(_ + 1)  ===  ${Seq(1, 2, 3).map(_ + 1)}")
  println(s"Seq(1, 2, 3).flatMap(Seq.range(0, _))  ===  ${Seq(1, 2, 3).flatMap(Seq.range(0, _))}")
  println(s"Seq(1, 0, 0, 1, 1).collect { case 1 => one }  ===  ${Seq(1, 0, 0, 1, 1).collect { case 1 => "one" }}")
  println(s"Seq(1, 2, 3).take(2)  ===  ${Seq(1, 2, 3).take(2)}")
  println(s"Seq(1, 2, 3).drop(2)  ===  ${Seq(1, 2, 3).drop(2)}")
  println(s"Seq(1, 3, 5, 4, 7).takeWhile(_ % 2 == 1)  ===  ${Seq(1, 3, 5, 4, 2).takeWhile(_ % 2 == 1)}")
  println(s"Seq(1, 2, 3, 4, 5).filter(_ % 2 == 1)  ===  ${Seq(1, 2, 3, 4, 5).filter(_ % 2 == 1)}")
  println(s"Seq(1, 2, 3, 4, 5).partition(_ % 2 == 1)  ===  ${Seq(1, 2, 3, 4, 5).partition(_ % 2 == 1)}")
  println(s"Seq(1, 2, 3, 4, 5).groupBy(_ % 3)  ===  ${Seq(1, 2, 3, 4, 5).groupBy(_ % 3)}")
  println(s"Seq(1, 2, 3).zip(Seq(2, 3))  ===  ${Seq(1, 2, 3).zip(Seq(2, 3))}")
  println(s"Seq(1, 2, 3).zipAll(Seq(2, 3), 0, 0)  ===  ${Seq(1, 2, 3).zipAll(Seq(2, 3), 0, 0)}")
  println(s"Seq(a, b, c).zipWithIndex  ===  ${Seq("a", "b", "c").zipWithIndex}")
  println(s"Seq(Apple, Orange, Pear).sortBy(_.reverse)  ===  ${Seq("Apple", "Orange", "Pear").sortBy(_.reverse)}")
}
