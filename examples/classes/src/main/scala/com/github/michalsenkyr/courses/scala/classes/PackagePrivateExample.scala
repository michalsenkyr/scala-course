package com.github.michalsenkyr.courses.scala.classes

import com.github.michalsenkyr.courses.scala.classes.subpackage._

/**
  * Example of package private usage
  */
object PackagePrivateExample {
  new ClassesPrivate
  // new SubpackagePrivate    // Does not compile
}
