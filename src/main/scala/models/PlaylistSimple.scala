package models

case class PlaylistSimple(
                           collaborative: Boolean,
                           external_urls: Map[String, String],
                           href: String,
                           id: String,
                           images: List[Image],
                           name: String,
                           owner: PublicUser,
                           public: Option[Boolean],
                           snapshot_id: String,
                           tracks: Map[String, String],
                           `type`: String,
                           uri: String
                         )