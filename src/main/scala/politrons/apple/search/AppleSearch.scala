package politrons.apple.search

import appleSearch.exceptions.AppleAPIException
import appleSearch.model.app.Application
import appleSearch.model.movie.Movie
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import politrons.apple.search.http.HttpClient._
import politrons.apple.search.implicits.AppleUtils.optionUtils
import politrons.apple.search.model.Results
import politrons.apple.search.model.music.Album
import scala.util.parsing.json.JSON._
import scalaj.http.{HttpRequest, HttpResponse}
import implicits.AppleUtils.{stringUtils}
/**
  * Created by pabloperezgarcia on 08/11/2016.
  */object AppleSearch {

  private val API: String = "itunes.apple.com/search?term="

  val mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  //Music

  def getDiscography(country: String, artist: Option[String]): List[Album] = {
    if(artist.isEmpty){
      throw new IllegalArgumentException
    }
    attachVideoClips(country, artist.get, findAlbums(artist.get))
  }

  private def findAlbums(artist: String): List[Album] = {
    get(s"$API${artist.replace(" ", "+")}", asJson, "https")
    val results = mapper.readValue(lastResponse.get, classOf[Results[Album]])
    results.getResults.asInstanceOf[List[Album]]
  }

  private def attachVideoClips(country: String, artist: String, albums: List[Album]): List[Album] = {
    get(s"$API${artist.createQuery(country, "musicVideo")}", asJson, "https")
    List()
    //    DiscographyF.attachVideos(lastResponse.get, albums)
  }

  //Apps

  def getApplications(country: String, app: Option[String]): List[Application] = {
    if(app.isEmpty){
      throw new IllegalArgumentException
    }
    findApps(country, app.get)
  }

  private def findApps(country: String, app: String): List[Application] = {
    get(s"$API${app.createQuery(country, "software")}", asJson, "https")
    val results =mapper.readValue(lastResponse.get, classOf[Results[Application]])
    results.getResults.asInstanceOf[List[Application]]

  }

  //Movies

  def getMovies(country: String, title: Option[String]): List[Movie] = {
    if(title.isEmpty){
      throw new IllegalArgumentException
    }
    findMovies(country, title.get)
  }

  private def findMovies(country: String, movie: String): List[Movie] = {
    get(s"$API${movie.createQuery(country, "movie")}", asJson, "https")
    val results = mapper.readValue(lastResponse.get, classOf[Results[Movie]])
    results.getResults.asInstanceOf[List[Movie]]
  }

  private def asJson: (HttpRequest) => String = {
    request =>
      val response: HttpResponse[String] = request.asString
      if (response.isSuccess) {
        parseFull(response.body).asString
      } else {
        throw new AppleAPIException(s"Error: $response")
      }
  }


}
