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
  * Insert data from user
  */
class MapServlet extends AircheckStack with JacksonJsonSupport with DatabaseSessionSupport{

  def logger = Logger[MapServlet]
  protected implicit val jsonFormats: Formats = DefaultFormats
  val INTERNAL_ERROR_MESSAGE = "Oops so awkward, something went wrong"

  post("/*"){
    val headers = Map[String,String]("Content-Type" -> "text/plain")
    try {
      val ip = parsedBody.extract[String]
      val found = IPDAO.searchByID(ip)
      // IP found
      if (found != null) {
        Ok()
      }
      // IP not found
      else {
       BadRequest()
      }
    }catch {
      case e: MappingException => {//Exception while extracting body JSON AST
        BadRequest("Wrong data format",headers)
      }
      case e: Exception => {//Anything else
        InternalServerError(INTERNAL_ERROR_MESSAGE,headers)
      }
    }
  }
}
