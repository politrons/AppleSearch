package politrons.apple.search.model

import java.util

/**
  * Created by pabloperezgarcia on 09/11/2016.
  */
case class Results[M](results:util.ArrayList[M]) {

  def getResults:util.ArrayList[M]=results

  def this() {
    this(new util.ArrayList())
  }

}
