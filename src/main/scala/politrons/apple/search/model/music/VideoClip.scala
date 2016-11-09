package appleSearch.model.music

import appleSearch.model.AppleBase

/**
  * Created by pabloperezgarcia on 27/8/16.
  */
class VideoClip(artistName: String,
                trackName: String,
                val previewUrl: String,
                val artWorkUrl: String
               ) extends AppleBase(artistName, trackName) {}
