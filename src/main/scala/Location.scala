/**
  * This file handles the game's locations
  */

package traincity
import java.awt.Color as JColor

/** This is a base class for Stations and Stops*/
abstract class Location(name: String,
                        position: Pos,
                        color: JColor,
                        textDisplacement: (Int, Int)
                        ):

    def init(): Unit = println("Initialized")

// Make Symbol to a class

/** This is a class for the game's Stations*/
case class Station(name: String, position: Pos, color: JColor, textDisplacement: (Int, Int), 
                    textSize: Int, symbols: Array[String], symbolsDisplacement: (Int, Int), id: Int)
                    extends Location(name, position, color, textDisplacement):
    
    var metroLines: Array[MetroLine] = Array()

    override def init(): Unit = println("Initialized Station")

/** This is a class for the game's Stops*/
case class Stop(name: String, position: Pos, textDisplacement: (Int, Int), color: JColor)
                    extends Location(name, position, color, textDisplacement):
    
    override def init(): Unit = println("Initialized Stop")