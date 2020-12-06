package adventofcode2020.airport.passports

case class ValidPassport(
  passportId: String,
  countryId: Option[String],
  birthdayYear: Int,
  issueYear: Int,
  expirationYear: Int,
  height: Height,
  hairColour: String,
  eyeColour: String
)
