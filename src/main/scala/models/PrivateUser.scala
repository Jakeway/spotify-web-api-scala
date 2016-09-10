package models

case class PrivateUser(
                      birthdate: Option[String],
                      country: Option[String],
                      display_name: Option[String],
                      email: Option[String],
                      external_urls: Map[String, String],
                      followers: Followers,
                      href: String,
                      id: String,
                      images: List[Image],
                      product: Option[String],
                      `type`: String,
                      uri: String
                      )