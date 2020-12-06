package adventofcode2020.accounting

class ExpenseReportSearch(report: Seq[Int], numberToFind: Int = 2) {
  def find(sum: Int): Seq[Int] =
    report.combinations(numberToFind).filter(_.sum == sum).map(_.product).toVector
}
