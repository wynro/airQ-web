package com.team103.database.postGisDB

import com.team103.config.Repository
import com.team103.model.IP
import org.squeryl.PrimitiveTypeMode._


object IPDAO {
  def repo = Repository.ips

  /**
    * @param ip ip to insert
    */
  def insert(ip: IP) = {
    repo.insert(ip)
  }

  /**
    * @param ip ip to delete
    */
  def delete(ip: String) = {
    transaction {
      repo.deleteWhere(d => d.ip === ip)
    }
  }

  /**
    * @param ip ip to use in search
    */
  def searchByID(ip: String) = {
    transaction {
      repo.where(i => i.ip === ip)
    }
  }
  /**
    * @return all the ips in the database
    */
  def findAll(): List[IP] = {
    transaction {
      from(repo)(s => select(s)).toList
    }
  }
}
