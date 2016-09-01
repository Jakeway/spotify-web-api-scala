package endpoints

import scalaj.http.HttpRequest


/**
  * The MeEndpoint contains all API calls which are related to the current user
  */

object MeEndpoint extends OauthSpotifyEndpoint {

  private val meEndpoint = baseAPIUrl + "/v1/me/"

  /**
    * Albums
    */

  def getUserSavedAlbums(oauthToken: String): Option[HttpRequest] =
    createRequest(authToken = oauthToken, endpoint = meEndpoint + "albums")


  def userSavedAlbumsContains(oauthToken: String, albumIds: Seq[String]): Option[HttpRequest] = {
    val endPoint = meEndpoint + "album/contains/"
    createRequest(authToken = oauthToken, endpoint = endPoint, params = Seq(("ids", albumIds.mkString(","))))
  }

  /**
    * Following
    */

  def getUserFollowing(oauthToken: String): Option[HttpRequest] =
    createRequest(authToken = oauthToken, endpoint = meEndpoint + "following", params = Seq(("type", "artist")))


  def userFollowingContains(oauthToken: String, containsType: String, ids: Seq[String]): Option[HttpRequest] =
    containsType.toUpperCase match {
      case "ARTIST" | "USER" =>
        val params = Seq(
          ("type", containsType),
          ("ids", ids.mkString(","))
        )
        val endpoint = meEndpoint + "following/contains"
        createRequest(authToken = oauthToken, endpoint = endpoint, params = params)
      case _ => None
    }

  /**
    * Tracks
    */

  def getUserTracks(oauthToken: String): Option[HttpRequest] =
    createRequest(authToken = oauthToken, endpoint = meEndpoint + "tracks")

  def userTracksContains(oauthToken: String, trackIds: Seq[String]): Option[HttpRequest] = {
    val endpoint = meEndpoint + "tracks/contains"
    createRequest(authToken = oauthToken, endpoint = endpoint, params = Seq(("ids", trackIds.mkString(","))))
  }

  /**
    * Personalization
    */

  private def getUserTopItems(oauthToken: String, itemType: String): Option[HttpRequest] =
    createRequest(authToken = oauthToken, endpoint = meEndpoint + "top/" + itemType)

  def getUserTopArtists(oauthToken: String): Option[HttpRequest] =
    getUserTopItems(oauthToken, "artists")

  def getUserTopTracks(oauthToken: String): Option[HttpRequest] =
    getUserTopItems(oauthToken, "tracks")

  /**
    * Playlists
    */

  def getCurrentUserPlaylists(oauthToken: String): Option[HttpRequest] =
    createRequest(authToken = oauthToken, endpoint = meEndpoint + "playlists")

  /**
    * Profiles
    */

  def getCurrentUserProfile(oauthToken: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = meEndpoint)
  }
}
