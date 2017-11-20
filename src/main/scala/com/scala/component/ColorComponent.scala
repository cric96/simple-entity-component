package com.scala.component
import com.scala.color.Color
final case class ColorComponent(var color:Color) extends Component {
  override def update(ref: Any): Unit = {
    ref match {
      case color : ColorComponent => this.color = color.color
      case _ =>
    }
  }
}
