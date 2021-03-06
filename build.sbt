name := "spotify-web-api-scala"
version := "1.0"
scalaVersion := "2.11.8"


resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.4.0"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.4.0"
