package endpoints

import scalaj.http.HttpRequest

object AudioFeaturesEndpoint extends OauthSpotifyEndpoint {

  private val audioFeaturesEndpoint = baseAPIUrl + "/v1/audio-features/"

  def getAudioFeatures(oauthToken: String, trackId: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = audioFeaturesEndpoint + trackId)
  }

  def getMultipleAudioFeatures(oauthToken: String, trackIds: Seq[String]): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = audioFeaturesEndpoint, params = Seq(("ids", trackIds.mkString(","))))
  }
}
