# movie-library

A server-rendered movie catalog web app built with Spring Boot 3, Thymeleaf, and Java 17, where you browse, add, edit, and delete movies through validated HTML views backed by a Spring Data JPA layer over an in-memory H2 database.

## Prerequisites

- Java 17
- Maven (or the bundled `./mvnw` wrapper)

## Build & run

```bash
./mvnw spring-boot:run        # starts on http://localhost:8080
./mvnw test                   # run tests
```

Open `http://localhost:8080` to browse the catalog.
