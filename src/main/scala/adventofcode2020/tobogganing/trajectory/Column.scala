package adventofcode2020.tobogganing.trajectory

case class Column(geologies: Seq[Geology]) {
  def at(index: Int): Geology = geologies(index)

  def length: Int = geologies.length
}

object Column {
  def load(geology: Seq[Char]): Column = Column(geology.map(Geology.determine))
}
