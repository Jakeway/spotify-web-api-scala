package models

case class SearchResult(
                       albums: Option[Page[AlbumSimple]],
                       artists: Option[Page[Artist]],
                       tracks: Option[Page[Track]],
                       playlists: Option[Page[PlaylistSimple]]
                       )