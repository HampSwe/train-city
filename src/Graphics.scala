/**
  * This file handles the game graphics.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  * 
  */

package traincity
import java.awt.Color as JColor

object Graphics:

    /* This objects stores the RGB-values of the java.awt.Colors that are used */
    object Colors:
        val white = new JColor(255, 255, 255)
        val black = new JColor(0, 0, 0)
        val red = new JColor(255, 0, 0)
        val green = new JColor(0, 255, 0)
        val blue = new JColor(0, 0, 255)
        val background = new JColor(255, 255, 255)
        val foreground = new JColor(0, 0, 0)

class Graphics(
    val windowTitle: String, /* The title of the window */
    val width: Int, /* The width of the window in px */
    val height: Int, /* The height of the window in px */
    ):

    import Graphics.*
    import introprog.PixelWindow

    val pixelWindow = new PixelWindow(width, height, windowTitle, Colors.background, Colors.foreground)