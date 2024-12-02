package cjs.game.model

import scalafx.scene.shape.Rectangle
import scala.collection.mutable

class Player(_worldX: Double, _worldY: Double) extends Entity(_worldX: Double, _worldY: Double){
  //instantiate default speed
  override final val speed = 100
  //instantiate default direction
  var direction: String = "down"
  //load image
  private val mcSprite = loadCharacterImage("/images/CharacterSprites/NateOverworldSpriteAtlas.png")
  private val battleSprite = loadCharacterImage("/images/CharacterBattleSprites/Nate/NateBattleSpriteAtlas.png")
  //add to buffer
  characterSkinSprites ++= mutable.Buffer(mcSprite, battleSprite)

  val hitBox: Rectangle = new Rectangle() {
    width = 42
    height = 24
    //positioning is in State class
  }
}

object Player{
  val player = new Player(386, 350)
}
