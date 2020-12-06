package adventofcode2020.tobogganing.shop

import scala.util.Try

case class CorporatePolicy(minimumAndMaximum: MinimumAndMaximum, letter: Char) {
  def validForSledShop(password: String): Boolean = {
    val occurrences = password.count(_ == letter)
    occurrences >= minimumAndMaximum.minimum && occurrences <= minimumAndMaximum.maximum
  }

  def valid(password: String): Boolean = {
    for {
      firstChar <- Try(password.charAt(minimumAndMaximum.minimum - 1))
      secondChar <- Try(password.charAt(minimumAndMaximum.maximum - 1))
    } yield (firstChar == letter) != (secondChar == letter)
  }.getOrElse(false)
}

object CorporatePolicy {
  val Separator = ' '

  def apply(min: Int, max: Int, letter: Char): CorporatePolicy = CorporatePolicy(MinimumAndMaximum(min, max), letter)

  def apply(rawPolicy: String): CorporatePolicy = rawPolicy.split(Separator) match {
    case Array(rawMinAndMax, givenLetter) => CorporatePolicy(MinimumAndMaximum(rawMinAndMax), letterAsChar(givenLetter))
  }

  def letterAsChar(letter: String): Char = {
    require(letter.length == 1)
    letter.toCharArray.head
  }
}
