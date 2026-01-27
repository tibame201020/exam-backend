# Exam Controller System Backend

This is the backend service for the Exam Controller System, recently upgraded to use modern Java technologies.

## Technology Stack

- **Java**: 21 LTS
- **Framework**: Spring Boot 3.2.2
- **Database**: H2 Database (Embedded)
- **Concurrency**: Virtual Threads (Project Loom) enabled
- **Build Tool**: Maven

## Key Features

- **Upgraded Architecture**: Migrated from Spring Boot 2/Java 8 to Spring Boot 3/Java 21.
- **High Performance**: Enabled Virtual Threads (`spring.threads.virtual.enabled=true`) for high-throughput concurrency.
- **Optimized Data Layer**: Tuned HikariCP connection pool and H2 database settings.
- **Dockerized**: Includes a multi-stage `Dockerfile` optimized for JDK 21 using Eclipse Temurin images.

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.9+ (or use included wrapper `mvnw`)

### Running Locally

```bash
# Clean and build
mvn clean package

# Run the application
mvn spring-boot:run
```

The application will start on port `12058`.

### Running with Docker

```bash
# Build the image
docker build -t exams-backend .

# Run the container
docker run -p 12058:12058 exams-backend
```

## API Documentation

See [endpoints.md](endpoints.md) for detailed API specifications.

## Recent Changes

- Migrated `javax.*` packages to `jakarta.*`.
- Fixed Hibernate 6 mapping issues with `Quiz[]` serialization using `@JdbcTypeCode(SqlTypes.VARBINARY)` and `LONGBLOB`.
