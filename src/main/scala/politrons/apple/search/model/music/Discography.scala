package appleSearch.model.music

import com.fasterxml.jackson.annotation.{JsonProperty, JsonCreator}
import politrons.apple.search.model.music.Album

/**
  * Created by pabloperezgarcia on 09/11/2016.
  */
@JsonCreator
class Discography(@JsonProperty("results") results:List[Album]) {

  def this() {
    this(List())
  }

}
