package models

case class Album(
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
                  tracks: Page[TrackSimple],
                  `type`: String,
                  uri: String
                )