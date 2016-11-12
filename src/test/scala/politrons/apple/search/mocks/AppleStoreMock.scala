package mocks

import play.libs.Json

import scala.io.Source

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object AppleStoreMock {

  def mockApps(): String = {
    val source = Source.fromURL(getClass.getResource("/apps.json")).getLines.mkString
    Json.parse(source).at("/results").toString
  }

}
