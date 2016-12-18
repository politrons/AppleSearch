package mocks

import java.net.URL

import play.libs.Json

import scala.io.Source

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object AppleTvMock {

  def mockApps(): String = {
    val resource: URL = getClass.getResource("/movie.json")
    resource.toURI
    val source = Source.fromURL(resource).getLines.mkString
    Json.parse(source).at("/results").toString
  }

}
