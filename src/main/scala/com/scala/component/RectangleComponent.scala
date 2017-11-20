package com.scala.component

import com.scala.shape.Rectangle
final object RectangleComponent extends Component {
  val rectangle = Rectangle(10,10)
  //do nothing
  override def update(ref: Any): Unit = {}

}
