package models

case class Track(
                album: AlbumSimple,
                artists: List[ArtistSimple],
                available_markets: List[String],
                disc_number: Int,
                duration_ms: Int,
                explicit: Boolean,
                external_ids: Map[String, String],
                external_urls: Map[String, String],
                href: String,
                id: String,
                is_playable: Option[Boolean],
                linked_from: Option[TrackLink],
                name: String,
                popularity: Int,
                preview_url: String,
                track_number: Int,
                `type`: String,
                uri: String
                )
