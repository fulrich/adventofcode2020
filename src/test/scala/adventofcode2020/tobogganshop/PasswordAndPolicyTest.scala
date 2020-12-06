package adventofcode2020.tobogganshop

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PasswordAndPolicyTest extends AnyFunSuite with Matchers {

  test("Can parse Password and Policy from a raw password and policy") {
    PasswordAndPolicy("1-3 a: abcde") shouldBe PasswordAndPolicy(CorporatePolicy(1, 3, 'a'), "abcde")
    PasswordAndPolicy("2-9 c: ccccccccc") shouldBe PasswordAndPolicy(CorporatePolicy(2, 9, 'c'), "ccccccccc")
  }

  test("Can validate if a password and policy are valid for the sled rental shop") {
    PasswordAndPolicy("1-3 a: abcde").validForSledShop shouldBe true
    PasswordAndPolicy("1-3 b: cdefg").validForSledShop shouldBe false
    PasswordAndPolicy("2-9 c: ccccccccc").validForSledShop shouldBe true
  }

  test("Can validate if a password and policy are valid for the toboggan shop") {
    PasswordAndPolicy("1-3 a: abcde").valid shouldBe true
    PasswordAndPolicy("1-3 b: cdefg").valid shouldBe false
    PasswordAndPolicy("2-9 c: ccccccccc").valid shouldBe false
  }
}
