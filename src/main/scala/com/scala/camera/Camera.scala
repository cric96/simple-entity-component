package com.scala.camera
import com.scala.engine.Engine

/**
  * the interface of a camera that display entity
  */
trait Camera {
  var engine: Option[Engine] = None
  def update()
}
