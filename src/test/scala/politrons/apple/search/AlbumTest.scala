import org.junit.Test
import politrons.apple.search.BaseTest
import politrons.apple.search.mocks.ItunesMock
import politrons.apple.search.model.music.Album

/**
  * Created by pabloperezgarcia on 18/8/16.
  */
class AlbumTest extends BaseTest{


  @Test def deserializeAlbums(): Unit = {
    val albums =  getJsonResults[Album](ItunesMock.mockMusic())
    assert(!albums.getResults.isEmpty)
  }

}
