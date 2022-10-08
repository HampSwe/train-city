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
        pixelWindow.fill(10, 10, 100, 100, Colors.green)