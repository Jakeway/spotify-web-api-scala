package endpoints

import scalaj.http.HttpResponse


object AlbumsEndpoint extends SpotifyEndpoint {

  private val albumEndpoint = baseAPIUrl + "/v1/albums/"

  def getAlbum(albumId: String): HttpResponse[String] = {
    makeRequest(endpoint = albumEndpoint + albumId)
  }

  def getAlbums(albumIds: Seq[String]): HttpResponse[String] = {
    makeRequest(endpoint = albumEndpoint, params = Seq(("ids", albumIds.mkString(","))))
  }

  def getAlbumTracks(albumId: String): HttpResponse[String] = {
    makeRequest(endpoint = albumEndpoint + albumId + "/tracks")
  }
}
