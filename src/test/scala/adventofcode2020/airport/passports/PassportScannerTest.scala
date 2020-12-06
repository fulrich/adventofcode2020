package adventofcode2020.airport.passports

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PassportScannerTest extends AnyFunSuite with Matchers {
  test("Can determine the number of passports are valid in the test file") {
    PassportScanner.load("airport/passports/test_passports.txt").validPassports.length shouldBe 2
  }

  test("Day 4:1 - Can determine the number of valid passports") {
    PassportScanner.load("airport/passports/passports.txt").validPassports.length shouldBe 179
  }
}
