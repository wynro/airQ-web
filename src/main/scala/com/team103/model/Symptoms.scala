package com.team103.model

/**
  * @author Team 103
  * @version 0.0.1
  *
  * @construct Join Symptoms list
  *
  * @param cough
  * @param airLackness
  * @param wheezing
  * @param obstruction
  * @param itchy
  * @param airQuality
  * @param sneezing
  */
case class Symptoms(
  var cough: Int,
  var airLackness: Int,
  var wheezing: Int,
  var obstruction: Int,
  var itchy: Int,
  var airQuality: Int,
  var sneezing: Int)
