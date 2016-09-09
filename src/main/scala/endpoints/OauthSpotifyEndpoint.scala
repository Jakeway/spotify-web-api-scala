package endpoints

import scalaj.http.{Http, HttpRequest}

abstract class OauthSpotifyEndpoint extends SpotifyEndpoint {

  private def getOauthBearer(authToken: String): Option[String] = authToken match {
    case "" => None
    case token => Some("Bearer " + token)
  }

  private def getAuthHeaders(authToken: String): Option[Map[String, String]] = getOauthBearer(authToken) match {
    case None => None
    case Some(authHeader) => Some(Map("Authorization" -> authHeader))
  }

  protected def createRequest(authToken:String, endpoint: String): Option[HttpRequest] = {
    getAuthHeaders(authToken).map(headers => {
      Some(Http(endpoint).headers(headers))
    }).getOrElse(None)
  }

  protected def createRequest(authToken:String,
                            endpoint: String,
                            params: Seq[(String, String)]) : Option[HttpRequest] = {
    getAuthHeaders(authToken).map(headers => {
      Some(Http(endpoint).headers(headers).params(params))
    }).getOrElse(None)
  }
}
