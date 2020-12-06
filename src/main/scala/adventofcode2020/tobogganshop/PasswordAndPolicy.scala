package adventofcode2020.tobogganshop

case class PasswordAndPolicy(policy: CorporatePolicy, password: String) {
  lazy val validForSledShop: Boolean = policy.validForSledShop(password)
  lazy val valid: Boolean = policy.valid(password)
}

object PasswordAndPolicy {
  val Separator = ':'

  def apply(rawPasswordAndPolicy: String): PasswordAndPolicy = rawPasswordAndPolicy.split(Separator) match {
    case Array(rawPolicy, password) => PasswordAndPolicy(CorporatePolicy(rawPolicy), password.strip)
    case _ => throw new IllegalArgumentException(s"Could not parse: $rawPasswordAndPolicy")
  }
}