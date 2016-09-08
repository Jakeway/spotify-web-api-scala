package models

case class Artist(
                   external_urls: ExternalURL,
                   followers: Followers,
                   genres: List[String],
                   href: String,
                   id: String,
                   images: List[Image],
                   name: String,
                   popularity: Int,
                   `type`: String,
                   uri: String
                 )