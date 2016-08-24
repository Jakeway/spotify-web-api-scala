package endpoints

import scalaj.http.{Http, HttpResponse}

abstract class OauthSpotifyEndpoint extends SpotifyEndpoint {

  private def getOauthBearer(authToken: String): Option[String] = authToken match {
    case "" => None
    case token => Some("Bearer " + token)
  }

  protected def getAuthHeaders(authToken: String): Option[Map[String, String]] = getOauthBearer(authToken) match {
    case None => None
    case Some(authHeader) => Some(Map("Authorization" -> authHeader))
  }

  protected def makeRequest(authToken:String, endpoint: String): Option[HttpResponse[String]] = {
    getAuthHeaders(authToken).map(headers => {
      Some(Http(endpoint).headers(headers).asString)
    }).getOrElse(None)
  }

  protected def makeRequest(authToken:String,
                            endpoint: String,
                            params: Seq[(String, String)]) : Option[HttpResponse[String]] = {
    getAuthHeaders(authToken).map(headers => {
      Some(Http(endpoint).headers(headers).params(params).asString)
    }).getOrElse(None)
  }
}
