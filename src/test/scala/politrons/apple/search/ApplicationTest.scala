
import appleSearch.model.app.Application
import mocks.AppleStoreMock
import org.junit.Test
import politrons.apple.search.BaseTest

/**
  * Created by pabloperezgarcia on 18/8/16.
  */
class ApplicationTest extends BaseTest {

  @Test def deserializeMovies(): Unit = {
    val apps =  getJsonResults[Application](AppleStoreMock.mockApps())
    assert(!apps.getResults.isEmpty)
  }

}
