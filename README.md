# Six Degrees

A full-stack application that implements a "Six Degrees of Separation" game using The Movie Database (TMDB) API to find connections between actors through their movie collaborations.

## Overview

This project provides both a REST API backend and a modern React web frontend for searching actors and visualizing their connections through shared movie credits.

## Tech Stack

### Backend
- **Java 25** (Temurin LTS)
- **Spring Boot 4.0.1** with:
  - Spring Web MVC
  - Spring Security
  - Spring Boot Actuator
  - Spring Boot DevTools
- **GraalVM Native Image** support
- **Gradle 9.2.1** build system
- **TMDB API** for movie and person data

### Frontend
- **React 19** with TypeScript 5
- **Vite 7** for build tooling
- **Tailwind CSS 3** + shadcn/ui
- **ReactFlow** for graph visualization
- **Zustand** for state management
- **React Query** for server state caching
- **React Router 7** for navigation

## Features

### Backend
- RESTful API for person search and connection finding
- TMDB integration for retrieving person and credit information
- Graph pathfinding algorithm for actor connections
- Virtual threads enabled for improved concurrency
- GraalVM native image compilation for fast startup and low memory footprint
- Health checks and metrics via Spring Boot Actuator
- CORS configuration for frontend integration

### Frontend
- Interactive actor search with autocomplete
- Detailed actor profiles with filmography
- Visual graph representation of actor connections
- Multiple connection paths visualization with color coding
- Responsive design with modern UI components
- Authentication integration with backend
- Comprehensive test coverage with Vitest

## Prerequisites

### Backend
- Java 25 or higher
- Gradle 9.2.1 or use the included Gradle wrapper
- TMDB API credentials (API key and access token)

### Frontend
- Node.js 20 or higher
- npm (comes with Node.js)

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

## Quick Start

### Using Docker Compose (Recommended)

The easiest way to run the full stack (frontend + backend + database) together:

```bash
# Set required environment variables
export TMDB_API_TOKEN=your_api_key_here
export TMDB_ACCESS_TOKEN=your_access_token_here
export NEO4J_USERNAME=neo4j
export NEO4J_PASSWORD=your_secure_password

# Start all services
docker-compose up
```

Access the application at:
- **Frontend**: `http://localhost`
- **Backend API**: `http://localhost:8080`
- **Neo4j Browser**: `http://localhost:7474` (optional, for database management)

Login with default credentials: `admin` / `admin`

### Development Mode

#### Backend (Spring Boot)

```bash
cd server
./gradlew bootRun
```

The backend API will start on `http://localhost:8080` with basic authentication (`admin`/`admin`).

#### Frontend (React + Vite)

```bash
cd web
npm install
npm run dev
```

