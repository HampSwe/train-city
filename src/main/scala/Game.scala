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

/** This class handles everything in the game.*/
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

        // Draws the river
        graphics.drawWorld()

        // Creates the stations
        val stations: Array[Station] = Array(
            new Station("Parliament", Pos(100, 200), Graphics.Colors.red,
                    (-30, 0), 12, Array("red-polygon"), (-10, 0)),

            new Station("Anna Book Arena", Pos(600, 600), Graphics.Colors.white,
                    (0, 30), 16, Array("red-polygon", "blue-polygon", "yellow-A"), (-5, 50)),
            
            new Station("Wit's End", Pos(700, 300), Graphics.Colors.blue,
                    (-30, 0), 12, Array("blue-polygon"), (15, 0)),
        )

        // Creates the stops on the red line
        val redLineStops: Array[Stop] = Array(
            new Stop("", Pos(140, 220), (0, 0), Graphics.Colors.red), // Start point, at Parliament
            new Stop("Weston Road", Pos(250, 220), (3, -21), Graphics.Colors.red),
            new Stop("Bridge Plaza", Pos(390, 220), (0, 10), Graphics.Colors.red),
            new Stop("Grand Square", Pos(480, 280), (-40, 2), Graphics.Colors.red),
            new Stop("Bremerton Alley", Pos(580, 350), (3, 10), Graphics.Colors.red),
            new Stop("Museum of Art", Pos(650, 350), (5, -21), Graphics.Colors.red), // should be anmed "National \n Museum of Art", but I have not added line-breaking capabilities to drawText()
        )

        // Creates the red line
        val redLine: MetroLine = new MetroLine(stations(0), stations(1),
                        Array(Pos(140, 220), Pos(420, 220), Pos(550, 350), Pos(650, 350), Pos(700, 400)),
                        Array(false, false, true, false, false), redLineStops, Graphics.Colors.red)
        
        // Draws the red line
        graphics.drawMetroLine(redLine)

        // Add Singapore


    
    /** Procedure that prints the start message to the screen */
    def startMessage(): Unit = println(s"${title} started.")
