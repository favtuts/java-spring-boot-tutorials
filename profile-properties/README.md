# Spring Boot – Profile based properties and yaml example

Article link : https://www.favtuts.com/spring-boot-profile-based-properties-and-yaml-example/

# How to start

```
$ git clone https://github.com/favtuts/java-spring-boot-tutorials.git
$ cd profile-properties
```

Spring Boot run
```
# The 'dev' profile is configured in application.properties

# Profile : dev , picks application-dev.properties
$ mvn spring-boot:run

# Profile : prod, picks application-prod.properties
$ mvn spring-boot:run -Dspring-boot.run.profiles=prod

# Profile : abc, a non-exists profile
$ mvn spring-boot:run -Dspring-boot.run.profiles=abc
```

Package JAR file and run it directly
```
$ mvn clean package

# The 'dev' profile is configured in application.properties

# Profile : dev , picks application-dev.properties 
$ java -jar target/spring-boot-profile-1.0.jar

# Profile : prod, picks application-prod.properties
$ java -jar -Dspring.profiles.active=prod target/spring-boot-profile-1.0.jar

# Profile : abc, a non-exists profile
$ java -jar -Dspring.profiles.active=abc target/spring-boot-profile-1.0.jar
```