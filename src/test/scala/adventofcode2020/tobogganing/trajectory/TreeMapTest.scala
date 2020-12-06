package adventofcode2020.tobogganing.trajectory

import adventofcode2020.ResourceLoader
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TreeMapTest extends AnyFunSuite with Matchers {
  val testRawLines: Seq[String] = Seq(
    "..#",
    "#..",
    ".#."
  )

  val testColumn1: Column = Column.load(Vector('.', '#', '.'))
  val testColumn2: Column = Column.load(Vector('.', '.', '#'))
  val testColumn3: Column = Column.load(Vector('#', '.', '.'))

  test("Can load a TreeMap from the raw data") {
    val treeMap = TreeMap.load(testRawLines)
    val expectedTreeMap = TreeMap(Vector(testColumn1, testColumn2, testColumn3))

    treeMap shouldBe expectedTreeMap
  }

  test("Can get the correct repeated column") {
    val treeMap = TreeMap.load(testRawLines)

    treeMap.column(0) shouldBe testColumn1
    treeMap.column(1) shouldBe testColumn2
    treeMap.column(2) shouldBe testColumn3
    treeMap.column(3) shouldBe testColumn1
    treeMap.column(4) shouldBe testColumn2
    treeMap.column(5) shouldBe testColumn3
    treeMap.column(6) shouldBe testColumn1
    treeMap.column(7) shouldBe testColumn2
    treeMap.column(8) shouldBe testColumn3
  }

  test ("Can determine if a tree is at any point on the map") {
    val treeMap = TreeMap.load(testRawLines)

    treeMap.at(Point(0, 0)) shouldBe Geology.Empty
    treeMap.at(Point(1, 2)) shouldBe Geology.Tree
  }

  test ("Can follow a slope from a starting point") {
    val treeMap = TreeMap.load(testRawLines)

    val followedPath = treeMap.followSlope(Slope(1, 1), Point(0, 0))
    val expectedFollowedPath = Vector(Geology.Empty, Geology.Empty, Geology.Empty)

    followedPath shouldBe expectedFollowedPath
  }

  test ("Can follow a slope from a starting point for different slopes") {
    val treeMap = TreeMap.load(testRawLines)

    val followedPath = treeMap.followSlope(Slope(rise = 2, run = 1), Point(0, 0))
    val expectedFollowedPath = Vector(Geology.Empty, Geology.Tree)

    followedPath shouldBe expectedFollowedPath
  }

  test("Can determine the number of trees along the slope of the test tree map") {
    val rawTreeMap = ResourceLoader.asLines("tobogganing/trajectory/test_tree_map.txt")(identity[String])
    val testTreeMap = TreeMap.load(rawTreeMap)

    val followedPath = testTreeMap.followSlope(Slope(rise = 1, run = 3))
    followedPath.count(_ == Geology.Tree) shouldBe 7
  }

  test("Can determine the number of trees along multiple slopes") {
    val rawTreeMap = ResourceLoader.asLines("tobogganing/trajectory/test_tree_map.txt")(identity[String])
    val testTreeMap = TreeMap.load(rawTreeMap)

    testTreeMap.followSlope(Slope(rise = 1, run = 1)).count(_ == Geology.Tree) shouldBe 2
    testTreeMap.followSlope(Slope(rise = 1, run = 3)).count(_ == Geology.Tree) shouldBe 7
    testTreeMap.followSlope(Slope(rise = 1, run = 5)).count(_ == Geology.Tree) shouldBe 3
    testTreeMap.followSlope(Slope(rise = 1, run = 7)).count(_ == Geology.Tree) shouldBe 4
    testTreeMap.followSlope(Slope(rise = 2, run = 1)).count(_ == Geology.Tree) shouldBe 2
  }

  test("Can determine the product of multiple trajectories") {
    val rawTreeMap = ResourceLoader.asLines("tobogganing/trajectory/test_tree_map.txt")(identity[String])
    val testTreeMap = TreeMap.load(rawTreeMap)

    val trajectories = Vector(
      Slope(rise = 1, run = 1),
      Slope(rise = 1, run = 3),
      Slope(rise = 1, run = 5),
      Slope(rise = 1, run = 7),
      Slope(rise = 2, run = 1)
    )

    val treesOnTrajectories = trajectories
      .map(trajectory => testTreeMap.followSlope(trajectory))
      .map(path => path.count(_ == Geology.Tree))

    treesOnTrajectories.product shouldBe 336
  }
}
