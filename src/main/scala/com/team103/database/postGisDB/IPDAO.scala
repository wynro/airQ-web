package com.team103.database.postGisDB

import com.team103.config.Repository
import com.team103.model.IP
import org.squeryl.PrimitiveTypeMode._


object IPDAO {

  def rep = Repository.ips
  /**
    * @param ip ip to insert
    */
  def insert(ip: IP) = {
    rep.insert(ip)
  }

  /**
    * @param ip ip to delete
    */
  def delete(ip: String) = {
    rep.deleteWhere(d => d.ip === ip)
  }
}
