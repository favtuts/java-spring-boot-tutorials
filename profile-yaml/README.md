# Spring Boot – Profile based properties and yaml example

Article link : https://tuts.heomi.net/spring-boot-profile-based-properties-and-yaml-example/

# How to start

```
$ git clone https://github.com/favtuts/java-spring-boot-tutorials.git
$ cd profile-yaml
```

Spring Boot run
```
# Profile : dev , picks YAML
$ mvn spring-boot:run

# Profile : prod, picks YAML
$ mvn spring-boot:run -Dspring-boot.run.profiles=prod

# Profile : abc, a non-exists profile
$ mvn spring-boot:run -Dspring-boot.run.profiles=abc
```

Package JAR file and run it directly
```
$ mvn clean package

# Profile : dev , picks YAML
$ java -jar target/spring-boot-profile-1.0.jar

# Profile : prod, picks YAML
$ java -jar -Dspring.profiles.active=prod target/spring-boot-profile-1.0.jar

# Profile : abc, a non-exists profile
$ java -jar -Dspring.profiles.active=abc target/spring-boot-profile-1.0.jar
```