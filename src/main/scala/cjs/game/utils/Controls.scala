package cjs.game.utils

import cjs.game.GameMain._
import cjs.game.model.ScriptedEvent.battleEncounter
import cjs.game.model.{EntityState, Player}
import scalafx.Includes._
import scalafx.beans.property.BooleanProperty
import scalafx.scene.Node
import scalafx.scene.input.{KeyCode, KeyEvent, MouseEvent}

object Controls {
  //keys
  var upPressed = false
  var downPressed = false
  var leftPressed = false
  var rightPressed = false
  var aPressed = false
  var bPressed = false

  def applyKeypad(node: Node, player: Player): Unit = {
    node.onKeyPressed = (ke: KeyEvent) => {
      ke.code match {
        case KeyCode.W =>
          if (playState == gameState) {
            upPressed = true
            player.direction = "up"
          }
        case KeyCode.S =>
          if (playState == gameState) {
            downPressed = true
            player.direction = "down"
          }
        case KeyCode.A =>
          if (playState == gameState) {
            leftPressed = true
            player.direction = "left"
          }
        case KeyCode.D =>
          if (playState == gameState) {
            rightPressed = true
            player.direction = "right"
          }
        case KeyCode.J =>
          aPressed = true
          if (EntityState.withinRange) {
            Sound().aButtonSE.play()
            if (gameState == playState){
              changeGameState(eventState)
            }
            else if (gameState == eventState){
              if (battleEncounter) {
                battle()
              }
              else changeGameState(playState)
            }
          }
        case KeyCode.K => bPressed = true
        case _ =>
      }
    }
    node.onKeyReleased = (ke: KeyEvent) => {
      ke.code match {
        case KeyCode.W => upPressed = false
        case KeyCode.S => downPressed = false
        case KeyCode.A => leftPressed = false
        case KeyCode.D => rightPressed = false
        case KeyCode.J => aPressed = false
        case KeyCode.K => bPressed = false
        case _ =>
      }
    }
  }

  //enable clicking to request focus
  def applyMouse(node: Node): Unit = {
    node.onMouseClicked = (me: MouseEvent) => {
      node.requestFocus()
    }
  }
}
