# Microservice with REST API secured by JWT

### Description
This microservice allows you to:

* register new user,
* login,
* add new item for logged user,
* see all the logged user's items,
* logout.

### Pre-activities
Before you start the application, please configure your database:

* Run scripts from src/main/resources/db/init
* ... OR/AND configure your JDBC connection in application.properties file.
* Ensure that you use Java 21. If you want to use another version, update pom.xml file.


### Running application
After running the application you can easily go through endpoints via OpenApi 3.0.
Paste in your browser: http://localhost:3000/swagger-ui/index.html. Change port if needed.
In Swagger paste: /api-docs and click "Explore" to enable using the Api.
You can also use the Api through Postman.

### Running tests
Test are not perfectly made. Before you run them, please paste a valid token to field "token" inside ApiControllerIntegrationTest.java.
After testing you may be willing to delete all generated testUsers and testItems from database.