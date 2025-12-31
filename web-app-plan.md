# Six Degrees Web Frontend Implementation Plan

## Overview

Build a modern React TypeScript SPA for the Six Degrees application that allows users to search for two actors and visualize their connection through shared movie credits using an interactive graph.

## Technology Stack

### Frontend
- **Framework**: React 18+ with TypeScript 5+
- **Build Tool**: Vite 6
- **UI Library**: shadcn/ui + Tailwind CSS 4
- **Graph Visualization**: ReactFlow (@xyflow/react)
- **State Management**: Zustand (auth, actor selection) + React Query (server state)
- **HTTP Client**: Axios with Basic Auth interceptors
- **Routing**: React Router 7
- **Testing**: Vitest (unit) + Playwright (e2e)
- **Form Handling**: React Hook Form + Zod validation

### Backend Changes Required
- New `/connection` endpoint for graph pathfinding using Neo4j
- CORS configuration for cross-origin requests
- Return connection paths with actor and movie nodes

## Backend Implementation

### 1. Add CORS Configuration

**File**: `server/src/main/java/org/kidoni/sixdegrees/configuration/SixDegreesConfiguration.java`

Add CORS bean to allow frontend access:

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:5173",  // Vite dev
                    "http://localhost:3000"   // Alternative
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        }
    };
}
```

### 2. Create Connection Endpoint

**New File**: `server/src/main/java/org/kidoni/sixdegrees/dto/ConnectionPath.java`

```java
public record ConnectionPath(
    List<ConnectionNode> nodes,
    List<ConnectionEdge> edges,
    Integer degree
) {}

public record ConnectionNode(
    String id,           // "actor-{id}" or "movie-{id}"
    String type,         // "actor" or "movie"
    String name,
    String imageUrl,     // profilePath or posterPath
    Map<String, Object> metadata
) {}

