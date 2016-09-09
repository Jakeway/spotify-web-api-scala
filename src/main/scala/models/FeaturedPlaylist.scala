package models

case class FeaturedPlaylist(
                           message: String,
                           playlists: Page[PlaylistSimple]
                           )
