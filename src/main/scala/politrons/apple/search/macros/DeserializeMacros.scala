package politrons.apple.search.macros

/**
  * Created by pabloperezgarcia on 13/11/2016.
  */
import java.util

import com.fasterxml.jackson.databind.`type`.{CollectionType, TypeFactory}
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}

import scala.collection.JavaConverters._
import scala.language.experimental.macros
import scala.reflect.macros.blackbox._

object DeserializeMacros {

  val mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  val typeFactory = TypeFactory.defaultInstance()

  def deserialize[T](json: String, clazz:Class[T]): List[T] = macro deserialize_impl[T]

  def deserialize_impl[T](c: Context)(json: c.Expr[String],clazz:c.Expr[Class[T]] ): c.Expr[List[T]] = {
    import c.universe._
    reify {
      val result: util.ArrayList[T] = mapper.readValue(json.splice, getCollectionType(clazz.splice))
      result.asScala.toList
    }
  }

  private def getCollectionType[T](clazz: Class[T]): CollectionType = {
    typeFactory.constructCollectionType(classOf[util.ArrayList[T]], clazz)
  }

}