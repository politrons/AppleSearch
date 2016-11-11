package politrons.apple.search.mocks

import scala.io.Source

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object ItunesMock {

  def mockVideos(): String = {
    Source.fromURL(getClass.getResource("/video.json")).getLines.mkString
  }

  def mockMusic(): String = {
    Source.fromURL(getClass.getResource("/music.json")).getLines.mkString
  }
}
