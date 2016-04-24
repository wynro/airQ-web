package com.team103.model

import java.sql.Timestamp

/**
  * @author Team 103
  * @version 0.0.1
  *
  * @constructor Represent IP model
  * @param ip ip direcction
  * @param timeStamp creation date
  */
  case class IP(
  var ip: String,
  var timeStamp: Timestamp)
