package endpoints

object AudioFeaturesEndpoint extends OauthSpotifyEndpoint {

  private val audioFeaturesEndpoint = baseAPIUrl + "/v1/audio-features/"

  def getAudioFeatures(oauthToken: String, trackId: String): Option[String] = {
    makeRequest(authToken = oauthToken, endpoint = audioFeaturesEndpoint + trackId)
  }

  def getMultipleAudioFeatures(oauthToken: String, trackIds: String): Option[String] = {
    makeRequest(authToken = oauthToken, endpoint = audioFeaturesEndpoint, params = Seq(("ids", trackIds)))
  }
}
