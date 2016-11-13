package politrons.apple.search.http

import scalaj.http.{HttpRequest, HttpResponse}

trait HttpExtensions {

  def CONNECT_TIMEOUT: Int = 60000

  def READ_TIMEOUT: Int = 60000

  implicit class HttpRequestExt(request: HttpRequest) {

    def sendJson: HttpResponse[String] = {
      request.header("content-type", "application/json").asString
    }

    def jsonContent: HttpRequest = {
      request.header("content-type", "application/json")
    }

    def asJson(function: HttpRequest => String):String = {
      function.apply(request)
    }

  }

}
