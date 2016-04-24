package com.team103.database.postGisDB

import com.team103.config.Repository
import com.team103.model.Response
import org.squeryl.PrimitiveTypeMode._


object ResponseDAO {

  def resp = Repository.responses
  /**
    * @param r response to insert
    */
  def insert(r: Response): Response = {
    resp.insert(r)
  }

  /**
    * @param id repsonse identificator to use
    */
  def delete(id: Long) = {
    resp.deleteWhere(r => r.id === id)
  }

  /**
    * @return all the responses in the database
    */
  def findAll(): List[Response] = {
    List[Response]()
  }
}
