/**
  * This file handles positions in the game.
  */

package traincity

object Pos:
    /** Gets the distance between two points*/
    def distance(p1: Pos, p2: Pos): Double =
        math.hypot(p1.x - p2.x, p1.y - p2.y)
    
    /** Gets the angle in radians at p2 between the points p1, p2 and p3*/ 
    def angle(p1: Pos, p2: Pos, p3: Pos): Double =
        val d1 = distance(p1, p2)
        val d2 = distance(p2, p3)
        val d3 = distance(p3, p1)
        val alpha = math.acos((d3 * d3 - d1 * d1 - d2 * d2) / (2 * d1 * d2))
        alpha

/** This class represents a position in the game as two coordinates.
    The coordinates are currently in a 1:1 relationship with the window's pixels*/
case class Pos(
    val x: Int,
    val y: Int,
):
    /** Checks if the point is on the left side
     * of the plane that is divided by the line that goes between p1 and p2 */
    def isLeftOfPlane(p1: Pos, p2: Pos): Boolean =
        val k = (p2.y - p1.y).toDouble / (p2.x - p1.x).toDouble
        def lineY(x: Double) = k * x + p1.y - k * p1.x

        if y < lineY(x) then true else false