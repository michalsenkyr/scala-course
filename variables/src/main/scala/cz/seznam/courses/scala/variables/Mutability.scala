package cz.seznam.courses.scala.variables

/**
  * Example of immutable/mutable variables
  */
object Mutability {
  val immutable = 1

  // immutable = 2    // Does not compile

  var mutable = 1

  mutable = 2
}
