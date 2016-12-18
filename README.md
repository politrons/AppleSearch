Author Pablo Perez Garcia

# Apple Search

![My image](src/main/resources/img/apple.jpeg)

Open source library in scala to consume public Apple API for music, movies and apps.

You need to follow the next steps in order to start using the library.

* Add the library in your project.
    ```
    gradle clean build --> create the jar
    /build/libs/AppleSearch-1.0.jar --> copy the library
    mv AppleSearch-1.0.jar /projectFolder/lib --> move it in your lib folder project
    ``

* Use one of the library classes( Album, Movie, Application ) or extend one of those and insanciate it with the artist name and country.

    ```
    val album = new Album(artistName = "incubus", country = "es")
    List[Album] discography = album.find()
    ```

* Automatically you will received a collection with all albums, movies or applicaiton of that artist for that country.


* The structure of the classes looks like:

    * Album
```
class Application(artistName: String = "",
                  artistViewUrl: String = "",
                  trackViewUrl: String = "",
                  country: String = "",
                  price: String = "",
                  artworkUrl100: String = ""
                 ) extends AppleBase(artistName,null, country) {}
```
    * Movie
```
class Movie(artistName: String = "",
            trackName: String = "",
            country: String = "",
            previewUrl: String = "",
            trackViewUrl: String = "",
            trackRentalPrice: String = "",
            trackPrice: String = "",
            collectionHdPrice: String = "",
            primaryGenreName: String = "",
            artworkUrl100: String = ""
           ) extends AppleBase(artistName, trackName, country) {}
```
    * Application
```
class Application(artistName: String = "",
                  artistViewUrl: String = "",
                  trackViewUrl: String = "",
                  country: String = "",
                  price: String = "",
                  artworkUrl100: String = ""
                 ) extends AppleBase(artistName,null, country) {}
```


You can see how the library works running the unit test:

![My image](src/main/resources/img/unit.png)

* [Unit test] (src/test/scala/politrons/apple/search)



