package com.scala.camera
import com.scala.engine.Engine
import com.scala.component.ColorComponent
object ConsoleCamera extends Camera{
  override def update(): Unit = {
    engine foreach(x => {
      x.entities foreach(y => {
        val com  = y._2.component(ColorComponent.getClass().getName)
        println(com.asInstanceOf[ColorComponent].color)
      })
    })
  }
}