The frontend will start on `http://localhost:5173` with hot reload enabled.

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
GET /search/person/{name}
```

Search for a person by name.

**Example:**

```bash
curl -u admin:admin http://localhost:8080/search/person/Tom%20Hanks
```

**Response:** Returns a `PersonSearchResult` containing matching people from TMDB.

### Person Details

```bash
GET /person/{id}
```

Get detailed information about a person by TMDB ID.

**Example:**

```bash
curl -u admin:admin http://localhost:8080/person/31
```

**Response:** Returns `PersonDetails` with biography, filmography, etc.

### Person Credits

```bash
GET /person/{id}/credits
```

Get combined movie and TV credits for a person.

**Example:**

```bash
curl -u admin:admin http://localhost:8080/person/31/credits
```

**Response:** Returns `PersonCombinedCredits` with cast and crew credits.

### Find Connection

```bash
GET /connection/{actor1Id}/{actor2Id}?maxDegrees=6
```

Find connection paths between two actors through shared movie credits.

**Example:**

```bash
curl -u admin:admin http://localhost:8080/connection/31/85
```

**Response:** Returns a list of `ConnectionPath` objects showing how actors are connected.

### Actuator Endpoints

Spring Boot Actuator endpoints are available at `/actuator`:

- `/actuator/health` - Health check endpoint
- `/actuator/metrics` - Application metrics
- `/actuator/info` - Application information

## Project Structure

```
six-degrees/
├── server/                                           # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/kidoni/sixdegrees/
│   │   │   │   ├── SixDegreesAppApplication.java
│   │   │   │   ├── SixDegreesController.java
│   │   │   │   ├── SixDegreesConfiguration.java
│   │   │   │   ├── service/
│   │   │   │   │   └── TmdbSixDegreesService.java
│   │   │   │   └── tmdb/
│   │   │   │       ├── TmdbClient.java
│   │   │   │       ├── DefaultTmdbClient.java
│   │   │   │       ├── TmdbConfigurationProperties.java
│   │   │   │       └── model/
│   │   │   │           ├── Person.java
│   │   │   │           ├── PersonDetails.java
│   │   │   │           ├── ConnectionPath.java
│   │   │   │           ├── ConnectionNode.java
│   │   │   │           └── ConnectionEdge.java
│   │   │   └── resources/
│   │   │       └── application.yaml
│   │   └── test/
│   ├── build.gradle
│   └── Dockerfile
├── web/                                              # React frontend
│   ├── src/
│   │   ├── components/
│   │   │   ├── ui/                                   # shadcn/ui components
│   │   │   ├── auth/                                 # Authentication
│   │   │   ├── actor/                                # Actor search & display
│   │   │   ├── graph/                                # Graph visualization
│   │   │   └── layout/                               # Layout components
│   │   ├── services/api/                             # API integration
│   │   ├── stores/                                   # Zustand state stores
│   │   ├── hooks/                                    # Custom React hooks
│   │   ├── pages/                                    # Page components
│   │   ├── types/                                    # TypeScript types
│   │   ├── utils/                                    # Utility functions
│   │   └── test/                                     # Test setup
│   ├── public/
│   ├── package.json
│   ├── vite.config.ts
│   ├── tailwind.config.ts
│   ├── Dockerfile
│   ├── nginx.conf
│   ├── README.md
│   └── DEPLOYMENT.md                                 # Deployment guide
├── docker-compose.yml                                # Full-stack orchestration
└── README.md
```

## Development

### Running Tests

**Backend:**
```bash
cd server
./gradlew test
```

**Frontend:**
```bash
cd web
npm test                  # Run tests in watch mode
npm run test:ui          # Run tests with UI
npm run test:coverage    # Run tests with coverage report
```

### Building

**Backend:**
```bash
cd server
./gradlew build          # Build JAR
./gradlew bootBuildImage # Build native image
```

**Frontend:**
```bash
cd web
npm run build            # Build for production
npm run preview          # Preview production build
```

### Code Style

The project follows clean code principles with:
- Descriptive naming and well-organized package structure
- TypeScript for type safety in frontend
- Unit tests for critical functionality
- Component-based architecture with React
- RESTful API design

## Documentation

- **Frontend README**: `web/README.md` - Detailed frontend documentation
- **Deployment Guide**: `web/DEPLOYMENT.md` - Production deployment options
- **Backend Guide**: `CLAUDE.md` - Development guidelines and conventions

## Roadmap

### Completed
- [x] RESTful API for person search
- [x] TMDB integration for actor and movie data
- [x] Graph algorithm to find connections between actors
- [x] Connection endpoint with pathfinding
- [x] Web UI with React and TypeScript
- [x] Interactive graph visualization with ReactFlow
- [x] Authentication and authorization
- [x] Docker deployment support
- [x] Comprehensive test coverage

### Future Enhancements
- [ ] Neo4j integration for optimized graph queries
- [ ] Advanced caching layer for TMDB API responses
- [ ] User registration and account management
- [ ] Save and share connection discoveries
- [ ] Advanced filtering (by genre, decade, etc.)
- [ ] Performance metrics and analytics
- [ ] Mobile-responsive optimizations
- [ ] Internationalization (i18n) support

## License

This is a demonstration project.

## Contributing

This is a personal project, but suggestions and feedback are welcome via issues.
