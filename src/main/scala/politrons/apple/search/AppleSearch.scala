package politrons.apple.search

import java.util

import appleSearch.exceptions.AppleAPIException
import appleSearch.model.app.Application
import appleSearch.model.movie.Movie
import com.fasterxml.jackson.databind.`type`.{CollectionType, TypeFactory}
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import play.libs.Json
import politrons.apple.search.http.HttpClient._
import politrons.apple.search.implicits.AppleUtils.stringUtils
import politrons.apple.search.model.music.Album

import scala.collection.JavaConverters._
import scala.util.Try
import scalaj.http.{HttpRequest, HttpResponse}

/**
  * Created by pabloperezgarcia on 08/11/2016.
  */
object AppleSearch {

  private val API: String = "itunes.apple.com/search?term="

  val mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  val typeFactory = TypeFactory.defaultInstance()

  def deserialize[T](json: String, clazz: Class[T]): List[T] = {
    val result: util.ArrayList[T] = mapper.readValue(json, getCollectionType(clazz))
    result.asScala.toList
  }

  //Music

  def getCollectionType[T](clazz: Class[T]): CollectionType = {
    typeFactory.constructCollectionType(classOf[util.ArrayList[T]], clazz)
  }

  def getDiscography(country: String, artist: Option[String]): Option[List[Album]] = {
    if (artist.isEmpty) {
      throw new IllegalArgumentException
    }
    Try(attachVideoClips(country, artist.get, findAlbums(artist.get))).toOption
  }

  private def findAlbums(artist: String): List[Album] = {
    get(s"$API${artist.replace(" ", "+")}", asJson, "https")
    val albums = deserialize[Album](lastResponse.get, classOf[Album])
    albums
  }

  private def attachVideoClips(country: String, artist: String, albums: List[Album]): List[Album] = {
    get(s"$API${artist.createQuery(country, "musicVideo")}", asJson, "https")
    List()
    //    DiscographyF.attachVideos(lastResponse.get, albums)
  }

  //Apps

  def getApplications(country: String, app: Option[String]): Option[List[Application]] = {
    if (app.isEmpty) {
      throw new IllegalArgumentException
    }
    Try(findApps(country, app.get)).toOption
  }

  private def findApps(country: String, app: String): List[Application] = {
    get(s"$API${app.createQuery(country, "software")}", asJson, "https")
    deserialize(lastResponse.get, classOf[Application]).asInstanceOf[List[Application]]
    //    val results = mapper.readValue(lastResponse.get, classOf[Results[Application]])
    //    results.getResults.asInstanceOf[List[Application]]

  }

  //Movies

  def getMovies(country: String, title: Option[String]): Option[List[Movie]] = {
    if (title.isEmpty) {
      throw new IllegalArgumentException
    }
    Try(findMovies(country, title.get)).toOption
  }

  private def findMovies(country: String, movie: String): List[Movie] = {
    get(s"$API${movie.createQuery(country, "movie")}", asJson, "https")
    deserialize(lastResponse.get, classOf[Movie]).asInstanceOf[List[Movie]]

    //    val results = mapper.readValue(lastResponse.get, classOf[Results[Movie]])
    //    results.getResults.asInstanceOf[List[Movie]]
  }

  private def asJson: (HttpRequest) => String = {
    request =>
      val response: HttpResponse[String] = request.asString
      if (response.isSuccess) {
        Json.parse(response.body).at("/results").toString
      } else {
        throw new AppleAPIException(s"Error: $response")
      }
  }


}
