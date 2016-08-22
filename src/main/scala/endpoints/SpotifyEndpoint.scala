package endpoints

import scalaj.http.Http

abstract class SpotifyEndpoint {

  protected val baseAPIUrl = "https://api.spotify.com"

  protected def makeRequest(endpoint: String): String = {
    Http(endpoint).asString.body
  }

  protected def makeRequest(endpoint: String, params: Seq[(String, String)]) = {
    Http(endpoint).params(params).asString.body
  }
}
