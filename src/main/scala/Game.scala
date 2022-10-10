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
            new Station("Parliament", Pos(107, 229), Graphics.Colors.redLine,
                    (-22, 5), 10, Array("red-polygon"), (0, 0), 1),

            new Station("Anna Book Arena", Pos(681, 410), Graphics.Colors.white,
                    (-29, 9), 12, Array("red-polygon", "blue-polygon", "yellow-A"), (-5, 50), 0),
            
            new Station("Wit's End", Pos(890+16, 150+9), Graphics.Colors.blueLine,
                    (-56-16, -6-9), 10, Array("blue-polygon"), (85, 20), 2),
        )

        // Creates the stops on the red line
        val redLineStops: Array[Stop] = Array(
            new Stop("", Pos(140, 220), (0, 0), Graphics.Colors.redLine), // First stop, at Parliament
            new Stop("Weston Road", Pos(250, 220), (3, -21), Graphics.Colors.redLine),
            new Stop("Bridge Plaza", Pos(390, 220), (0, 10), Graphics.Colors.redLine),
            new Stop("Grand Square", Pos(480, 280), (-40, 2), Graphics.Colors.redLine),
            new Stop("Bremerton Alley", Pos(580, 350), (3, 10), Graphics.Colors.redLine),
            new Stop("Museum of Art", Pos(650, 350), (5, -21), Graphics.Colors.redLine), // should be anmed "National \n Museum of Art", but I have not added line-breaking capabilities to drawText()
            new Stop("", Pos(700, 400), (0, 0), Graphics.Colors.redLine), // Last stop, at Anna Book arena
        )
        // Creates the red line
        val redLine: MetroLine = new MetroLine(stations(0), stations(1),
                        Array(Pos(140, 220), Pos(420, 220), Pos(550, 350), Pos(650, 350), Pos(700, 400)),
                        Array(false, false, true, false, false), Array(0, 0, 0, 0, 0),
                        redLineStops, Graphics.Colors.redLine)


        // Creates the stops on the blue line
        val blueLineStops: Array[Stop] = Array(
            new Stop("", Pos(700, 400-4), (0, 0), Graphics.Colors.blueLine), // First stop, at Anna Book arena
            new Stop("Cascade", Pos(865, 371), (31, 1), Graphics.Colors.blueLine),
            new Stop("Prague Road", Pos(890, 250), (-42, -7), Graphics.Colors.blueLine),
            new Stop("", Pos(890, 150), (0, 0), Graphics.Colors.blueLine), // Last stop, at Wit's End
        )
        // Creates the blue line
        val blueLine: MetroLine = new MetroLine(stations(1), stations(2),
                        Array(Pos(700, 400-4), Pos(840, 400-4), Pos(890, 350-4), Pos(890, 150-4)),
                        //Array(Pos(890, 150), Pos(890, 350), Pos(840, 400), Pos(700, 400)),
                        Array(false, false, false, false, false), Array(0, 1, 0, 0, 0),
                        blueLineStops, Graphics.Colors.blueLine)

        
        // Draws all the MetroLines
        graphics.drawMetroLine(redLine)
        graphics.drawMetroLine(blueLine)

        // Draws all stations (their names and symbols)
        graphics.drawStations(stations)

        // Draws Anna Book Arena
        graphics.drawLargeStation(stations(1).position)

        // Add Singapore


    
    /** Procedure that prints the start message to the screen */
    def startMessage(): Unit = println(s"${title} started.")
