package com.scala.main

import com.scala.camera.{ SwingCamera}
import com.scala.engine.BasicEngine

object Main {
  def main(args: Array[String]) = {
    val camera = SwingCamera

    val engine = BasicEngine
    engine.attachCamera(camera)
    engine.start()
  }
}
