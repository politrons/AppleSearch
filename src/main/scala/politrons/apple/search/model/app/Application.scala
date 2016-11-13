package appleSearch.model.app

import appleSearch.model.AppleBase
import politrons.apple.search.AppleSearch.application

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
class Application(artistName: String = "",
                  artistViewUrl: String = "",
                  trackViewUrl: String = "",
                  country: String = "",
                  price: String = "",
                  artworkUrl100: String = ""
                 ) extends AppleBase(artistName,null, country) {
  def this() {
    this("")
  }

  def find(): Option[List[Application]] = {
    this.getApplications(country, artistName)
  }

  def getCountry: String = country

  def getPrice: String = price

  def getTrackViewUrl: String = trackViewUrl

  def getArtworkUrl100: String = artworkUrl100

}

