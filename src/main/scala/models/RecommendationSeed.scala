package models

case class RecommendationSeed(
                               afterFilteringSize: Int,
                               afterRelinkingSize: Int,
                               href: String,
                               id: String,
                               initialPoolSize: Int,
                               `type`: String
                             )