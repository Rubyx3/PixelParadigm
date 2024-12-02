package cjs.game.model

import cjs.game.utils.UI
import scalafx.collections.ObservableBuffer
import scalafx.scene.image.Image
import scala.collection.mutable

class Pokemon(val name: String,
              val baseHp: Int,
              val baseAttack: Int,
              val baseDefense: Int,
              val baseSPAttack: Int,
              val baseSPDefense: Int,
              val baseSpeed: Int,
              var level: Int) {
  var hp: Int = baseHp * level
  var attack: Int = baseAttack * level
  var defense: Int = baseDefense * level
  var spAttack: Int = baseSPAttack * level
  var spDefense: Int = baseSPDefense * level
  var speed: Int = baseSpeed * level
  var pokemonMoveset: Array[PokemonMove] = Array.ofDim[PokemonMove](4)
  val pokemonFrontSprites = new ObservableBuffer[Image]()
  val pokemonBackSprites = new ObservableBuffer[Image]()

  //taken from Entity Class, I could put it in GameMain and call from there but nvm
  private def loadPokemonImage(filePath: String): Image = {
    UI.loadImage(filePath)
  }

  //sprites
  //can't make private or objects can't access
  val fennekin0: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin0.png")
  val fennekin1: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin1.png")
  val fennekin2: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin2.png")
  val fennekin3: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin3.png")
  val fennekin4: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin4.png")
  val fennekin5: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin5.png")
  val fennekin6: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin6.png")
  val fennekin7: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin7.png")
  val fennekin8: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin8.png")
  val fennekin9: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin9.png")
  val fennekin10: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin10.png")
  val fennekin11: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin11.png")
  val fennekin12: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin12.png")
  val fennekin13: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin13.png")
  val fennekin14: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin14.png")
  val fennekin15: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin15.png")
  val fennekin16: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin16.png")
  val fennekin17: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin17.png")
  val fennekin18: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin18.png")
  val fennekin19: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin19.png")
  val fennekin20: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin20.png")
  val fennekin21: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin21.png")
  val fennekin22: Image = loadPokemonImage("/images/PokemonSprites/Fennekin/fennekin22.png")
  val zorua0 = loadPokemonImage("/images/PokemonSprites/Zorua/shinyZorua.png")

}

object Pokemon {
  val fennekin: Pokemon = new Pokemon("Fennekin", 40, 45, 40, 62, 60, 60, 5 ) {
    pokemonMoveset = Array(PokemonMove.ember)
    pokemonFrontSprites ++= mutable.Buffer(fennekin0, fennekin1, fennekin2, fennekin3, fennekin4, fennekin5, fennekin6, fennekin7, fennekin8, fennekin9, fennekin10, fennekin11, fennekin12, fennekin13, fennekin14, fennekin15, fennekin16, fennekin17, fennekin18, fennekin19, fennekin20, fennekin21, fennekin22)
  }

  val zorua: Pokemon = new Pokemon("Zorua", 40, 65, 40, 80, 40, 65, 5) {
    pokemonMoveset = Array(PokemonMove.scratch)
    pokemonBackSprites ++= mutable.Buffer(zorua0)
  }
}
