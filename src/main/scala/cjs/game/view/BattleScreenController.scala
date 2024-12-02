package cjs.game.view

import cjs.game.GameMain
import cjs.game.GameMain.{battleState, gameState}
import cjs.game.model.TileSize.tileSize
import cjs.game.model.{Pokemon, PokemonMove}
import cjs.game.utils.{Controls, Sound, UI}
import scalafx.animation.{AnimationTimer, FadeTransition}
import scalafx.collections.ObservableBuffer
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.Image
import scalafx.scene.layout.AnchorPane
import scalafx.util.Duration
import scalafxml.core.macros.sfxml


@sfxml
class BattleScreenController(val battleCanvas: Canvas,
                             val move1: Button,
                             val move2: Button,
                             val move3: Button,
                             val move4: Button,
                             val fightButton: Button,
                             val runButton: Button,
                             val nextButton: Button,
                             val backButton: Button,
                             val battleMessage: Label,
                             val movePane: AnchorPane,
                             val actionPane: AnchorPane,
                             val transitionPane: AnchorPane
                             ) {
  private val gc = battleCanvas.graphicsContext2D
  private val battleBackground = UI.loadImage("/images/BattleBackground/LabBattleBackground.png")
  private val opponentX: Double = 520
  private val opponentY: Double = 180
  private val opponentSize: Double = tileSize * 2
  private val myX: Double = 100
  private val myY: Double = 220
  private val mySize: Double = tileSize * 4
  private var spriteCounter: Int = 0
  private var inTurn: Boolean = false
  battleMessage.wrapText = true
  battleMessage.visible = false
  nextButton.visible = false
  backButton.visible = false
  movePane.visible = false
  private var opponent = Pokemon.fennekin
  private val opponentSpriteList = Pokemon.fennekin.pokemonFrontSprites
  private var myPokemon = Pokemon.zorua
  private val myPokemonSpriteList = Pokemon.zorua.pokemonBackSprites
  private var enemyTurn: Boolean = false // placeholder

  Controls.applyMouse(battleCanvas)

  private val fadeTransition = new FadeTransition(new Duration(2000), transitionPane){
    fromValue = 0
    toValue = 1
  }
  fadeTransition.playFromStart()

  //binds moves to current pokemonMoves
  private def checkMoves(pokemonMoves: Array[PokemonMove]): Unit = {
    pokemonMoves.length match {
      case 1 =>
        move1.visible = true
        move1.text = pokemonMoves(0).name
        move2.visible = false
        move3.visible = false
        move4.visible = false
      case 2 =>
        move1.visible = true
        move1.text = pokemonMoves(0).name
        move2.visible = true
        move2.text = pokemonMoves(1).name
        move3.visible = false
        move4.visible = false
      case 3 =>
        move1.visible = true
        move1.text = pokemonMoves(0).name
        move2.visible = true
        move2.text = pokemonMoves(1).name
        move3.visible = true
        move3.text = pokemonMoves(2).name
        move4.visible = false
      case 4 =>
        move1.visible = true
        move1.text = pokemonMoves(0).name
        move2.visible = true
        move1.text = pokemonMoves(1).name
        move3.visible = true
        move1.text = pokemonMoves(2).name
        move4.visible = true
        move1.text = pokemonMoves(3).name

    }
  }

  //animate moveSprites and stop it using a new thread
  private def playMove(pokemon: Pokemon, i: Int, party: String): Unit = {
    val moveSpriteList = pokemon.pokemonMoveset(i).moveSprites
    val numFrames = moveSpriteList.length
    var frameCounter = 0
    var frame = 0
    val animation = AnimationTimer(t => {
      nextButton.visible = false
      if (frame >= numFrames) {
        inTurn = false
        if (!inTurn && !actionPane.visible() && !movePane.visible()) { //holy crap I can't believe I have to catch the condition here too, I'm scared of Threads now.
          nextButton.visible = true
        }
        else {nextButton.visible = false}
      }
      if(actionPane.visible()){
        battleMessage.visible = false
      }
      if (frame < numFrames) {
        if(party == "my"){
          gc.drawImage(moveSpriteList(frame), opponentX, opponentY, opponentSize, opponentSize)
          displayBattleMessage(pokemon.name + " used " + pokemon.pokemonMoveset(i).name)
        }
        if (party == "opponent") {
          gc.drawImage(moveSpriteList(frame), myX, myY, mySize, mySize)
          displayBattleMessage(pokemon.name + " used " + pokemon.pokemonMoveset(i).name)
        }
        if (frameCounter % 10 == 0) {frame += 1}
        frameCounter += 1
      }
    })
    animation.start()

    val stopTask = new Thread{
      override def run(): Unit = {
        Thread.sleep(1000)
        animation.stop()
      }
    }
  }

  private def paintBackground(img: Image): Unit = {
    gc.drawImage(img, 0, 0, battleCanvas.width(), battleCanvas.height())
  }

  private def displayBattleMessage(message: String): Unit = {
    battleMessage.text = message
    battleMessage.visible = true
  }

  def fight(): Unit = {
    Sound().aButtonSE.play()
    movePane.visible = true
    actionPane.visible = false
    nextButton.visible = false
    backButton.visible = false
    battleMessage.visible = false
  }

  def run(): Unit = {
    Sound().aButtonSE.play()
//    displayBattleMessage("You can't run away from a Trainer battle!")
//    nextButton.visible = true
//    movePane.visible = false
//    actionPane.visible = false
//    backButton.visible = false
    GameMain.returnToOverworld()
  }

  def back(): Unit = {
    Sound().aButtonSE.play()
    movePane.visible = false
    nextButton.visible = false
    backButton.visible = false
    actionPane.visible = true
    battleMessage.visible = false
  }

  def move1Animate(): Unit = {
    Sound().aButtonSE.play()
    inTurn = true
    enemyTurn = true
    playMove(myPokemon, 0, "my")
  }

  def move2Animate(): Unit = {
    Sound().aButtonSE.play()
    inTurn = true
    enemyTurn = true
    playMove(myPokemon, 1, "my")
  }

  def move3Animate(): Unit = {
    Sound().aButtonSE.play()
    inTurn = true
    enemyTurn = true
    playMove(myPokemon, 2, "my")
  }

  def move4Animate(): Unit = {
    Sound().aButtonSE.play()
    inTurn = true
    enemyTurn = true
    playMove(myPokemon, 3, "my")
  }

  private def animateSprite(pokemonSpriteList: ObservableBuffer[Image], party: String): Unit = {
    party match {
      case "opponent" => gc.drawImage(pokemonSpriteList(spriteCounter), opponentX, opponentY, opponentSize, opponentSize)
      case "my" => gc.drawImage(pokemonSpriteList(spriteCounter), myX, myY, mySize, mySize)
    }
  }


  private var frameCounter: Int = 0
  val battle: AnimationTimer = AnimationTimer { time =>
    battleCanvas.requestFocus()
    UI.cleanGameCanvas(gc, battleCanvas)
    paintBackground(battleBackground)
    //fennekin
    animateSprite(opponentSpriteList, "opponent")
    //zorua doesnt have multiple sprites
    gc.drawImage(myPokemonSpriteList.head, myX, myY, mySize, mySize)
    checkMoves(myPokemon.pokemonMoveset)
    UI.drawBattleBox(gc,battleCanvas)
    if (movePane.visible() && !inTurn) {backButton.visible = true}
    if (actionPane.visible() || nextButton.visible()) {backButton.visible = false}
    if (actionPane.visible() || movePane.visible()) {nextButton.visible = false}
    if (inTurn) {
      backButton.visible = false
      nextButton.visible = false
      movePane.visible = false
      actionPane.visible = false
    }
    if (!inTurn && !actionPane.visible() && !movePane.visible()) {
      nextButton.visible = true
    }
    if (actionPane.visible()) {battleMessage.visible = false}
    nextButton.onAction = _ => {
      Sound().aButtonSE.play()
      nextButton.visible = false
      battleMessage.visible = false
      actionPane.visible = true
      backButton.visible = false
      movePane.visible = false
      if(enemyTurn) {
        inTurn = true
        playMove(opponent, 0 , "opponent")
        enemyTurn = false
      }
    }

    frameCounter += 1
    if (frameCounter % 10 == 0) {
      spriteCounter += 1
      if(spriteCounter == opponentSpriteList.size()) spriteCounter = 0
    }
    if(gameState != battleState) battle.stop()
  }
  battle.start()

}
