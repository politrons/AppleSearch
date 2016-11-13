package politrons.apple.search

import appleSearch.exceptions.AppleAPIException
import appleSearch.model.app.Application
import appleSearch.model.movie.Movie
import play.libs.Json
import politrons.apple.search.http.HttpClient._
import politrons.apple.search.implicits.AppleUtils.stringUtils
import politrons.apple.search.model.Deserializer
import politrons.apple.search.model.music.Album

import scala.util.Try
import scalaj.http.{HttpRequest, HttpResponse}
/**
  * Created by pabloperezgarcia on 08/11/2016.
  */
object AppleSearch extends Deserializer {

  private val API: String = "itunes.apple.com/search?term="

  implicit class music(music: Album) {

    //Music

    def getDiscography(country: String, artist: String): Option[List[Album]] = {
      if (artist.isEmpty) {
        throw new IllegalArgumentException
      }
      Try(attachVideoClips(country, artist, findAlbums(artist))).toOption
    }

    private def findAlbums(artist: String): List[Album] = {
      get(s"$API${artist.replace(" ", "+")}", asJson, "https")
      deserialize[Album](lastResponse.get, classOf[Album])
    }

    private def attachVideoClips(country: String, artist: String, albums: List[Album]): List[Album] = {
      get(s"$API${artist.createQuery(country, "musicVideo")}", asJson, "https")
      albums
      //    DiscographyF.attachVideos(lastResponse.get, albums)
    }

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
    deserialize(lastResponse.get, classOf[Application])
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
    deserialize(lastResponse.get, classOf[Movie])
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
