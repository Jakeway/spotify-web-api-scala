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
      MeEndpoint.getUserSavedAlbums(oauthToken = authToken)

    def userSavedAlbumsContains(oauthToken: String, albumIds: String*): Option[String] =
      MeEndpoint.userSavedAlbumsContains(oauthToken = authToken, albumIds.mkString(","))

    def getAlbum(albumId: String): String =
      AlbumsEndpoint.getAlbum(albumId)

    def getAlbums(albumIds: String*): String =
      AlbumsEndpoint.getAlbums(albumIds.mkString(","))

    def getAlbumTracks(albumId: String): String =
      AlbumsEndpoint.getAlbumTracks(albumId)
  }

  object Artists {

    import endpoints.ArtistsEndpoint

    def getArtist(artistId: String): String =
      ArtistsEndpoint.getArtist(artistId)

    def getArtists(artistIds: String*): String =
      ArtistsEndpoint.getArtists(artistIds.mkString(","))

    def getArtistAlbums(artistId: String): String =
      ArtistsEndpoint.getArtistAlbums(artistId)

    def getArtistTopTracks(artistId: String): String =
      ArtistsEndpoint.getArtistTopTracks(artistId)

    def getArtistRelatedArtists(artistId: String): String =
      ArtistsEndpoint.getRelatedArtists(artistId)
  }

  object AudioFeatures {

    import endpoints.AudioFeaturesEndpoint

    def getAudioFeatures(oauthToken: String, trackId: String): Option[String] =
      AudioFeaturesEndpoint.getAudioFeatures(oauthToken = authToken, trackId = trackId)

    def getAudioFeatures(oauthToken: String, trackIds: String*): Option[String] =
      AudioFeaturesEndpoint.getMultipleAudioFeatures(oauthToken = authToken, trackIds.mkString(","))
  }

  object Browse {

    import endpoints.BrowseEndpoint

    def getFeaturedPlaylists(oauthToken: String): Option[String] =
      BrowseEndpoint.getFeaturedPlaylists(oauthToken = authToken)

    def getNewReleases(oauthToken: String): Option[String] =
      BrowseEndpoint.getNewReleases(oauthToken = authToken)

    def getCategories(oauthToken: String): Option[String] =
      BrowseEndpoint.getCategories(oauthToken = authToken)

    def getCategory(oauthToken: String, categoryId: String): Option[String] =
      BrowseEndpoint.getCategory(oauthToken = authToken, categoryId = categoryId)

    def getCategoryPlaylists(oauthToken: String, categoryId: String): Option[String] =
      BrowseEndpoint.getCategoryPlaylists(oauthToken = authToken, categoryId = categoryId)
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
        seedTracks = seedTracks)
  }

  object Search {

    import endpoints.SearchEndpoint

    def search(query: String, queryType: String*): String =
      SearchEndpoint.search(query, queryType.mkString(","))
  }

  object Tracks {

    import endpoints.{TracksEndpoint,MeEndpoint}

    def getTrack(trackId: String): String =
      TracksEndpoint.getTrack(trackId)

    def getTracks(trackIds: String*): String =
      TracksEndpoint.getTracks(trackIds.mkString(","))

    def getUserTracks(oauthToken: String): Option[String] =
      MeEndpoint.getUserTracks(oauthToken = authToken)

    def userTracksContains(oauthToken: String, trackIds: String*): Option[String] =
      MeEndpoint.userTracksContains(oauthToken = authToken, trackIds = trackIds.mkString(","))
  }

  object Users {

    import endpoints.{UsersEndpoint,MeEndpoint}

    def getCurrentUserPlaylists(oauthToken: String): Option[String] =
      MeEndpoint.getCurrentUserPlaylists(oauthToken = authToken)

    def getUserPlaylist(authToken: String, userId: String, playlistId: String): Option[String] =
      UsersEndpoint.getUserPlaylist(authToken = authToken, userId = userId, playlistId = playlistId)

    def getUserPlaylists(authToken: String, userId: String): Option[String] =
      UsersEndpoint.getUserPlaylists(authToken = authToken, userId = userId)

    def getUserPlaylistTracks(authToken: String, userId: String, playlistId: String): Option[String] =
      UsersEndpoint.getUserPlaylistTracks(authToken = authToken, userId = userId, playlistId = playlistId)

    def userFollowsPlaylist(authToken: String,
                            playlistOwnerId: String,
                            playlistId: String,
                            userIds: String*): Option[String] =
      UsersEndpoint.userFollowsPlaylist(authToken = authToken,
        playlistOwnerId = playlistOwnerId,
        playlistId = playlistId,
        userIds.mkString(","))

    def getUserFollowing(oauthToken: String): Option[String] =
      MeEndpoint.getUserFollowing(oauthToken = authToken)

    def userFollowingContains(oauthToken: String, containsType: String, ids: String*): Option[String] =
      MeEndpoint.userFollowingContains(oauthToken = authToken,
        containsType = containsType,
        ids = ids.mkString(","))

    def getUserTopArtists(oauthToken: String): Option[String] =
      MeEndpoint.getUserTopArtists(oauthToken = authToken)

    def getUserTopTracks(oauthToken: String): Option[String] =
      MeEndpoint.getUserTopTracks(oauthToken = authToken)

    def getUserProfile(userId: String): String =
      UsersEndpoint.getUserProfile(userId)

    def getCurrentUserProfile(oauthToken: String): Option[String] =
      MeEndpoint.getCurrentUserProfile(oauthToken = authToken)
  }
}
