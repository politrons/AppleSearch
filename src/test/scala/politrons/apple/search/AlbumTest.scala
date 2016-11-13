package politrons.apple.search

import org.junit.Test
import politrons.apple.search.mocks.ItunesMock
import politrons.apple.search.model.music.Album

/**
  * Created by pabloperezgarcia on 18/8/16.
  */
class AlbumTest extends BaseTest {

  /**
    * This sort of test require internet connection.
    */
  @Test def getDiscographyTest(): Unit = {
    val album = new Album(artistName = "incubus", country = "es")
    val albums = album.find()
    assert(albums.isDefined)
    assert(albums.get.head.country.equals("USA"))
  }

  @Test def deserializeAlbumsTest(): Unit = {
    val albums = getJsonResults[Album](ItunesMock.mockMusic(), classOf[Album])
    assert(!albums.isEmpty)
    assert(albums.get(0).country.equals("USA"))
  }

}
