package cz.seznam.courses.scala.classes

import scala.reflect.ClassTag

/**
  * Example of ClassTags usage
  */
object ClassTagsExample extends App {
  printType(List("abc", "def"))
  printType(List(12, 34))
  printType(List(new java.lang.Integer(12), new java.lang.Long(34)))
  printType(List.empty[String])

  def printType[T](list: List[T])(implicit classTag: ClassTag[T]) =
    println(s"List of ${classTag.runtimeClass}")
}
