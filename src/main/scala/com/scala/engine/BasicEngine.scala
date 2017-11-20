package com.scala.engine
import com.scala.camera.Camera
import com.scala.entity.Entity
import com.scala.component._
import com.scala.point.Point2D
import com.scala.color.Color

import scala.util.Random
/**
  * a simple engine that create some random entity
  */
object BasicEngine extends Engine{
  private val FPS = 60
  private var camera:Option[Camera] = None
  private val ONE_SECOND = 1000L
  private var second = ONE_SECOND
  private val _entities = randomWorld()
  private var currentFPS = 0
  override def entities: Map[Int, Entity] = this._entities

  override def start(): Unit = {
    while(true) {
      val beforeMillis = System.currentTimeMillis()
      camera foreach(_.update())
      updateColor()
      val afterMillis = System.currentTimeMillis()

      if(afterMillis-beforeMillis < period) {
        Thread.sleep(period.toInt - (afterMillis-beforeMillis))
      }
      second = second - (System.currentTimeMillis() - beforeMillis)
      currentFPS += 1
      if(second < 0) {
        println(s"FPS := $currentFPS")
        second = ONE_SECOND
        currentFPS = 0
      }
    }
  }
  private def period = 1.0 / FPS * 1000
  private def updateColor() = {

    _entities foreach(x => {
      x._2.update(ColorComponent(Color.randomColor))
    })
  }
  private[this] def randomWorld() : Map[Int,Entity] = {
    val rand = Random
    (0 until World.entities) map(x => {
      val entity = Entity(x)
      entity.addComponent(RectangleComponent,RectangleComponent.getClass.getName)
      entity.addComponent(Transform(Point2D(rand.nextInt(World.x), rand.nextInt(World.y))),
                          Transform.getClass.getName)
      entity.addComponent(ColorComponent(Color.black),ColorComponent.getClass.getName)
      entity.id -> entity
    }) toMap
  }

  override def attachCamera(camera:Camera): Unit = {
    this.camera = Some(camera)
    camera.engine = Some(this)
  }
}
