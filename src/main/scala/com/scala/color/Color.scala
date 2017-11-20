package com.scala.color

import scala.util.Random

sealed trait Color
case class RGBColor (red:Int, green:Int, blue:Int) extends  Color

object Color {
  private val rand = Random
  private val maxColor = 255
  val black = RGBColor(0,0,0)
  val white = RGBColor(255,255,255)
  val green = RGBColor(0,255,0)
  val red = RGBColor(255,0,0)
  val blue = RGBColor(255,0,0)
  def randomColor = RGBColor(rand.nextInt(maxColor),rand.nextInt(maxColor),rand.nextInt(maxColor))
}