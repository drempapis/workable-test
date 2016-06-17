# README #

## Structure - Server
Java 8 / Spring MVC 4 / maven 3 have been used to create a web application exposing the HTTP-REST-API.

The code is layered following the standards with top-down only dependencies between the layers.
-- controller
-- service
-- repository
-- domain

For caching ehCache has been used applying 1 week TTL for all entries. 

### HTTP-REST-API

- Description : NOW PLAYING - GET - REQUEST 
- Path        : [/movies/now_playing]
- Method      : [GET]
- Consumes    : [application/x-www-form-urlencoded]
- Produces    : [application/json]
- Headers     : [Content-Type:application/x-www-form-urlencoded]
- Response Status Code : 200 - OK

- Description : SEARCH TITLE - GET - REQUEST 
- Path        : [/movies/search/{text}]
- Method      : [GET]
- Consumes    : [application/x-www-form-urlencoded]
- Produces    : [application/json]
- Headers     : [Content-Type:application/x-www-form-urlencoded]
- Response Status Code : 200 - OK

- Response     : 
```
{ "movieList": [ 
    { "id": int,
      "title": String,
      "description": String,
      "releaseDate": String,
      "actorList": [{"name": String}], 
      "bestActors": String,
      "reviewList": [{"author": String, "review": String],
      "reviewsNumber": int } 
   ] 
}
```

## Structure - Client
A simple nodejs web app has been implemented connecting to the movierama local server through http rest calls, retrieving
the movies information.

### Execute the application
Maven 3, java 8, nodejs need to be installed in order to execute the application

## Open a console for the server and type     
mvn clean install jetty:run

## Open a console for the client and type 
npm install

node server.js

## Open a browser and type
http://localhost:8081/index.html

- In the browse appear the (Now Playing) and the (Title) search options. 
- The (Now Playing) search, brings the now playing movies caching the results. 
- The (Search), checks the cache for the movie with the given title. If it not stored, returns a movie list, matching the introduced title. All results are cached.
- To navigate the pages, use the browser arrows.