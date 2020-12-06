package adventofcode2020.tobogganshop

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CorporatePolicyTest extends AnyFunSuite with Matchers {

  test("Can parse out the corporate policy from a raw String") {
    CorporatePolicy("1-3 a") shouldBe CorporatePolicy(MinimumAndMaximum(1, 3), 'a')
    CorporatePolicy("2-9 c") shouldBe CorporatePolicy(MinimumAndMaximum(2, 9), 'c')
    CorporatePolicy("1-15 k") shouldBe CorporatePolicy(MinimumAndMaximum(1, 15), 'k')
  }

  test("Can validate policies against a password for the sled rental shop") {
    CorporatePolicy(MinimumAndMaximum(1, 3), 'a').validForSledShop("abcde") shouldBe true
    CorporatePolicy(MinimumAndMaximum(1, 3), 'b').validForSledShop("cdefg") shouldBe false
    CorporatePolicy(MinimumAndMaximum(2, 9), 'c').validForSledShop("ccccccccc") shouldBe true
  }

  test("Can validate policies against a password for the toboggan shop") {
    CorporatePolicy(MinimumAndMaximum(1, 3), 'a').valid("abcde") shouldBe true
    CorporatePolicy(MinimumAndMaximum(1, 3), 'b').valid("cdefg") shouldBe false
    CorporatePolicy(MinimumAndMaximum(2, 9), 'c').valid("ccccccccc") shouldBe false
  }
}
