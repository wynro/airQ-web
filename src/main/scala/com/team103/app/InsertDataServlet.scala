package com.team103.app

import com.team103.config._
import com.team103.database.postGisDB.DatabaseSessionSupport
import com.team103.model._
import grizzled.slf4j.Logger
import java.net._
import java.sql.Timestamp
import java.util.Date
import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonAST._
import org.json4s.MappingException
import org.scalatra._
import org.scalatra.json._
import org.squeryl.PrimitiveTypeMode._
import scalaj.http._



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

  /** POST method to introduce data for environment conditions */
  post("/environment") {
    try {
      val (lat,long,ip) = extractFields(parsedBody)
      transaction{
        IPDAO.insert(new IP(ip,new Timestamp(System.getCurrentTimeMillis)))
      }
      val environment = (parsedBody \ "environment").extract[Environment]//Extract env from data body
      val selectCartoDB = createURL(lat,long,"5","SelectEnvironment",11)
      val response = sendCartoDB(selectCartoDB)
      var index = extractIndex(response.body)
      index = index + 1
      logger.info("Index: " + index)
      val result = extractEvent(environment)
      logger.info("This is result: " + result)
      sendCartoDB(createURL(lat,long,result,"InsertEnvironment",index))
      Ok()
    } catch {
      case e:Exception => {
        logger.debug("Exception while parsing the body")
        BadRequest()
      }
    }
  }

  /** POST methods to introduce data for symptoms from user */
  post("/symptoms") {
    try {
      logger.info("Inside symptoms: " + parsedBody)
      val (lat,long,ip) = extractFields(parsedBody)
      transaction{
        IPDAO.insert(new IP(ip,new Timestamp(System.getCurrentTimeMillis)))
      }
      val symptoms = (parsedBody \ "symptoms").extract[Symptoms]//Extract symptoms from data body
      val selectCartoDB = createURL(lat,long,"5","SelectSymptoms",11)
      val response = sendCartoDB(selectCartoDB)
      var index = extractIndex(response.body)
      index = index + 1
      logger.info("Index: " + index)
      val result = calculateFormula(symptoms)
      logger.info("This is result: " + result)
      sendCartoDB(createURL(lat,long,result.toString,"InsertSymptoms",index))
      Ok()
    } catch {
      case e:Exception => {
        logger.debug("Exception while parsing the body")
        BadRequest()
      }
    }
  }

  /**
    * Extract the coordinates from the request
    */
  private def extractFields(parsedBody:JValue):(Double,Double,String) = {
    val coordinates = parsedBody \ "coords"
    val ip = parsedBody \ "ip"
    ((coordinates \ "lat").extract[Double],(coordinates \ "long").extract[Double],ip)
  }

  /**
    * Calculate formula for quality
    */
  private def calculateFormula(symptoms:Symptoms):Int = {
    logger.info("This is product: " + symptoms)
    var sum =  symptoms.cough * weight("cough").asInstanceOf[Int]
      sum += symptoms.airLackness * weight("airLackness").asInstanceOf[Int]
      sum += symptoms.wheezing * weight("wheezing").asInstanceOf[Int]
      sum += symptoms.obstruction * weight("obstruction").asInstanceOf[Int]
      sum += symptoms.itchy * weight("itchy").asInstanceOf[Int]
      sum += symptoms.sneezing * weight("sneezing").asInstanceOf[Int]
      sum += (11 - symptoms.airQuality) * weight("airQuality").asInstanceOf[Int]
    logger.info("This is sum: " + sum)
    var acum = 0
    var fieldsAsPairs = for (field <- symptoms.getClass.getDeclaredFields) yield {
      field.setAccessible(true)
      if (field.get(symptoms).asInstanceOf[Int] > 0) acum += weight(field.getName).asInstanceOf[Int]
    }
    logger.info("This is acum: " + acum)
    sum/acum
  }

  /** Extract the environmental condition */
  private def extractEvent(environment:Environment):String = {
    var event = ""
    var fieldsAsPairs = for (field <- environment.getClass.getDeclaredFields) yield {
      field.setAccessible(true)
      if (field.get(environment).asInstanceOf[Int] > 0) event = field.getName
    }
    logger.info("This is event: " + event)
    event
  }

  /**
    * Create url insert cartoDB
    */
  private def createURL(lat:Double,long:Double,value:String,queryType:String,max:Int):String = {
    queryType match {
      case "InsertSymptoms" => {
        val firstString = "INSERT%20INTO%20"+ ApiValues.API_TABLE_AIR + "%20VALUES%28"+max+","
        val substitute =  "%20ST_SetSRID%28ST_MakePoint%28"+lat+","+long+"%29,4326%29,"
        val lastString = "ST_SetSRID%28ST_MakePoint%28"+lat+","+long+"%29,3857%29,"+value+"%29"
        firstString+substitute+lastString+ "&api_key="+ApiValues.API_KEY
      }
      case "SelectSymptoms" => {
        /*TODO: Poner + 1*/
        "SELECT%20MAX%28cartodb_id%29%20from%20" + ApiValues.API_TABLE_AIR + "&api_key="+ApiValues.API_KEY
      }
      case "InsertEnvironment" => {
        val firstString = "INSERT%20INTO%20"+ ApiValues.API_TABLE_EVENT + "%20VALUES%28"+max+","
        val substitute =  "%20ST_SetSRID%28ST_MakePoint%28"+lat+","+long+"%29,4326%29,"
        val lastString = "ST_SetSRID%28ST_MakePoint%28"+lat+","+long+"%29,3857%29,"+value+"%29"
        firstString+substitute+lastString+ "&api_key="+ApiValues.API_KEY
      }
      case "SelectEnvironment" => {
        "SELECT%20MAX%28cartodb_id%29%20from%20" + ApiValues.API_TABLE_EVENT + "&api_key="+ApiValues.API_KEY
      }
    }

  }

  /** Returns the response from GET request */
  private def sendCartoDB(query:String):HttpResponse[String] = {
    logger.info(query)
    val url = "https://balaber.cartodb.com/api/v2/sql?q=" + query
    val response: HttpResponse[String] = scalaj.http.Http(url).asString
    logger.info("This is the response" + response.body)
    response
  }

  /** Extract index for insert on database by doin GET request */
  private def extractIndex(response:String):Int = {
    val myJSON = parse(response)
    logger.info("This is json in JSON format: " + myJSON)
    logger.info("This is my json: " + (myJSON \ "rows")(0) )
    val totalNumber = (((myJSON \ "rows")(0)) \ "max").extract[Int]
    logger.info("This is totalNumber: " + totalNumber)
    totalNumber
  }
}
