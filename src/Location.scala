/**
  * This file handles the game's locations
  */

package traincity

abstract class Location(name: String, position: Pos, metroLines: MetroLine):
    def hi(): Unit = println("hi")

case class Station(name: String, position: Pos, metroLines: MetroLine) extends Location(name, position, metroLines):
    
    def hello(): Unit = hi()