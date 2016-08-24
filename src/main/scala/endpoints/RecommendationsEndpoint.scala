package endpoints

import scalaj.http.HttpResponse


object RecommendationsEndpoint extends OauthSpotifyEndpoint {

  private val recommendationsEndpoint = baseAPIUrl + "/v1/recommendations/"

  def getRecommendations(authToken: String,
                         seedArtists: Seq[String],
                         seedGenres: Seq[String],
                         seedTracks: Seq[String]): Option[HttpResponse[String]] = {

    // spotify dictates max of 5 seed combinations from {artists, genres, tracks}

    val totalSeqLengths = seedArtists.length + seedGenres.length + seedTracks.length
    if (totalSeqLengths > 5) None else {
      val params = Seq(
        ("seed_artists", seedArtists.mkString(",")),
        ("seed_genres", seedGenres.mkString(",")),
        ("seed_tracks", seedTracks.mkString(","))
      )
      makeRequest(authToken = authToken, endpoint = recommendationsEndpoint, params = params)
    }
  }
}
