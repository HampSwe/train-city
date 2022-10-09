/**
  * This file handles the game's main window.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

package traincity
import java.awt.Color as JColor

object CityWindow:
    ???

/**
  * Essentially, a "CityWindow" works like a wrapper around a "PixelWindow".
  * This layer of abstraction could seem unnecessary at the moment, but it might become useful in the future
  * (if we want to add scaling and anti-alias capabilities for instance).
  * 
  * Upon construction, a "PixelWindow" is instatiated and a reference to it is stored as "pixelWindow".
  * 
  * The classes "Graphics" and "InputHandler" both take a "CityWindow" object as a parameter, and they
  * are the only classes that directly store references to CityWindow objects.
*/
class CityWindow(
    val windowTitle: String, /** The title of the window */
    val width: Int, /** The width of the window in px */
    val height: Int, /** The height of the window in px */
    ):

    import CityWindow.*
    import introprog.PixelWindow

    /* A pixelWindow is instantiated and attached to the CityWindow */
    val pixelWindow = new PixelWindow(width, height, windowTitle, Graphics.Colors.background, Graphics.Colors.foreground)