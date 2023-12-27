Example Jakarta API, Jetty and WildFly Servers
=============================
Example or template to start a REST API project using jakarta, and deploy using jetty and wildfly plugins.

System Requirements:
--------------------
- OpenJDK for Java 11
- Git
- Maven 3.3.9 or higher

Building and running the example project:
-----------------------------

To run using Jetty maven plugin:

    mvn clean install -P jetty
    
    mvn jetty:run -P jetty

To run using Wildfly maven plugin:

    mvn clean install -P wildfly
    
    mvn wildfly:run -P wildfly

After run, test the endpoint Hello World:

    http://localhost:8080/api/hellomessage?message=This%20is%20my%20message

To stop Jetty or Wildfly:

    Ctrl + C
To test all the endpoints, use the postman collection:
    
    https://elements.getpostman.com/redirect?entityId=7003370-8a3c6c89-53c0-4c42-95d8-8940ef9a887f&entityType=collection