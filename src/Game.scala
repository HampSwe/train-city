/**
  * This file handles the game loop.
  */

package traincity

/** Declares and initializes some constant variables containing info about the game */
object Game:
     /** The title of the game */
    val title: String = "Train City"

    /** The width of the game's window in pixels*/
    val width: Int = 1000

    /** aspectRatio represents the value of the window's (height / width) */
    val aspectRatio: Double = 0.7153

    /** Calculates the height of the window in pixels*/
    val height: Int = (width * aspectRatio).round.toInt

class Game:
    import Game.*

    /** Procedure that starts the game */
    def start(printStartMsg: Boolean = true): Unit =
        if printStartMsg then startMessage()

        // Creates an instance of the CityWindow class
        val cityWindow = new CityWindow(title, width, height) 

        // Creates an instance, graphics, of the Graphics class.
        // This instance is used to perform all the screen operations on the cityWindow.
        val graphics = new Graphics(cityWindow)

        // Creates an instance, inputHandler, of the InputHandler class.
        //This instance handles all the input events that are triggered by the cityWindow
        val inputHandler = new InputHandler(cityWindow)

        graphics.drawWorld()

    
    /** Procedure that prints the start message to the screen */
    def startMessage(): Unit = println(s"${title} started.")
