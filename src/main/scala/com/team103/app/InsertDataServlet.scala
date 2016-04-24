package com.team103.app

import com.team103.config._
import com.team103.database.postGisDB.DatabaseSessionSupport
import com.team103.model._
import grizzled.slf4j.Logger
import java.net._
import java.sql.Timestamp
import java.util.Date
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.params.HttpMethodParams
import org.apache.commons.httpclient.methods.GetMethod;
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
  * Controller that allows user to insert data
  */
class InsertDataServlet extends AircheckStack with JacksonJsonSupport with DatabaseSessionSupport{

  def logger = Logger[InsertDataServlet]
  def weight = Map[String,Int](
    "cough" -> 1,
    "airLackness" -> 5,
    "wheezing"->5,
    "sneezing" -> 3,
    "obstruction"->2,
    "itchy"->7,
    "airQuality"->10)
  protected implicit val jsonFormats: Formats = DefaultFormats

  post("/environment") {
    try {
      val (lat,long) = extractCoords(parsedBody)
      val environment = parsedBody.extract[Environment]//Extract symptoms from data body
    //  val svc = url("http://api.hostip.info/country.php")

    } catch {
      case e:Exception => {
        logger.debug("Exception while parsing the body")
      }
    }
  }

  post("/symptoms") {
    try {
      logger.info("Inside symptoms: " + parsedBody)
      val (lat,long) = extractCoords(parsedBody)
      val symptoms = (parsedBody \ "symptoms").extract[Symptoms]//Extract symptoms from data body
      val selectCartoDB = createURL(lat,long,5,"Insert",11)
      sendCartoDB(selectCartoDB)
      Ok("Everything updated")
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
    ((coordinates \ "lat").extract[Double],(coordinates \ "long").extract[Double])
  }

  /**
    * Calculate formula for quality
    */
  private def calculateFormula(symptoms:Symptoms) = {
    var sum =  symptoms.cough * weight("cough") +
      symptoms.airLackness * weight("airLackness") +
      symptoms.wheezing * weight("weezing") +
      symptoms.obstruction * weight("obstruction") +
      symptoms.itchy * weight("itchy") +
      symptoms.sneezing * weight("sneezing") +
      symptoms.airQuality * weight("airQuality")
  }

  /**
    * Create url insert cartoDB
    */
  private def createURL(lat:Double,long:Double,value:Int,queryType:String,max:Int):String = {
    queryType match {
      case "Insert" => {
        val firstString = "INSERT INTO "+ ApiValues.API_TABLE + " VALUES("+max+","
        val substitute =  " ST_SetSRID(ST_MakePoint("+lat+","+long+"),4326),ST_SetSRID(ST_MakePoint("+lat+","+long+"),3857),"+value+")"
        firstString+substitute
      }
      case "Select" => {
        /*TODO: Poner + 1*/
        "SELECT MAX(cartodb_id) from " + ApiValues.API_TABLE
      }
    }

  }

  private def sendCartoDB(query:String){
    logger.info(query)
    var client = new HttpClient()
    var method = new GetMethod("https://balaber.cartodb.com/api/v2/sql")
    method.addRequestHeader("Accept","application/json")
    var params = new HttpMethodParams()
    params.setParameter("q",query)
    params.setParameter("api_key",ApiValues.API_KEY)
    method.setParams(params)
    logger.info("This is the uri: " + method.getURI.toString)
    client.executeMethod(method)
    logger.info("This is the response" + response)
  }
}
