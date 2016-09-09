package models

case class TrackSimple(
                      artists: List[ArtistSimple],
                      available_markets: List[String],
                      disc_number: Int,
                      duration_ms: Int,
                      explicit: Boolean,
                      external_urls: Map[String, String],
                      href: String,
                      id: String,
                      is_playable: Option[Boolean],
                      linked_from: Option[TrackLink],
                      name: String,
                      preview_url: String,
                      track_number: Int,
                      `type`: String,
                      uri: String
                      )

