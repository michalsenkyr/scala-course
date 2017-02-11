package com.github.michalsenkyr.courses.scala.collections

import scala.collection.mutable.ListBuffer

/**
  * Some collections operations examples
  */
object BasicOpsExample extends App {
  println(s"Seq(1, 2, 3).sum  ===  ${Seq(1, 2, 3).sum}")
  println(s"Seq(1, 2 ,3).head  ===  ${Seq(1, 2, 3).head}")
  println(s"Seq(1, 2, 3).tail  ===  ${Seq(1, 2, 3).tail}")
  println(s"Seq(3, 1, 5).min  ===  ${Seq(3, 1, 5).min}")
  println(s"ListBuffer(1, 2, 3).transform(_ * 2)  ===  ${ListBuffer(1, 2, 3).transform(_ * 2)}")
  println(s"Seq(Orange, Apple, Pear).minBy(_.reverse)  ===  ${Seq("Orange", "Apple", "Pear").minBy(_.reverse)}")
  println(s"Seq(2, 6, 12).forall(_ % 2 == 0)  ===  ${Seq(2, 6, 12).forall(_ % 2 == 0)}")
}
