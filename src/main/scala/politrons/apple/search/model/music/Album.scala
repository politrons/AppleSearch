package politrons.apple.search.model.music

import appleSearch.model.AppleBase
import appleSearch.model.music.{Song, VideoClip}
import politrons.apple.search.AppleSearch.music

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
class Album(artistName: String = "",
            trackName: String = "",
            country: String = "",
            var collectionName: String = "",
            var primaryGenreName: String = "",
            var trackPrice: String = "",
            var trackViewUrl: String = "",
            var releaseDate: String = "",
            var previewUrl: String = "",
            var artworkUrl100: String = ""
           )
  extends AppleBase(artistName, trackName, country) {

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

  var songs: List[Song] = List[Song]()

  def addSongs(song: Song): Album = {
    songs = songs.::(song)
    this
  }

  def attachVideoClips(albums: List[Album], videos: List[VideoClip]): List[Album] = {
    albums.toStream
      .flatMap(album => videos.toStream
        .filter(video => video.trackName.equals(album.trackName))
        .map(video => album.replace(findSong(album.songs, video.trackName))))
      .toList
  }

  def mergeSongs(albums: List[Album]): List[Album] = {
    albums.toStream
      .flatMap(album => albums.toStream
        .filter(album1 => album1.trackName.equals(album.trackName))
        .map(album1 => album.addSongs(createSong(album1))))
      .toList
  }

  private def findSong(songs: List[Song], trackName: String): Song = {
    songs.toStream
      .filter(song => song.trackName.equals(trackName))
      .head
  }

  private def createSong(album1: Album): Song = {
    new Song(album1.getPreviewUrl, album1.getTrackPrice, album1.getTrackViewUrl)
  }

}


