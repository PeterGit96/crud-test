# Exercise - Spring Boot - CRUD test
* write a Spring Boot application with the necessary dependencies that:
  * has an entity called `Student` with the following columns:
    * a primary key
    * `name`
    * `surname`
    * a boolean `isWorking`
  * has a basic service for changing the `isWorking` value
  * has a controller for the following CRUD operations:
    * (1) creating a new `Student`
    * (2) getting a list of all the `Student`s
    * (3) getting a specific `Student` by:
      * passing the primary key as path variable
    * (4) updating the primary key of a `Student` by:
      * passing the primary key as path variable
      * passing a `Student` in the request body
    * (5) updating the `isWorking` value by:
      * passing the primary key as path variable
      * passing a request param called `working`
    * (6) deleting a `Student`
* test using an `H2` mock database with the profile `test`:
  * (a) the controller
  * (b) the service
