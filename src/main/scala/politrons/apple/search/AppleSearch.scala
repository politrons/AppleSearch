package politrons.apple.search

import appleSearch.exceptions.AppleAPIException
import politrons.apple.search.http.HttpClient._
import politrons.apple.search.implicits.AppleUtils.optionalUtils
import appleSearch.model.app.{AppleStore, Application}
import appleSearch.model.movie.{AppleTv, Movie}
import politrons.apple.search.model.music.Album

import scala.util.parsing.json.JSON._
import scala.util.parsing.json._
import scalaj.http.{HttpRequest, HttpResponse}

/**
  * Created by pabloperezgarcia on 08/11/2016.
  */
object AppleSearch {

  private val API: String = "itunes.apple.com/search?term="

  //Music

  def getDiscography(country: String, artist: Option[String]): List[Album] = {
    attachVideoClips(country, artist, findAlbums(artist))
  }

  private def findAlbums(artist: Option[String]): List[Album] = {
    get(s"$API${artist.get.replace(" ", "+")}", asJson, "https")
    List()
//    DiscographyF.albums(lastResponse.get)
  }

  private def attachVideoClips(country: String, artist: Option[String], albums: List[Album]): List[Album] = {
    get(s"$API${artist.createQuery(country, "musicVideo")}", asJson, "https")
    List()
//    DiscographyF.attachVideos(lastResponse.get, albums)
  }

  //Apps

  def getApplications(country: String, app: Option[String]): List[Application] = {
    findApps(country, app)
  }

  private def findApps(country: String, app: Option[String]): List[Application] = {
    get(s"$API${app.createQuery(country, "software")}", asJson, "https")
    AppleStore.applications(lastResponse.get)
  }

  //Movies

  def getMovies(country: String, title: Option[String]): List[Movie] = {
    findMovies(country, title)
  }

  private def findMovies(country: String, movie: Option[String]): List[Movie] = {
    get(s"$API${movie.createQuery(country, "movie")}", asJson, "https")
    AppleTv.movies(lastResponse.get)
  }


  private def asJson: (HttpRequest) => JSONArray = {
    request =>
      val response: HttpResponse[String] = request.asString
      response.isSuccess match {
        case true =>
          val map = parseFull(response.body).get.asInstanceOf[Map[String, Any]]
          val jsonList = map("results").asInstanceOf[List[Map[String, Any]]]
          JSONArray(jsonList)
        case _ => throw new AppleAPIException(s"Error: $response")
      }
  }


}
