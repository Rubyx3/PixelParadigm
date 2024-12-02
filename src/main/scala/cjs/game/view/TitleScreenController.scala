package cjs.game.view

import cjs.game.GameMain
import cjs.game.utils.Sound
import scalafxml.core.macros.sfxml

@sfxml
class TitleScreenController {
  Sound().playIndefinite(Sound().titleScreenTheme, 0.8)

  def getStarted(): Unit = {
    Sound().aButtonSE.play()
    GameMain.displayGameCanvas()
    Sound().stop(Sound().titleScreenTheme)
  }
}
