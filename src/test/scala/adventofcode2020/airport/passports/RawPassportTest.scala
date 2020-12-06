package adventofcode2020.airport.passports

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class RawPassportTest extends AnyFunSuite with Matchers {
  test("Can parse out fields from a raw passport resource") {
    val raw = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
    val passport = RawPassport(raw)

    passport.passportId should contain ("860033327")
    passport.countryId should contain ("147")
    passport.birthdayYear should contain ("1937")
    passport.issueYear should contain ("2017")
    passport.expirationYear should contain ("2020")
    passport.height should contain ("183cm")
    passport.hairColour should contain ("#fffffd")
    passport.eyeColour should contain ("gry")
  }

  test("Can parse and handle when all fields are empty") {
    val passport = RawPassport("")

    passport.passportId shouldBe None
    passport.countryId shouldBe None
    passport.birthdayYear shouldBe None
    passport.issueYear shouldBe None
    passport.expirationYear shouldBe None
    passport.height shouldBe None
    passport.hairColour shouldBe None
    passport.eyeColour shouldBe None
  }

  test("Can validate a passport into a ValidPassport") {
    val validRaw = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
    val invalidRaw = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd cid:147 hgt:183cm"

    val validRawPassport = RawPassport(validRaw)
    val invalidRawPassport = RawPassport(invalidRaw)

    validRawPassport.validate should contain (ValidPassport(
      passportId = "860033327",
      countryId = Some("147"),
      birthdayYear = "1937",
      issueYear = "2017",
      expirationYear = "2020",
      height = "183cm",
      eyeColour = "gry",
      hairColour = "#fffffd"
    ))
    invalidRawPassport.validate shouldBe None
  }

  test("A Raw Passport can validate without a country ID" ) {
    val missingCountryId = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 hgt:183cm"

    RawPassport(missingCountryId).validate.isDefined shouldBe true
  }
}
