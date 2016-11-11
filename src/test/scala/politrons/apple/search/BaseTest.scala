package politrons.apple.search

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import politrons.apple.search.model.Results

/**
  * Created by pabloperezgarcia on 11/11/2016.
  */
class BaseTest {

  def getJsonResults[T](jsonAlbums: String): Results[T] = {
    val mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    mapper.readValue(jsonAlbums, classOf[Results[T]])
  }

}
