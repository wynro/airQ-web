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
class DefaultServlet extends AircheckStack with JacksonJsonSupport with DatabaseSessionSupport{

  def logger = Logger[DefaultServlet]
  protected implicit val jsonFormats: Formats = DefaultFormats

  post("/*"){
    logger.info("Something came in the mail today from : " + request.getRemoteHost)
  }
}
