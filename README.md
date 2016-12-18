Author Pablo Perez Garcia

# Apple Search

![My image](src/main/resources/img/apple.jpeg)

Open source library to consume Apple API for music, movies and apps.

You need to follow the next steps in order to start using the library.

* Add the library in your project

* Use one of the library classes( Album, Movie, Application ) or extend one of those and insanciate it with the artist name and country
    val album = new Album(artistName = "incubus", country = "es")
    ```
    List[Album] discography = album.find()
    ```

* Automatically you will received a collection with all albums, movies or applicaiton of that artist for that country.


You can see how the library works running the unit test:

![My image](src/main/resources/img/unit.png)

* [Unit test] (src/test/scala/politrons/apple/search)



