package politrons.apple.search.mocks

import play.libs.Json

import scala.io.Source


/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object ItunesMock {

  def mockVideos(): String = {
    val source = Source.fromURL(getClass.getResource("/video.json")).getLines.mkString
    Json.parse(source).at("/results").toString

  }

  def mockMusic(): String = {
    val source = Source.fromURL(getClass.getResource("/music.json")).getLines.mkString
    Json.parse(source).at("/results").toString
  }
}
