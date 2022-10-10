/**
  * This file handles representations of shapes.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

package traincity
import java.awt.Color as JColor

/* A Shape trait. All polygons can be drawn and filled */
trait Shape:
    def draw(color: JColor, width: Int = 1): Unit
    def fill(color: JColor): Unit

    // graphics: Graphics should be a field


/** A class that represents an arbitrary even polygon */
case class Polygon(start: Pos, var side: Double, vertices: Int, graphics: Graphics) extends Shape:
    val pixelWindow = graphics.pixelWindow

    /** Draws an arbitrary even polygon */
    override def draw(color: JColor, width: Int = 1): Unit =
        var turn: Double = (math.Pi * 2) / vertices.toDouble
        var angle: Double = 0
        var oldPos: (Double, Double) = (start.x, start.y)
        var currentPos: (Double, Double) = (start.x, start.y)

        for i <- 0 until vertices - 1 do
            currentPos = (oldPos(0) + math.cos(angle) * side, oldPos(1) + math.sin(angle) * side)
            pixelWindow.line(oldPos(0).round.toInt, oldPos(1).round.toInt, currentPos(0).round.toInt, currentPos(1).round.toInt, color, width)
            angle -= turn
            oldPos = currentPos

        pixelWindow.line(oldPos(0).round.toInt, oldPos(1).round.toInt, start.x, start.y, color, width)


    /** Fills an arbitrary even polygon */
    override def fill(color: JColor): Unit =
        val oldSide = side
        var tmpSide = oldSide.toDouble
        val precision = 3
        var width = 1

        for i <- 1 to (side * precision).toInt do
            this.draw(color, width = width)
            tmpSide -= 1D / precision.toDouble
            side = tmpSide.round.toInt
    
            if i == 1 then
                width += 1

        side = oldSide

        // Brute force the pixels that aren't colored. This should be replaced with a better fill-algorithm, preferably A*
        for x <- 0 until side.toInt do
            for y <- 0 until (side * 2).toInt do
                if pixelWindow.getPixel(start.x + x, start.y - y) != color then
                    if pixelWindow.getPixel(start.x + x + 1, start.y - y) == color
                        && pixelWindow.getPixel(start.x + x - 1, start.y - y) == color
                        && pixelWindow.getPixel(start.x + x, start.y - y + 1) == color
                        && pixelWindow.getPixel(start.x + x, start.y - y - 1) == color then
                            pixelWindow.setPixel(start.x + x, start.y - y, color)


/** A case class that represents a circle */
case class Circle(center: Pos, var radius: Double, edges: Int = 100, graphics: Graphics) extends Shape:
    val pixelWindow = graphics.pixelWindow

    /** Draws a circle */
    override def draw(color: JColor, width: Int = 1): Unit =
        var turn: Double = (math.Pi * 2) / edges.toDouble
        var angle: Double = 0
        var currentPos: (Double, Double) = (center.x + radius, center.y)
        var nextPos: (Double, Double) = (center.x + radius, center.y)

        for i <- 0 to edges do
            nextPos = (center.x + math.cos(angle) * radius, center.y + math.sin(angle) * radius)
            pixelWindow.line(currentPos(0).round.toInt, currentPos(1).round.toInt, nextPos(0).round.toInt, nextPos(1).round.toInt, color, width)
            angle -= turn
            currentPos = nextPos

    /** Fills a circle */
    override def fill(color: JColor): Unit =
        val oldRadius = radius
        val width = 2

        for i <- 1 to radius.toInt do
            this.draw(color, width = width)
            radius -= 1
        radius = oldRadius



/** A case class that represents a RoundRectangle. Not implemented yet.
 * RoundRectangles will represent larger stations, such as Anna Book Arena */
case class RoundRectangle(start: Pos, width: Int, height: Int, graphics: Graphics) extends Shape:
    val pixelWindow = graphics.pixelWindow

    /** Draws a circle */
    override def draw(color: JColor, width: Int = 1): Unit =
        ???

    /** Fills a circle */
    override def fill(color: JColor): Unit =
        ???


