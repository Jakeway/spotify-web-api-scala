package models

case class TrackLink(
                    external_urls: Map[String, String],
                    href: String,
                    id: String,
                    `type`: String,
                    uri: String
                    )