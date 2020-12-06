package adventofcode2020.tobogganing.shop

import adventofcode2020.ResourceLoader
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TobogganShopTest extends AnyFunSuite with Matchers {
  test("Day 2:1 - Determines the number of valid passwords for the sled rental shop") {
    val passwords: Vector[PasswordAndPolicy] = ResourceLoader.asLines("tobogganing/shop/password_list.txt"){
      rawPasswordAndPolicy => PasswordAndPolicy(rawPasswordAndPolicy)
    }

    passwords.count(_.validForSledShop) shouldBe 447
  }

  test("Day 2:2 - Determines the number of valid passwords for the toboggan shop") {
    val passwords: Vector[PasswordAndPolicy] = ResourceLoader.asLines("tobogganing/shop/password_list.txt"){
      rawPasswordAndPolicy => PasswordAndPolicy(rawPasswordAndPolicy)
    }

    passwords.count(_.valid) shouldBe 249
  }
}
