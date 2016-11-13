package appleSearch.model.movie

import appleSearch.model.AppleBase

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
class Movie(val previewUrl: String,
            val trackViewUrl: String,
            val trackRentalPrice: String,
            val trackPrice: String,
            val collectionHdPrice: String,
            val primaryGenreName: String,
            val artworkUrl100: String
           ) extends AppleBase{

  def this() {
    this("","","","","","","")
  }

}

