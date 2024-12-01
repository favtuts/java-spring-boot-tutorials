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

# Modify JPA entities and generate new migrations

Let’s say we want to categorize the bookmarks and also add an additional column called `status` to indicate whether a bookmark is in `DRAFT` or `PUBLISHED` state.

Create a new JPA entity called `Category` as follows:
```java
package com.jetbrains.bookmarks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_generator")
   @SequenceGenerator(name = "category_id_generator", sequenceName = "category_id_seq")
   private Long id;

   private String name;

   // setters and getters
}
```

Update our `Bookmark` entity to add a `String` type property called `status` with a default value of `DRAFT` and a `ManyToOne` association with Categ`ory as follows:
```java
package com.jetbrains.bookmarks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "bookmarks")
public class Bookmark {
   //...
   @ColumnDefault("'DRAFT'")
   @Column(name = "status", nullable = false)
   private String status;
  
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id")
   private Category category;

   // setters and getters
}
```

To make the corresponding changes to the database schema, we need to create the second Flyway migration script named `V2__add_status_category_to_bookmarks.sql`.
```sql
CREATE SEQUENCE IF NOT EXISTS category_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE categories
(
   id   BIGINT NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_categories PRIMARY KEY (id)
);

ALTER TABLE bookmarks
   ADD category_id BIGINT;

ALTER TABLE bookmarks
   ADD status VARCHAR(255) DEFAULT 'DRAFT';

ALTER TABLE bookmarks
   ALTER COLUMN status SET NOT NULL;

ALTER TABLE bookmarks
   ADD CONSTRAINT FK_BOOKMARKS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);
```

As you can see, the second Flyway migration script generated contains the SQL script to create a `categories` table, as well as add a `category_id` foreign key and a `status` column with a default value of `DRAFT` to the `bookmarks` table.

If you restart the Spring Boot application and check the database, the `categories` table should have been created and the `category_id` and `status` columns should have been added to the `bookmarks` table.

# Update existing JPA entities from database schema changes

Let’s create a third Flyway migration script with file name `V3__add_published_at_col_to_bookmarks.sql`
```sql
ALTER TABLE bookmarks ADD published_at timestamp;
```

We are now adding a new column called `published_at` to the bookmarks table.

Restart the Spring Boot application and ensure that the `published_at` column has been added to the `bookmarks` table.

The `Bookmark` entity will then be updated to add the `publishedAt` property as follows:
```java
package com.jetbrains.bookmarks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@Entity
@Table(name = "bookmarks")
public class Bookmark {
   //...
  
   @Column(name = "published_at")
   private Instant publishedAt;

   // setters and getters
}
```

# Handling Rollbacks

The Flyway Community Edition doesn’t support automatic rollbacks. When you need to roll back a migration, you can create a new migration script to undo the changes. This script should be named with a higher version number than the original migration.

Create a file named `V4__remove_published_at_col_on_bookmarks.sql`:
```sql
ALTER TABLE bookmarks DROP published_at role;
```
Then run the migration as usual to apply the rollback. This will remove the role column from the users table as defined in the script.

The Flyway Pro and Enterprise Editions offer additional features like undo and repair commands for automatic rollback and fixing failed migrations. You can explore these options if you require more advanced rollback capabilities.