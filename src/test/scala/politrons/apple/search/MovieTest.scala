package politrons.apple.search

import _root_.mocks.AppleTvMock
import appleSearch.model.movie.Movie
import org.junit.Test

/**
  * Created by pabloperezgarcia on 18/8/16.
  */
class MovieTest extends BaseTest{

  /**
    * This sort of test require internet connection.
    */
  @Test def getMovieTest(): Unit = {
    val movie = new Movie(artistName = "matrix", country = "es")
    val movies = movie.find()
    assert(movies.isDefined)
    assert(movies.get.head.getArtistName.equals("Andy Wachowski & Larry Wachowski"))
  }

  @Test def deserializeMovies(): Unit = {
    val movies =  getJsonResults[Movie](AppleTvMock.mockApps(), classOf[Movie])
    assert(!movies.isEmpty)
  }

}
