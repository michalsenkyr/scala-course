package cz.seznam.courses.scala.collections

import scala.collection.immutable.{HashSet, TreeSet}

/**
  * Example of special construction methods
  */
object ConstructionMethodsExample extends App {
  def printMatrix(matrix: Array[Array[Int]]) = println(matrix.map(_.mkString(" ")).mkString("\n"))

  println(s"Seq.range(0, 100, 3)  ===  ${Seq.range(0, 100, 3)}")

  println(s"Array.tabulate(10, 10) { (x, y) => (x+1) * (y+1) }  ===")
  printMatrix(Array.tabulate(10, 10) { (x, y) => (x + 1) * (y + 1) })

  println(s"Seq.iterate(1.0, 10)(_ * Math.E)  ===  ${Seq.iterate(1.0, 10)(_ * Math.E)}")

  println(s"Seq.tabulate(10)(Math.PI * Math.pow(_, 2))  ===  ${Seq.tabulate(10)(Math.PI * Math.pow(_, 2))}")

  println(s"List(1, 2, 3) == Vector(1, 2, 3)  ===  ${List(1, 2, 3) == Vector(1, 2, 3)}")
  println(s"List(1, 2, 3) == Set(1, 2, 3)  ===  ${List(1, 2, 3) == Set(1, 2, 3)}")
  println(s"HashSet(1, 2, 3) == TreeSet(1, 2, 3)  ===  ${HashSet(1, 2, 3) == TreeSet(1, 2, 3)}")
}
