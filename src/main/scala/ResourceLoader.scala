import scala.io.Source

object ResourceLoader {
  def asLines[A](resource: String)(fromLines: String => A): Vector[A] =
    Source.fromResource(resource).getLines.toVector.map(fromLines)
}
