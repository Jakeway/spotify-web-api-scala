package endpoints

import org.scalatest.FlatSpec



class AlbumsEndpointTest extends FlatSpec {

  val reflektorAlbumId = "4E0m7AIVc2d2QZMrMNXdMZ"
  val suburbsAlbumId = "76Rnn8D33IjnJxv4hQdmRX"

  "Get album" should "create proper URL" in {
    assert(
      AlbumsEndpoint.getAlbum(reflektorAlbumId).url == "https://api.spotify.com/v1/albums/4E0m7AIVc2d2QZMrMNXdMZ")
  }

  "Get album tracks" should "create proper URL" in {
    assert(
      AlbumsEndpoint.getAlbumTracks(reflektorAlbumId).url == "https://api.spotify.com/v1/albums/4E0m7AIVc2d2QZMrMNXdMZ/tracks")
  }
}
