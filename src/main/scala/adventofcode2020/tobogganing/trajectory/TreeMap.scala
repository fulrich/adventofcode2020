package adventofcode2020.tobogganing.trajectory

import scala.annotation.tailrec

case class TreeMap(columns: Seq[Column]) {
  def column(index: Int): Column = columns(index % columns.length)

  def at(point: Point): Geology = column(point.column).at(point.row)

  def width: Int = columns.length

  def height: Int = columns.head.length

  @tailrec
  final def followSlope(slope: Slope, point: Point = Point(0, 0), geologies: Seq[Geology] = Vector.empty): Seq[Geology] =
    if(point.row >= height) geologies
    else followSlope(slope, point.applySlope(slope), geologies :+ at(point))
}

object TreeMap {
  def load(rawRows: Seq[String]): TreeMap = TreeMap(loadColumns(rawRows))

  def loadColumns(rawRows: Seq[String]): Seq[Column] =
    (0 until rawRows.head.length).toVector
      .map { column => rawRows.map(_.apply(column)) }
      .map(Column.load)
}
