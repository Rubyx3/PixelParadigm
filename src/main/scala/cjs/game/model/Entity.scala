package cjs.game.model

import cjs.game.utils.UI
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.Image
import scalafx.scene.shape.Rectangle

abstract class Entity(var worldX: Double, var worldY: Double){
  def apply(_worldX: Double, _worldY: Double): Entity = {
    worldX = _worldX
    worldY = _worldY
    this
  }
  val speed: Int = 100
  var direction: String
  val hitBox : Rectangle
  val dialogues = new ObservableBuffer[String]()
  val characterSkinSprites = new ObservableBuffer[Image]()

  def loadCharacterImage(filePath: String): Image = {
    UI.loadImage(filePath)
  }
}
