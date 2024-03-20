# tripPlanner
A trip planning application that allows travel agencies to maintain their travel packages - itinerary and passengers.

## Requirements
For building and running the application you need:

- [JDK 11.0.21](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 2.5.4](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.app.tripPlanner.tripPlannerApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
<br><br>

<div align="center">
  <h2>Design</h2>
</div>

<br>

<div align="center">
  <img src="https://github.com/vipuldhurve/tripPlanner/blob/main/assets/tripPlanner%20EA.png" alt="Image" style="display:block; margin:auto;">
  <p style="position:absolute; bottom:0; text-align:center; width:100%;">This diagram illustrates the high-level structure and components of tripPlanner application.</p>
</div>

<br><br><br>

 ## Database
<div align="center">
  <img src="https://github.com/vipuldhurve/tripPlanner/blob/main/assets/tripPlanner%20database.png" alt="Image" style="display:block; margin:auto;">
  <p style="position:absolute; bottom:0; text-align:center; width:100%;">The database design depicts the schema, tables, and relationships for tripPlanner application.</p>
</div>

<br><br><br>


 ## Class Diagram
<div align="center">
  <img src="https://github.com/vipuldhurve/tripPlanner/blob/main/assets/tripPlanner%20class%20diagram.png" alt="Image" style="display:block; margin:auto;">
  <p style="position:absolute; bottom:0; text-align:center; width:100%;">The class diagram showcases the classes, attributes, and relationships within tripPlanner application.</p>
</div>

<br><br><br>
	
## Swagger API
Default swagger url - [{base_url}/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html#/travel-package-controller)
<br><br>
<div align="center">
  <img src="https://github.com/vipuldhurve/tripPlanner/blob/main/assets/swagger%20api.png" alt="Image" style="display:block; margin:auto;">
  <p style="position:absolute; bottom:0; text-align:center; width:100%;"><br>This screenshot displays the Swagger API documentation for our application, showcasing all available endpoints and their details.</p>
</div>

<br>
The below mentioned GET APIs are just Helper APIs for printing data in the console:

- Itinerary list of a travel package (`print-itinerary/{id}`)
- Passenger list of a travel package (`print-passengers/{id}`)
- Available activities in a travel package with spaces (`print-available-activities/{id}`)
- Passenger details (`print-passenger-details/{id}`)


<br><br><br>
