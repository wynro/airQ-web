package com.team103.config

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.slf4j.LoggerFactory

/**
  * @author Team 103
  * @version 0.0.1
  *
  * Inicializates database
  */
trait DatabaseInit {
  val logger = LoggerFactory.getLogger(getClass)
  val databaseHost = "localhost"
  val databaseName = "aircheck"
  val databaseUsername = "nasa"
  val databasePassword = "nasa"
  val databaseConnection = "jdbc:postgresql://" + databaseHost + "/" + databaseName

  var cpds = new ComboPooledDataSource

  /**
    *
    */
  def configureDb() {
    cpds.setDriverClass("org.postgresql.Driver")
    cpds.setJdbcUrl(databaseConnection)
    cpds.setUser(databaseUsername)
    cpds.setPassword(databasePassword)

    cpds.setMinPoolSize(1)
    cpds.setAcquireIncrement(1)
    cpds.setMaxPoolSize(50)

    SessionFactory.concreteFactory = Some(() => connection)

    def connection = {
      logger.info("Creating connection with c3po connection pool")
      Session.create(cpds.getConnection, new PostgreSqlAdapter)
    }
  }

  def closeDbConnection() {
    logger.info("Closing c3po connection pool")
    cpds.close()
  }
}
