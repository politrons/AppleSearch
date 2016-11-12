package politrons.apple.search.model

import java.util

import com.fasterxml.jackson.databind.`type`.{CollectionType, TypeFactory}
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}

import scala.collection.JavaConverters._


/**
  * Created by pabloperezgarcia on 12/11/2016.
  */
object Deserializer {

  val mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  val typeFactory = TypeFactory.defaultInstance()

  def deserialize[T](json: String, clazz: Class[T]): List[T] = {
    val result: util.ArrayList[T] = mapper.readValue(json, getCollectionType(clazz))
    result.asScala.toList
  }

  private def getCollectionType[T](clazz: Class[T]): CollectionType = {
    typeFactory.constructCollectionType(classOf[util.ArrayList[T]], clazz)
  }

}
