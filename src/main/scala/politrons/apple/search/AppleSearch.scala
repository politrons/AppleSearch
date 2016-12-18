package politrons.apple.search

import appleSearch.exceptions.AppleAPIException
import appleSearch.model.app.Application
import appleSearch.model.movie.Movie
import appleSearch.model.music.VideoClip
import play.libs.Json
import politrons.apple.search.http.HttpClient._
import politrons.apple.search.implicits.Utils.stringUtils
import politrons.apple.search.model.Deserializer
import politrons.apple.search.model.music.Album

import scala.util.Try
import scalaj.http.{HttpRequest, HttpResponse}

/**
  * Created by pabloperezgarcia on 08/11/2016.
  */
package object AppleSearch extends Deserializer {

  private val API: String = "itunes.apple.com/search?term="

  implicit class music(album: Album) {

    def getDiscography(country: String, artist: String): Option[List[Album]] = {
      if (artist.isEmpty) {
        throw new scala.IllegalArgumentException
      }
//      Try(findAllAlbums(country, artist)).toOption
      Try(findAlbums(artist)).toOption

    }

//    def findAllAlbums(country: String, artist: String): List[Album] = {
//      for {
//        albums: List[Album] <- findAlbums(artist)
//        videos: List[VideoClip] <- findVideos(country, artist)
//      } yield album.attachVideoClips(albums, videos).head
//    }

    private def findAlbums(artist: String): List[Album] = {
      get(s"$API${artist.replace(" ", "+")}", asJson, "https")
      val albums = deserialize[Album](lastResponse.get, classOf[Album])
      album.mergeSongs(albums)
    }

    private def findVideos(country: String, artist: String): List[VideoClip] = {
      get(s"$API${artist.createQuery(country, "musicVideo")}", asJson, "https")
      deserialize[VideoClip](lastResponse.get, classOf[VideoClip])
    }
  }

  implicit class application(app: Application) {

    def getApplications(country: String, app: String): Option[List[Application]] = {
      if (app.isEmpty) {
        throw new IllegalArgumentException
      }
      Try(findApps(country, app)).toOption
    }

    private def findApps(country: String, app: String): List[Application] = {
      get(s"$API${app.createQuery(country, "software")}", asJson, "https")
      deserialize(lastResponse.get, classOf[Application])
    }

  }

  implicit class movie(movie: Movie) {

    def getMovies(country: String, title: String): Option[List[Movie]] = {
      if (title.isEmpty) {
        throw new IllegalArgumentException
      }
      Try(findMovies(country, title)).toOption
    }

    private def findMovies(country: String, movie: String): List[Movie] = {
      get(s"$API${movie.createQuery(country, "movie")}", asJson, "https")
      deserialize(lastResponse.get, classOf[Movie])
    }
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
