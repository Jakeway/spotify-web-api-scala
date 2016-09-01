package endpoints

import scalaj.http.HttpRequest


object AlbumsEndpoint extends SpotifyEndpoint {

  private val albumEndpoint = baseAPIUrl + "/v1/albums/"

  def getAlbum(albumId: String): HttpRequest = {
    createRequest(endpoint = albumEndpoint + albumId)
  }

  def getAlbums(albumIds: Seq[String]): HttpRequest = {
    createRequest(endpoint = albumEndpoint, params = Seq(("ids", albumIds.mkString(","))))
  }

  def getAlbumTracks(albumId: String): HttpRequest = {
    createRequest(endpoint = albumEndpoint + albumId + "/tracks")
  }
}
