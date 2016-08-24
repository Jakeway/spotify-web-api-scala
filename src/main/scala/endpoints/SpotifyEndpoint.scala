package endpoints

import scalaj.http.{Http, HttpResponse}

abstract class SpotifyEndpoint {

  protected val baseAPIUrl = "https://api.spotify.com"

  protected def makeRequest(endpoint: String): HttpResponse[String] = Http(endpoint).asString

  protected def makeRequest(endpoint: String, params: Seq[(String, String)]): HttpResponse[String] = {
    Http(endpoint).params(params).asString
  }
}
