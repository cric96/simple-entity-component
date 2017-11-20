package com.scala.point

sealed trait Point
case class Point2D(x:Int, y:Int) extends Point
case class Point3D(x:Int, y:Int, z:Int) extends Point