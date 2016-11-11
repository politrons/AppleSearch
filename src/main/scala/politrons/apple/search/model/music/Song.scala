package appleSearch.model.music

import appleSearch.model.AppleBase

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
class Song(previewUrl: String,
           trackPrice: String,
           trackViewUrl: String
          ) extends AppleBase {

  var videoClip: VideoClip = _

  def addVideoClip(videoClip: VideoClip): Song = {
    this.videoClip = videoClip
    this
  }

}
