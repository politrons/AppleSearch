package politrons.apple.search.model.music

import appleSearch.model.AppleBase
import appleSearch.model.music.Song
import politrons.apple.search.AppleSearch.music

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
class Album(artistName: String="",
            trackName: String="",
            storeCountry:String="",
            collectionName: String="",
            primaryGenreName: String="",
            country: String="",
            trackPrice: String="",
            trackViewUrl: String="",
            releaseDate: String="",
            previewUrl: String="",
            artworkUrl100: String=""
           )
  extends AppleBase(artistName, trackName, storeCountry) {

  def this() {
    this("")
  }

  def find(): Option[List[Album]] = {
    this.getDiscography(country, getArtistName)
  }

  def getCollectionName: String = collectionName

  def getPrimaryGenreName: String = primaryGenreName

  def getCountry: String = country

  def getTrackPrice: String = trackPrice

  def getTrackViewUrl: String = trackViewUrl

  def getReleaseDate: String = releaseDate

  def getPreviewUrl: String = previewUrl

  def getArtworkUrl100: String = artworkUrl100

  def replace(song: Song): Album = {
    songs.map { case song => song }
    this
  }

  var songs: List[Song] = List()
  //    List(new Song(trackName, previewUrl, trackPrice, trackViewUrl))

  def addSongs(song: Song) {
    songs = songs.::(song)
  }

}


