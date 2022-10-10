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
        val river = new JColor(86, 154, 255)
        val redLine = new JColor(251, 0, 7)

    val riverWidth: Int = 28
    val metroLineWidth: Int = 10
    val stopSize = 8
    val stopTextSize = 10

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

        drawRiver()

        //val p1 = Pos(100, 100)
        //val p2 = Pos(300, 100)
        //val p3 = Pos(400, 300)
        //val width = 10

        //val thickLine = new ThickLine(p1, p2, p3, width, this, Colors.black, offSet = 0, flipCorner = false)
        //thickLine.draw()

    /** A procedure that draws a wide line */
    def drawWideLine(p1: Pos, p2: Pos, width: Int, color: JColor): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color, width)

    /** A procedure that draws the background river */
    def drawRiver(): Unit =
        val width = riverWidth

        // An array of the ThickLines that make up the river
        // (Everything should move down a bit)
        val lines: Array[ThickLine] = Array(
            new ThickLine(Pos(0, 400), Pos(200, 400), Pos(300, 300), width, this, Colors.river, offSet = 0, flipCorner = false),
            new ThickLine(Pos(420, 715), Pos(420, 690), Pos(300, 570), width, this, Colors.river, offSet = 0, flipCorner = false),
            new ThickLine(Pos(420, 690), Pos(300, 570), Pos(300, 300), width, this, Colors.river, offSet = 0, flipCorner = true),
            new ThickLine(Pos(300, 570), Pos(300, 250), Pos(380, 170), width, this, Colors.river, offSet = 0, flipCorner = true),
            new ThickLine(Pos(300, 250), Pos(380, 170), Pos(480, 170), width, this, Colors.river, offSet = 0, flipCorner = true),
            new ThickLine(Pos(380, 170), Pos(480, 170), Pos(560, 250), width, this, Colors.river, offSet = 0, flipCorner = true),
            new ThickLine(Pos(480, 170), Pos(560, 250), Pos(630, 250), width, this, Colors.river, offSet = 0, flipCorner = true), //intersection
            new ThickLine(Pos(560, 250), Pos(630, 250), Pos(680, 200), width, this, Colors.river, offSet = 0, flipCorner = false),
            new ThickLine(Pos(630, 250), Pos(680, 200), Pos(680, 0), width, this, Colors.river, offSet = 0, flipCorner = false),
            new ThickLine(Pos(560, 250), Pos(630, 250), Pos(850, 470), width, this, Colors.river, offSet = 0, flipCorner = true),
            new ThickLine(Pos(630, 250), Pos(850, 470), Pos(1000, 470), width, this, Colors.river, offSet = 0, flipCorner = true),
        )

        for i <- 0 until lines.length do
            lines(i).draw()


    /** Draws the specified MetroLine */
    def drawMetroLine(metroLine: MetroLine): Unit =
        def drawBackground(): Unit =
            val edges = metroLine.edges
            val numberOfEdges = edges.length
            var thickLine: ThickLine = null
            var flippedCorner: Boolean = false

            for i <- 0 until numberOfEdges - 2 do
                flippedCorner = {if metroLine.flippedCorners(i + 1) then true else false}
                thickLine = new ThickLine(edges(i), edges(i + 1), edges(i + 2), metroLineWidth, this, metroLine.color, flipCorner = flippedCorner)
                thickLine.draw()
        
        def drawStops(): Unit =
            val poly: Polygon = new Polygon(Pos(200, 100), 30, 5, this)
            poly.fill(Colors.black)

            var circle: Circle = null
            var perimeter: Circle = null
            val stops = metroLine.stops

            for stop <- stops do
                perimeter = new Circle(stop.position, stopSize, 1000, this)
                circle = new Circle(stop.position, stopSize - 3, 1000, this)
                perimeter.fill(Colors.white)
                circle.fill(metroLine.color)
            
                // Add line break capabilities
                pixelWindow.drawText(stop.name, stop.position.x + stop.textDisplacement(0) - stop.name.length * 3,
                                    stop.position.y + stop.textDisplacement(1), Colors.black, stopTextSize, 1, "Arial")
                


        def drawStations(): Unit =
            println("hej")

        
    
        drawBackground()
        drawStops()
        drawStations()


    /** This procedure for anti-aliasing is incredibly inefficient and is only used for testing */
    def simpleAntiAlias(): Unit =
        // Currently skips the perimeter (which should be supported later)
        val xRange = 1 until cityWindow.width - 1
        val yRange = 1 until cityWindow.height - 1
        var i = 0

        var pixel: JColor = Colors.white
        var newColor = Colors.white
        var leftColor = Colors.white
        var rightColor = Colors.white
        var upColor = Colors.white
        var downColor = Colors.white

        for x <- xRange do
            for y <- yRange do
                pixel = pixelWindow.getPixel(x, y)
                if pixel != Colors.black then
                    leftColor = pixelWindow.getPixel(x - 1, y)
                    rightColor = pixelWindow.getPixel(x + 1, y)
                    upColor = pixelWindow.getPixel(x, y + 1)
                    downColor = pixelWindow.getPixel(x, y - 1)
            
                    newColor = JColor((leftColor.getRed() + rightColor.getRed() + upColor.getRed() + downColor.getRed() + pixel.getRed()) / 5,
                                    (leftColor.getBlue() + rightColor.getBlue() + upColor.getBlue() + downColor.getBlue() + pixel.getBlue()) / 5,
                                    (leftColor.getGreen() + rightColor.getGreen() + upColor.getGreen() + downColor.getGreen() + pixel.getGreen()) / 5)
                    
                    pixelWindow.setPixel(x, y, newColor)

        println("Finished anti-aliasing")

// Add anti-aliasing!


/** This class handles ThickLines - that is, a wide line between three points.
 * What is special about a ThickLine is that it adds extra "padding" so that
 * when the line turns, it gets a nice looking edge.
 * 
 * If the edge doesn't appear, you can manually set flipCorner to true.
 * If the edge appears slightly off, you can change offSet to a different value
 * 
 * It seems as if all the lines in the diagram sofar are 45 degrees, which makes a lot of this code redundant...
*/
case class ThickLine(a: Pos, b: Pos, c: Pos, width: Int, 
                    graphics: Graphics, color: JColor,
                    offSet: Int = 0, flipCorner: Boolean = false):

    import introprog.PixelWindow
    val pixelWindow = graphics.pixelWindow

    /** Gets two corners of the wide line that goes through p1 and p2 */
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
    
    /* Draws the ThickLine */
    def draw(): Unit =
        // Draws the base
        pixelWindow.line(a.x, a.y, b.x, b.y, color, width)
        pixelWindow.line(b.x, b.y, c.x, c.y, color, width)

        val orientation = getOrientation
        val (h1, h2) = getCorners(a, b, orientation)
        val (q1, q2) = getCorners(b, c, orientation)
        val intersect: Pos = getIntersectionPoint(h1, h2, q1, q2)

        if !Pos.pointsOnLine(a, b, c) then
            // Fills the gap with padding
            val t1 = new Triangle(h2, intersect, b, graphics)
            val t2 = new Triangle(q1, intersect, b, graphics)
            t1.fill(color)
            t2.fill(color)

        pixelWindow.line(h1.x, h1.y, h2.x, h2.y, color)
        pixelWindow.line(q1.x, q1.y, q2.x, q2.y, color)

        //pixelWindow.line(h1.x, h1.y, intersect.x, intersect.y, Graphics.Colors.green)
        //pixelWindow.line(intersect.x, intersect.y, q2.x, q2.y, Graphics.Colors.red)



