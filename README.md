# movie-library

A server-rendered movie catalog web app built with Spring Boot 3, Thymeleaf, and
Java 17. Browse, add, edit, and delete movies through HTML views backed by a
JPA persistence layer.

## Stack

- **Spring Boot Web + Thymeleaf**: server-side rendered views, `MovieController`
- **Spring Data JPA**: persistence (`MovieRepository`, `Movie` entity)
- **Bean Validation**: request validation on the create/edit forms
- **H2**: in-memory database, seeded on startup by `DataSeeder`

## Run

```bash
./mvnw spring-boot:run        # starts on http://localhost:8080
./mvnw test                   # run tests
```

Open `http://localhost:8080` to browse the catalog. The H2 console is available
at `/h2-console` while running.
