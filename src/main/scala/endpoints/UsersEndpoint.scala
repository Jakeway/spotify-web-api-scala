package endpoints

import scalaj.http.HttpRequest


object UsersEndpoint extends OauthSpotifyEndpoint {

  private val usersEndpoint = baseAPIUrl + "/v1/users/"

  /**
    * Playlists
    */

  def getUserPlaylist(authToken: String, userId: String, playlistId: String): Option[HttpRequest] = {
    createRequest(authToken = authToken, endpoint = usersEndpoint + userId + "/playlists/" + playlistId)
  }

  def getUserPlaylists(authToken: String, userId: String): Option[HttpRequest] = {
    createRequest(authToken = authToken, endpoint = usersEndpoint + userId + "/playlists")
  }

  def getUserPlaylistTracks(authToken: String, userId: String, playlistId: String): Option[HttpRequest] = {
    createRequest(authToken = authToken, endpoint = usersEndpoint + userId + "/playlists" + playlistId + "/tracks")
  }

  def userFollowsPlaylist( authToken: String,
                           playlistOwnerId: String,
                           playlistId: String,
                           userIds: Seq[String]): Option[HttpRequest] = {

    // create userIds allowed is 5
    val userIdsLength = userIds.length
    if (userIdsLength > 5) None else {
      val endpoint = usersEndpoint + playlistOwnerId + "/playlists/" + playlistId + "/followers/contains"
      val params = Seq(
        ("ids", userIds.mkString(","))
      )
      createRequest(authToken = authToken, endpoint = endpoint, params = params)
    }
  }

  /**
    * Profiles
    */

  def getUserProfile(userId: String): HttpRequest = {
    createRequest(usersEndpoint + userId)
  }
}
