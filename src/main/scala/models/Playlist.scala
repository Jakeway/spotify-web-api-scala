package models

case class Playlist(
                   collaborative: Boolean,
                   description: String,
                   external_urls: Map[String, String],
                   followers: Followers,
                   href: String,
                   id: String,
                   images: List[Image],
                   name: String,
                   owner: PublicUser,
                   public: Option[Boolean],
                   snapshot_id: String,
                   tracks: Page[PlaylistTrack],
                   `type`: String,
                   uri: String
                   )
