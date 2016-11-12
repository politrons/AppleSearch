package politrons.apple.search

import java.util

import com.fasterxml.jackson.databind.`type`.TypeFactory
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}

/**
  * Created by pabloperezgarcia on 11/11/2016.
  */
class BaseTest {

  def getJsonResults[T](json: String, clazz:Class[T]): util.ArrayList[T] = {
    val mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    val typeFactory = TypeFactory.defaultInstance()
    mapper.readValue(json, typeFactory.constructCollectionType(classOf[util.ArrayList[T]],clazz))
  }

}
