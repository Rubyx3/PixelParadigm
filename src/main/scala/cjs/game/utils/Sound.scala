package cjs.game.utils

import scalafx.scene.media.AudioClip
import scalafx.scene.media.AudioClip.Indefinite

import scala.util.{Failure, Success, Try}

class Sound {
  def load(s: String): AudioClip = {
    val fileName = Try(getClass.getResource(s).toURI.toString) //idk why this thing needs me to convert to URI and back to String to not throw errors
    match {
      case Success(x) => Some(x)
      case Failure(exception) => None
    }
    val audio = new AudioClip(fileName.get)
    audio
  }

  val titleScreenTheme: AudioClip = load("/soundtrack/TitleScreenTheme.wav")
  val audio1: AudioClip = load("/soundtrack/LittlerootTownTheme.wav")
  val cyrusTheme: AudioClip = load("/soundtrack/CyrusTheme.wav")
  cyrusTheme.priority = 1
  audio1.priority = 1 //necessary otherwise repeatedly pressing aButton will overwrite this
  val collisionSE: AudioClip = load("/soundtrack/Thud.wav")
  val aButtonSE: AudioClip = load("/soundtrack/AButton.wav")

  def playIndefinite(audio: AudioClip, volume: Double): Unit = {
    audio.cycleCount = Indefinite
    audio.play(volume)
  }

  def stop(audio: AudioClip): Unit = {
    audio.stop()
  }
}

object Sound{
  def apply() = new Sound
}
