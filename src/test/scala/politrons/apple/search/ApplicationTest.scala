
import appleSearch.model.app.Application
import mocks.AppleStoreMock
import org.junit.Test
import politrons.apple.search.BaseTest

/**
  * Created by pabloperezgarcia on 18/8/16.
  */
class ApplicationTest extends BaseTest {

  /**
    * This sort of test require internet connection.
    */
  @Test def getApplicationsTest(): Unit = {
    val app = new Application(artistName = "fifa", country = "es")
    val apps = app.find()
    assert(apps.isDefined)
    assert(apps.get.head.getArtistName.equals("Electronic Arts"))
  }

  @Test def deserializeApplication(): Unit = {
    val apps =  getJsonResults[Application](AppleStoreMock.mockApps(), classOf[Application])
    assert(!apps.isEmpty)
  }

}
