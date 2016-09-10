import java.text.SimpleDateFormat

import endpoints._
import models._
import org.json4s._
import org.json4s.jackson.JsonMethods._


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

  implicit val formats = new DefaultFormats {
    override def dateFormatter: SimpleDateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:SS'Z'")
  }

  object Albums {

    def getUserSavedAlbums: Option[Page[SavedAlbum]] =
      MeEndpoint.getUserSavedAlbums(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[SavedAlbum]]
        }).orElse(None)

    def userSavedAlbumsContains(albumIds: Seq[String]): Option[List[Boolean]] =
      MeEndpoint.userSavedAlbumsContains(oauthToken = authToken, albumIds)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[List[Boolean]]
        }).orElse(None)

    def getAlbum(albumId: String): Album = {
      val response = AlbumsEndpoint.getAlbum(albumId).asString
      parse(response.body).extract[Album]
    }

    def getAlbums(albumIds: Seq[String]): Seq[Album] = {
      val response = AlbumsEndpoint.getAlbums(albumIds).asString
      val json = parse(response.body)
      (json \ "albums").extract[List[Album]]
    }

    def getAlbumTracks(albumId: String): Page[TrackSimple] = {
      val response = AlbumsEndpoint.getAlbumTracks(albumId).asString
      parse(response.body).extract[Page[TrackSimple]]
    }
  }

  object Artists {

    def getArtist(artistId: String): Artist = {
      val response = ArtistsEndpoint.getArtist(artistId).asString
      parse(response.body).extract[Artist]
    }

    def getArtists(artistIds: Seq[String]): Seq[Artist] = {
      val response = ArtistsEndpoint.getArtists(artistIds).asString
      val json = parse(response.body)
      (json \ "artists").extract[List[Artist]]
    }

    def getArtistAlbums(artistId: String): Page[AlbumSimple] = {
      val response = ArtistsEndpoint.getArtistAlbums(artistId).asString
      parse(response.body).extract[Page[AlbumSimple]]
    }

    def getArtistTopTracks(artistId: String): Seq[Track] = {
      val response = ArtistsEndpoint.getArtistTopTracks(artistId).asString
      val json = parse(response.body)
      (json \ "tracks").extract[List[Track]]
    }

    def getArtistRelatedArtists(artistId: String): Seq[Artist] = {
      val response = ArtistsEndpoint.getRelatedArtists(artistId).asString
      val json = parse(response.body)
      (json \ "artists").extract[List[Artist]]
    }
  }

  object AudioFeatures {

    def getAudioFeatures(trackId: String): Option[AudioFeatures] =
      AudioFeaturesEndpoint.getAudioFeatures(oauthToken = authToken, trackId = trackId)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[AudioFeatures]
        }).orElse(None)

    def getAudioFeatures(trackIds: Seq[String]): Option[Seq[AudioFeatures]] =
      AudioFeaturesEndpoint.getMultipleAudioFeatures(oauthToken = authToken, trackIds)
        .map(request => {
          val response = request.asString
          val json = parse(response.body)
          (json \ "audio_features").extract[List[AudioFeatures]]
        }).orElse(None)
  }

  object Browse {

    def getFeaturedPlaylists: Option[FeaturedPlaylist] =
      BrowseEndpoint.getFeaturedPlaylists(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          val json = parse(response.body)
          val message = (json \ "message").extract[String]
          val playlists = (json \ "playlists").extract[Page[PlaylistSimple]]
          FeaturedPlaylist(message, playlists)
        }).orElse(None)

    def getNewReleases: Option[Page[AlbumSimple]] =
      BrowseEndpoint.getNewReleases(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          val json = parse(response.body)
          (json \ "albums").extract[Page[AlbumSimple]]
        }).orElse(None)

    def getCategories: Option[Page[Category]] =
      BrowseEndpoint.getCategories(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          val json = parse(response.body)
          (json \ "categories").extract[Page[Category]]
        }).orElse(None)

    def getCategory(categoryId: String): Option[Category] =
      BrowseEndpoint.getCategory(oauthToken = authToken, categoryId = categoryId)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Category]
        }).orElse(None)

    def getCategoryPlaylists(categoryId: String): Option[Page[PlaylistSimple]] =
      BrowseEndpoint.getCategoryPlaylists(oauthToken = authToken, categoryId = categoryId)
        .map(request => {
          val response = request.asString
          val json = parse(response.body)
          (json \ "playlists").extract[Page[PlaylistSimple]]
        }).orElse(None)
  }

  object Recommendations {

    def getRecommendations(seedArtists: Seq[String] = Seq.empty,
                           seedGenres: Seq[String] = Seq.empty,
                           seedTracks: Seq[String] = Seq.empty): Option[Recommendations] =

      RecommendationsEndpoint.getRecommendations(authToken = authToken,
        seedArtists = seedArtists,
        seedGenres = seedGenres,
        seedTracks = seedTracks)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Recommendations]
        }).orElse(None)
  }

  object Search {

    def search(query: String, queryType: Seq[String]): SearchResult = {
      val response = SearchEndpoint.search(query.replace(" ", "+"), queryType).asString
      parse(response.body).extract[SearchResult]
    }
  }

  object Tracks {

    def getTrack(trackId: String): Track = {
      val response = TracksEndpoint.getTrack(trackId).asString
      parse(response.body).extract[Track]
    }

    def getTracks(trackIds: Seq[String]): Seq[Track] = {
      val response = TracksEndpoint.getTracks(trackIds).asString
      val json = parse(response.body)
      (json \ "tracks").extract[List[Track]]
    }

    def getUserSavedTracks: Option[Page[SavedTrack]] =
      MeEndpoint.getUserTracks(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[SavedTrack]]
        }).orElse(None)

    def userSavedTracksContains(trackIds: Seq[String]): Option[List[Boolean]] =
      MeEndpoint.userTracksContains(oauthToken = authToken, trackIds = trackIds)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[List[Boolean]]
        }).orElse(None)
  }

  object Users {

    def getCurrentUserPlaylists: Option[Page[PlaylistSimple]] =
      MeEndpoint.getCurrentUserPlaylists(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[PlaylistSimple]]
        }).orElse(None)

    def getUserPlaylist(userId: String, playlistId: String): Option[Playlist] =
      UsersEndpoint.getUserPlaylist(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Playlist]
        }).orElse(None)

    def getUserPlaylists(userId: String): Option[Page[PlaylistSimple]] =
      UsersEndpoint.getUserPlaylists(authToken = authToken, userId = userId)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[PlaylistSimple]]
        }).orElse(None)

    def getUserPlaylistTracks(userId: String, playlistId: String): Option[Page[PlaylistTrack]] =
      UsersEndpoint.getUserPlaylistTracks(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[PlaylistTrack]]
        }).orElse(None)

    def usersFollowPlaylist(playlistOwnerId: String,
                            playlistId: String,
                            userIds: Seq[String]): Option[List[Boolean]] =
      UsersEndpoint.userFollowsPlaylist(
        authToken = authToken,
        playlistOwnerId = playlistOwnerId,
        playlistId = playlistId,
        userIds = userIds)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[List[Boolean]]
        }).orElse(None)

    def getUserFollowing: Option[CursorPage[Artist]] =
      MeEndpoint.getUserFollowing(oauthToken = authToken, `type` = "artist")
        .map(request => {
          val response = request.asString
          val json = parse(response.body)
          (json \ "artists").extract[CursorPage[Artist]]
        }).orElse(None)

    def currentUserFollowingContains(containsType: String, ids: Seq[String]): Option[List[Boolean]] =
      MeEndpoint.userFollowingContains(
        oauthToken = authToken,
        containsType = containsType,
        ids = ids)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[List[Boolean]]
        }).orElse(None)

    def getUserTopArtists: Option[Page[Artist]] =
      MeEndpoint.getUserTopArtists(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[Artist]]
        }).orElse(None)

    def getUserTopTracks: Option[Page[Track]] =
      MeEndpoint.getUserTopTracks(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[Page[Track]]
        }).orElse(None)

    def getUserProfile(userId: String): PublicUser = {
      val response = UsersEndpoint.getUserProfile(userId).asString
      parse(response.body).extract[PublicUser]
    }

    def getCurrentUserProfile: Option[PrivateUser] = {
      MeEndpoint.getCurrentUserProfile(oauthToken = authToken)
        .map(request => {
          val response = request.asString
          parse(response.body).extract[PrivateUser]
        }).orElse(None)
    }
  }
}
