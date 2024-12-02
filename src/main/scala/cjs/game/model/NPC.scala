package cjs.game.model

import TileSize.tileSize
import cjs.game.utils.UI
import scalafx.collections.ObservableBuffer
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import scalafx.scene.shape.Rectangle

import scala.collection.mutable


class NPC(_worldX: Double, _worldY: Double, val name: String) extends Entity(_worldX, _worldY) {
  var direction = "down"
  //npc sprites
  //Red
  val redOverworldSprite: Image = loadCharacterImage("/images/CharacterSprites/RedOverworldSpriteAtlas.png")
  val redBattleSprite: Image = loadCharacterImage("/images/CharacterBattleSprites/Red/red0.png")
  //load all NPC sprites
  characterSkinSprites ++= mutable.Buffer(redOverworldSprite)

  def drawSprite(gc: GraphicsContext, sprite: Image, npc: NPC, direction: String, i: Int): Unit = {
    direction match{
      case "up" => i match{
        //default
        case 1 => gc.drawImage(sprite, 14, 204, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        //moving
        case 2 => gc.drawImage(sprite, 78, 204, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 3 => gc.drawImage(sprite, 206, 204, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
      }
      case "down" => i match {
        case 1 => gc.drawImage(sprite, 14, 10, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 2 => gc.drawImage(sprite, 78, 10, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 3 => gc.drawImage(sprite, 206, 10, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
      }
      case "left" => i match {
        case 1 => gc.drawImage(sprite, 14, 76, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 2 => gc.drawImage(sprite, 78, 76, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 3 => gc.drawImage(sprite, 206, 76, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
      }
      case "right" => i match {
        case 1 => gc.drawImage(sprite, 14, 140, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 2 => gc.drawImage(sprite, 78, 140, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
        case 3 => gc.drawImage(sprite, 206, 140, 36, 54, npc.worldX - tileSize/2, npc.worldY - tileSize/2, tileSize, tileSize)
      }
    }
  }

  val hitBox: Rectangle = new Rectangle() {
    width = 45
    height = 50
    x = worldX - 24
    y = worldY - 22
  }

  dialogues ++= mutable.Buffer("...")

  def speak(npc: NPC): Unit = {
    UI.currentDialogue = dialogues(0)
    UI.currentSpeaker = npc.name
  }

}

object NPC {
  val red = new NPC(386,270,"Red")
  val npcList = new ObservableBuffer[NPC]()
  npcList ++= mutable.Buffer(red)
}
