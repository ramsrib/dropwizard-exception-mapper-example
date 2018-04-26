# dropwizard-exception-mapper-example
**Dropwizard Persistence Exception Mapper Example**

By default, dropwizard doesn't handle any peristence exceptions including `ConstraintViolationException`. This example demonstrates how to handle the persistence exception (using `PersistenceExceptionMapper`) i.e, returns `409` instead of `500` http response.


To build the application,

    $ ./mvnw clean package -DskipTests


To run the application,

    $ java -jar target/validation-mapper-example.jar server config.yml


To test the application,

    $ ./mvnw test
