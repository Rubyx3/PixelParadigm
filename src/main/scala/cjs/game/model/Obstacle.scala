package cjs.game.model

import scalafx.scene.shape.Rectangle

class Obstacle() extends Rectangle{
 //npc hit-boxes
 private val redHitBox = NPC.red.hitBox
  //counter desk
   private val obs1 = new Rectangle(){
    width = 315
    height = 104
    x = 46
    y = 65
   }
  //bookshelves and potted plant
   private val obs2 = new Rectangle(){
    width = 355
    height = 111
    x = 318
    y = 0
   }
  //top right horizontal wall
   private val obs3 = new Rectangle(){
    width = 49
    height = 83
    x = 673
    y = 0
   }
  //top right diagonal wall
   private val obs4 = new Rectangle() {
    width = 49
    height = 89
    x = 722
    y = 18
   }
  //right wall
   private val obs5 = new Rectangle() {
    width = 1
    height = 422
    x = 770
    y = 111
   }
  //top left diagonal wall
   private val obs6 = new Rectangle() {
    width = 49
    height = 95
    x = -3
    y = 18
   }
  //left wall
   private val obs7 = new Rectangle() {
    width = 1
    height = 422
    x = -5
    y = 112
   }
  //bottom left diagonal wall
   private val obs8 = new Rectangle() {
    width = 40
    height = 41
    x = -3
    y = 520
   }
  //bottom right diagonal wall
   private val obs9 = new Rectangle() {
    width = 49
    height = 41
    x = 730
    y = 515
   }
  //bottom wall
   private val obs10 = new Rectangle() {
    width = 746
    height = 1
    x = 46
    y = 551
   }

   val obstacles: List[Rectangle] = List[Rectangle](redHitBox,obs1,obs2,obs3,obs4,obs5,obs6,obs7,obs8,obs9,obs10)
}

//companion object to have apply method
object Obstacle {
  def apply() = new Obstacle
}
