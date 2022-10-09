/**
  * This file handles the game's MetroLines
  */

package traincity
import java.awt.Color as JColor

object MetroLine:
    ???

/** This class represents a MetroLine */
case class MetroLine(startStation: Station, endStation: Station,
                    edges: Array[Pos], flippedCorners: Array[Boolean],
                    stops: Array[Stop], color: JColor
):
    def init(): Unit = println("Initialized")