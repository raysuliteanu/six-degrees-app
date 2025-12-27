# Six Degrees

A Spring Boot application that implements a "Six Degrees of Separation" game using The Movie Database (TMDB) API to find connections between actors through their movie collaborations.

## Overview

This project provides a REST API to search for people (actors, directors, etc.) from TMDB and explore their connections through shared movie credits.

## Tech Stack

- **Java 25** (Temurin LTS)
- **Spring Boot 4.0.1** with:
  - Spring Web MVC
  - Spring Security
  - Spring Boot Actuator
  - Spring Boot DevTools
- **GraalVM Native Image** support
- **Gradle 9.2.1** build system
- **TMDB API** for movie and person data

## Features

- Person search via REST API
- TMDB integration for retrieving person and credit information
- Virtual threads enabled for improved concurrency
- GraalVM native image compilation for fast startup and low memory footprint
- Health checks and metrics via Spring Boot Actuator

## Prerequisites

- Java 25 or higher
- Gradle 9.2.1 or use the included Gradle wrapper
- TMDB API credentials (API key and access token)

## Configuration

### TMDB API Credentials

You need to obtain API credentials from [The Movie Database](https://www.themoviedb.org/):

1. Create an account at [TMDB](https://www.themoviedb.org/)
2. Go to Settings → API and request an API key
3. Generate an access token

Set the following environment variables:

```bash
export TMDB_API_TOKEN=your_api_key_here
export TMDB_ACCESS_TOKEN=your_access_token_here
```

### Application Properties

The application is configured via `server/src/main/resources/application.yaml`:

- **Application name**: `six-degrees-app`
- **Security**: Basic authentication (default user: `admin`, password: `admin`)
- **Logging**: Debug level for `org.kidoni` package
- **Virtual threads**: Enabled for improved concurrency
- **TMDB API**: Base URL configured to `https://api.themoviedb.org/`

## Running the Application

### Development Mode (JVM)

```bash
cd server
./gradlew bootRun
```

The application will start on `http://localhost:8080` with basic authentication (`admin`/`admin`).

### Building a JAR

```bash
cd server
./gradlew bootJar
```

The executable JAR will be created at `server/build/libs/sixdegrees-0.0.1-SNAPSHOT.jar`.

### Building a Native Image

```bash
cd server
./gradlew bootBuildImage
```

This creates a Docker image with a GraalVM native executable:
- Image name: `org.kidoni/sixdegrees:0.0.1-SNAPSHOT`
- Builder: Paketo Buildpacks with Noble Java Tiny builder
- Startup time: ~100ms
- Memory footprint: Significantly reduced compared to JVM

Run the native image:

```bash
docker run -p 8080:8080 \
  -e TMDB_API_TOKEN=your_api_key \
  -e TMDB_ACCESS_TOKEN=your_access_token \
  org.kidoni/sixdegrees:0.0.1-SNAPSHOT
```

## API Endpoints

### Person Search

```bash
GET /person/{name}
```

Search for a person by name.

**Example:**

```bash
curl -u admin:admin http://localhost:8080/person/Tom%20Hanks
```

**Response:** Returns a `PersonSearchResult` containing matching people from TMDB.

### Actuator Endpoints

Spring Boot Actuator endpoints are available at `/actuator`:

- `/actuator/health` - Health check endpoint
- `/actuator/metrics` - Application metrics
- `/actuator/info` - Application information

## Project Structure

```
six-degrees/
├── server/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/kidoni/sixdegrees/
│   │   │   │   ├── SixDegreesAppApplication.java    # Main application
│   │   │   │   ├── SixDegreesController.java        # REST controller
│   │   │   │   ├── SixDegreesConfiguration.java     # Bean configuration
│   │   │   │   └── tmdb/                             # TMDB client
│   │   │   │       ├── TmdbClient.java
│   │   │   │       ├── DefaultTmdbClient.java
│   │   │   │       ├── TmdbConfigurationProperties.java
│   │   │   │       ├── Person.java
│   │   │   │       ├── Credit.java
│   │   │   │       └── PersonSearchResult.java
│   │   │   └── resources/
│   │   │       └── application.yaml
│   │   └── test/
│   │       └── java/org/kidoni/sixdegrees/
│   ├── build.gradle
│   ├── settings.gradle
│   └── gradle.properties
└── web/                                              # Future web UI
```

## Development

### Running Tests

```bash
cd server
./gradlew test
```

### Building

```bash
cd server
./gradlew build
```

### Code Style

The project follows clean code principles with descriptive naming and well-organized package structure.

## Roadmap

- [ ] Implement graph algorithm to find connections between two people
- [ ] Add caching layer for TMDB API responses
- [ ] Build web UI for interactive gameplay
- [ ] Add more endpoints for exploring connections
- [ ] Implement breadth-first search for shortest path finding

## License

This is a demonstration project.

## Contributing

This is a personal project, but suggestions and feedback are welcome via issues.
