package adventofcode2020.tobogganing.trajectory

sealed abstract class Geology(symbol: Char)

object Geology {
  case object Empty extends Geology('.')
  case object Tree extends Geology('#')

  def determine(symbol: Char): Geology = symbol match {
    case '.' => Empty
    case '#' => Tree
    case _ => throw new IllegalArgumentException(s"Could not parse symbol ${symbol} into a Geology")
  }
}
