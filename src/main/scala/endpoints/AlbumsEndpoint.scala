package endpoints


object AlbumsEndpoint extends SpotifyEndpoint {

  private val albumEndpoint = baseAPIUrl + "/v1/albums/"

  def getAlbum(albumId: String): String = {
    makeRequest(endpoint = albumEndpoint + albumId)
  }

  def getAlbums(albumIds: String): String = {
    makeRequest(endpoint = albumEndpoint, params = Seq(("ids", albumIds)))
  }

  def getAlbumTracks(albumId: String): String = {
    makeRequest(endpoint = albumEndpoint + albumId + "/tracks")
  }

}
