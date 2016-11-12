package mocks

import play.libs.Json

import scala.io.Source

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object AppleTvMock {

  def mockApps(): String = {
    val source = Source.fromURL(getClass.getResource("/movie.json")).getLines.mkString
    Json.parse(source).at("/results").toString
  }

}
