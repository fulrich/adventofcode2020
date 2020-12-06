package adventofcode2020.tobogganshop

import adventofcode2020.ResourceLoader
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TobogganShopTest extends AnyFunSuite with Matchers {
  test("Day 2:1 - Determines the number of valid passwords") {
    val passwords: Vector[PasswordAndPolicy] = ResourceLoader.asLines("tobogganshop/password_list.txt"){
      rawPasswordAndPolicy => PasswordAndPolicy(rawPasswordAndPolicy)
    }

    passwords.count(_.valid) shouldBe 447
  }

}
