/**
  * This file handles the game's main window.
  * 
  * Essentially, the "CityWindow" works like a wrapper around "PixelWindow".
  * It is called "CityWindow", so that it isn't confused with a "PixelWindow".
  * 
  * Upon construction, a "PixelWindow" is instatiated and a reference to it is stored as "pixelWindow".
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

    /* A pixelWindow is instantiated and attached to the CityWindow */
    val pixelWindow = new PixelWindow(width, height, windowTitle, Graphics.Colors.background, Graphics.Colors.foreground)