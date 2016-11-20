package cz.seznam.courses.scala.classes

/**
  * Example of constructor definition
  */
object ConstructorExample {
  class Test(param: Int, val get: Int, var getSet: Int, private val privateGet: Int) {
    def this(all: Int) = this(all, all, all, all)
  }

  val test = new Test(1, 2, 3, 4)

  // test.param             // Does not compile: Parameter only

  println(test.get)
  // test.get = 1           // Does not compile: Immutable

  println(test.getSet)
  test.getSet = 1

  // test.privateGet        // Does not compile: Private

  val test2 = new Test(1)   // Auxiliary constructor usage
}
