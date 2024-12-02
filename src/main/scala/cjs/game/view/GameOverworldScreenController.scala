package cjs.game.view

import cjs.game.GameMain
import cjs.game.GameMain._
import cjs.game.model.TileSize.tileSize
import cjs.game.model.{EntityState, NPC, Player, ScriptedEvent}
import cjs.game.utils.Controls.{downPressed, leftPressed, rightPressed, upPressed}
import cjs.game.utils.{Controls, Sound, UI}
import scalafx.animation.AnimationTimer
import scalafx.beans.property.ObjectProperty
import scalafx.scene.canvas._
import scalafx.scene.image.Image
import scalafxml.core.macros.sfxml

@sfxml
class GameOverworldScreenController(private val gameCanvas: Canvas) {
 private val gc = gameCanvas.graphicsContext2D
 //player at world coordinates
 private val player = Player.player
 private val playerSprite = player.characterSkinSprites.head
 //npc
 private val red: NPC = NPC.red
 //map
 private val pokeCentreMap = UI.loadImage("/images/Map/pokehealcentre.png")
 //controls
 Controls.applyKeypad(gameCanvas, player)
 Controls.applyMouse(gameCanvas)

 private def paintMap(map: Image): Unit = {
  gc.drawImage(map, 0, 0, gameCanvas.width(), gameCanvas.height())
 }

 private def shutAnimation(): Unit = {
  timer.stop()
 }

 private var lastTime = 0L
 private var spriteCounter: Int = 0
 var counter: Int = 0
 private val state = ObjectProperty(EntityState(player))
 Sound().playIndefinite(Sound().audio1, 0.5)

 private val timer = AnimationTimer { time =>
  gameCanvas.requestFocus()
  UI.cleanGameCanvas(gc, gameCanvas)
  paintMap(pokeCentreMap)
  //TileManager().drawTile(gc)

  //event
  ScriptedEvent.redEncounter(gc, gameCanvas)
  //npc sprite updates after
  red.drawSprite(gc, red.redOverworldSprite, red, red.direction, 1)

  //interval conveniently also returns 1 frame at ~0.016s so it means AnimationTimer is refreshing at roughly 60fps
  val interval = (time - lastTime) / 1e9 //convert from nano seconds to seconds
  if (lastTime != 0) {
   if (!upPressed && !downPressed && !leftPressed && !rightPressed || gameState != playState) {
    player.direction match {
     // we are using sprites from one big image instead of multiple small images, so need to declare offset
     // can easily count pixel offset using Microsoft Paint
     case "up" => gc.drawImage(playerSprite, 10, 193, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     case "down" => gc.drawImage(playerSprite, 10, 1, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     case "left" => gc.drawImage(playerSprite, 14, 67, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     case "right" => gc.drawImage(playerSprite, 10, 131, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     case _ => gc.drawImage(playerSprite, 10, 3, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
    }
   }
   if (playState == gameState) {
    if (upPressed) {
     //update coordinates
     state.update(state().newState("up", interval, player, counter))
     //draw
     if (spriteCounter <= 10 || spriteCounter >= 20 && spriteCounter <= 30) {
      gc.drawImage(playerSprite, 10, 193, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 10 && spriteCounter <= 20) {
      gc.drawImage(playerSprite, 74, 193, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 30) {
      gc.drawImage(playerSprite, 202, 193, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 40) {
      spriteCounter = 0
     }
    }
    else if (downPressed) {
     //update coordinates
     state.update(state().newState("down", interval, player, counter))
     //draw
     if (spriteCounter <= 10 || spriteCounter >= 20 && spriteCounter <= 30) {
      gc.drawImage(playerSprite, 10, 1, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 10 && spriteCounter <= 20) {
      gc.drawImage(playerSprite, 74, 1, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 30) {
      gc.drawImage(playerSprite, 202, 1, 44, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 40) {
      spriteCounter = 0
     }
    }
    else if (leftPressed) {
     //update coordinates
     state.update(state().newState("left", interval, player, counter))
     //draw
     if (spriteCounter <= 10 || spriteCounter >= 20 && spriteCounter <= 30) {
      gc.drawImage(playerSprite, 14, 65, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 10 && spriteCounter <= 20) {
      gc.drawImage(playerSprite, 78, 65, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 30) {
      gc.drawImage(playerSprite, 205, 65, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 40) {
      spriteCounter = 0
     }
    }
    else if (rightPressed) {
     //update coordinates
     state.update(state().newState("right", interval, player, counter))
     //draw
     if (spriteCounter <= 10 || spriteCounter >= 20 && spriteCounter <= 30) {
      gc.drawImage(playerSprite, 10, 129, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 10 && spriteCounter <= 20) {
      gc.drawImage(playerSprite, 74, 129, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 30) {
      gc.drawImage(playerSprite, 202, 129, 40, 58, player.worldX - tileSize / 2, player.worldY - tileSize / 2, tileSize, tileSize)
     }
     if (spriteCounter >= 40) {
      spriteCounter = 0
     }
    }
   }


  }
  lastTime = time
  spriteCounter += 1
  counter += 1
  val fps = (1 / interval).toInt //frequency = 1/time
  println("FPS: " + fps)
   if(gameState == battleState) shutAnimation()
 }
 timer.start

}

