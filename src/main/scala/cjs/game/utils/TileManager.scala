package cjs.game.utils

import cjs.game.model.TileSize
import scalafx.collections.ObservableBuffer
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image

import scala.io.Source
import scala.util.{Success, Using}

case class TileManager() {
  val tileSize: Double = TileSize.tileSize
  val maxScreenColumn: Int = TileSize.maxScreenColumn
  val maxScreenRow: Int = TileSize.maxScreenRow

  //each tile
  private class Tile(image: Image) extends Image(image: Image){
    //does the tile have collision
    var collision = false
  }

  private val tileResource = new ObservableBuffer[Tile]()

  private def loadTileImage(): tileResource.type = {
    val tileImage1 = new Image(getClass.getResourceAsStream("/images/Tiles/grass.png"))
    val tileImage2 = new Image(getClass.getResourceAsStream("/images/Tiles/tree.png"))
    val tileImage3 = new Image(getClass.getResourceAsStream("/images/Tiles/water.png"))

    val tile1 = new Tile(tileImage1)
    val tile2 = new Tile(tileImage2) {collision = true}
    val tile3 = new Tile(tileImage3) {collision = true}

    tileResource ++= List(tile1,tile2,tile3)
  }
  loadTileImage()


  private val myMapList: Array[Array[Int]] = Using(Source.fromFile("src/main/resources/mapdata/map01.txt")){
    source => source.getLines().map{x => x.split(" ").map(_.toInt)}.toArray
  }
  match {
    case Success(myMapList) => myMapList
  }
  //myMapList.foreach(x => println(x.mkString(" ")))

  def drawTile(tileGC: GraphicsContext): Unit = {
    var col: Int = 0
    var row: Int = 0
    var x: Double = 0
    var y: Double = 0

    while (col < maxScreenColumn && row < maxScreenRow) {
      tileGC.drawImage(tileResource(myMapList(row)(col)), x, y, tileSize, tileSize)
      x += tileSize
      col += 1
      if(col == maxScreenColumn) {
        col = 0
        x = 0
        y += tileSize
        row = row + 1
      }
    }
  }
}