public record ConnectionEdge(
    String from,
    String to,
    String label         // e.g., "as Tony Stark"
) {}
```

**Update**: `server/src/main/java/org/kidoni/sixdegrees/controller/SixDegreesController.java`

Add new endpoint:

```java
@GetMapping(path = "/connection/{actor1Id}/{actor2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
public List<ConnectionPath> findConnection(
    @PathVariable Integer actor1Id,
    @PathVariable Integer actor2Id,
    @RequestParam(defaultValue = "6") Integer maxDegrees
) {
    return sixDegreesService.findConnections(actor1Id, actor2Id, maxDegrees);
}
```

**Update**: `server/src/main/java/org/kidoni/sixdegrees/service/TmdbSixDegreesService.java`

Add method to interface and implementation:

```java
/**
 * Find connection paths between two actors using Neo4j graph traversal.
 * Uses Cypher query to find shortest paths through shared movies.
 * Returns multiple paths if they exist at the same degree.
 */
List<ConnectionPath> findConnections(Integer actor1Id, Integer actor2Id, Integer maxDegrees);
```

Implementation will use Neo4j Cypher query:
```cypher
MATCH path = shortestPath(
  (a1:PersonDetails {id: $actor1Id})-[:KNOWN_FOR*..12]-(a2:PersonDetails {id: $actor2Id})
)
RETURN path
```

Transform Neo4j path results into ConnectionPath DTOs with proper node types and edge labels.

## Frontend Architecture

### Project Structure

```
web/
├── src/
│   ├── components/
│   │   ├── ui/                    # shadcn/ui components (button, card, input, etc.)
│   │   ├── auth/
│   │   │   └── LoginForm.tsx      # Login form with Basic Auth
│   │   ├── actor/
│   │   │   ├── ActorSearchInput.tsx   # Autocomplete search input
│   │   │   ├── ActorCard.tsx          # Display actor details
│   │   │   └── ActorCardSkeleton.tsx  # Loading state
│   │   ├── graph/
│   │   │   ├── ConnectionGraph.tsx    # Main graph component (ReactFlow)
│   │   │   ├── ActorNode.tsx          # Custom actor node (round, with photo)
│   │   │   ├── MovieNode.tsx          # Custom movie node (square, with poster)
│   │   │   ├── GraphControls.tsx      # Zoom, fit view, legend
│   │   │   └── NoConnectionMessage.tsx
│   │   └── layout/
│   │       ├── MainLayout.tsx
│   │       └── ProtectedRoute.tsx
│   ├── services/
│   │   └── api/
│   │       ├── client.ts          # Axios instance with Basic Auth
│   │       ├── personApi.ts       # Person endpoints
│   │       ├── connectionApi.ts   # Connection endpoint
│   │       └── types.ts           # API response types (mirror backend DTOs)
│   ├── stores/
│   │   ├── authStore.ts           # Zustand: username, password, isAuthenticated
│   │   └── actorStore.ts          # Zustand: leftActorId, rightActorId
│   ├── hooks/
│   │   ├── useAuth.ts
│   │   ├── useActorSearch.ts
│   │   ├── useDebounce.ts         # 300ms debounce for search
│   │   └── useConnection.ts       # Fetch connection paths
│   ├── pages/
│   │   ├── LoginPage.tsx
│   │   ├── MainPage.tsx           # Main app: search + cards + graph
│   │   └── NotFoundPage.tsx
│   ├── types/
│   │   ├── api.ts                 # Backend types (PersonDetails, etc.)
│   │   └── graph.ts               # ReactFlow types
│   ├── utils/
│   │   ├── imageUrl.ts            # TMDB image URL builder
│   │   └── constants.ts
│   ├── App.tsx
│   ├── main.tsx
│   └── index.css
├── tests/
│   ├── unit/
│   └── e2e/
├── vite.config.ts
├── vitest.config.ts
├── playwright.config.ts
├── tailwind.config.ts
└── package.json
```

### Key Components

#### 1. LoginPage & Authentication

**LoginForm.tsx**
- Username/password inputs (default: admin/admin)
- Form validation with Zod
- Store credentials in Zustand authStore
- Test connection with simple API call
- Redirect to MainPage on success

**Zustand authStore.ts**
```typescript
interface AuthState {
  username: string | null;
  password: string | null;
  isAuthenticated: boolean;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
}
```

**ProtectedRoute.tsx**
- Check `isAuthenticated` from authStore
- Redirect to `/login` if not authenticated

#### 2. Main Page Layout

**MainPage.tsx** - Two-column layout:

```
┌─────────────────────────────────────────────────┐
│  Actor 1: [search input]  Actor 2: [search input]│
├────────────────┬────────────────────────────────┤
│  ActorCard     │  ActorCard                     │
│  (left actor)  │  (right actor)                 │
├────────────────┴────────────────────────────────┤
│  ConnectionGraph                                │
│  (Shows paths between actors)                   │
└─────────────────────────────────────────────────┘
```

**State Management**:
- Zustand store: `leftActorId`, `rightActorId`
- React Query: Cache actor details, credits, connection paths

**Async Behavior**:
- User enters "Tom" in left input → debounced search
- User selects "Tom Hanks" → `setLeftActorId(31)`
- **User tabs to right input** → `onBlur` triggers prefetch:
  - `queryClient.prefetchQuery(['person', 31])`
  - `queryClient.prefetchQuery(['person-credits', 31])`
- Left ActorCard renders with cached data
- Same flow for right actor
- When both actors selected → ConnectionGraph fetches paths

#### 3. Actor Search Input

**ActorSearchInput.tsx**
- Debounced search (300ms) on input change
- Calls `GET /search/person/{name}`
- Dropdown with results (name, known for, profile image)
- Click to select → update actor store
- `onBlur` prop to trigger async prefetch

**React Query Hook**:
```typescript
const { data: searchResults } = useQuery({
  queryKey: ['person-search', debouncedSearch],
  queryFn: () => personApi.searchByName(debouncedSearch),
  enabled: debouncedSearch.length >= 2,
  staleTime: 5 * 60 * 1000, // 5 min cache
});
```

#### 4. Actor Card

**ActorCard.tsx** - shadcn/ui Card displaying:
- Profile image (from TMDB `profilePath`)
- Name
- Birthday / Deathday
- Known for department
- Place of birth
- Biography (truncated, expandable)
- Top 5 credits

**Data Source**:
- React Query: `useQuery(['person', actorId])`
- Shows ActorCardSkeleton while loading
- Error state if fetch fails

#### 5. Connection Graph

**ConnectionGraph.tsx** - Core visualization component

**Flow**:
1. Watch `leftActorId` and `rightActorId` from store
2. When both present, fetch: `GET /connection/{actor1Id}/{actor2Id}?maxDegrees=6`
3. Backend returns `List<ConnectionPath>` (already structured as graph)
4. Transform to ReactFlow format:
   - `ConnectionNode` → ReactFlow `Node` with custom types
   - `ConnectionEdge` → ReactFlow `Edge` with colors/labels
5. Apply layout algorithm (dagre) for positioning
6. Render with ReactFlow

**Multiple Paths Display**:
- Each path gets different color (blue, purple, pink, orange)
- All paths shown simultaneously
- Shortest path has thicker edges + animation
- Legend shows degree for each path

**Custom Nodes**:

**ActorNode.tsx**:
- shadcn/ui Card with rounded corners
- Circular profile image
- Name + department
- ReactFlow handles (top/bottom)

**MovieNode.tsx**:
- shadcn/ui Card with square design
- Movie poster thumbnail
- Title + release year
- Film icon
- ReactFlow handles (top/bottom)

**Layout**:
```typescript
import dagre from 'dagre';

// Top-to-bottom layout with automatic positioning
dagreGraph.setGraph({ rankdir: 'TB', ranksep: 100, nodesep: 80 });
```

**ReactFlow Setup**:
```typescript
const nodeTypes = {
  actorNode: ActorNode,
  movieNode: MovieNode,
};

<ReactFlow
  nodes={nodes}
  edges={edges}
  nodeTypes={nodeTypes}
  fitView
  attributionPosition="bottom-right"
>
  <Background />
  <Controls />
</ReactFlow>
```

**No Connection Handling**:
- If backend returns empty array → show friendly message
- "No connection found within 6 degrees"
- Suggest trying different actors

### API Integration

**Axios Client** (`services/api/client.ts`):

```typescript
const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080',
  timeout: 10000,
});

