package appleSearch.model.movie

import appleSearch.factory.MovieFactory
import implicits.Utils.anyUtils

import scala.util.parsing.json.{JSONArray, JSONObject}

/**
  * Created by pabloperezgarcia on 31/8/16.
  */
object AppleTv {

  def movies(moviesArray: JSONArray): List[Movie] = {
    moviesArray.list.toStream
      .map(movie => {
        try {
          MovieFactory.create(new JSONObject(movie.asStringMap))
        } catch {
          case e: NoSuchElementException => null
        }
      })
      .filter(movie => movie != null)
      .toList
  }

}
