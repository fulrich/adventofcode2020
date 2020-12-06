package adventofcode2020.airport.passports

import scala.util.Try
import scala.util.matching.Regex

case class Height(value: Int, measurement: String) {
  def valid: Boolean = measurement match {
    case "cm" => value >= 150 && value <= 193
    case "in" => value >= 59 && value <= 76
    case _ => false
  }
}

object Height {
  val HeightPattern: Regex = "(\\d{2,3})(cm|in)".r

  def apply(rawHeight: String): Option[Height] = rawHeight match {
    case HeightPattern(height, measurement) => apply(height, measurement)
    case _ => None
  }

  def apply(height: String, measurement: String): Option[Height] =
    Try(Height(height.toInt, measurement)).toOption
}
