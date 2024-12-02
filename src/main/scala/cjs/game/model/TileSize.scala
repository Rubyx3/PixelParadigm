package cjs.game.model

object TileSize {
  private final val originalTileSize: Double = 16 //16x16 tile
  private final val scale: Int = 3

  final val tileSize: Double = originalTileSize * scale //48x48 tile
  val maxScreenColumn: Int = 16
  val maxScreenRow: Int = 12

  val screenWidth: Double = tileSize * maxScreenColumn // 768 pixels
  val screenHeight: Double = tileSize * maxScreenRow //576 pixels

  //maintaining 4:3 aspect ratio
}
