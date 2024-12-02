package cjs.game.utils

import cjs.game.GameMain.eventState
import cjs.game.model.TileSize.tileSize
import scalafx.animation.KeyValue
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import scalafx.scene.text.Font

import scala.util.{Failure, Success, Try}

object UI{
  //dialog
  private val dialogSprite = loadImage("/images/DialogBox/TextBox.png")
  private val battleBoxSprite = loadImage("/images/DialogBox/RGBBox.png")
  var currentSpeaker: String = ""
  var currentDialogue: String = ""

  def drawDialogue(gc: GraphicsContext, canvas: Canvas, playState: Int): Unit = {
    if (playState == eventState) {
      gc.drawImage(dialogSprite, 0, 8 * tileSize, canvas.width(), 3 * tileSize)
      gc.font = new Font("Trebuchet MS", 30)
      gc.stroke = Color.Coral
      gc.strokeText(currentSpeaker, 200, 415, 2 * tileSize)
      gc.fillText(currentDialogue, tileSize, 460, canvas.width() - 2 * tileSize)
    }
  }

  def drawBattleBox(gc: GraphicsContext, canvas: Canvas): Unit = {
    gc.drawImage(battleBoxSprite, 244, 781, 1429, 265, 0, 410, canvas.width(), 3 * tileSize)
  }

  def cleanGameCanvas(gc: GraphicsContext, canvas: Canvas): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, canvas.width(), canvas.height())
  }

  def loadImage(filePath: String): Image = {
    val image: Option[Image] = Try(new Image(getClass.getResourceAsStream(filePath)))
    match {
      case Success(x) => Some(x)
      case Failure(exception) => None
    }
    image.get
  }
}
