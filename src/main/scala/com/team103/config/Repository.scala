package com.team103.config

import com.team103.model.IP
import com.team103.model.Response
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema

/**
  * @author Team 103
  * @version 0.0.1
  *
  * Defines datbase Schema
  */
object Repository extends Schema {
  //When the table name doesn't match the class name, it is specified here :
  val responses = table[Response]
  val ips = table[IP]

  on(responses)(response => declare(
      response.id is (autoIncremented)
    ))
}
