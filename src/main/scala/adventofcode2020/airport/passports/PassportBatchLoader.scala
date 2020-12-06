package adventofcode2020.airport.passports

import scala.annotation.tailrec

object PassportBatchLoader {
  def loadBatch(lines: Seq[String]): Seq[RawPassport] = load(lines, "", Vector.empty)

  @tailrec
  final def load(all: Seq[String], current: String, rawPassports: Seq[RawPassport]): Seq[RawPassport] =
    if (all.isEmpty) rawPassports :+ RawPassport(current.strip)
    else {
      load(
        all.tail,
        if (all.head.isEmpty) "" else s"$current ${all.head}",
        if (all.head.isEmpty) rawPassports :+ RawPassport(current.strip) else rawPassports
      )
    }
}
