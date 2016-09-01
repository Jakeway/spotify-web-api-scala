package endpoints

import scalaj.http.HttpRequest


object SearchEndpoint extends SpotifyEndpoint {

  private val searchEndpoint = baseAPIUrl + "/v1/search/"

  def search(query: String, queryType: Seq[String]): HttpRequest = {
    val params = Seq(
      ("q", query),
      ("type", queryType.mkString(","))
    )
    createRequest(endpoint = searchEndpoint, params = params)
  }
}
