package adventofcode2020.tobogganing.trajectory

case class Point(column: Int, row: Int) {
  def applySlope(slope: Slope): Point = Point(
    column = column + slope.run,
    row = row + slope.rise
  )
}
