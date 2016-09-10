package models

case class CursorPage[T](
                 href: String,
                 items: List[T],
                 limit: Int,
                 next: Option[String],
                 cursors: Cursor,
                 total: Int
                 )
