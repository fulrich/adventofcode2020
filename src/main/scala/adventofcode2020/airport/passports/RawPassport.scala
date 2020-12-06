package adventofcode2020.airport.passports

case class RawPassport(rawData: String) {
  lazy val parsedRawData: Map[String, String] = rawData
    .split(' ').toVector
    .filter(_.nonEmpty)
    .map(parseData).toMap

  private def parseData(data: String): (String, String) = data.split(':') match {
    case Array(key, value) => (key, value)
    case _ => throw new IllegalArgumentException(s"Could not parse data: $data")
  }

  def passportId: Option[String] = parsedRawData.get(RawPassport.PassportIdField)
  def countryId: Option[String] = parsedRawData.get(RawPassport.CountryIdField)
  def birthdayYear: Option[String] = parsedRawData.get(RawPassport.BirthdayYearField)
  def issueYear: Option[String] = parsedRawData.get(RawPassport.IssueYearField)
  def expirationYear: Option[String] = parsedRawData.get(RawPassport.ExpirationYearField)
  def height: Option[String] = parsedRawData.get(RawPassport.HeightField)
  def hairColour: Option[String] = parsedRawData.get(RawPassport.HairColourField)
  def eyeColour: Option[String] = parsedRawData.get(RawPassport.EyeColourField)

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
}
