/**
  * SpotifyClient is the main class used to interact with the Spotify API's.
  *
  * If the return type of a method is wrapped in an Option, this means that the method
  * requires an authToken to successfully call. These methods will return None if token
  * is not present, expired, or perhaps typed wrong.
  *
  * @param authToken oauthToken used to make API calls which require authorization
  *
  */
class SpotifyClient(authToken: String = "") {

  object Albums {

    import endpoints.{AlbumsEndpoint, MeEndpoint}

    def getUserSavedAlbums(oauthToken: String): Option[String] =
      MeEndpoint.getUserSavedAlbums(oauthToken = authToken).map(response => response.body).orElse(None)

    def userSavedAlbumsContains(oauthToken: String, albumIds: Seq[String]): Option[String] =
      MeEndpoint.userSavedAlbumsContains(oauthToken = authToken, albumIds).map(response => response.body).orElse(None)

    def getAlbum(albumId: String): String =
      AlbumsEndpoint.getAlbum(albumId).body

    def getAlbums(albumIds: Seq[String]): String =
      AlbumsEndpoint.getAlbums(albumIds).body

    def getAlbumTracks(albumId: String): String =
      AlbumsEndpoint.getAlbumTracks(albumId).body
  }

  object Artists {

    import endpoints.ArtistsEndpoint

    def getArtist(artistId: String): String =
      ArtistsEndpoint.getArtist(artistId).body

    def getArtists(artistIds: Seq[String]): String =
      ArtistsEndpoint.getArtists(artistIds).body

    def getArtistAlbums(artistId: String): String =
      ArtistsEndpoint.getArtistAlbums(artistId).body

    def getArtistTopTracks(artistId: String): String =
      ArtistsEndpoint.getArtistTopTracks(artistId).body

    def getArtistRelatedArtists(artistId: String): String =
      ArtistsEndpoint.getRelatedArtists(artistId).body
  }

  object AudioFeatures {

    import endpoints.AudioFeaturesEndpoint

    def getAudioFeatures(oauthToken: String, trackId: String): Option[String] =
      AudioFeaturesEndpoint.
        getAudioFeatures(oauthToken = authToken, trackId = trackId).map(response => response.body).orElse(None)

    def getAudioFeatures(oauthToken: String, trackIds: Seq[String]): Option[String] =
      AudioFeaturesEndpoint.
        getMultipleAudioFeatures(oauthToken = authToken, trackIds).map(response => response.body).orElse(None)
  }

  object Browse {

    import endpoints.BrowseEndpoint

    def getFeaturedPlaylists(oauthToken: String): Option[String] =
      BrowseEndpoint.getFeaturedPlaylists(oauthToken = authToken).map(response => response.body).orElse(None)

    def getNewReleases(oauthToken: String): Option[String] =
      BrowseEndpoint.getNewReleases(oauthToken = authToken).map(response => response.body).orElse(None)

    def getCategories(oauthToken: String): Option[String] =
      BrowseEndpoint.getCategories(oauthToken = authToken).map(response => response.body).orElse(None)

    def getCategory(oauthToken: String, categoryId: String): Option[String] =
      BrowseEndpoint.
        getCategory(oauthToken = authToken, categoryId = categoryId).map(response => response.body).orElse(None)

    def getCategoryPlaylists(oauthToken: String, categoryId: String): Option[String] =
      BrowseEndpoint.
        getCategoryPlaylists(oauthToken = authToken, categoryId = categoryId).map(response => response.body).orElse(None)
  }

  object Recommendations {

    import endpoints.RecommendationsEndpoint

    def getRecommendations(authToken: String,
                           seedArtists: Seq[String],
                           seedGenres: Seq[String],
                           seedTracks: Seq[String]): Option[String] =

      RecommendationsEndpoint.getRecommendations(authToken = authToken,
        seedArtists = seedArtists,
        seedGenres = seedGenres,
        seedTracks = seedTracks).map(response => response.body).orElse(None)
  }

  object Search {

    import endpoints.SearchEndpoint

    def search(query: String, queryType: Seq[String]): String =
      SearchEndpoint.search(query, queryType).body
  }

  object Tracks {

    import endpoints.{TracksEndpoint,MeEndpoint}

    def getTrack(trackId: String): String =
      TracksEndpoint.getTrack(trackId).body

    def getTracks(trackIds: Seq[String]): String =
      TracksEndpoint.getTracks(trackIds).body

    def getUserTracks(oauthToken: String): Option[String] =
      MeEndpoint.getUserTracks(oauthToken = authToken).map(response => response.body).orElse(None)

    def userTracksContains(oauthToken: String, trackIds: Seq[String]): Option[String] =
      MeEndpoint.
        userTracksContains(oauthToken = authToken, trackIds = trackIds).map(response => response.body).orElse(None)
  }

  object Users {

    import endpoints.{UsersEndpoint,MeEndpoint}

    def getCurrentUserPlaylists(oauthToken: String): Option[String] =
      MeEndpoint.getCurrentUserPlaylists(oauthToken = authToken).map(response => response.body).orElse(None)

    def getUserPlaylist(authToken: String, userId: String, playlistId: String): Option[String] =
      UsersEndpoint
        .getUserPlaylist(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(response => response.body).orElse(None)

    def getUserPlaylists(authToken: String, userId: String): Option[String] =
      UsersEndpoint.getUserPlaylists(authToken = authToken, userId = userId).map(response => response.body).orElse(None)

    def getUserPlaylistTracks(authToken: String, userId: String, playlistId: String): Option[String] =
      UsersEndpoint
        .getUserPlaylistTracks(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(response => response.body).orElse(None)

    def userFollowsPlaylist(authToken: String,
                            playlistOwnerId: String,
                            playlistId: String,
                            userIds: Seq[String]): Option[String] =
      UsersEndpoint.userFollowsPlaylist(authToken = authToken,
        playlistOwnerId = playlistOwnerId,
        playlistId = playlistId,
        userIds = userIds).map(response => response.body).orElse(None)

    def getUserFollowing(oauthToken: String): Option[String] =
      MeEndpoint.getUserFollowing(oauthToken = authToken).map(response => response.body).orElse(None)

    def userFollowingContains(oauthToken: String, containsType: String, ids: Seq[String]): Option[String] =
      MeEndpoint.userFollowingContains(oauthToken = authToken,
        containsType = containsType,
        ids = ids).map(response => response.body).orElse(None)

    def getUserTopArtists(oauthToken: String): Option[String] =
      MeEndpoint.getUserTopArtists(oauthToken = authToken).map(response => response.body).orElse(None)

    def getUserTopTracks(oauthToken: String): Option[String] =
      MeEndpoint.getUserTopTracks(oauthToken = authToken).map(response => response.body).orElse(None)

    def getUserProfile(userId: String): String =
      UsersEndpoint.getUserProfile(userId).body

    def getCurrentUserProfile(oauthToken: String): Option[String] =
      MeEndpoint.getCurrentUserProfile(oauthToken = authToken).map(response => response.body).orElse(None)
  }
}
