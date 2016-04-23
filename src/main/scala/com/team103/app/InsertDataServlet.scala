package com.team103.app

import com.team103.model._
import grizzled.slf4j.Logger
import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonAST._
import org.json4s.MappingException
import org.scalatra._
import org.scalatra.json._

/**
  * Insert data from user
  */
class InsertDataServlet extends AircheckStack with JacksonJsonSupport {

  def logger = Logger[InsertDataServlet]
  protected implicit val jsonFormats: Formats = DefaultFormats

  post("/enviroment") {
    try {
      val coordinates = extractCoords(parsedBody)
      val enviroment = parsedBody.extract[Enviroment]//Extract symptoms from data body
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
