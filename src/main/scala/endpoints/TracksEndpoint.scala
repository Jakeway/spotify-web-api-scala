package endpoints

import scalaj.http.HttpResponse


object TracksEndpoint extends SpotifyEndpoint {

  private val tracksEndpoint = baseAPIUrl + "/v1/tracks/"

  def getTrack(trackId: String): HttpResponse[String] =
    makeRequest(tracksEndpoint + trackId)

  def getTracks(trackIds: Seq[String]): HttpResponse[String] =
    makeRequest(tracksEndpoint, params = Seq(("ids", trackIds.mkString(","))))
}
