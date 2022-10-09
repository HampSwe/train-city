/**
  * This file handles the game's graphics.
  * 
  * All graphical operations are performed on a CityWindow object.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

package traincity
import java.awt.Color as JColor

// Never call pixelWindow. directly! Add a method in graphics instead...

object Graphics:

    /** This objects stores the RGB-values of the java.awt.Colors that are used */
    object Colors:
        val white = new JColor(255, 255, 255)
        val black = new JColor(0, 0, 0)
        val red = new JColor(255, 0, 0)
        val green = new JColor(0, 255, 0)
        val blue = new JColor(0, 0, 255)
        val background = new JColor(255, 255, 255)
        val foreground = new JColor(0, 0, 0)


/** This class performs all the graphical operations on a CityWindow */
class Graphics(
    val cityWindow: CityWindow
    ):

    import Graphics.*
    import introprog.PixelWindow

    // Gets a reference to the pixelWindow that is attached to the given cityWindow.
    val pixelWindow = cityWindow.pixelWindow

    /** Initializes the world */
    def drawWorld(): Unit =
        //pixelWindow.fill(10, 10, 100, 100, Colors.green)

        //drawWideLine(p1, p2, 30, Colors.black)
        //pixelWindow.line(p1.x, p1.y, p2.x, p2.y, Colors.red)

        /*
        val t = new Triangle(Pos(10, 10), Pos(10, 300), Pos(500, 50), this)
        t.draw(Colors.red)
        Thread.sleep(2000)
        t.fill(Colors.blue)
        */

        val p1 = Pos(100, 100)
        val p2 = Pos(300, 300)
        //val p2 = Pos(200, 300)
        val p3 = Pos(400, 700)
        val width = 20

        println(p1.isLeftOfPlane(p3, p2))

        //val p3 = Pos(100, 700) konstig

        val thickLine = new ThickLine(p1, p2, p3, width, this, Colors.black, offSet=1)
        thickLine.draw()


    def drawWideLine(p1: Pos, p2: Pos, width: Int, color: JColor): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color, width)

// Add anti-aliasing!


case class ThickLine(a: Pos, b: Pos, c: Pos, width: Int, 
                    graphics: Graphics, color: JColor,
                    offSet: Int = -1, flipCorner: Boolean = false):

    import introprog.PixelWindow
    val pixelWindow = graphics.pixelWindow

    def getCorners(p1: Pos, p2: Pos, orientation: Int): (Pos, Pos) =
        //val alpha = math.atan((p2.y - p1.y) / (p2.x - p1.x))
        //val xDelta = math.sin(alpha) * ((width.toDouble) / 2) * orientation
        //val yDelta = math.cos(alpha) * ((width.toDouble) / 2) * orientation

        val distance = Pos.distance(p1, p2)
        val xDelta = ((p1.y - p2.y) * width) / (2 * distance) * orientation
        val yDelta = ((p1.x - p2.x) * width) / (2 * distance) * orientation

        val corner1 = Pos((p1.x - xDelta).round.toInt - offSet, (p1.y + yDelta).round.toInt)
        val corner2 = Pos((p2.x - xDelta).round.toInt - offSet, (p2.y + yDelta).round.toInt)
        (corner1, corner2)
    
    /** Gets the point of intersection between the line that goes through p1 and p2
     * and the line that goes through p3 and p4 */
    def getIntersectionPoint(p1: Pos, p2: Pos, p3: Pos, p4: Pos): (Pos) =
        if p1.x == p2.x then
            val xM = p1.x
            val k2: Double = ((p4.y - p3.y).toDouble / (p4.x - p3.x).toDouble)
            val yM = xM * k2 + p3.y - k2 * p3.x
            Pos(xM.round.toInt, yM.round.toInt)
        
        else if p4.x == p3.x then
            val xM: Double = p3.x
            val k1: Double = ((p2.y - p1.y).toDouble / (p2.x - p1.x).toDouble)
            val yM: Double = xM * k1 + p1.y - k1 * p1.x
            Pos(xM.round.toInt, yM.round.toInt)

        else
            val k1: Double = ((p2.y - p1.y).toDouble / (p2.x - p1.x).toDouble)
            val k2: Double = ((p4.y - p3.y).toDouble / (p4.x - p3.x).toDouble)
            val xM: Double = (p3.y - k2 * p3.x - p1.y + k1 * p1.x).toDouble / (k1 - k2)
            val yM: Double = xM * k1 + p1.y - k1 * p1.x
            Pos(xM.round.toInt, yM.round.toInt)
    
    // This function should be improved; flipCorner should not have to be specified
    def getOrientation: Int =
        var orientation = {if flipCorner then -1 else 1}
        orientation *= {if c.isLeftOfPlane(a, c) then -1 else 1}
        orientation *= {if c.y > a.y then 1 else -1}
        orientation
    
    def draw(): Unit =
        // Draws the base
        pixelWindow.line(a.x, a.y, b.x, b.y, color, width)
        pixelWindow.line(b.x, b.y, c.x, c.y, color, width)

        val orientation = getOrientation
        val (h1, h2) = getCorners(a, b, orientation)
        val (q1, q2) = getCorners(b, c, orientation)
        val intersect: Pos = getIntersectionPoint(h1, h2, q1, q2)

        // Fills the gap
        val t1 = new Triangle(h2, intersect, b, graphics)
        val t2 = new Triangle(q1, intersect, b, graphics)
        t1.fill(Graphics.Colors.black)
        t2.fill(Graphics.Colors.black)

        //pixelWindow.line(h1.x, h1.y, h2.x, h2.y, Graphics.Colors.red)
        //pixelWindow.line(q1.x, q1.y, q2.x, q2.y, Graphics.Colors.blue)

        //pixelWindow.line(h1.x, h1.y, intersect.x, intersect.y, Graphics.Colors.green)
        //pixelWindow.line(intersect.x, intersect.y, q2.x, q2.y, Graphics.Colors.red)



