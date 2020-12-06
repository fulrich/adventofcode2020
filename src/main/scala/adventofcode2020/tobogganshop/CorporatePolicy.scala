package adventofcode2020.tobogganshop

case class CorporatePolicy(minimumAndMaximum: MinimumAndMaximum, letter: Char) {
  def valid(password: String): Boolean = {
    val occurrences = password.count(_ == letter)
    occurrences >= minimumAndMaximum.minimum && occurrences <= minimumAndMaximum.maximum
  }
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
