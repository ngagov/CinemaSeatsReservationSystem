This project represents application for online booking of cinema seats.

Architecture:
1.It uses Maven for dependency resolution and build tool, see http://maven.apache.org/
2.It uses Spring Core (dependency injection, transaction management), Spring MVC(restful services, json functionality),
Spring security(declarative authentication and authorization), see http://www.springsource.org/,
Spring Test - integration with JUnit
3.It uses Eclipse Link for persistence provider, see http://www.eclipse.org/eclipselink/
4.hsqldb - lightweight relational database, see http://hsqldb.org/
5.cglib - advanced proxying mechanism, see http://cglib.sourceforge.net/
6.JQuery - platform independent javascript library, see http://jquery.com/

Layers:
Client side:
1.Jquery, CSS, HTML, images are placed under SeatsBooking/src/main/webapp/resources in a appropriate folders
Server side:
1.Controllers are placed under SeatsBooking/src/main/java/com/ccbilleu/cinema/controller
2.Services are placed under SeatsBooking/src/main/java/com/ccbilleu/cinema/service - here we have the separation between functionality and implementation.
The implementation is under impl sub-folder
3.DAO layer - SeatsBooking/src/main/java/com/ccbilleu/cinema/db/dao
4.Transformers - used to convert from persistence entities to DTO. This layer is used in order to decouple the persistence and web layers.
They are placed under SeatsBooking/src/main/java/com/ccbilleu/cinema/transform
5.Exceptions are placed under SeatsBooking/src/main/java/com/ccbilleu/cinema/db/exception
6.JPA entities - Annotated pojos - they are placed under SeatsBooking/src/main/java/com/ccbilleu/cinema/db/model
7. DTO layer (Data Transfer Objects) - they are placed under SeatsBooking/src/main/java/com/ccbilleu/cinema/dto
8. Under the base package com.ccbilleu.cinema we have constants and utility classes
9. JUnit tests - they are placed under SeatsBooking/src/test/java/com/ccbilleu/cinema/service/
10.Spring and Web configurations - they are placed under SeatsBooking/src/main/webapp/WEB-INF/
11.Persistence information is placed under SeatsBooking/src/main/resources/META-INF/persistence.xml
12.Maven Dependencies and build configuration are placed under workspace/SeatsBooking/pom.xml
13.UML diagrams are placed under SeatsBooking/doc
Functionality:
1.User needs to login, there are predefined users in the system. They could be found under 
SeatsBooking/src/main/webapp/WEB-INF/spring-security.xml
2.User selects movie
3.User selects movie show
4.User selects free seat by clicking on it
5.Seats are colored in different colors depending on their status (free, booked, booked by me)
6.User clicks purchase button and he receives response from the server about the booking and some payment information
7.User decides to cancel his choice by clicking the reset button, or just by changing the movie/movie show filter
8.User clicks on the upcoming movies link and previews the information about it - banner and a short information
9.User log-out from the system by clicking the logout button. He will be automatically redirected to the login page

How to run the application:
1. You need to have installed maven in advance
2. You need to have installed Apache Tomcat in advance. The application has been tested under Tomcat 7.0.39
3. extract the project and go to its main directory
4. execute mvn clean install -Dmaven.test.skip=true
5. execute cp -r target/SeatsBooking $TOMCAT_HOME/webapps/
6. cd $TOMCAT_HOME/bin
7. execute ./catalina.sh
8. Open your favorite browser and enter http://localhost:8080/SeatsBooking/cinema
9. You will see the login page

Possible known issues:
1. You see persistence errors during the start of the server - HSQLDB writes it data to a file on the file system located under $TOMCAT_HOME/bin/resources/db.
Make sure that there you have testb.script and testb.properties(check the directory rights). If necessary delete $TOMCAT_HOME/bin/resources/db directory and restart the server

Suggested improvements:
1.Provide price information about the seats
2.Add optimistic lock - in order to avoid stale data
3.Provide information about price discounts - price for adults, children, disabled people
4.Provide information about the age restrictions of the movie
5.Provide information about the genre of the movie - comedy, horror, action, etc
6.Provide internationalization - put the messages inside resource bundles