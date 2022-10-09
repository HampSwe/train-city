/**
  * This file handles representations of polygons.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

package traincity
import java.awt.Color as JColor

/* A Polygon trait. All polygons can be drawn and filled */
trait Polygon:
    def draw(color: JColor): Unit
    def fill(color: JColor): Unit


/** A class that represents a 2D Triangle */
case class Triangle(p1: Pos, p2: Pos, p3: Pos, graphics: Graphics) extends Polygon:
    val pixelWindow = graphics.pixelWindow

    /** Draws the triangle on the screen */
    override def draw(color: JColor): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color)
        pixelWindow.line(p2.x, p2.y, p3.x, p3.y, color)
        pixelWindow.line(p3.x, p3.y, p1.x, p1.y, color)

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
            pixelWindow.line(p1.x, p1.y, x(t), y(t))

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