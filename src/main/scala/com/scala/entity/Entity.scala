package com.scala.entity
import com.scala.component.Component

/**
  * Basic entity of the system
  */
trait Entity {
  val id : Int
  def component : Map[String,Component]

  def addComponent (comp: Component, name: String)

  def update(event:Any) = component.foreach(_._2.update(event))
}
object Entity {
  def apply(_id: Int): Entity = new Entity {
    override val id: Int = _id
    var _component = Map[String,Component]()

    override def component: Map[String, Component] = this._component

    override def addComponent(comp: Component, name: String): Unit = {
      this._component += name -> comp
    }
  }
}