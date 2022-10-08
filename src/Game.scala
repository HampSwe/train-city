/**
  * This file handles the game loop.
  */

package traincity

/* Declares and initializes some constant variables containing info about the game */
object Game:
    val title: String = "Train City" /* The title of the game */
    val width: Int = 1000 /*The width of the window in px*/
    val aspectRatio: Double = 0.7153 /* aspectRatio represents the value of (height / width) */
    val height: Int = (width * aspectRatio).round.toInt /*Calculates the height of the window in px*/

class Game:
    import Game.*

    /* Procedure that starts the game */
    def start(printStartMsg: Boolean = true): Unit =
        if printStartMsg then startMessage()

        // Creates an instance of the CityWindow class
        val cityWindow = new CityWindow(title, width, height) 

        // Creates an instance, graphics, of the Graphics class. The instance is used to perform all screen operations on the cityWindow.
        val graphics = new Graphics(cityWindow)

        graphics.drawWorld()

    
    /* Procedure that prints the start message to the screen */
    def startMessage(): Unit = println(s"${title} started.")
