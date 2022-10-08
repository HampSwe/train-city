/**
  * This file handles positions in the game.
  */

package traincity

object Pos:
    def distance(p1: Pos, p2: Pos): Double =
        math.hypot(p1.x - p2.x, p1.y - p2.y)

/** This class represents a position in the game as two coordinates.
    The coordinates are currently in a 1:1 relationship with the window's pixels*/
case class Pos(
    val x: Int,
    val y: Int,
)