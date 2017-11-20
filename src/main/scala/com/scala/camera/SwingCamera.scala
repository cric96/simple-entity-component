package com.scala.camera
import java.awt.{Color, Dimension}

import com.scala.color.RGBColor
import com.scala.component.{ColorComponent, RectangleComponent, Transform}
import com.scala.engine.{Engine, World}
import com.scala.point.Point2D

import scala.swing.{Button, FlowPanel, Graphics2D, MainFrame}

object SwingCamera extends Camera {
  val main = new FlowPanel(){
    override def paint(g: Graphics2D): Unit = {
      super.paint(g)
      engine foreach(x => {
        x.entities foreach(y => {
          val position = y._2.component(Transform.getClass.getName).asInstanceOf[Transform]
          val shape = y._2.component(RectangleComponent.getClass.getName).asInstanceOf[RectangleComponent.type].rectangle
          val color = y._2.component(ColorComponent.getClass.getName).asInstanceOf[ColorComponent]
          color.color match {
            case e : RGBColor => g.setColor(new Color(e.red,e.green,e.blue))
            case _ =>
          }
          position.point match {
            case e : Point2D => {
              g.fillRect(e.y,e.x,shape.base.toInt,shape.height.toInt)

            }
            case _ =>
          }
        })
      })
    }
  }
  val frame = new MainFrame{
    title = "simple view"
    contents = main
    size = new Dimension(World.x,World.y)
  }
  frame.visible = true

  override def update(): Unit = {
    main.repaint()
  }
}
