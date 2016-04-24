package com.team103.services

import _root_.akka.actor.{Actor, ActorRef, ActorSystem}
import _root_.akka.pattern.ask
import com.team103.config.ScheduledConfig
import com.team103.database.postGisDB.IPDAO
import com.team103.model.IP
import grizzled.slf4j.Logger
import java.util.Calendar
import org.scalatra._
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  * @author Team 103
  * @version 0.0.1
  *
  *  Service to update IP database
  */
class IPactor extends Actor {

  def logger = Logger[IPactor]

  def repo = IPDAO

  /** Abstract method inherited from Actor. When message comes the actor invokes
    * the db check method
    */
  def receive = {
    case ScheduledConfig.CHECK_IP_MESSAGE => {
      logger.info("[PB]: Deleting IPS from DB")
      checkIPs
    }
  }

  /** Checks the accounts that aren't validated yet, deleting every account which
    * hasn't been validated in 7 days
    */
  protected def checkIPs = {
    val ips = repo.findAll
    ips.map(r => checkIP(r))
    logger.info("[PB]: Tuple's deleted from DB")
  }

  /** Chech the date where an account has been created and if it is not validated
    * this method deletes it
    * @param r representing the response to check
    */
  private def checkIP(r:IP) = {
    val now = Calendar.getInstance.getTimeInMillis
    if ((now - r.timeStamp.getTime) >= ScheduledConfig.TIME_IP_EXPIRE) {
      repo.delete(r.ip)
    }
  }
}
