/**
  * This file handles the game's locations
  */

package traincity

abstract class Location(name: String,
                        position: Pos, 
                        metroLines: Array[MetroLine],
                        textDisplacement: (Int, Int)):

    def init(): Unit = ???

case class Station(name: String, position: Pos, metroLines: Array[MetroLine], textDisplacement: (Int, Int),
                    textSize: Int, symbol: String)
                    extends Location(name, position, metroLines, textDisplacement):
    
    ???

case class Stop(name: String, position: Pos, metroLines: Array[MetroLine],
                    textDisplacement: (Int, Int))
                    extends Location(name, position, metroLines, textDisplacement):
    
    ???