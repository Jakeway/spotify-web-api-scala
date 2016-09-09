package models

case class ArtistSimple(
                         external_urls: Map[String, String],
                         href: String,
                         id: String,
                         name: String,
                         `type`: String,
                         uri: String
                       )