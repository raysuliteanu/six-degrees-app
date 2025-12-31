# Deployment Guide

This guide covers deploying the Six Degrees web frontend in various configurations.

## Table of Contents

- [Production Build](#production-build)
- [Environment Variables](#environment-variables)
- [Deployment Options](#deployment-options)
  - [Option 1: Standalone with Nginx](#option-1-standalone-with-nginx)
  - [Option 2: Served by Spring Boot](#option-2-served-by-spring-boot)
  - [Option 3: Docker Container](#option-3-docker-container)
- [Performance Optimization](#performance-optimization)

## Production Build

Build the application for production:

```bash
npm run build
```

This creates an optimized production build in the `dist/` directory with:

- **Minified and bundled JavaScript** (code splitting enabled)
- **Optimized CSS** with Tailwind purging
- **Vendor chunk splitting** for better caching:
  - `react-vendor` - React core libraries
  - `graph-vendor` - ReactFlow and dagre
  - `query-vendor` - TanStack Query
  - `form-vendor` - React Hook Form and Zod
  - `ui-vendor` - UI utilities
  - `index` - Application code

**Build Output:**
```
dist/
├── index.html
├── assets/
│   ├── index-*.css          (~29 kB, gzip: ~6 kB)
│   ├── react-vendor-*.js    (~47 kB, gzip: ~17 kB)
│   ├── graph-vendor-*.js    (~267 kB, gzip: ~89 kB)
│   ├── query-vendor-*.js    (~33 kB, gzip: ~10 kB)
│   ├── form-vendor-*.js     (~82 kB, gzip: ~25 kB)
│   ├── ui-vendor-*.js       (~29 kB, gzip: ~10 kB)
│   └── index-*.js           (~242 kB, gzip: ~79 kB)
```

## Environment Variables

### Development (.env.development)

```env
VITE_API_URL=http://localhost:8080
VITE_TMDB_IMAGE_BASE_URL=https://image.tmdb.org/t/p
```

### Production (.env.production)

```env
VITE_API_URL=/api
VITE_TMDB_IMAGE_BASE_URL=https://image.tmdb.org/t/p
```

**Note:** The production build uses relative paths (`/api`) assuming the frontend and backend are served from the same domain.

### Custom Environment Variables

Create a `.env.production.local` file (gitignored) for environment-specific overrides:

```env
VITE_API_URL=https://api.example.com
```

## Deployment Options

### Option 1: Standalone with Nginx

Deploy the frontend as a static site with Nginx proxying API requests to the backend.

#### Nginx Configuration

```nginx
server {
    listen 80;
    server_name example.com;
    root /var/www/six-degrees/dist;
    index index.html;

    # Serve static files
    location / {
        try_files $uri $uri/ /index.html;
    }

    # Proxy API requests to Spring Boot backend
    location /api {
        rewrite ^/api(.*)$ $1 break;
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # Cache static assets
    location ~* \.(js|css|png|jpg|jpeg|gif|ico|svg|woff|woff2|ttf|eot)$ {
        expires 1y;
        add_header Cache-Control "public, immutable";
    }

    # Gzip compression
    gzip on;
    gzip_vary on;
    gzip_min_length 1024;
    gzip_types text/plain text/css text/xml text/javascript application/javascript application/json application/xml+rss;
}
```

#### Deployment Steps

1. Build the production bundle:
   ```bash
   npm run build
   ```

2. Copy `dist/` to server:
   ```bash
   rsync -avz dist/ user@server:/var/www/six-degrees/dist/
   ```

3. Reload Nginx:
   ```bash
   sudo nginx -t
   sudo systemctl reload nginx
   ```

### Option 2: Served by Spring Boot

Configure Spring Boot to serve the frontend static files.

#### Spring Boot Configuration

Add to `application.yaml`:

```yaml
spring:
  web:
    resources:
      static-locations: classpath:/static/
      cache:
        period: 31536000  # 1 year for static assets
```

#### Gradle Build Configuration

Update `build.gradle` to copy frontend build output:

```groovy
task copyFrontend(type: Copy) {
    from '../web/dist'
    into 'src/main/resources/static'
}

processResources.dependsOn copyFrontend
```

Or manually:

```bash
# From web/ directory
npm run build

# Copy to Spring Boot static resources
cp -r dist/* ../server/src/main/resources/static/
```

#### Controller for SPA Routing

Create a controller to handle SPA routing:

```java
@Controller
public class SpaController {
    @GetMapping(value = "/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}
```

This ensures React Router handles client-side routing.

#### Build and Run

```bash
cd ../server
./gradlew bootRun
```

Access at: `http://localhost:8080`

### Option 3: Docker Container

#### Multi-Stage Dockerfile

Create `Dockerfile` in the `web/` directory:

```dockerfile
# Build stage
FROM node:20-alpine AS build

WORKDIR /app

# Copy package files
COPY package*.json ./
RUN npm ci

# Copy source and build
COPY . .
RUN npm run build

# Production stage
FROM nginx:alpine

# Copy built files
COPY --from=build /app/dist /usr/share/nginx/html

# Copy nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
```

#### Nginx Configuration for Docker

Create `nginx.conf`:

```nginx
server {
    listen 80;
    server_name _;
    root /usr/share/nginx/html;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html;
    }

    location /api {
        proxy_pass http://backend:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    gzip on;
    gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript;
}
```

#### Docker Compose

The root `docker-compose.yml` includes Neo4j database:

```yaml
version: '3.8'

services:
  neo4j:
    image: neo4j:5
    container_name: sixdegrees-neo4j
    ports:
      - "7474:7474"  # HTTP for Neo4j Browser
      - "7687:7687"  # Bolt protocol
    environment:
      - NEO4J_AUTH=${NEO4J_USERNAME}/${NEO4J_PASSWORD}
    volumes:
      - neo4j_data:/data
    networks:
      - sixdegrees

  backend:
    build: ./server
    container_name: sixdegrees-backend
    ports:
      - "8080:8080"
    environment:
      - TMDB_API_TOKEN=${TMDB_API_TOKEN}
      - TMDB_ACCESS_TOKEN=${TMDB_ACCESS_TOKEN}
      - NEO4J_URI=bolt://neo4j:7687
      - NEO4J_USERNAME=${NEO4J_USERNAME}
      - NEO4J_PASSWORD=${NEO4J_PASSWORD}
    depends_on:
      neo4j:
        condition: service_healthy
    networks:
      - sixdegrees

  frontend:
    build: ./web
    container_name: sixdegrees-frontend
    ports:
      - "80:80"
    depends_on:
      backend:
        condition: service_healthy
    networks:
      - sixdegrees

networks:
  sixdegrees:
    driver: bridge

volumes:
  neo4j_data:
```

#### Build and Run

```bash
# Set required environment variables
export TMDB_API_TOKEN=your_api_token
export TMDB_ACCESS_TOKEN=your_access_token
export NEO4J_USERNAME=neo4j
export NEO4J_PASSWORD=your_secure_password

# Build images
docker-compose build

# Run containers
docker-compose up -d

# View logs
docker-compose logs -f

# Stop containers
docker-compose down
```

Access at: `http://localhost`

## Performance Optimization

### Caching Strategy

**Static Assets:**
- Set long cache expiration (1 year) for fingerprinted assets
- Vite automatically adds content hashes to filenames
- Update `Cache-Control` headers in your web server

**API Responses:**
- Frontend uses React Query with 5-minute stale time
- Backend can add appropriate `Cache-Control` headers

### CDN Deployment

For better global performance, deploy static assets to a CDN:

1. Upload `dist/assets/*` to CDN (e.g., CloudFront, Cloudflare)
2. Update `index.html` to reference CDN URLs
3. Configure CORS on CDN to allow API requests

### Compression

All deployment options should enable:
- **Gzip** compression (minimum)
- **Brotli** compression (preferred)

Nginx example:
```nginx
gzip on;
gzip_vary on;
gzip_min_length 1024;
gzip_types text/plain text/css application/json application/javascript;

# If brotli module is available
brotli on;
brotli_types text/plain text/css application/json application/javascript;
```

### Bundle Analysis

Analyze bundle size and dependencies:

```bash
npm run build -- --mode analyze
```

Or use `rollup-plugin-visualizer` for detailed analysis.

## Health Checks

### Frontend Health Check

Nginx can serve a health check endpoint:

```nginx
location /health {
    access_log off;
    return 200 "OK";
    add_header Content-Type text/plain;
}
```

### Backend Health Check

Ensure Spring Boot actuator is configured:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health
```

Access at: `/actuator/health`

## Security Considerations

1. **HTTPS**: Always use HTTPS in production
2. **CORS**: Configure proper CORS headers on backend
3. **CSP**: Consider Content Security Policy headers
4. **Authentication**: Basic Auth credentials should be transmitted over HTTPS only

Example security headers in Nginx:

```nginx
add_header X-Frame-Options "SAMEORIGIN" always;
add_header X-Content-Type-Options "nosniff" always;
add_header X-XSS-Protection "1; mode=block" always;
add_header Referrer-Policy "strict-origin-when-cross-origin" always;
```

## Monitoring

### Error Tracking

Consider integrating error tracking:
- Sentry
- LogRocket
- Bugsnag

### Analytics

Add analytics if needed:
- Google Analytics
- Plausible
- Matomo

### Performance Monitoring

Monitor Core Web Vitals:
- Lighthouse CI
- WebPageTest
- New Relic Browser

## Troubleshooting

### Issue: 404 on page refresh

**Solution:** Ensure your web server is configured to serve `index.html` for all routes (SPA fallback).

### Issue: API requests failing

**Solution:** Check:
1. `VITE_API_URL` environment variable is correct
2. CORS is properly configured on backend
3. Backend is accessible from frontend server

### Issue: Blank page

**Solution:** Check:
1. Browser console for JavaScript errors
2. Network tab for failed asset loads
3. Ensure all environment variables are set

### Issue: Large bundle size

**Solution:**
1. Analyze bundle with `npm run build -- --mode analyze`
2. Check for duplicate dependencies
3. Consider lazy loading routes with React.lazy()

## Support

For deployment issues, check:
- [Vite Deployment Guide](https://vitejs.dev/guide/static-deploy.html)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- Project README.md
