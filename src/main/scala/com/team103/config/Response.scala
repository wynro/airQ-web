package com.team103.config

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.annotations.Column
import java.util.Date
import java.sql.Timestamp

/**
  * If isEvent == 1, then is event and value are 1,2,3...
  * If isEvent == 0, then is atmospheric and value are 0-10
  */
class Response(
  val id: Long,
  val isEvent: Boolean,
  val timeStamp: Timestamp,
  val locX: Double,
  val locY: Double,
  val value: Int) {
	  def this() = this(0,false,null,0,0,0)
}
