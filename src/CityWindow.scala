/**
  * This file handles the game window
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

package traincity
import java.awt.Color as JColor

object CityWindow:
    ???

class CityWindow(
    val windowTitle: String, /* The title of the window */
    val width: Int, /* The width of the window in px */
    val height: Int, /* The height of the window in px */
    ):

    import CityWindow.*
    import introprog.PixelWindow

    val pixelWindow = new PixelWindow(width, height, windowTitle, Graphics.Colors.background, Graphics.Colors.foreground)