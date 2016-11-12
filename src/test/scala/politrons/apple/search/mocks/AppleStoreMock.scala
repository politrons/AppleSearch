package mocks

import scala.io.Source
import scala.util.parsing.json.JSON
import politrons.apple.search.implicits.AppleUtils.optionUtils

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
object AppleStoreMock {

  def mockApps(): String = {
    val source = Source.fromURL(getClass.getResource("/apps.json")).getLines.mkString
    val map = JSON.parseFull(source).get.asInstanceOf[Map[String, Any]]
    map.get("results").asString
  }

}
