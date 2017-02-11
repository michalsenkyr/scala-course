package com.github.michalsenkyr.courses.scala.collections

/**
  * Examples of collection views and streams
  */
object ViewsStreamsExample extends App {
  def time(f: => Unit): String = {
    val before = System.nanoTime()
    f
    val after = System.nanoTime()
    f"${(after - before).toDouble / 1000000}%.3f ms"
  }

  println("range = 1 to 10000000")
  val range = 1 to 10000000

  print(s"range.map(_ + 1).map(_ * 2).take(5)  ...  ")
  println(time(range.map(_ * 2).take(5)))

  print(s"range.view.map(_ + 1).map(_ * 2).take(5).force  ...  ")
  println(time(range.view.map(_ * 2).take(5).force))

  println("arr = (0 to 9).toArray")
  val arr = (0 to 9).toArray
  println("arr.view.slice(3, 7).transform(- _)")
  arr.view.slice(3, 7).transform(-_)
  println(s"arr  ===  ${arr.mkString("Array(", ", ", ")")}")

  println("Seq(1, 2, 3, 4, 5).view.map { x => println(Encountered $x); x }.take(3).force  ===  ")
  Seq(1, 2, 3, 4, 5).view.map { x =>
    println(s"Encountered $x")
    x
  }.take(3).force

  def fib(a: BigDecimal, b: BigDecimal): Stream[BigDecimal] = a #:: fib(b, a + b)

  println(s"fib(123)  ===  ${fib(0, 1)(123)}")
}
