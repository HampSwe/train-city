/**
  * This file handles the game's locations
  */

package traincity
import java.awt.Color as JColor

abstract class Location(name: String,
                        position: Pos,
                        color: JColor,
                        textDisplacement: (Int, Int),
                        ):

    def init(): Unit = ???


// Make Symbol to a class

case class Station(name: String, position: Pos, color: JColor, textDisplacement: (Int, Int), 
                    textSize: Int, symbols: Array[String], symbolsDisplacement: (Int, Int))
                    extends Location(name, position, color, textDisplacement):
    
    var metroLines: Array[MetroLine] = Array()

case class Stop(name: String, position: Pos, textDisplacement: (Int, Int), color: JColor)
                    extends Location(name, position, color, textDisplacement):
    
    ???