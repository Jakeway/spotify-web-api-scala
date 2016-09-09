package models

case class AlbumSimple(
                        album_type: String,
                        available_markets: List[String],
                        external_urls: Map[String, String],
                        href: String,
                        id: String,
                        images: List[Image],
                        name: String,
                        `type`: String,
                        uri: String
                      )
