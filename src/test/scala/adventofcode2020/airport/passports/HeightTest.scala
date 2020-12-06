package adventofcode2020.airport.passports

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class HeightTest extends AnyFunSuite with Matchers {
  test("Can parse height from a raw height string") {
    Height("150cm") should contain (Height(150, "cm"))
    Height("22in") should contain (Height(22, "in"))
    Height("200mm") shouldBe None
  }

  test("Can validate cm based Height") {
    Height(150, "cm").valid shouldBe true
    Height(193, "cm").valid shouldBe true

    Height(149, "cm").valid shouldBe false
    Height(194, "cm").valid shouldBe false
  }

  test("Can validate in based Height") {
    Height(59, "in").valid shouldBe true
    Height(76, "in").valid shouldBe true

    Height(58, "in").valid shouldBe false
    Height(77, "in").valid shouldBe false
  }

  test("Any non valid measurement types return false") {
    Height(59, "mm").valid shouldBe false
    Height(76, "mm").valid shouldBe false
  }
}
