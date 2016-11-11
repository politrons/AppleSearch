package mocks

import scala.io.Source

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object AppleTvMock {

  def mockApps(): String = {
    Source.fromURL(getClass.getResource("/movie.json")).getLines.mkString
  }

}
