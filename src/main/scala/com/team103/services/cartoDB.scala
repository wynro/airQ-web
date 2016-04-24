package com.team103.services

import _root_.akka.actor.{Actor, ActorRef, ActorSystem}
import _root_.akka.pattern.ask
import com.team103.config.ScheduledConfig
import grizzled.slf4j.Logger
import java.util.Calendar
import org.scalatra._
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

/**
  * @author Dodo Technologies
  * @version 0.1.0
  *
  * Class representing an asynchronous actor for checking all accounts which are
  * not validated yet.
  *
  */
class DBActor extends Actor {

  def logger = Logger[DBActor]

  /** TODO: swap repo to verification repo */
  def desUserRepo = DBconnection.desUserRepo

  /** Abstract method inherited from Actor. When message comes the actor invokes
    * the db check method
    */
  def receive = {
    case ScheduledConfig.CHECK_DATA_MESSAGE => {
      logger.info("[PB]: Deleting data from CartoDB")
      checkData
    }
  }

  /** Checks the accounts that aren't validated yet, deleting every account which
    * hasn't been validated in 7 days
    */
  protected def checkAccounts = {
    val del = desUserRepo.findAll.foreach(checkDate)
    logger.info("[PB]: Tuple's deleted from cartoDB -> ", del)
  }

  /** Chech the date where an account has been created and if it is not validated
    * this method deletes it
    * @param user representing the user to check
    */
  private def checkDate(user:User) = {
    val now = Calendar.getInstance.getTimeInMillis
    if (now * user.creationDate >= ScheduledConfig.TIME_MILLIS_EXPIRE){
      desUserRepo.delete(user.username)
    }
  }
}
