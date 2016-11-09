package appleSearch.model.music

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonCreator

/**
  * Created by pabloperezgarcia on 09/11/2016.
  */
@JsonCreator
class Discography(@JsonProperty("results") results:List[Album]) {

  def this() {
    this(List())
  }

}
