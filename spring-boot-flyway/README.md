# How to Use Flyway for Database Migrations in Spring Boot Applications
* https://tuts.heomi.net/how-to-use-flyway-for-database-migrations-in-spring-boot-applications/

# Create a Spring Boot project
Based on the Bookmarks application: [How to Build a CRUD REST API Using Spring Boot](./../spring-boot-crud-restapi/README.md)

Spring Boot project will be adding the following dependencies:
* Spring Web
* Validation
* Spring Data JPA
* PostgreSQL Driver
* Flyway Migration

# Run PostgreSQL DB as container

First we create Docker volume to store the PostgreSQL data if not exists
```bash
$ docker volume ls
$ docker volume create postgres-volumn
```

The below command starts PostgreSQL as a container, and mount the Docker volume `postgres-volumn` to the PostgreSQL data directory `/var/lib/postgresql/data` (this directory inside the container).
```bash
$ docker run --name postgres-container -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -v postgres-volumn:/var/lib/postgresql/data -d postgres:15.4
```