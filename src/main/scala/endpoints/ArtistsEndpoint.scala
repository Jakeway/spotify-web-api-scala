package endpoints


object ArtistsEndpoint extends SpotifyEndpoint {

  private val artistEndpoint = baseAPIUrl + "/v1/artists/"

  def getArtist(artistId: String): String = {
    makeRequest(endpoint = artistEndpoint + artistId)
  }

  def getArtists(artistIds: String): String = {
    makeRequest(endpoint = artistEndpoint, params = Seq(("ids", artistIds)))
  }

  def getArtistAlbums(artistId: String): String = {
    makeRequest(endpoint = artistEndpoint + artistId + "/albums")
  }

  def getArtistTopTracks(artistId: String): String = {
    makeRequest(endpoint = artistEndpoint + artistId + "/top-tracks")
  }

  def getRelatedArtists(artistId: String): String = {
    makeRequest(endpoint = artistEndpoint + artistId + "/related-artists")
  }
}
