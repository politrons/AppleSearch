package mocks

import scala.io.Source

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object AppleStoreMock {

  def mockApps(): String = {
    Source.fromURL(getClass.getResource("/apps.json")).getLines.mkString
  }

}
