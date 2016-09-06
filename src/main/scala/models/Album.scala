package models

trait Album

case class AlbumFull(
                    album_type: String,
                    artists: List[ArtistSimple],
                    available_markets: List[String],
                    copyrights: List[Copyright],
                    external_ids: ExternalSpotifyID,
                    external_urls: ExternalURL,
                    genres: List[String],
                    href: String,
                    id: String,
                    images: List[Image],
                    name: String,
                    popularity: Int,
                    release_date: String,
                    release_date_precision: String,
                    tracks: Paging[TrackSimple],
                    `type`: String,
                    uri: String
                  ) extends Album