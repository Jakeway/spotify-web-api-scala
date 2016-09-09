package models

case class PlaylistTrack(
                          added_at: java.util.Date,
                          added_by: Option[PublicUser],
                          is_local: Boolean,
                          track: Track
                        )
