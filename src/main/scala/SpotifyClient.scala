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

    def userSavedAlbumsContains(albumIds: Seq[String]): Option[Boolean] =
      MeEndpoint.userSavedAlbumsContains(oauthToken = authToken, albumIds)
        .map(request => {
          val response = request.asString.body
          parse(response).extract[List[Boolean]].head
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

    def search(query: String, queryType: Seq[String]): String =
      SearchEndpoint.search(query, queryType).asString.body
  }

  object Tracks {

    def getTrack(trackId: String): String =
      TracksEndpoint.getTrack(trackId).asString.body

    def getTracks(trackIds: Seq[String]): String =
      TracksEndpoint.getTracks(trackIds).asString.body

    def getUserTracks: Option[String] =
      MeEndpoint.getUserTracks(oauthToken = authToken)
        .map(request => request.asString.body).orElse(None)

    def userTracksContains(trackIds: Seq[String]): Option[String] =
      MeEndpoint.
        userTracksContains(oauthToken = authToken, trackIds = trackIds)
        .map(request => request.asString.body).orElse(None)
  }

  object Users {

    def getCurrentUserPlaylists: Option[String] =
      MeEndpoint.getCurrentUserPlaylists(oauthToken = authToken)
        .map(request => request.asString.body).orElse(None)

    def getUserPlaylist(userId: String, playlistId: String): Option[String] =
      UsersEndpoint.getUserPlaylist(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(request => request.asString.body).orElse(None)

    def getUserPlaylists(userId: String): Option[String] =
      UsersEndpoint.getUserPlaylists(authToken = authToken, userId = userId)
        .map(request => request.asString.body).orElse(None)

    def getUserPlaylistTracks(userId: String, playlistId: String): Option[String] =
      UsersEndpoint.getUserPlaylistTracks(authToken = authToken, userId = userId, playlistId = playlistId)
        .map(request => request.asString.body).orElse(None)

    def userFollowsPlaylist(playlistOwnerId: String,
                            playlistId: String,
                            userIds: Seq[String]): Option[String] =
      UsersEndpoint.userFollowsPlaylist(
        authToken = authToken,
        playlistOwnerId = playlistOwnerId,
        playlistId = playlistId,
        userIds = userIds)
        .map(request => request.asString.body).orElse(None)

    def getUserFollowing: Option[String] =
      MeEndpoint.getUserFollowing(oauthToken = authToken)
        .map(request => request.asString.body).orElse(None)

    def userFollowingContains(containsType: String, ids: Seq[String]): Option[String] =
      MeEndpoint.userFollowingContains(
        oauthToken = authToken,
        containsType = containsType,
        ids = ids)
        .map(request => request.asString.body).orElse(None)

    def getUserTopArtists: Option[String] =
      MeEndpoint.getUserTopArtists(oauthToken = authToken)
        .map(request => request.asString.body).orElse(None)

    def getUserTopTracks: Option[String] =
      MeEndpoint.getUserTopTracks(oauthToken = authToken)
        .map(request => request.asString.body).orElse(None)

    def getUserProfile(userId: String): String =
      UsersEndpoint.getUserProfile(userId).asString.body

    def getCurrentUserProfile: Option[String] =
      MeEndpoint.getCurrentUserProfile(oauthToken = authToken)
        .map(request => request.asString.body).orElse(None)
  }
}
