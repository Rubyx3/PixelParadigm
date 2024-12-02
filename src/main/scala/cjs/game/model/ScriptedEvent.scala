package cjs.game.model

import cjs.game.GameMain.{eventState, gameState}
import cjs.game.model.NPC._
import cjs.game.model.Player.player
import cjs.game.utils.Controls.aPressed
import cjs.game.utils.UI
import scalafx.scene.canvas.{Canvas, GraphicsContext}

object ScriptedEvent {
  var battleEncounter : Boolean = false
  //for turning the npc to face player
  private def triggerEvent(): Unit = {
    //obtains boolean condition from EntityState class
    val withinRange = EntityState.withinRange
    if(gameState == eventState){
      for (npc <- npcList) {
        if (withinRange && aPressed) {
          player.direction match {
            case "up" => npc.direction = "down"
            case "down" => npc.direction = "up"
            case "left" => npc.direction = "right"
            case "right" => npc.direction = "left"
          }
        }
      }
    }
  }

  //all scripted events below here
  def redEncounter(gc: GraphicsContext, canvas: Canvas): Unit = {
    ScriptedEvent.triggerEvent()
    red.speak(red)
    UI.drawDialogue(gc, canvas, gameState)
    if (gameState == eventState) battleEncounter = true
  }
}
