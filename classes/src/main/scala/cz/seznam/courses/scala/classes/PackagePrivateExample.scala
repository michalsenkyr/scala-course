package cz.seznam.courses.scala.classes

import cz.seznam.courses.scala.classes.subpackage._

/**
  * Example of package private usage
  */
object PackagePrivateExample {
  new ClassesPrivate
  // new SubpackagePrivate    // Does not compile
}
