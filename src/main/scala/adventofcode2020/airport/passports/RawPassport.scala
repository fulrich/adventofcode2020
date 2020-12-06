package adventofcode2020.airport.passports

import scala.util.Try

case class RawPassport(rawData: String) {
  lazy val parsedRawData: Map[String, String] = rawData
    .split(' ').toVector
    .filter(_.nonEmpty)
    .map(parseData).toMap

  private def parseData(data: String): (String, String) = data.split(':') match {
    case Array(key, value) => (key, value)
    case _ => throw new IllegalArgumentException(s"Could not parse data: $data")
  }

  def passportId: Option[String] = parsedRawData.get(RawPassport.PassportIdField).filter(_.length == 9)

  def countryId: Option[String] = parsedRawData.get(RawPassport.CountryIdField)

  def birthdayYear: Option[Int] = parsedRawData.get(RawPassport.BirthdayYearField)
    .flatMap(rawYear => Try(rawYear.toInt).toOption)
    .filter(year => year >= 1920 && year <= 2002)

  def issueYear: Option[Int] = parsedRawData.get(RawPassport.IssueYearField)
    .flatMap(rawYear => Try(rawYear.toInt).toOption)
    .filter(year => year >= 2010 && year <= 2020)

  def expirationYear: Option[Int] = parsedRawData.get(RawPassport.ExpirationYearField)
    .flatMap(rawYear => Try(rawYear.toInt).toOption)
    .filter(year => year >= 2020 && year <= 2030)

  def height: Option[Height] = parsedRawData.get(RawPassport.HeightField).flatMap(Height.apply).filter(_.valid)


  def hairColour: Option[String] = parsedRawData.get(RawPassport.HairColourField)
    .filter(colour => colour.matches("(^#)([a-f|\\d]{6})"))

  def eyeColour: Option[String] = parsedRawData.get(RawPassport.EyeColourField)
    .filter(colour => RawPassport.ValidEyeColours.contains(colour))

  def validate: Option[ValidPassport] = for {
    validPassportId <- passportId
    validBirthdayYear <- birthdayYear
    validIssueYear <- issueYear
    validExpirationYear <- expirationYear
    validHeight <- height
    validHairColour <- hairColour
    validEyeColour <- eyeColour
  } yield ValidPassport(
    passportId = validPassportId,
    countryId = countryId,
    birthdayYear = validBirthdayYear,
    issueYear = validIssueYear,
    expirationYear = validExpirationYear,
    height = validHeight,
    hairColour = validHairColour,
    eyeColour = validEyeColour
  )
}

object RawPassport {
  val PassportIdField: String = "pid"
  val CountryIdField: String = "cid"
  val BirthdayYearField: String = "byr"
  val IssueYearField: String = "iyr"
  val ExpirationYearField: String = "eyr"
  val HeightField: String = "hgt"
  val HairColourField: String = "hcl"
  val EyeColourField: String = "ecl"

  val ValidEyeColours = Vector("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
}
