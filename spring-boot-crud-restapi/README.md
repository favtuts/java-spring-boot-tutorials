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
package com.example.bookmarks;

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

Assuming we just want to return only the `id`, `title`, `url`, and `createdAt` fields of `Bookmark`, we can create a Spring Data JPA projection as `BookmarkInfo.java` interface:
```java
package com.example.bookmarks;

import java.time.Instant;

/**
 * Projection for {@link Bookmark}
 */
public interface BookmarkInfo {
    Long getId();

    String getTitle();

    String getUrl();

    Instant getCreatedAt();
}
```


Create Spring Data JPA repository
```java
package com.example.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<BookmarkInfo> findAllByOrderByCreatedAtDesc();

    Optional<BookmarkInfo> findBookmarkById(Long id);
}
```

Create a `BookmarkNotFoundException`, which we will throw when the requested bookmark is not found.
```java
package com.example.bookmarks;

public class BookmarkNotFoundException extends RuntimeException {
    public BookmarkNotFoundException(String message) {
        super(message);
    }
}
```

Now, let’s create `BookmarkController` as follows:
```java
package com.example.bookmarks;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController {
    private final BookmarkRepository bookmarkRepository;

    BookmarkController(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    //CRUD API handler methods
}
```

# Test API endpoints using the HTTP Client


Get all bookmarks
```bash
curl --location 'http://localhost:8080/api/bookmarks'
```

Get bookmark by id
```bash
curl --location 'http://localhost:8080/api/bookmarks/1'
```

Create bookmark successfully
```bash
curl --location 'http://localhost:8080/api/bookmarks' \
--header 'Content-Type: application/json' \
--data '{
  "title": "bookmark title",
  "url": "https://bookmark.com"
}'
```

Update bookmark successfully
```bash
curl --location --request PUT 'http://localhost:8080/api/bookmarks/152' \
--header 'Content-Type: application/json' \
--data '{
  "title": "bookmark title updated",
  "url": "https://bookmark.com"
}'
```

Delete bookmark by id
```bash
curl --location --request DELETE 'http://localhost:8080/api/bookmarks/152'
```