/** A case class that represents a Rectangle */
case class Rectangle(start: Pos, recWidth: Int, recHeight: Int, graphics: Graphics) extends Shape:
    val pixelWindow = graphics.pixelWindow

    /** Draws a circle */
    override def draw(color: JColor, width: Int = 1): Unit =
        pixelWindow.line(start.x, start.y, start.x + recWidth - 1, start.y, color)
        pixelWindow.line(start.x + (recWidth - 1), start.y, start.x + (recWidth - 1), start.y - (recHeight - 1),  color)
        pixelWindow.line(start.x + (recWidth - 1), start.y - (recHeight - 1), start.x, start.y - (recHeight - 1), color)
        pixelWindow.line(start.x, start.y - (recHeight - 1), start.x, start.y, color)

    /** Fills a circle */
    override def fill(color: JColor): Unit =
        pixelWindow.fill(start.x, start.y - recHeight + 1, recWidth, recHeight, color)


/** A class that represents a 2D Triangle */
case class Triangle(p1: Pos, p2: Pos, p3: Pos, graphics: Graphics) extends Shape:
    val pixelWindow = graphics.pixelWindow

    /** Draws the triangle on the screen */
    override def draw(color: JColor, width: Int = 1): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color, width)
        pixelWindow.line(p2.x, p2.y, p3.x, p3.y, color, width)
        pixelWindow.line(p3.x, p3.y, p1.x, p1.y, color, width)

    /** Fills the triangle fast, but is inaccurate */
    def fillTriangleFast(color: JColor, multiplier: Int = 10): Unit =
        val deltaX: Double = p3.x - p2.x
        val deltaY: Double = p3.y - p2.y
        val distance: Double = math.hypot(deltaX, deltaY)
        val n: Double = distance * multiplier
        val range = 0 to n.ceil.toInt

        def x(t: Double): Int = (p3.x - deltaX * (t / n)).round.toInt
        def y(t: Double): Int = (p3.y - deltaY * (t / n)).round.toInt
        
        for t <- range do
            pixelWindow.line(p1.x, p1.y, x(t), y(t), color)

    /** Fills the triangle accurately, but is slow */
    def fillTriangleExact(color: JColor): Unit =
        val pointsX = Vector(p1.x, p2.x, p3.x)
        val pointsY = Vector(p1.y, p2.y, p3.y)
        val xRange = pointsX.reduce(_.min(_)) to pointsX.reduce(_.max(_))
        val yRange = pointsY.reduce(_.min(_)) to pointsY.reduce(_.max(_))

        this.draw(color)
        for x <- xRange do
            for y <- yRange do
                if this.pointIsInTriangle(Pos(x, y)) then pixelWindow.setPixel(x, y, color)
                // pixels should not be changed directly (instead, do this in CityWindow to allow scaling and anti-alias)

    /* Sets the default fill procedure to fillTriangleExact*/
    override def fill(color: JColor): Unit = this.fillTriangleExact(color)

    // Checks if a point (Pos-object) is inside (or on) a triangle
    def pointIsInTriangle(point: Pos, precision: Int = 5): Boolean =
        val actualArea = this.areaOfTriangle()

        val triangle1 = new Triangle(point, p1, p2, graphics)
        val triangle2 = new Triangle(point, p2, p3, graphics)
        val triangle3 = new Triangle(point, p1, p3, graphics)

        // This could be done quicker without calculating the area,
        // if we instead check if the point is on the "right" side of
        // each plane that every edge of the triangle cuts
        val area1 = triangle1.areaOfTriangle()
        val area2 = triangle2.areaOfTriangle()
        val area3 = triangle3.areaOfTriangle()

        val sum = area1 + area2 + area3
        val roundedSum = BigDecimal(sum).setScale(precision, BigDecimal.RoundingMode.HALF_UP).toDouble

        if sum == actualArea || point == p1 || point == p2 || point == p3 then true else false

    // Calculates the area of the triangle using Heron's formula
    def areaOfTriangle(precision: Int = 5): Double = 
        val a = Pos.distance(p1, p2)
        val b = Pos.distance(p2, p3)
        val c = Pos.distance(p3, p1)
        val s = (a + b + c) / 2
        val area = math.sqrt(math.abs(s * (s - a) * (s - b) * (s - c)))
        val roundedArea = BigDecimal(area).setScale(precision, BigDecimal.RoundingMode.HALF_UP).toDouble
        roundedArea