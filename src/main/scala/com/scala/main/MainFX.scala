package com.scala.main

import javafx.scene.paint.Paint

import com.scala.camera.Camera
import com.scala.color.RGBColor
import com.scala.component.{ColorComponent, RectangleComponent, Transform}
import com.scala.engine.{BasicEngine, World}
import com.scala.point.Point2D
import com.sun.javafx.tk.Toolkit.Task
import com.sun.prism.paint.Paint.Type

import scalafx.application
import scalafx.application.JFXApp
import scalafx.scene.{Group, Scene}
import scalafx.scene.canvas.Canvas
//a simple version of javafx
object MainFX extends JFXApp with Camera{
  val canvas = new Canvas(World.x,World.y)

  stage = new JFXApp.PrimaryStage() {
    title.value = "Hello Stage"
    width = World.x
    height = World.y
    scene = new Scene() {
      content = new Group {
        content = canvas
      }
    }
  }
  val en = BasicEngine
  new Thread(() => {
    en.attachCamera(this)
    en.start()
  }).start()
  var isUpdate = false
  def updateScreen(): Unit = {

    engine foreach(x => {
      x.entities foreach(y => {
        val position = y._2.component(Transform.getClass.getName).asInstanceOf[Transform]
        val shape = y._2.component(RectangleComponent.getClass.getName).asInstanceOf[RectangleComponent.type].rectangle
        val color = y._2.component(ColorComponent.getClass.getName).asInstanceOf[ColorComponent]
        color.color match {
          case e : RGBColor => canvas.graphicsContext2D.setStroke(javafx.scene.paint.Color.rgb(e.red,e.green,e.blue))
          case _ =>
        }
        position.point match {
          case e : Point2D => {
            canvas.graphicsContext2D.strokeRect(e.x,e.y,shape.base,shape.height)
          }
          case _ =>
        }
      })
    })
    isUpdate = true
  }
  override def update(): Unit = {

    application.Platform.runLater(() => {
      updateScreen()
    })
    //synchronization
    while(!isUpdate){
      Thread.sleep(1)
    }
    isUpdate = false
  }
}
