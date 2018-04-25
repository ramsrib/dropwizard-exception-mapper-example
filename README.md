# dropwizard-exception-mapper-example
Dropwizard Exception Mapper Example


To build the application,

    $ ./mvnw clean package -DskipTests


To run the application,

    $ java -jar target/validation-mapper-example.jar server config.yml


To test the application,

    $ ./mvnw test


Note: Test will fail because of the bug in dropwizard