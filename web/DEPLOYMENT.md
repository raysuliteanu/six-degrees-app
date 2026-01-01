# Frontend Deployment

For complete deployment instructions covering the entire Six Degrees application (frontend, backend, and database), please refer to the main deployment guide:

**[../DEPLOYMENT.md](../DEPLOYMENT.md)**

## Quick Frontend Build

To build the frontend for production:

```bash
npm install
npm run build
```

This creates an optimized production build in the `dist/` directory.

## Frontend-Specific Configuration

### Environment Variables

**Development (.env.development):**

```env
VITE_API_URL=http://localhost:8080
VITE_TMDB_IMAGE_BASE_URL=https://image.tmdb.org/t/p
```

**Production (.env.production):**

```env
VITE_API_URL=/api
VITE_TMDB_IMAGE_BASE_URL=https://image.tmdb.org/t/p
```

### Build Output

The production build includes:

- **Optimized vendor chunks** for better caching:
  - `react-vendor` - React core libraries (~47 kB gzipped)
  - `graph-vendor` - ReactFlow and dagre (~89 kB gzipped)
  - `query-vendor` - TanStack Query (~10 kB gzipped)
  - `form-vendor` - React Hook Form and Zod (~25 kB gzipped)
  - `ui-vendor` - UI utilities (~10 kB gzipped)
  - `index` - Application code (~79 kB gzipped)

Total gzipped size: ~230 kB

### Docker Build

To build the frontend Docker image:

```bash
docker build -t sixdegrees-frontend .
```

The Dockerfile uses a multi-stage build to create a production-ready Nginx image.

## For Complete Deployment Instructions

See the main [DEPLOYMENT.md](../DEPLOYMENT.md) guide which covers:

- Full-stack Docker Compose deployment
- Standalone component deployment
- Cloud deployment options (AWS, Kubernetes)
- Production configuration
- Monitoring and health checks
- Security considerations
- Troubleshooting
- Backup and recovery
