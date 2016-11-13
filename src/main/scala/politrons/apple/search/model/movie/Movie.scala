package appleSearch.model.movie

import appleSearch.model.AppleBase
import politrons.apple.search.AppleSearch.movie

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
class Movie(artistName: String = "",
            trackName: String = "",
            country: String = "",
            previewUrl: String = "",
            trackViewUrl: String = "",
            trackRentalPrice: String = "",
            trackPrice: String = "",
            collectionHdPrice: String = "",
            primaryGenreName: String = "",
            artworkUrl100: String = ""
           ) extends AppleBase(artistName, trackName, country) {

  def this() {
    this("")
  }

  def find(): Option[List[Movie]] = {
    this.getMovies(country, artistName)
  }

  def getTrackRentalPrice: String = trackRentalPrice

  def getPrimaryGenreName: String = primaryGenreName

  def getCountry: String = country

  def getTrackPrice: String = trackPrice

  def getTrackViewUrl: String = trackViewUrl

  def getCollectionHdPrice: String = collectionHdPrice

  def getPreviewUrl: String = previewUrl

  def getArtworkUrl100: String = artworkUrl100

}

