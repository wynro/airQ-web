package com.team103.model

import java.util.Date
import java.sql.Timestamp
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column

/**
  * @author Team 103
  * @version 0.0.1
  *
  *  Represent a user response
  * @param id identificator
  * @param isEvent indicate if is an event
  * @param timeStamp creation date
  * @param locX coordinate X
  * @param locY coordinate Y
  * @param value value of quality
  */
class Response(
  val id: Long,
  val isEvent: Boolean,
  val timeStamp: Timestamp,
  val locX: Double,
  val locY: Double,
  val value: Int) extends KeyedEntity[Long] {
	  def this() = this(0,false,null,0,0,0)
}
