# How to Build a CRUD REST API Using Spring Boot
* https://tuts.heomi.net/how-to-build-a-crud-rest-api-using-spring-boot/


# Init Spring Boot Project

Spring Project properties
* Name: bookmarks
* Group: com.example
* Artifact: bookmarks
* Package name: com.example.bookmarks
* Java JDK: 17
* Spring Boot: 3.4.0
* Type: Gradle-Groovy

Spring Project dependencies
* Spring Web
* Validation
* Spring Data JPA
* PostgreSQL Driver


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

# Generate Java codes

Bookmark JPA entity:
```java
package com.jetbrains.bookmarks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;

@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmarks_id_gen")
    @SequenceGenerator(name = "bookmarks_id_gen", sequenceName = "bookmark_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Size(max = 500)
    @NotNull
    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    // setters & getters
}
```