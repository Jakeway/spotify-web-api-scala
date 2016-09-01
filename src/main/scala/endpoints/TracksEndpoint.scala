package endpoints

import scalaj.http.HttpRequest


object TracksEndpoint extends SpotifyEndpoint {

  private val tracksEndpoint = baseAPIUrl + "/v1/tracks/"

  def getTrack(trackId: String): HttpRequest =
    createRequest(tracksEndpoint + trackId)

  def getTracks(trackIds: Seq[String]): HttpRequest =
    createRequest(tracksEndpoint, params = Seq(("ids", trackIds.mkString(","))))
}
