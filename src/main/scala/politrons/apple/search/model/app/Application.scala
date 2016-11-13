package appleSearch.model.app

import appleSearch.model.AppleBase

/**
  * Created by pabloperezgarcia on 29/8/16.
  */
class Application(artistViewUrl: String,
                  trackViewUrl: String,
                  price: String,
                  artworkUrl100: String
                 ) extends AppleBase {
  def this() {
    this("","","","")
  }

}

