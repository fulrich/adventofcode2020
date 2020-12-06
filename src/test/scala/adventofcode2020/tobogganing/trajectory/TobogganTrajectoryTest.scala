package adventofcode2020.tobogganing.trajectory

import adventofcode2020.ResourceLoader
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TobogganTrajectoryTest extends AnyFunSuite with Matchers {
  test("Day 3:1 - Can determine how many trees are along the trajectory") {
    val rawTreeMap = ResourceLoader.asLines("tobogganing/trajectory/tree_map.txt")(identity[String])
    val treeMap = TreeMap.load(rawTreeMap)

    val followedPath = treeMap.followSlope(Slope(rise = 1, run = 3))
    followedPath.count(_ == Geology.Tree) shouldBe 230
  }

  test("Day 3:2 - Can determine how many trees are along all the requested trajectories") {
    val rawTreeMap = ResourceLoader.asLines("tobogganing/trajectory/tree_map.txt")(identity[String])
    val treeMap = TreeMap.load(rawTreeMap)

    val trajectories = Vector(
      Slope(rise = 1, run = 1),
      Slope(rise = 1, run = 3),
      Slope(rise = 1, run = 5),
      Slope(rise = 1, run = 7),
      Slope(rise = 2, run = 1)
    )

    val treesOnTrajectories = trajectories
      .map(trajectory => treeMap.followSlope(trajectory))
      .map(path => path.count(_ == Geology.Tree): Long)

    treesOnTrajectories.product shouldBe 9533698720L
  }
}
