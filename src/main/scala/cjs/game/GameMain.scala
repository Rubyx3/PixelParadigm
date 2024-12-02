package cjs.game

import cjs.game.model.TileSize.{screenHeight, screenWidth}
import cjs.game.utils.Sound
import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.image.Image
import scalafx.scene.paint.Color
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object GameMain extends JFXApp{
  val playState: Int = 1
  val eventState: Int = 2
  val battleState: Int = 3
  var gameState: Int = playState

  def changeGameState(desiredState: Int): Unit = {
    GameMain.gameState = desiredState
  }

  def battle(): Unit = {
    changeGameState(battleState)
    Sound().stop(Sound().audio1)
    displayBattle()
    Sound().playIndefinite(Sound().cyrusTheme, 0.7)
  }

  def returnToOverworld(): Unit =  {
    changeGameState(playState)
    Sound().stop(Sound().cyrusTheme)
    displayGameCanvas()
    Sound().playIndefinite(Sound().audio1, 0.7)
  }

  // transform path of RootLayout.fxml to URI for resource location.
  private val rootResource = getClass.getResource("view/RootLayout.fxml")
  // initialize the loader object.
  private val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load()
  // retrieve the root component BorderPane from the FXML
  private val roots = loader.getRoot[jfxs.layout.BorderPane]

  // initialize stage
  stage = new PrimaryStage {
    resizable = false
    title = "Pixel Paradigm"
    icons += new Image("/images/IconLogo/class-mage-alternative-svgrepo-com.png")
    scene = new Scene(screenWidth,screenHeight) {
      root = roots
    }
  }

  private def displayTitleScreen(): Unit = {
    val resource = getClass.getResource("view/TitleScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  displayTitleScreen()

  def displayGameCanvas(): Unit = {
    val resource = getClass.getResource("view/GameOverworldScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  private def displayBattle(): Unit = {
    val resource = getClass.getResource("view/BattleScreen.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.StackPane]
    this.roots.setCenter(roots)
  }

}


