package cjs.game.view

import cjs.game.GameMain.stage
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController {
  def handleClose(): Unit = {
    System.exit(0)
  }

  def showInstructions(): Unit = {
    new Alert(AlertType.Information) {
      initOwner(stage)
      title = "Game Instructions"
      headerText = "InGame Controls"
      contentText = "For Open-world travelling: \nW => UP direction \nS => DOWN direction \nA => LEFT direction \nD => RIGHT direction \nJ => A button (OK/Interact) \nK => B Button (Back/Exit) \n\nFor Battle: \nMouse Only"
    }.showAndWait()
  }



}
