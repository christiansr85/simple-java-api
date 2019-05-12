# API REST JAVA
API Rest built with Java, Spring Boot, Gradle and MongoDB.

## Requirements

- java 1.8.0
- gradle 5.4.1
- mongodb 4.0.6

## Launch service

1. Launch mongodb server through `mongod` command from a bash (you should have already a database called `starshot`)
2. Unzip the source code
3. Inside the folder where the code has been unzipped, open a terminal and execute `./gradlew bootRun` command. After a few seconds, the server has to be running

## What can we find now?
 ___
 Server is up in the url `http://127.0.0.1:9090`
 ___

Once you already have started the server, you can test it through `Postman` or `Swagger`:

- From `Postman`: you have to import the json collection and environment files to your postman application and then you should be able to try the api services.

- From `Swagger`: you can access to the url `http://localhost:9090/swagger-ui.html#/`, where an instance of swagger is running and try the api from there.