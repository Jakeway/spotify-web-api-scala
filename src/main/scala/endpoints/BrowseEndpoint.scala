package endpoints

import scalaj.http.HttpRequest

object BrowseEndpoint extends OauthSpotifyEndpoint {

  private val browseEndpoint = baseAPIUrl + "/v1/browse/"

  def getFeaturedPlaylists(oauthToken: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = browseEndpoint + "featured-playlists")
  }

  def getNewReleases(oauthToken: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = browseEndpoint + "new-releases")
  }

  def getCategories(oauthToken: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = browseEndpoint + "categories")
  }

  def getCategory(oauthToken: String, categoryId: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = browseEndpoint + "categories/" + categoryId)
  }

  def getCategoryPlaylists(oauthToken: String, categoryId: String): Option[HttpRequest] = {
    createRequest(authToken = oauthToken, endpoint = browseEndpoint + "categories/" + categoryId + "/playlists")
  }

}
