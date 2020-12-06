package adventofcode2020.airport.passports

import adventofcode2020.ResourceLoader

case class PassportScanner(passports: Seq[RawPassport]) {
  lazy val validPassports: Seq[ValidPassport] = passports.flatMap(_.validate)
}

object PassportScanner {
  def load(resource: String): PassportScanner = PassportScanner(
    PassportBatchLoader.loadBatch(
      ResourceLoader.asLines(resource)(identity[String])
    )
  )
}