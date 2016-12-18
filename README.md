Author Pablo Perez Garcia

# AppleSearch

![My image](src/main/resources/img/apple.jpeg)

Open source library to consume Apple API for music, movies and apps.

You need to follow the next steps in order to start using the library.

* Add the library in your project

* Create a new instance of class Album, or extend this class and insanciate with the artist name and country
    val album = new Album(artistName = "incubus", country = "es")
    ```

* Use the method find()

    List[Album] discography = album.find()
    ```

* Automatically you will received all albums of that artist for that country.

Reuse those steps for Movies and Applications

You can see how the library works running the unit test:

![My image](src/main/resources/img/unit.png)

* [Unit test] (src/test/scala/politrons/apple/search)



