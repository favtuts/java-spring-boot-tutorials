# Spring Boot Profiles example

Article link : https://tuts.heomi.net/spring-boot-profiles-example/

# How to start

```
$ git clone https://github.com/favtuts/java-spring-boot-tutorials.git
$ cd profile-simple

## Spring Boot run with default profile
$ mvn spring-boot:run

## Spring Boot run with specific profile
$ mvn spring-boot:run -Dspring-boot.run.profiles=raining
```

You can package JAR file and run it directly
```
$ git clone https://github.com/favtuts/java-spring-boot-tutorials.git
$ cd profile-simple
$ mvn clean package

## Run app with default profile
$ java -jar target/spring-boot-profile-1.0.jar

## Run app with specific profile
$ java -jar -Dspring.profiles.active=raining target/spring-boot-profile-1.0.jar
```


## For command line argument styles

You can provide commandline argument like this:
```
$ mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=raining"
$ java -jar target/spring-boot-profile-1.0.jar --spring.profiles.active=raining
```
You can provide JVM argument like this:
```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=raining"
$ java -jar -Dspring.profiles.active=raining target/spring-boot-profile-1.0.jar
```