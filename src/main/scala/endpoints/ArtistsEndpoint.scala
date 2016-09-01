package endpoints

import scalaj.http.HttpRequest


object ArtistsEndpoint extends SpotifyEndpoint {

  private val artistEndpoint = baseAPIUrl + "/v1/artists/"

  def getArtist(artistId: String): HttpRequest = {
    createRequest(endpoint = artistEndpoint + artistId)
  }

  def getArtists(artistIds: Seq[String]): HttpRequest = {
    createRequest(endpoint = artistEndpoint, params = Seq(("ids", artistIds.mkString(","))))
  }

  def getArtistAlbums(artistId: String): HttpRequest = {
    createRequest(endpoint = artistEndpoint + artistId + "/albums")
  }

  def getArtistTopTracks(artistId: String): HttpRequest = {
    createRequest(endpoint = artistEndpoint + artistId + "/top-tracks")
  }

  def getRelatedArtists(artistId: String): HttpRequest = {
    createRequest(endpoint = artistEndpoint + artistId + "/related-artists")
  }
}
