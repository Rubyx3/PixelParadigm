package cjs.game.model

import cjs.game.utils.UI
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.Image

import scala.collection.mutable

class PokemonMove(val name: String, val basePower: Int, val powerPoint: Int, val isAttack: Boolean) {
  private def loadSprite(filePath: String): Image = {
    UI.loadImage(filePath)
  }

  val moveSprites = new ObservableBuffer[Image]()
  //cant't be private else in companion object cannot access
  val scratch0 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch0.png")
  val scratch1 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch1.png")
  val scratch2 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch2.png")
  val scratch3 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch3.png")
  val scratch4 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch4.png")
  val scratch5 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch5.png")
  val scratch6 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch6.png")
  val scratch7 = loadSprite("/images/PokemonMoveSprites/Scratch/scratch7.png")

  val ember0 = loadSprite("/images/PokemonMoveSprites/Ember/fire0.png")
  val ember1 = loadSprite("/images/PokemonMoveSprites/Ember/fire1.png")
  val ember2 = loadSprite("/images/PokemonMoveSprites/Ember/fire2.png")
  val ember3 = loadSprite("/images/PokemonMoveSprites/Ember/fire3.png")
  val ember4 = loadSprite("/images/PokemonMoveSprites/Ember/fire4.png")
  val ember5 = loadSprite("/images/PokemonMoveSprites/Ember/fire5.png")
  val ember6 = loadSprite("/images/PokemonMoveSprites/Ember/fire6.png")
  val ember7 = loadSprite("/images/PokemonMoveSprites/Ember/fire7.png")
  val ember8 = loadSprite("/images/PokemonMoveSprites/Ember/fire8.png")
  val ember9 = loadSprite("/images/PokemonMoveSprites/Ember/fire9.png")
  val ember10 = loadSprite("/images/PokemonMoveSprites/Ember/fire10.png")
  val ember11 = loadSprite("/images/PokemonMoveSprites/Ember/fire11.png")
  val ember12 = loadSprite("/images/PokemonMoveSprites/Ember/fire12.png")
  val ember13 = loadSprite("/images/PokemonMoveSprites/Ember/fire13.png")
  val ember14 = loadSprite("/images/PokemonMoveSprites/Ember/fire14.png")
  val ember15 = loadSprite("/images/PokemonMoveSprites/Ember/fire15.png")
  val ember16 = loadSprite("/images/PokemonMoveSprites/Ember/fire16.png")
  val ember17 = loadSprite("/images/PokemonMoveSprites/Ember/fire17.png")
  val ember18 = loadSprite("/images/PokemonMoveSprites/Ember/fire18.png")
  val ember19 = loadSprite("/images/PokemonMoveSprites/Ember/fire19.png")
  val ember20 = loadSprite("/images/PokemonMoveSprites/Ember/fire20.png")
  val ember21 = loadSprite("/images/PokemonMoveSprites/Ember/fire21.png")
  val ember22 = loadSprite("/images/PokemonMoveSprites/Ember/fire22.png")
  val ember23 = loadSprite("/images/PokemonMoveSprites/Ember/fire23.png")
  val ember24 = loadSprite("/images/PokemonMoveSprites/Ember/fire24.png")
  val ember25 = loadSprite("/images/PokemonMoveSprites/Ember/fire25.png")
  val ember26 = loadSprite("/images/PokemonMoveSprites/Ember/fire26.png")
  val ember27 = loadSprite("/images/PokemonMoveSprites/Ember/fire27.png")
  val ember28 = loadSprite("/images/PokemonMoveSprites/Ember/fire28.png")
  val ember29 = loadSprite("/images/PokemonMoveSprites/Ember/fire29.png")
  val ember30 = loadSprite("/images/PokemonMoveSprites/Ember/fire30.png")
  val ember31 = loadSprite("/images/PokemonMoveSprites/Ember/fire31.png")


}

object PokemonMove {
  val ember: PokemonMove = new PokemonMove("Ember",40,25, true) {
    moveSprites ++= mutable.Buffer(ember0, ember1, ember2, ember3, ember4, ember5, ember6, ember7, ember8, ember9, ember10, ember11, ember12, ember13, ember14, ember15, ember16, ember17, ember18, ember19, ember20, ember21, ember22, ember23, ember24, ember25, ember26, ember27, ember28, ember29, ember30, ember31)
  }
  val scratch: PokemonMove = new PokemonMove("Scratch",40, 35, true) {
    moveSprites ++= mutable.Buffer(scratch0,scratch1,scratch2,scratch3,scratch4,scratch5,scratch6,scratch7)
  }
}

