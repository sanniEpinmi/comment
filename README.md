# Spring Boot  Database CRUD example: Building Rest API with Spring Data JPA

LIST OF API
1. http://localhost:8080/comment -------POST


http://localhost:8080/api/comments

REQUEST

{"title" : "PROG",
"description" : "big",

"authorName" : "SANNI"





}




RESPONSE


{
    "id": 1,
    "title": "PROG",
    "description": "big",
    "published": false,
    "authorName": "SANNI",
    "publishedDate": "2022-11-01T05:32:57.215+00:00"
}




2. http://localhost:8080/comment---------GET ALL
3. http://localhost:8080/comment/{id}----------DELETE
4. http://localhost:8080/comment/{id}-----PUT
5. http://localhost:8080/comment/{id}-------GET BY ID
6. http://localhost:8080/comment/published---GET

## Run Spring Boot application
```
mvn spring-boot:run
```