// Add Basic Auth to all requests
apiClient.interceptors.request.use((config) => {
  const { username, password } = useAuthStore.getState();
  if (username && password) {
    config.headers.Authorization = `Basic ${btoa(`${username}:${password}`)}`;
  }
  return config;
});

// Handle 401 → logout
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      useAuthStore.getState().logout();
    }
    return Promise.reject(error);
  }
);
```

**TypeScript Types** - Mirror backend DTOs exactly:

```typescript
// types/api.ts
export interface PersonDetails {
  id: number;
  name: string;
  birthday: string | null;
  deathday: string | null;
  biography: string | null;
  profilePath: string | null;
  knownForDepartment: string | null;
  placeOfBirth: string | null;
  popularity: number;
  // ... all fields from backend
}

export interface ConnectionPath {
  nodes: ConnectionNode[];
  edges: ConnectionEdge[];
  degree: number;
}

export interface ConnectionNode {
  id: string;
  type: 'actor' | 'movie';
  name: string;
  imageUrl: string | null;
  metadata: Record<string, any>;
}

export interface ConnectionEdge {
  from: string;
  to: string;
  label: string | null;
}
```

### Performance Optimizations

1. **Code Splitting**:
   - Lazy load MainPage and LoginPage
   - Lazy load ConnectionGraph (only when both actors selected)
   - ReactFlow bundle loaded on-demand

2. **React Query Caching**:
   - `staleTime: 5 minutes` for actor details
   - `cacheTime: 10 minutes` for all queries
   - Prefetch on input blur

3. **Debouncing**: 300ms for search inputs

4. **Image Optimization**:
   - Use TMDB's sized images: `w92` for thumbnails, `w185` for cards
   - Lazy load images below the fold

5. **Bundle Splitting**:
   ```javascript
   manualChunks: {
     'react-vendor': ['react', 'react-dom', 'react-router-dom'],
     'graph-vendor': ['@xyflow/react'],
     'ui-vendor': ['@radix-ui/...'],
   }
   ```

### Testing Strategy

**Unit Tests (Vitest)**:
- API client auth interceptors
- Zustand stores (login, logout, actor selection)
- Custom hooks (useDebounce, useActorSearch)
- Utility functions (imageUrl builder)
- Component logic (ActorCard rendering)

**E2E Tests (Playwright)**:
1. Login flow (invalid creds, valid creds, logout)
2. Actor search (search, select, display details)
3. Connection graph (two actors, graph renders, multiple paths)
4. No connection scenario
5. Async prefetch behavior

**Mocking**: MSW (Mock Service Worker) for API mocking in tests

## Implementation Phases

### Phase 1: Project Setup & Authentication (Days 1-2)
- [ ] Initialize Vite + React + TypeScript project in `web/`
- [ ] Install dependencies: React Router, Zustand, React Query, Axios
- [ ] Setup Tailwind CSS + shadcn/ui CLI
- [ ] Add shadcn/ui components: button, card, input, label, form
- [ ] Create axios client with Basic Auth interceptors
- [ ] Implement authStore (Zustand)
- [ ] Build LoginPage and LoginForm
- [ ] Setup routing with ProtectedRoute
- [ ] Test login flow

### Phase 2: Backend Changes (Days 2-3)
- [ ] Add CORS configuration to SixDegreesConfiguration
- [ ] Create ConnectionPath, ConnectionNode, ConnectionEdge DTOs
- [ ] Add `findConnections()` method to TmdbSixDegreesService interface
- [ ] Implement Neo4j Cypher query for shortest paths
- [ ] Transform Neo4j paths to ConnectionPath DTOs
- [ ] Add `/connection/{actor1Id}/{actor2Id}` endpoint to controller
- [ ] Test endpoint with curl/Postman
- [ ] Write unit tests for connection service
- [ ] Write integration test for connection endpoint

### Phase 3: Actor Search & Display (Days 3-5)
- [ ] Create TypeScript types mirroring backend DTOs
- [ ] Implement personApi service (searchByName, getById, getCredits)
- [ ] Build ActorSearchInput with autocomplete
- [ ] Implement useDebounce hook
- [ ] Create ActorCard component
- [ ] Create ActorCardSkeleton for loading state
- [ ] Build actorStore (Zustand)
- [ ] Build MainPage layout (two-column)
- [ ] Implement async prefetch on blur
- [ ] Test actor search and card display
- [ ] Add error handling and empty states

### Phase 4: Graph Visualization (Days 5-7)
- [ ] Install ReactFlow (@xyflow/react) and dagre
- [ ] Create connectionApi service
- [ ] Build ActorNode custom component
- [ ] Build MovieNode custom component
- [ ] Implement ConnectionGraph component
- [ ] Transform ConnectionPath to ReactFlow nodes/edges
- [ ] Apply dagre layout algorithm
- [ ] Add multiple path colors and styling
- [ ] Implement GraphControls (zoom, fit view)
- [ ] Add NoConnectionMessage component
- [ ] Test with various actor pairs
- [ ] Add loading spinner for graph calculation

### Phase 5: Polish & Testing (Days 7-9)
- [ ] Setup Vitest configuration
- [ ] Write unit tests for stores, hooks, utilities
- [ ] Setup Playwright configuration
- [ ] Write e2e tests (login, search, graph)
- [ ] Add comprehensive error handling
- [ ] Implement loading states everywhere
- [ ] Add keyboard navigation
- [ ] Accessibility audit (ARIA labels, semantic HTML)
- [ ] Performance audit (bundle size, lighthouse)
- [ ] Add error boundaries
- [ ] Polish animations and transitions
- [ ] Documentation (README, component docs)

### Phase 6: Build & Integration (Days 9-10)
- [ ] Configure environment variables (.env files)
- [ ] Setup Vite build config (proxy, chunks)
- [ ] Test production build
- [ ] Configure Spring Boot to serve static assets (optional)
- [ ] Create Docker configuration (if needed)
- [ ] Write deployment documentation

## Critical Files

### Backend (to modify/create)
- `server/src/main/java/org/kidoni/sixdegrees/configuration/SixDegreesConfiguration.java` - Add CORS
- `server/src/main/java/org/kidoni/sixdegrees/dto/ConnectionPath.java` - New DTO
- `server/src/main/java/org/kidoni/sixdegrees/dto/ConnectionNode.java` - New DTO
- `server/src/main/java/org/kidoni/sixdegrees/dto/ConnectionEdge.java` - New DTO
- `server/src/main/java/org/kidoni/sixdegrees/controller/SixDegreesController.java` - Add endpoint
- `server/src/main/java/org/kidoni/sixdegrees/service/TmdbSixDegreesService.java` - Add method
- `server/src/main/java/org/kidoni/sixdegrees/service/impl/TmdbSixDegreesServiceImpl.java` - Implementation

### Frontend (to create)
- `web/vite.config.ts` - Build configuration
- `web/src/services/api/client.ts` - Axios with auth
- `web/src/services/api/connectionApi.ts` - Connection endpoint
- `web/src/stores/authStore.ts` - Authentication state
- `web/src/stores/actorStore.ts` - Actor selection state
- `web/src/pages/LoginPage.tsx` - Login page
- `web/src/pages/MainPage.tsx` - Main application page
- `web/src/components/actor/ActorSearchInput.tsx` - Search with autocomplete
- `web/src/components/actor/ActorCard.tsx` - Actor details card
- `web/src/components/graph/ConnectionGraph.tsx` - Graph visualization
- `web/src/components/graph/ActorNode.tsx` - Custom ReactFlow node
- `web/src/components/graph/MovieNode.tsx` - Custom ReactFlow node

## Environment Configuration

**Development**:
```bash
# .env.development
VITE_API_URL=http://localhost:8080
VITE_TMDB_IMAGE_BASE_URL=https://image.tmdb.org/t/p
```

**Vite Proxy** (alternative to CORS for dev):
```typescript
// vite.config.ts
server: {
  port: 5173,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true,
      rewrite: (path) => path.replace(/^\/api/, ''),
    },
  },
}
```

## Open Items & Future Enhancements

**Decisions to finalize during implementation**:
1. Profile image placeholder design (when actor has no profilePath)
2. Maximum degree control (fixed at 6 or user-adjustable slider?)
3. Graph zoom/pan UX (default zoom level, fit-to-view behavior)
4. Mobile responsiveness strategy (stack cards vertically?)

**Future enhancements** (out of scope for initial implementation):
1. User registration and account management
2. Save favorite actor searches
3. Share connection links (shareable URLs)
4. Export graph as image
5. Advanced filtering (by genre, decade, etc.)
6. Performance metrics (show query time)
7. Actor comparison stats (shared movies count, etc.)

## Success Criteria

- [ ] User can log in with Basic Auth credentials
- [ ] User can search for actors with autocomplete
- [ ] Actor details display correctly in card format
- [ ] Async prefetch works on input blur
- [ ] Connection graph renders for connected actors
- [ ] Multiple paths display with different colors
- [ ] "No connection" message shows when appropriate
- [ ] All unit tests pass
- [ ] All e2e tests pass
- [ ] Page load < 2 seconds
- [ ] Graph renders < 1 second for typical connections
- [ ] Accessibility score > 90 (Lighthouse)
- [ ] No console errors in production build
