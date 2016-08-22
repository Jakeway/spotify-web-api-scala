package endpoints


object SearchEndpoint extends SpotifyEndpoint {

  private val searchEndpoint = baseAPIUrl + "/v1/search/"

  def search(query: String, queryType: String*): String = {
    val params = Seq(
      ("q", query),
      ("type", queryType.mkString(","))
    )
    makeRequest(endpoint = searchEndpoint, params = params)
  }
}
