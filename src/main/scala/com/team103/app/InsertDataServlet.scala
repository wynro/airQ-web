package com.team103.app

import com.team103.model._
import com.team103.database.DatabaseSessionSupport
import grizzled.slf4j.Logger
import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonAST._
import org.json4s.MappingException
import org.scalatra._
import org.scalatra.json._
import java.util.Date
import java.sql.Timestamp
import com.team103.config._
import org.squeryl.PrimitiveTypeMode._


/**
  * Insert data from user
  */
class InsertDataServlet extends AircheckStack with JacksonJsonSupport with DatabaseSessionSupport{

  def logger = Logger[InsertDataServlet]
  protected implicit val jsonFormats: Formats = DefaultFormats

  get("/*"){
    transaction{
      Repository.responses.insert(new Response(1,false,new Timestamp(System.currentTimeMillis()),0.458,7.523,10))
    }
  }

  post("/environment") {
    try {
      val coordinates = extractCoords(parsedBody)
      val environment = parsedBody.extract[Environment]//Extract symptoms from data body
    } catch {
      case e:Exception => {
        logger.debug("Exception while parsing the body")
      }
    }
  }

  post("/symptoms") {
    try {
      val coordinates = extractCoords(parsedBody)
      val symptoms = parsedBody.extract[Symptoms]//Extract symptoms from data body
    } catch {
      case e:Exception => {
        logger.debug("Exception while parsing the body")
      }
    }
  }

  /**
    * Extract the coordinates from the request
    */
  private def extractCoords(implicit parsedBody:JValue):(Double,Double) = {
    val coordinates = parsedBody \ "coords"
    ((coordinates \ "latitude").extract[Double],(coordinates \ "longitude").extract[Double])
  }
}
