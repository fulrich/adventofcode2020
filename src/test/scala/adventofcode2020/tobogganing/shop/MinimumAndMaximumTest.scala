package adventofcode2020.tobogganing.shop

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MinimumAndMaximumTest extends AnyFunSuite with Matchers {
  test("Can parse out minimum and maximum from a raw string value") {
    MinimumAndMaximum("1-3") shouldBe MinimumAndMaximum(1, 3)
    MinimumAndMaximum("2-9") shouldBe MinimumAndMaximum(2, 9)
    MinimumAndMaximum("12-9") shouldBe MinimumAndMaximum(12, 9)
    MinimumAndMaximum("2-19") shouldBe MinimumAndMaximum(2, 19)
    MinimumAndMaximum("22-32") shouldBe MinimumAndMaximum(22, 32)
  }
}
