import org.scalatest.LoneElement
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ExpenseReportSearchTest extends AnyFunSuite with Matchers with LoneElement {

  test("Day 1:1 - Example Run") {
    val exampleReport = Vector(1721, 979, 366, 299, 675, 1456)
    val report = new ExpenseReportSearch(exampleReport)

    report.find(2020).loneElement shouldBe 514579
  }

  test("Day 1:1 - Real Run") {
    val report = ResourceLoader.asLines("accounting/expense_report.txt")(_.toInt)
    val reportSearcher = new ExpenseReportSearch(report)

    reportSearcher.find(2020).loneElement shouldBe 996996
  }

  test("Day 1:2 - Real Run") {
    val report = ResourceLoader.asLines("accounting/expense_report.txt")(_.toInt)
    val reportSearcher = new ExpenseReportSearch(report, numberToFind = 3)

    reportSearcher.find(2020).loneElement shouldBe 9210402
  }
}
