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

        val p1 = Pos(400, 400)
        val p2 = Pos(300, 300)
        val p3 = Pos(300, 100)
        val width = 20

        val thickLine = new ThickLine(p1, p2, p3, width, this, Colors.black)
        thickLine.draw()


    def drawWideLine(p1: Pos, p2: Pos, width: Int, color: JColor): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color, width)

// Add anti-aliasing!


case class ThickLine(p1: Pos, p2: Pos, p3: Pos, width: Int, graphics: Graphics, color: JColor):
    import introprog.PixelWindow
    val pixelWindow = graphics.pixelWindow
    
    def draw(): Unit =
        pixelWindow.line(p1.x, p1.y, p2.x, p2.y, color, width)
        pixelWindow.line(p2.x, p2.y, p3.x, p3.y, color, width)
