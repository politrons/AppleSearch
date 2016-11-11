package politrons.apple.search

import _root_.mocks.AppleTvMock
import appleSearch.model.movie.Movie
import org.junit.Test

/**
  * Created by pabloperezgarcia on 18/8/16.
  */
class MovieTest extends BaseTest{

  @Test def deserializeMovies(): Unit = {
    val movies =  getJsonResults[Movie](AppleTvMock.mockApps())
    assert(!movies.getResults.isEmpty)
  }

}
