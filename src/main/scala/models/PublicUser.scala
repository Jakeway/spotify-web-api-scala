package models

case class PublicUser(
                     display_name: Option[String],
                     external_urls: Map[String, String],
                     followers: Option[Followers],
                     href: String,
                     id: String,
                     images: Option[List[Image]],
                     `type`: String,
                     uri: String
                     )