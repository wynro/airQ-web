package com.team103.model

/**
  * Case class grouping all atmospheric conditions
  */
case class Enviroment(
  var humidity: Int = 0,
  var fire: Int = 0,
  var dustStorm: Int = 0,
  var airQuality: Int = 0,
  var smokePlumes: Int = 0,
  var ashPlumes: Int = 0)
