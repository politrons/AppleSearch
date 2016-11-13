package appleSearch.model

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
abstract class AppleBase(artistName: String="",
                         trackName: String="") {

  def getArtistName: String = artistName
  def getTrackName: String = trackName


}

