package com.scala.component

import com.scala.point.Point
final case class Transform(var point :Point) extends Component {
  override def update(ref:Any): Unit = {
    ref match {
      case t @ Transform(_) => this.point = t.point
      case _ =>
    }
  }
}
