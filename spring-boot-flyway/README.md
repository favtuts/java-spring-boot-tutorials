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

Once the project has been created and opened in the IDE, you should see the following Flyway dependencies in the `build.gradle` file:
```groovy
implementation 'org.flywaydb:flyway-core'
implementation 'org.flywaydb:flyway-database-postgresql'
```

Spring Boot provides out-of-the-box support for [Flyway database migrations](https://docs.spring.io/spring-boot/how-to/data-initialization.html#howto.data-initialization.migration-tool). Once the Flyway Migrations dependency is added, you can add your Flyway migration scripts in the `src/main/resources/db/migration` directory. When you start the application, Spring Boot will apply the pending Flyway migrations automatically.

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

# Creating Flyway migrations manually

We can manually create a file with the name `V1__create_bookmarks_table.sql` under the `src/main/resources/db/migration` directory with the following content:
```sql
CREATE SEQUENCE IF NOT EXISTS bookmark_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE bookmarks
(
   id         BIGINT                                    NOT NULL,
   title      VARCHAR(200)                              NOT NULL,
   url        VARCHAR(500)                              NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW() NOT NULL,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_bookmarks PRIMARY KEY (id)
);
```

Now, if you run the Spring Boot application, the first Flyway migration ( `V1__create_bookmarks_table.sql`) will be applied and the `bookmarks` table will be created.