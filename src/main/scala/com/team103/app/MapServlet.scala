package com.team103.app

import com.team103.config._
import com.team103.database.postGisDB.DatabaseSessionSupport
import com.team103.model._
import com.team103.database.postGisDB.IPDAO
import grizzled.slf4j.Logger
import java.util.Date
import java.sql.Timestamp
import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonAST._
import org.json4s.MappingException
import org.scalatra._
import org.scalatra.json._
import org.squeryl.PrimitiveTypeMode._


/**
  * @author Team 103
  * @version 0.0.1
  *
  * Controller that ensure a user has writed the form to see the map
  */
class MapServlet extends AircheckStack with JacksonJsonSupport with DatabaseSessionSupport{

  def logger = Logger[MapServlet]
  protected implicit val jsonFormats: Formats = DefaultFormats
  val INTERNAL_ERROR_MESSAGE = "Oops so awkward, something went wrong"

  post("/*"){
    val headers = Map[String,String]("Content-Type" -> "text/plain")
    try {
      val ip = parsedBody.extract[String]
      val found = IPDAO.searchByID(ip).toList
      //IP has been found
      if (!found.isEmpty) {
        logger.info("IP address found!")
        Ok()
      }
      // IP not found
      else {
       logger.info("IP address not found!")
       BadRequest()
      }
    }catch {
      case e: MappingException => {//Exception while extracting body JSON AST
        logger.info("Wrong Data format")
        BadRequest("Wrong data format",headers)
      }
      case e: Exception => {//Anything else
        logger.info("Internal Error")
        logger.info(e)
        InternalServerError(INTERNAL_ERROR_MESSAGE,headers)
      }
    }
  }
}
