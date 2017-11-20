package com.scala.shape

sealed trait Shape

case class Rectangle(base:Float, height: Float) extends Shape
