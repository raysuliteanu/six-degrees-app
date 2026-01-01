# Six Degrees Web Frontend

Modern React TypeScript SPA for visualizing actor connections through their movie collaborations.

## Features

- **Actor Search**: Real-time autocomplete search with debouncing
- **Actor Profiles**: Detailed actor information with biography, filmography, and images
- **Connection Visualization**: Interactive graph showing relationships between actors
- **Authentication**: Basic auth integration with the backend API
- **Responsive Design**: Modern UI built with Tailwind CSS and shadcn/ui

## Tech Stack

- **Framework**: React 18 + TypeScript 5
- **Build Tool**: Vite 7
- **Styling**: Tailwind CSS 3 + shadcn/ui
- **Graph Visualization**: ReactFlow (@xyflow/react)
- **State Management**: Zustand + React Query
- **HTTP Client**: Axios
- **Routing**: React Router 7
- **Form Handling**: React Hook Form + Zod
- **Testing**: Vitest + Testing Library

## Getting Started

### Prerequisites

- Node.js 20+ and npm
- Backend server running on `http://localhost:8080`

### Installation

```bash
npm install
```

### Development

```bash
npm run dev
```

Runs the app in development mode at `http://localhost:5173`.

### Building

```bash
npm run build
```

Builds the app for production to the `dist` folder.

### Testing

```bash
# Run tests
npm test

# Run tests with UI
npm run test:ui

# Run tests with coverage
npm run test:coverage
```

## Project Structure

```
src/
├── components/          # Reusable UI components
│   ├── ui/             # shadcn/ui base components
│   ├── auth/           # Authentication components
│   ├── actor/          # Actor search and display
│   ├── graph/          # Graph visualization
│   └── layout/         # Layout components
├── services/           # API services
│   └── api/           # Backend API integration
├── stores/            # Zustand state stores
├── hooks/             # Custom React hooks
├── pages/             # Page components
├── types/             # TypeScript type definitions
├── utils/             # Utility functions
└── test/              # Test utilities and setup
```

## Environment Variables

Create a `.env.development` file:

```env
VITE_API_URL=http://localhost:8080
VITE_TMDB_IMAGE_BASE_URL=https://image.tmdb.org/t/p
```

## API Integration

The frontend communicates with the Spring Boot backend:

- `GET /search/person/{name}` - Search for actors
- `GET /person/{id}` - Get actor details
- `GET /person/{id}/credits` - Get actor credits
- `GET /connection/{actor1Id}/{actor2Id}` - Find connections

Authentication uses HTTP Basic Auth with credentials stored in Zustand.

## Key Features

### Actor Search

- Debounced autocomplete (300ms)
- Displays profile images and known-for department
- Prefetches actor details on blur for smooth UX

### Connection Graph

- Automatic layout using dagre algorithm
- Multiple path visualization with color coding
- Interactive zoom, pan, and navigation controls
- Custom nodes for actors (circular) and movies (square)

### Performance

- React Query caching (5-minute stale time)
- Code splitting for optimal bundle size
- Lazy image loading with fallbacks
- Debounced search to minimize API calls

## Development Guidelines

- All components are typed with TypeScript
- Use shadcn/ui components for consistency
- Follow React Query patterns for data fetching
- Add unit tests for utilities and hooks
- Maintain accessibility with ARIA labels

## License

This project follows the parent repository's license.
