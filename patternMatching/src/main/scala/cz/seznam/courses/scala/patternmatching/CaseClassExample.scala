package cz.seznam.courses.scala.patternmatching

/**
  * Example of pattern matching with case classes
  */
object CaseClassExample extends App {

  case class Address(city: String, street: String)

  sealed trait Person {
    def name: String
  }

  object Person {
    def unapply(person: Person) = Some(person.name)
  }

  case class PersonWithEmail(name: String, email: String) extends Person

  case class PersonWithAddress(name: String, address: Address) extends Person

  case class PersonWithBirthsign(name: String, sign: String) extends Person

  def process(person: Person) = person match {
    case PersonWithEmail(_, email) => println(s"Let's spam $email!")
    case PersonWithAddress(_, address) => println(s"Let's swat ${address.street} in ${address.city}!")
    case Person(name) => println(s"Let's beat up $name!")
  }

  process(PersonWithEmail("Chell", "chell@aperturescience.com"))
  process(PersonWithAddress("Gordon", Address("City 17", "1 Pickupth Atcan")))
  process(PersonWithBirthsign("Fargoth", "The Atronach"))
}
