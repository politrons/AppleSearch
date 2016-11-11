package appleSearch.model.movie

import appleSearch.model.AppleBase

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
class Movie(previewUrl: String="",
            trackViewUrl: String="",
            trackRentalPrice: String="",
            trackPrice: String="",
            collectionHdPrice: String="",
            primaryGenreName: String="",
            artworkUrl100: String=""
           ) extends AppleBase{

  def this() {
    this("")
  }

}

