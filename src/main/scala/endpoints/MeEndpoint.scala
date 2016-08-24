package endpoints

import scalaj.http.HttpResponse


/**
  * The MeEndpoint contains all API calls which are related to the current user
  */

object MeEndpoint extends OauthSpotifyEndpoint {

  private val meEndpoint = baseAPIUrl + "/v1/me/"

  /**
    * Albums
    */

  def getUserSavedAlbums(oauthToken: String): Option[HttpResponse[String]] =
    makeRequest(authToken = oauthToken, endpoint = meEndpoint + "albums")


  def userSavedAlbumsContains(oauthToken: String, albumIds: Seq[String]): Option[HttpResponse[String]] = {
    val endPoint = meEndpoint + "album/contains/"
    makeRequest(authToken = oauthToken, endpoint = endPoint, params = Seq(("ids", albumIds.mkString(","))))
  }

  /**
    * Following
    */

  def getUserFollowing(oauthToken: String): Option[HttpResponse[String]] =
    makeRequest(authToken = oauthToken, endpoint = meEndpoint + "following", params = Seq(("type", "artist")))


  def userFollowingContains(oauthToken: String, containsType: String, ids: Seq[String]): Option[HttpResponse[String]] =
    containsType.toUpperCase match {
      case "ARTIST" | "USER" =>
        val params = Seq(
          ("type", containsType),
          ("ids", ids.mkString(","))
        )
        val endpoint = meEndpoint + "following/contains"
        makeRequest(authToken = oauthToken, endpoint = endpoint, params = params)
      case _ => None
    }

  /**
    * Tracks
    */

  def getUserTracks(oauthToken: String): Option[HttpResponse[String]] =
    makeRequest(authToken = oauthToken, endpoint = meEndpoint + "tracks")

  def userTracksContains(oauthToken: String, trackIds: Seq[String]): Option[HttpResponse[String]] = {
    val endpoint = meEndpoint + "tracks/contains"
    makeRequest(authToken = oauthToken, endpoint = endpoint, params = Seq(("ids", trackIds.mkString(","))))
  }

  /**
    * Personalization
    */

  private def getUserTopItems(oauthToken: String, itemType: String): Option[HttpResponse[String]] =
    makeRequest(authToken = oauthToken, endpoint = meEndpoint + "top/" + itemType)

  def getUserTopArtists(oauthToken: String): Option[HttpResponse[String]] =
    getUserTopItems(oauthToken, "artists")

  def getUserTopTracks(oauthToken: String): Option[HttpResponse[String]] =
    getUserTopItems(oauthToken, "tracks")

  /**
    * Playlists
    */

  def getCurrentUserPlaylists(oauthToken: String): Option[HttpResponse[String]] =
    makeRequest(authToken = oauthToken, endpoint = meEndpoint + "playlists")

  /**
    * Profiles
    */

  def getCurrentUserProfile(oauthToken: String): Option[HttpResponse[String]] = {
    makeRequest(authToken = oauthToken, endpoint = meEndpoint)
  }
}
