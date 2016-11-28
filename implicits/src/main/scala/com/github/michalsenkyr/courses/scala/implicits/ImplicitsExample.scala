package com.github.michalsenkyr.courses.scala.implicits

/**
  * Examples of implicits usage
  */
object ImplicitsExample extends App {

  // Simple implicit class
  implicit class SimpleImplicit(str: String) {
    def prefix(prefix: String) = prefix + str
  }

  print("World.prefix(Hello )  ===  ")
  println("World".prefix("Hello "))


  // Simple extension class
  implicit class SimpleExtension(val str: String) extends AnyVal {
    def suffix(suffix: String) = str + suffix
  }

  print("Hello.suffix( World)  ===  ")
  println("Hello".suffix(" World"))

  // Implicit class with internal field
  implicit class ImplicitWithFields(str: String) {
    lazy val letterCounts = str.groupBy(_.toLower).mapValues(_.length)

    def letterCounts(letters: Char*): Map[Char, Int] = letterCounts.filterKeys(letters.contains)
  }

  print("Hello World.letterCounts('l', 'h')  ===  ")
  println("Hello World".letterCounts('l', 'h'))
}
