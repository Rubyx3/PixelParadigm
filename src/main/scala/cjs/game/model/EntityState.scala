package cjs.game.model
import cjs.game.model.EntityState.withinRange
import cjs.game.utils.Sound
import scalafx.scene.shape.Shape

//state the player is on map
case class EntityState(coordinates: Entity) {
  def newState(d: String, i: Double, entity: Entity, counter: Int): EntityState = {
    val (currentWorldX, currentWorldY) = (entity.worldX, entity.worldY)
    val (newX, newY) = d match {
      case "up" => (currentWorldX, currentWorldY - (i * entity.speed))
      case "down" => (currentWorldX, currentWorldY + (i * entity.speed))
      case "left" => (currentWorldX - (i * entity.speed), currentWorldY)
      case "right" => (currentWorldX + (i * entity.speed), currentWorldY)
      case _ => (currentWorldX, currentWorldY)
    }

    //collision detection
    val entityHitBox = entity.hitBox
    entityHitBox.x = newX - 22
    entityHitBox.y = newY

    val clear = Obstacle().obstacles.forall(x => {
      Shape.intersect(entityHitBox, x).boundsInLocal().isEmpty
    })

    //to return the newX and newY playerHitBox for Scripted event
    withinRange = NPC.npcList.forall(x => {
      !Shape.intersect(entityHitBox, x.hitBox).boundsInLocal().isEmpty
    })

    val newPosition: Entity = {
      if (!clear) {
        if(counter % 20 == 0){
          Sound().collisionSE.play()
        }
        entity(currentWorldX, currentWorldY)
      } else
        entity(newX, newY)
    }

    EntityState(newPosition)
  }
}

object EntityState{
  var withinRange: Boolean = false
}


