package endpoints

import scalaj.http.HttpResponse

object AudioFeaturesEndpoint extends OauthSpotifyEndpoint {

  private val audioFeaturesEndpoint = baseAPIUrl + "/v1/audio-features/"

  def getAudioFeatures(oauthToken: String, trackId: String): Option[HttpResponse[String]] = {
    makeRequest(authToken = oauthToken, endpoint = audioFeaturesEndpoint + trackId)
  }

  def getMultipleAudioFeatures(oauthToken: String, trackIds: Seq[String]): Option[HttpResponse[String]] = {
    makeRequest(authToken = oauthToken, endpoint = audioFeaturesEndpoint, params = Seq(("ids", trackIds.mkString(","))))
  }
}
