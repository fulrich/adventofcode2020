package adventofcode2020.airport.passports

import adventofcode2020.ResourceLoader
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class PassportBatchLoaderTest extends AnyFunSuite with Matchers {
  val rawPassport1: String = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
  val rawPassport2: String = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929"
  val rawPassport3: String = "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm"
  val rawPassport4: String = "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in"

  test("Can batch load the raw passports from the test file") {
    val rawPassportLines = ResourceLoader.asLines("airport/passports/test_passports.txt")(identity[String])
    val rawBatch = PassportBatchLoader.loadBatch(rawPassportLines)

    rawBatch.head.rawData shouldBe rawPassport1
    rawBatch(1).rawData shouldBe rawPassport2
    rawBatch(2).rawData shouldBe rawPassport3
    rawBatch.last.rawData shouldBe rawPassport4
  }
}
