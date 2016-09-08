package models

case class ArtistSimple(
                         external_urls: ExternalURL,
                         href: String,
                         id: String,
                         name: String,
                         `type`: String,
                         uri: String
                       )