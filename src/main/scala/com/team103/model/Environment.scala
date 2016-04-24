package com.team103.model

/**
  * Case class grouping all atmospheric conditions
  */
case class Environment(
  var fire: Int = 0,
  var dustStorm: Int = 0,
  var tornado: Int = 0,
  var smokePlumes: Int = 0,
  var earthquake: Int = 0,
  var volcano: Int = 0)
