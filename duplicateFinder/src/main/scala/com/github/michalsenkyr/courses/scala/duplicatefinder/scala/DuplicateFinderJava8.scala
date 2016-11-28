package com.github.michalsenkyr.courses.scala.duplicatefinder.scala

import java.nio.file.{Files, Path, Paths}
import java.security.MessageDigest

import scala.collection.JavaConverters._

/**
  * Duplicate file finder in Scala using JRE 8
  */
object DuplicateFinderJava8 extends App {
  lazy val digest = MessageDigest.getInstance("SHA-1")

  println(args.view
    .map(Paths.get(_))
    .flatMap(Files.walk(_).asScala)
    .filter(Files.isRegularFile(_))
    .filterDuplicates(Files.size)
    .flatMap(_.filterDuplicates(hash))
    .map(_.mkString(", "))
    .mkString("Duplicate files:\n", "\n", ""))

  def hash(path: Path) = digest.digest(Files.readAllBytes(path)).toSeq

  implicit class StreamConverter[T](stream: java.util.stream.Stream[T]) {
    def asScala: Stream[T] = stream.iterator().asScala.toStream
  }

  implicit class DuplicateFiltering[T](iterable: Iterable[T]) {
    def filterDuplicates[K](key: T => K) = iterable
      .groupBy(key)
      .values
      .filter(_.size > 1)
  }

}
