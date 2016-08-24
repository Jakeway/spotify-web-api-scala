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

    def getUserSavedAlbums: Option[String] =
      MeEndpoint.getUserSavedAlbums(oauthToken = authToken).map(response => response.body).orElse(None)

    def userSavedAlbumsContains(albumIds: Seq[String]): Option[String] =
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

    def getAudioFeatures(trackId: String): Option[String] =
      AudioFeaturesEndpoint.
        getAudioFeatures(oauthToken = authToken, trackId = trackId).map(response => response.body).orElse(None)

    def getAudioFeatures(trackIds: Seq[String]): Option[String] =
      AudioFeaturesEndpoint.
        getMultipleAudioFeatures(oauthToken = authToken, trackIds).map(response => response.body).orElse(None)
  }

  object Browse {

    import endpoints.BrowseEndpoint

    def getFeaturedPlaylists: Option[String] =
      BrowseEndpoint.getFeaturedPlaylists(oauthToken = authToken).map(response => response.body).orElse(None)

    def getNewReleases: Option[String] =
      BrowseEndpoint.getNewReleases(oauthToken = authToken).map(response => response.body).orElse(None)

    def getCategories: Option[String] =
      BrowseEndpoint.getCategories(oauthToken = authToken).map(response => response.body).orElse(None)

    def getCategory(categoryId: String): Option[String] =
      BrowseEndpoint.
        getCategory(oauthToken = authToken, categoryId = categoryId).map(response => response.body).orElse(None)

    def getCategoryPlaylists(categoryId: String): Option[String] =
      BrowseEndpoint.
        getCategoryPlaylists(oauthToken = authToken, categoryId = categoryId).map(response => response.body).orElse(None)
  }

  object Recommendations {

    import endpoints.RecommendationsEndpoint

    def getRecommendations(seedArtists: Seq[String],
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

    def getUserTracks: Option[String] =
      MeEndpoint.getUserTracks(oauthToken = authToken).map(response => response.body).orElse(None)

    def userTracksContains(trackIds: Seq[String]): Option[String] =
      MeEndpoint.
        userTracksContains(oauthToken = authToken, trackIds = trackIds).map(response => response.body).orElse(None)
  }

  object Users {

    import endpoints.{UsersEndpoint,MeEndpoint}

    def getCurrentUserPlaylists: Option[String] =
      MeEndpoint.getCurrentUserPlaylists(oauthToken = authToken).map(response => response.body).orElse(None)

    def getUserPlaylist(userId: String, playlistId: String): Option[String] =
      UsersEndpoint
        .getUserPlaylist(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(response => response.body).orElse(None)

    def getUserPlaylists(userId: String): Option[String] =
      UsersEndpoint.getUserPlaylists(authToken = authToken, userId = userId).map(response => response.body).orElse(None)

    def getUserPlaylistTracks(userId: String, playlistId: String): Option[String] =
      UsersEndpoint
        .getUserPlaylistTracks(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(response => response.body).orElse(None)

    def userFollowsPlaylist(playlistOwnerId: String,
                            playlistId: String,
                            userIds: Seq[String]): Option[String] =
      UsersEndpoint.userFollowsPlaylist(authToken = authToken,
        playlistOwnerId = playlistOwnerId,
        playlistId = playlistId,
        userIds = userIds).map(response => response.body).orElse(None)

    def getUserFollowing: Option[String] =
      MeEndpoint.getUserFollowing(oauthToken = authToken).map(response => response.body).orElse(None)

    def userFollowingContains(containsType: String, ids: Seq[String]): Option[String] =
      MeEndpoint.userFollowingContains(oauthToken = authToken,
        containsType = containsType,
        ids = ids).map(response => response.body).orElse(None)

    def getUserTopArtists: Option[String] =
      MeEndpoint.getUserTopArtists(oauthToken = authToken).map(response => response.body).orElse(None)

    def getUserTopTracks: Option[String] =
      MeEndpoint.getUserTopTracks(oauthToken = authToken).map(response => response.body).orElse(None)

    def getUserProfile(userId: String): String =
      UsersEndpoint.getUserProfile(userId).body

    def getCurrentUserProfile: Option[String] =
      MeEndpoint.getCurrentUserProfile(oauthToken = authToken).map(response => response.body).orElse(None)
  }
}
