package com.github.michalsenkyr.courses.scala.duplicatefinder.scala

import java.io.{File, FileInputStream}
import java.security.MessageDigest

/**
  * Duplicate file finder in Scala using JRE pre-8
  */
object DuplicateFinder extends App {
  lazy val digest = MessageDigest.getInstance("SHA-1")

  println(args.view
    .map(new File(_))
    .flatMap(listAllFiles)
    .findDuplicates(_.length)
    .flatMap(_.findDuplicates(hash))
    .map(_.mkString(", "))
    .mkString("Duplicate files:\n", "\n", ""))

  def hash(file: File) = digest.digest(file.readBytes()).toSeq

  def listAllFiles(dir: File): Seq[File] = dir.listFiles().flatMap {
    case file if file.isDirectory => listAllFiles(file)
    case file if file.isFile => file :: Nil
    case file => throw new RuntimeException(s"Unknown file type: $file")
  }

  implicit class DuplicateFiltering[T](iterable: Iterable[T]) {
    def findDuplicates[K](f: T => K) = iterable
      .groupBy(f)
      .values
      .filter(_.size > 1)
  }

  implicit class FileReading(file: File) {
    val bufferSize = 4096

    def readBytes() = doWith(new FileInputStream(file)) { input =>
      val buffer = Array.ofDim[Byte](bufferSize)
      Stream.continually {
        buffer.view.take(input.read(buffer))
      }.takeWhile(_.nonEmpty).flatten.toArray
    }
  }

  def doWith[T <: AutoCloseable, R](t: T)(f: T => R) = try f(t) finally t.close()
}
