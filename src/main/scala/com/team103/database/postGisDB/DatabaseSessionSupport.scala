package com.team103.database.postGisDB

import org.squeryl.Session
import org.squeryl.SessionFactory
import org.scalatra._
import org.squeryl.PrimitiveTypeMode._

/**
  * @author Team 103
  * @version 0.0.1
  *
  *  Support servlet connection with database
  */
object DatabaseSessionSupport {
  val key = {
    val n = getClass.getName
    if (n.endsWith("$")) n.dropRight(1) else n
  }
}

/** Trait implementing database connection */
trait DatabaseSessionSupport { this: ScalatraBase =>
  import DatabaseSessionSupport._

  def dbSession = request.get(key).orNull.asInstanceOf[Session]

  before() {
    request(key) = SessionFactory.newSession
    dbSession.bindToCurrentThread
  }

  after() {
    dbSession.close
    dbSession.unbindFromCurrentThread
  }

}
