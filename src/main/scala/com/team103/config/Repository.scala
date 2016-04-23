package com.team103.config

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema

object Repository extends Schema {
  //When the table name doesn't match the class name, it is specified here :
  val responses = table[Response]("RESPONSES")

  on(responses)(response => declare(
      response.id is (autoIncremented)
    ))
}
