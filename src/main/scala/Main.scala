/**
  * This file contains the main function.
  * 
  * Read more about the package "PixelWindow" here:
  * https://fileadmin.cs.lth.se/pgk/api/api/introprog/PixelWindow.html
  */

//> using scala "3.1.3"
//> using lib "se.lth.cs::introprog:1.3.1"

package traincity

object Main:
    /** The main function starts the game */
    def main(args: Array[String]): Unit =
        val game = new Game()
        game.start()