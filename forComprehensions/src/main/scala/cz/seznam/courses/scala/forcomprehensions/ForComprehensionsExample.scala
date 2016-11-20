package cz.seznam.courses.scala.forcomprehensions

/**
  * Example of for-comprehensions
  */
object ForComprehensionsExample extends App {
  println("Simple foreach")
  for {
    i <- 1 to 10
    j <- 1 to 10 if i != j
  } print(s"${i * j}, ")
  println()

  println("Simple for-comprehension")
  val x = for {
    i <- 1 to 10
    j <- 1 to 10 if i != j
  } yield i * j
  println(s"i = 1..10, j = 1..10, i != j  ==>  $x")

  println("Case class for-comprehension")
  case class Address(city: String)
  case class Person(name: String, registered: Boolean, addresses: Seq[Address])
  val people = Seq(
    Person("Roger", registered = true, Seq(Address("NYC"), Address("DC"))),
    Person("Jane", registered = false, Seq(Address("Sydney"))),
    Person("Joe", registered = true, Seq(Address("NYC"), Address("LA"))),
    Person("Honza", registered = true, Seq(Address("Prague")))
  )
  val cities = (for {
    person <- people if person.registered
    address <- person.addresses
  } yield address.city).distinct
  println(s"Registered cities: $cities")
}
