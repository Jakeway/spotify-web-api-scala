package endpoints


object TracksEndpoint extends SpotifyEndpoint {

  private val tracksEndpoint = baseAPIUrl + "/v1/tracks/"

  def getTrack(trackId: String): String =
    makeRequest(tracksEndpoint + trackId)

  def getTracks(trackIds: String*): String =
    makeRequest(tracksEndpoint, params = Seq(("ids", trackIds.mkString(","))))
}
