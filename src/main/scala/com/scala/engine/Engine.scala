package com.scala.engine
import com.scala.entity.Entity
import com.scala.camera._
/**
  * the interface of the engine
  */
trait Engine {
  def attachCamera(camera : Camera)
  def entities : Map[Int,Entity]
  def start()
}
