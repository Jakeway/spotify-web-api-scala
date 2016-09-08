package models

case class AudioFeatures(
                        acousticness: Float,
                        analysis_url: String,
                        danceability: Float,
                        duration_ms: Int,
                        energy: Float,
                        id: String,
                        instrumentalness: Float,
                        key: Int,
                        liveness: Float,
                        loudness: Float,
                        mode: Int,
                        speechiness: Float,
                        tempo: Float,
                        time_signature: Float,
                        track_href: String,
                        `type`: String,
                        uri: String,
                        valence: Float
                        )
