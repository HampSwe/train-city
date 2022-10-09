/**
  * This file handles the game's inputs.
  * 
  * All input events are triggered by a CityWindow object.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

package traincity
import java.awt.Color as JColor

object InputHandler:
    ???

/** This class handles all the input events that are triggered by a CityWindow */
class InputHandler(
    val cityWindow: CityWindow
    ):

    import InputHandler.*
    import introprog.PixelWindow

    // Gets a reference to the pixelWindow that is attached to the given cityWindow.
    val pixelWindow = cityWindow.pixelWindow

    def getEvents(): Unit =
        ???