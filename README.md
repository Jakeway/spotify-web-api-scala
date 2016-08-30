[![Build Status](https://travis-ci.org/Jakeway/spotify-web-api-scala.svg?branch=master)](https://travis-ci.org/Jakeway/spotify-web-api-scala)

# Spotify Web API Scala Client 

A Scala client for interacting with the Spotify Web API.

Currently, all GET requests have been implemented, which makes up ~90% of the API. However, only required parameters have
been taken into account. Optional parameters, as well as POST / PUT / DELETE requests are in the works.

More documentation about the Spotify Web API can be found [here.](https://developer.spotify.com/web-api/)

## Sample Usage

_Getting an album (no auth required)_
```
val spotify = new SpotifyClient()
val reflektorID = "4E0m7AIVc2d2QZMrMNXdMZ"
val reklektorAlbum = spotify.Albums.getAlbum(reflektorID)
```

_Getting current user's profile (auth required)_
```
val token = "ValidAuthTokenHere"
val spotify = new SpotifyClient(token)
val profile = spotify.Users.getCurrentUserProfile
```
_If you try to make a request to an endpoint that requires authentication, you will get back a None object (perhaps should throw an exception?)_

## Notes

All of the requests are grouped into Objects inside of the SpotifyClient. Those objects are:
* Albums
* Artists
* AudioFeatures
* Browse
* Recommendations
* Search
* Tracks
* Users

### todo
* marshall the string response into JSON
* post / put / delete requests
* optional parameters
* use the oauth token provided even if the endpoint doesn't require one (increase rate-limits)
* create test cases
* add documentation
* sample code