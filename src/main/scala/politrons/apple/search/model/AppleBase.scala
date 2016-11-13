package appleSearch.model

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
abstract class AppleBase(val artistName: String="",
                         val trackName: String="",
                         val country: String="") {

  def getArtistName: String = artistName

  def getTrackName: String = trackName


}

