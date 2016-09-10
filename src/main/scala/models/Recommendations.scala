package models

case class Recommendations(
                          seeds: List[RecommendationSeed],
                          tracks: List[TrackSimple]
                          )
