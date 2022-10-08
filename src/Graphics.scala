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

        val p1 = Pos(10, 10)
        val p2 = Pos(50, 70)

        drawWideLine(p1, p2, 30, Colors.black)

        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, Colors.red)
    

    def drawWideLine(p1: Pos, p2: Pos, width: Int, color: JColor): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color, width)

    def fillTriangle(p1: Pos, p2: Pos, p3: Pos, color: JColor): Unit =
        val deltaX = p3.x - p2.x
        val deltaY = p3.y - p2.y
        val distance = math.hypot(deltaX, deltaY).ceil.toInt

        def x(t: Int): Int = (p3.x + deltaX * (t / distance)).round.toInt
        def y(t: Int): Int = (p3.y + deltaY * (t / distance)).round.toInt
        
        for t <- 0 to distance do
            pixelWindow.line(p1.x, p1.y, x(t), y(t))