package adventofcode2020.airport.passports

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class RawPassportTest extends AnyFunSuite with Matchers {
  test("Can parse out fields from a raw passport resource") {
    val raw = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm"
    val passport = RawPassport(raw)

    passport.passportId should contain ("860033327")
    passport.countryId should contain ("147")
    passport.birthdayYear should contain (1937)
    passport.issueYear should contain (2017)
    passport.expirationYear should contain (2020)
    passport.height should contain (Height(183, "cm"))
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
      birthdayYear = 1937,
      issueYear = 2017,
      expirationYear = 2020,
      height = Height(183, "cm"),
      eyeColour = "gry",
      hairColour = "#fffffd"
    ))
    invalidRawPassport.validate shouldBe None
  }

  test("A Raw Passport can validate without a country ID" ) {
    val missingCountryId = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 hgt:183cm"

    RawPassport(missingCountryId).validate.isDefined shouldBe true
  }

  test("passwordId is only considered present if valid") {
    RawPassport("pid:000000000").passportId should contain ("000000000")

    RawPassport("pid:0000000001").passportId shouldBe None
    RawPassport("pid:00000000").passportId shouldBe None
  }

  test("birthdayYear is only considered present if valid") {
    RawPassport("byr:1920").birthdayYear should contain (1920)
    RawPassport("byr:2002").birthdayYear should contain (2002)

    RawPassport("byr:1919").birthdayYear shouldBe None
    RawPassport("byr:2003").birthdayYear shouldBe None
  }

  test("issueYear is only considered present if valid") {
    RawPassport("iyr:2010").issueYear should contain (2010)
    RawPassport("iyr:2020").issueYear should contain (2020)

    RawPassport("iyr:2009").issueYear shouldBe None
    RawPassport("iyr:2021").issueYear shouldBe None
  }

  test("expirationYear is only considered present if valid") {
    RawPassport("eyr:2020").expirationYear should contain (2020)
    RawPassport("eyr:2030").expirationYear should contain (2030)

    RawPassport("eyr:2019").expirationYear shouldBe None
    RawPassport("eyr:2031").expirationYear shouldBe None
  }

  test("hairColour is only considered present if valid") {
    RawPassport("hcl:#123456").hairColour should contain ("#123456")
    RawPassport("hcl:#123abf").hairColour should contain ("#123abf")

    RawPassport("hcl:#123abz").hairColour shouldBe None
    RawPassport("hcl:#1234561").hairColour shouldBe None
    RawPassport("hcl:123456").hairColour shouldBe None
    RawPassport("hcl:123456a").hairColour shouldBe None
  }

  test("eyeColour is only considered present if valid") {
    val ValidEyeColours = Vector("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    RawPassport("ecl:amb").eyeColour should contain ("amb")
    RawPassport("ecl:blu").eyeColour should contain ("blu")
    RawPassport("ecl:brn").eyeColour should contain ("brn")
    RawPassport("ecl:gry").eyeColour should contain ("gry")
    RawPassport("ecl:grn").eyeColour should contain ("grn")
    RawPassport("ecl:hzl").eyeColour should contain ("hzl")
    RawPassport("ecl:oth").eyeColour should contain ("oth")

    RawPassport("ecl:aaa").eyeColour shouldBe None
    RawPassport("ecl:red").eyeColour shouldBe None
    RawPassport("ecl:olv").eyeColour shouldBe None
  }
}
