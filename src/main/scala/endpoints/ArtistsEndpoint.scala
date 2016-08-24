package endpoints

import scalaj.http.HttpResponse


object ArtistsEndpoint extends SpotifyEndpoint {

  private val artistEndpoint = baseAPIUrl + "/v1/artists/"

  def getArtist(artistId: String): HttpResponse[String] = {
    makeRequest(endpoint = artistEndpoint + artistId)
  }

  def getArtists(artistIds: Seq[String]): HttpResponse[String] = {
    makeRequest(endpoint = artistEndpoint, params = Seq(("ids", artistIds.mkString(","))))
  }

  def getArtistAlbums(artistId: String): HttpResponse[String] = {
    makeRequest(endpoint = artistEndpoint + artistId + "/albums")
  }

  def getArtistTopTracks(artistId: String): HttpResponse[String] = {
    makeRequest(endpoint = artistEndpoint + artistId + "/top-tracks")
  }

  def getRelatedArtists(artistId: String): HttpResponse[String] = {
    makeRequest(endpoint = artistEndpoint + artistId + "/related-artists")
  }
}
