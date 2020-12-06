package adventofcode2020.tobogganing.shop

case class MinimumAndMaximum(minimum: Int, maximum: Int)

object MinimumAndMaximum {
  val Separator = '-'

  def apply(rawMinimumAndMaximum: String): MinimumAndMaximum = rawMinimumAndMaximum.split(Separator) match {
    case Array(minimum, maximum) => MinimumAndMaximum(minimum.toInt, maximum.toInt)
  }
}
