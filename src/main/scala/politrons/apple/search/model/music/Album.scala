package appleSearch.model.music

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.{JsonCreator, JsonIgnoreProperties}

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonCreator
class Album(@JsonProperty("artistName")val artistName: String
//            ,
//            val collectionName: String,
//            val primaryGenreName: String,
//            val country: String,
//            val trackPrice: String,
//            val trackViewUrl: String,
//            val releaseDate: String,
//            val trackName: String,
//            val previewUrl: String,
//            val artWorkUrl100: String
           ){
//  extends AppleBase(artistName, trackName) {

  def this() {
    this("")
  }

  def replace(song: Song): Album = {
    songs.map { case song => song }
    this
  }


  var songs: List[Song] =List()
//    List(new Song(trackName, previewUrl, trackPrice, trackViewUrl))

  def addSongs(song: Song) {
    songs = songs.::(song)
  }

}


