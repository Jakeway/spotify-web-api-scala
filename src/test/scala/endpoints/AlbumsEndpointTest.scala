package endpoints

import org.scalatest.FlatSpec

/**
  * Sample testing of an endpoint - only verifies the response code given by Spotify, not the actual content.
  */

class AlbumsEndpointTest extends FlatSpec {

  val reflektorAlbumId = "4E0m7AIVc2d2QZMrMNXdMZ"
  val subrbsAlbumId = "76Rnn8D33IjnJxv4hQdmRX"

  "Get album" should "return status code 200" in {
    assert(AlbumsEndpoint.getAlbum(reflektorAlbumId).code == 200)
  }

  "Get albums" should "return status code 200" in {
    assert(AlbumsEndpoint.getAlbums(Seq(reflektorAlbumId, subrbsAlbumId)).code == 200)
  }

  "Get album tracks" should "return status code 200" in {
    assert(AlbumsEndpoint.getAlbumTracks(reflektorAlbumId).code == 200)
  }
}
