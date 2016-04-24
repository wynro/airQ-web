package com.team103.config

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._

/**
* @author Dodo Technologies
* @version 0.1.0
*
*  Singleton wrapper for shared values on actor services
*/
object ScheduledConfig {
  // Data check config
  val CHECK_DATA_MESSAGE = '1'
  val CHECK_DATA_INTERVAL:FiniteDuration = 1 days
  val CHECK_DATA_DELAY:FiniteDuration = 1 days
  val TIME_MILLIS_EXPIRE = 1 * 24 * 3600 * 1000 // days*hours*sec*millis

  // IP check config
  val CHECK_IP_MESSAGE = '1'
  val CHECK_IP_INTERVAL:FiniteDuration = 1 days
  val CHECK_IP_DELAY:FiniteDuration = 1 days
  val TIME_IP_EXPIRE = 1 * 24 * 3600 * 1000 // days*hours*sec*millis
}
