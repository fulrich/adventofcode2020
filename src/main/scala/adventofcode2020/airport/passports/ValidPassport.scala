package adventofcode2020.airport.passports

case class ValidPassport(
  passportId: String,
  countryId: Option[String],
  birthdayYear: String,
  issueYear: String,
  expirationYear: String,
  height: String,
  hairColour: String,
  eyeColour: String
)
