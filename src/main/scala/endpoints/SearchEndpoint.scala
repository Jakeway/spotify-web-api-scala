package endpoints

import scalaj.http.HttpResponse


object SearchEndpoint extends SpotifyEndpoint {

  private val searchEndpoint = baseAPIUrl + "/v1/search/"

  def search(query: String, queryType: Seq[String]): HttpResponse[String] = {
    val params = Seq(
      ("q", query),
      ("type", queryType.mkString(","))
    )
    makeRequest(endpoint = searchEndpoint, params = params)
  }
}
