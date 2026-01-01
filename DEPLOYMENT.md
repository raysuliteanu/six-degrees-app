# Six Degrees Application Deployment Guide

This guide covers deploying the complete Six Degrees application stack: React
frontend, Spring Boot backend, and Neo4j database.

## Table of Contents

- [Quick Start with Docker Compose](#quick-start-with-docker-compose)
- [Environment Variables](#environment-variables)
- [Component-Specific Builds](#component-specific-builds)
- [Deployment Options](#deployment-options)
  - [Option 1: Docker Compose (Recommended)](#option-1-docker-compose-recommended)
  - [Option 2: Standalone Components](#option-2-standalone-components)
  - [Option 3: Cloud Deployment](#option-3-cloud-deployment)
- [Production Configuration](#production-configuration)
- [Monitoring and Health Checks](#monitoring-and-health-checks)
- [Security Considerations](#security-considerations)
- [Troubleshooting](#troubleshooting)

## Quick Start with Docker Compose

The fastest way to deploy the entire application:

```bash
# Set required environment variables
export TMDB_ACCESS_TOKEN=your_tmdb_access_token
export NEO4J_USERNAME=neo4j
export NEO4J_PASSWORD=your_secure_password

# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

**Access Points:**

- Frontend: `http://localhost`
- Backend API: `http://localhost:8080`
- Neo4j Browser: `http://localhost:7474`

**Default Credentials:**

- Application: `admin` / `admin`
- Neo4j: `${NEO4J_USERNAME}` / `${NEO4J_PASSWORD}`

## Environment Variables

### Required for All Deployments

| Variable            | Description             | Example             |
| ------------------- | ----------------------- | ------------------- |
| `TMDB_ACCESS_TOKEN` | TMDB access token       | `your-access-token` |
| `NEO4J_USERNAME`    | Neo4j database username | `neo4j`             |
| `NEO4J_PASSWORD`    | Neo4j database password | `secure-password`   |

### Backend-Specific

| Variable    | Description          | Default                 |
| ----------- | -------------------- | ----------------------- |
| `NEO4J_URI` | Neo4j connection URI | `bolt://localhost:7687` |

### Frontend-Specific

| Variable                   | Description         | Default                      |
| -------------------------- | ------------------- | ---------------------------- |
| `VITE_API_URL`             | Backend API URL     | `http://localhost:8080`      |
| `VITE_TMDB_IMAGE_BASE_URL` | TMDB image base URL | `https://image.tmdb.org/t/p` |

## Component-Specific Builds

### Backend (Spring Boot)

**Standard JAR:**

```bash
cd server
./gradlew bootJar
```

Output: `server/build/libs/sixdegrees-0.0.1-SNAPSHOT.jar`

**Native Image (GraalVM):**

```bash
cd server
./gradlew bootBuildImage
```

Output: Docker image `org.kidoni/sixdegrees:0.0.1-SNAPSHOT`

**Run JAR:**

```bash
java -jar server/build/libs/sixdegrees-0.0.1-SNAPSHOT.jar
```

### Frontend (React/Vite)

**Production Build:**

```bash
cd web
npm install
npm run build
```

Output: `web/dist/` directory

**Build Output:**

- Total size: ~700 kB (uncompressed), ~230 kB (gzipped)
- Optimized vendor chunks for better caching
- All assets have content-based hashes for cache busting

### Database (Neo4j)

**Docker:**

```bash
docker run -d \
  --name neo4j \
  -p 7474:7474 -p 7687:7687 \
  -e NEO4J_AUTH=${NEO4J_USERNAME}/${NEO4J_PASSWORD} \
  -v neo4j_data:/data \
  neo4j:5
```

**Standalone:**
Download from [neo4j.com/download](https://neo4j.com/download/) and configure:

```properties
dbms.connector.bolt.enabled=true
dbms.connector.bolt.listen_address=0.0.0.0:7687
dbms.security.auth_enabled=true
```

## Deployment Options

### Option 1: Docker Compose (Recommended)

Deploy the complete stack with a single command.

#### Architecture

```text
┌─────────────┐
│   Frontend  │ :80
│   (Nginx)   │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   Backend   │ :8080
│ (Spring)    │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│   Neo4j     │ :7687, :7474
│  Database   │
└─────────────┘
```

#### Configuration

The `docker-compose.yml` at the project root includes:

**Services:**

- `neo4j` - Neo4j 5 database with health checks
- `backend` - Spring Boot application (depends on Neo4j)
- `frontend` - React SPA served by Nginx (depends on backend)

**Features:**

- Health check dependencies ensure proper startup order
- Persistent volumes for Neo4j data
- Automatic service discovery via Docker DNS
- Isolated network for inter-service communication

#### Deployment Steps

1. **Set environment variables:**

   ```bash
   export TMDB_ACCESS_TOKEN=your_access_token
   export NEO4J_USERNAME=neo4j
   export NEO4J_PASSWORD=your_secure_password
   ```

2. **Build images:**

   ```bash
   docker-compose build
   ```

3. **Start services:**

   ```bash
   docker-compose up -d
   ```

4. **Verify deployment:**

   ```bash
   # Check all services are running
   docker-compose ps

   # Check backend health
   curl -u admin:admin http://localhost:8080/actuator/health

   # Access frontend
   curl http://localhost
   ```

5. **View logs:**

   ```bash
   # All services
   docker-compose logs -f

   # Specific service
   docker-compose logs -f backend
   ```

#### Production Considerations

For production deployment:

1. **Use production-ready images:**
   - Build backend as native image for better performance
   - Use multi-stage builds to minimize image size

2. **Configure resource limits:**

   ```yaml
   services:
     backend:
       deploy:
         resources:
           limits:
             cpus: "2"
             memory: 2G
           reservations:
             cpus: "1"
             memory: 1G
   ```

3. **Enable restart policies:**

   ```yaml
   services:
     backend:
       restart: unless-stopped
   ```

4. **Use secrets for sensitive data:**

   ```yaml
   services:
     backend:
       secrets:
         - neo4j_password
   ```

### Option 2: Standalone Components

Deploy each component separately on different servers.

#### Neo4j Database Server

**Install Neo4j:**

```bash
# Ubuntu/Debian
wget -O - https://debian.neo4j.com/neotechnology.gpg.key | sudo apt-key add -
echo 'deb https://debian.neo4j.com stable latest' | sudo tee /etc/apt/sources.list.d/neo4j.list
sudo apt-get update
sudo apt-get install neo4j=1:5.* -y
```

**Configure:**
Edit `/etc/neo4j/neo4j.conf`:

```properties
# Network settings
dbms.connector.bolt.enabled=true
dbms.connector.bolt.listen_address=0.0.0.0:7687
dbms.connector.http.enabled=true
dbms.connector.http.listen_address=0.0.0.0:7474

# Security
dbms.security.auth_enabled=true

# Memory settings (adjust based on available RAM)
dbms.memory.heap.initial_size=1G
dbms.memory.heap.max_size=2G
dbms.memory.pagecache.size=1G
```

**Start service:**

```bash
sudo systemctl enable neo4j
sudo systemctl start neo4j
```

**Set initial password:**

```bash
neo4j-admin dbms set-initial-password your_secure_password
```

#### Backend Server (Spring Boot)

**Prerequisites:**

- Java 25+ installed
- Neo4j accessible

**Deploy JAR:**

```bash
# Copy JAR to server
scp server/build/libs/sixdegrees-0.0.1-SNAPSHOT.jar user@backend-server:/opt/sixdegrees/

# Create systemd service
sudo tee /etc/systemd/system/sixdegrees.service << EOF
[Unit]
Description=Six Degrees Backend
After=network.target

[Service]
Type=simple
User=sixdegrees
WorkingDirectory=/opt/sixdegrees
ExecStart=/usr/bin/java -jar /opt/sixdegrees/sixdegrees-0.0.1-SNAPSHOT.jar
Environment="TMDB_ACCESS_TOKEN=your_access_token"
Environment="NEO4J_URI=bolt://neo4j-server:7687"
Environment="NEO4J_USERNAME=neo4j"
Environment="NEO4J_PASSWORD=your_password"
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF

# Enable and start
sudo systemctl enable sixdegrees
sudo systemctl start sixdegrees
```

**Configure reverse proxy (Nginx):**

```nginx
server {
    listen 80;
    server_name api.example.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

#### Frontend Server (Nginx)

**Build frontend:**

```bash
cd web
npm install
npm run build
```

**Deploy to server:**

```bash
# Copy built files
rsync -avz dist/ user@frontend-server:/var/www/sixdegrees/

# Nginx configuration
sudo tee /etc/nginx/sites-available/sixdegrees << 'EOF'
server {
    listen 80;
    server_name example.com;
    root /var/www/sixdegrees;
    index index.html;

    # Serve static files
    location / {
        try_files $uri $uri/ /index.html;
    }

    # Proxy API requests to backend
    location /api {
        rewrite ^/api(.*)$ $1 break;
        proxy_pass http://backend-server:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
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
EOF

# Enable site
sudo ln -s /etc/nginx/sites-available/sixdegrees /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

### Option 3: Cloud Deployment

#### AWS Deployment

**Architecture:**

```text
┌─────────────┐
│ CloudFront  │ (CDN for static assets)
└──────┬──────┘
       │
┌──────▼──────┐
│     ALB     │ (Application Load Balancer)
└──────┬──────┘
       │
   ┌───┴───┐
   │   │   │
┌──▼─┐ ┌─▼──┐
│ S3 │ │ECS │ (Backend containers)
└────┘ └─┬──┘
         │
    ┌────▼────┐
    │  Neo4j  │ (EC2 or AWS Marketplace)
    └─────────┘
```

**Components:**

- **S3 + CloudFront**: Host static frontend files
- **ECS Fargate**: Run backend containers
- **EC2 or Marketplace**: Neo4j database
- **ALB**: Route traffic between S3 and ECS

**Setup:**

1. **Neo4j on EC2:**

   ```bash
   # Launch EC2 instance (t3.medium or larger)
   # Install Neo4j as described in standalone section
   # Configure security group: 7474, 7687 from backend subnet
   ```

2. **Backend on ECS:**

   ```bash
   # Push image to ECR
   aws ecr create-repository --repository-name sixdegrees-backend
   docker tag org.kidoni/sixdegrees:0.0.1-SNAPSHOT \
     <account-id>.dkr.ecr.<region>.amazonaws.com/sixdegrees-backend:latest
   docker push <account-id>.dkr.ecr.<region>.amazonaws.com/sixdegrees-backend:latest

   # Create ECS task definition with environment variables
   # Create ECS service with ALB target group
   ```

3. **Frontend on S3:**

   ```bash
   # Build frontend
   cd web && npm run build

   # Upload to S3
   aws s3 sync dist/ s3://sixdegrees-frontend/

   # Configure CloudFront distribution
   aws cloudfront create-distribution \
     --origin-domain-name sixdegrees-frontend.s3.amazonaws.com
   ```

#### Kubernetes Deployment

**manifests/namespace.yaml:**

```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: sixdegrees
```

**manifests/neo4j.yaml:**

```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: neo4j
  namespace: sixdegrees
spec:
  serviceName: neo4j
  replicas: 1
  selector:
    matchLabels:
      app: neo4j
  template:
    metadata:
      labels:
        app: neo4j
    spec:
      containers:
        - name: neo4j
          image: neo4j:5
          ports:
            - containerPort: 7474
              name: http
            - containerPort: 7687
              name: bolt
          env:
            - name: NEO4J_AUTH
              valueFrom:
                secretKeyRef:
                  name: neo4j-credentials
                  key: auth
          volumeMounts:
            - name: data
              mountPath: /data
  volumeClaimTemplates:
    - metadata:
        name: data
      spec:
        accessModes: ["ReadWriteOnce"]
        resources:
          requests:
            storage: 10Gi
---
apiVersion: v1
kind: Service
metadata:
  name: neo4j
  namespace: sixdegrees
spec:
  ports:
    - port: 7474
      name: http
    - port: 7687
      name: bolt
  selector:
    app: neo4j
```

**manifests/backend.yaml:**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: backend
  namespace: sixdegrees
spec:
  replicas: 2
  selector:
    matchLabels:
      app: backend
  template:
    metadata:
      labels:
        app: backend
    spec:
      containers:
        - name: backend
          image: org.kidoni/sixdegrees:0.0.1-SNAPSHOT
          ports:
            - containerPort: 8080
          env:
            - name: NEO4J_URI
              value: "bolt://neo4j:7687"
            - name: NEO4J_USERNAME
              valueFrom:
                secretKeyRef:
                  name: neo4j-credentials
                  key: username
            - name: NEO4J_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: neo4j-credentials
                  key: password
            - name: TMDB_ACCESS_TOKEN
              valueFrom:
                secretKeyRef:
                  name: tmdb-credentials
                  key: access-token
          livenessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 60
            periodSeconds: 10
          readinessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 30
            periodSeconds: 5
---
apiVersion: v1
kind: Service
metadata:
  name: backend
  namespace: sixdegrees
spec:
  type: ClusterIP
  ports:
    - port: 8080
      targetPort: 8080
  selector:
    app: backend
```

**manifests/frontend.yaml:**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: frontend
  namespace: sixdegrees
spec:
  replicas: 2
  selector:
    matchLabels:
      app: frontend
  template:
    metadata:
      labels:
        app: frontend
    spec:
      containers:
        - name: frontend
          image: sixdegrees-frontend:latest # Build from web/Dockerfile
          ports:
            - containerPort: 80
---
apiVersion: v1
kind: Service
metadata:
  name: frontend
  namespace: sixdegrees
spec:
  type: LoadBalancer
  ports:
    - port: 80
      targetPort: 80
  selector:
    app: frontend
```

**Deploy:**

```bash
# Create secrets
kubectl create secret generic neo4j-credentials \
  --from-literal=auth=neo4j/your_password \
  --from-literal=username=neo4j \
  --from-literal=password=your_password \
  -n sixdegrees

kubectl create secret generic tmdb-credentials \
  --from-literal=access-token=your_access_token \
  -n sixdegrees

# Apply manifests
kubectl apply -f manifests/
```

## Production Configuration

### Backend Tuning

**application-production.yaml:**

```yaml
server:
  port: 8080
  compression:
    enabled: true
    mime-types: application/json,application/xml,text/html,text/xml,text/plain

spring:
  threads:
    virtual:
      enabled: true

  neo4j:
    uri: ${NEO4J_URI}
    authentication:
      username: ${NEO4J_USERNAME}
      password: ${NEO4J_PASSWORD}
    connection-timeout: 30s
    max-transaction-retry-time: 30s

logging:
  level:
    root: INFO
    org.kidoni: INFO

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  metrics:
    export:
      prometheus:
        enabled: true
```

### Neo4j Tuning

**For production workloads:**

```properties
# Memory (adjust based on available RAM)
dbms.memory.heap.initial_size=4G
dbms.memory.heap.max_size=4G
dbms.memory.pagecache.size=4G

# Transaction log
dbms.tx_log.rotation.retention_policy=2 days

# Query tuning
dbms.query.cache.enabled=true
dbms.query.cache.size=1000

# Monitoring
metrics.enabled=true
metrics.csv.enabled=true
metrics.csv.interval=5s
```

### Frontend Optimization

**Nginx production config:**

```nginx
# Enable HTTP/2
listen 443 ssl http2;

# SSL configuration
ssl_certificate /etc/ssl/certs/example.com.crt;
ssl_certificate_key /etc/ssl/private/example.com.key;
ssl_protocols TLSv1.2 TLSv1.3;
ssl_ciphers HIGH:!aNULL:!MD5;

# Compression
gzip on;
gzip_vary on;
gzip_min_length 1024;
gzip_comp_level 6;
gzip_types text/plain text/css text/xml text/javascript application/javascript application/json application/xml+rss;

# Brotli (if available)
brotli on;
brotli_comp_level 6;
brotli_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss;

# Security headers
add_header Strict-Transport-Security "max-age=31536000; includeSubDomains" always;
add_header X-Frame-Options "SAMEORIGIN" always;
add_header X-Content-Type-Options "nosniff" always;
add_header X-XSS-Protection "1; mode=block" always;
add_header Referrer-Policy "strict-origin-when-cross-origin" always;
```

## Monitoring and Health Checks

### Application Health

**Backend:**

```bash
# Basic health
curl http://localhost:8080/actuator/health

# Detailed health (requires authentication)
curl -u admin:admin http://localhost:8080/actuator/health/readiness
curl -u admin:admin http://localhost:8080/actuator/health/liveness
```

**Neo4j:**

```bash
# HTTP endpoint
curl http://localhost:7474/

# Cypher query
cypher-shell -u neo4j -p password "CALL dbms.cluster.overview();"
```

**Frontend:**

```bash
curl http://localhost/
```

### Prometheus Metrics

**Backend exposes metrics at:**

```text
http://localhost:8080/actuator/prometheus
```

**Sample Prometheus config:**

```yaml
scrape_configs:
  - job_name: "sixdegrees-backend"
    static_configs:
      - targets: ["backend:8080"]
    metrics_path: "/actuator/prometheus"
    basic_auth:
      username: "admin"
      password: "admin"
```

### Logging

**Centralized logging with ELK stack:**

1. **Configure backend to output JSON logs:**

   ```yaml
   logging:
     pattern:
       console: '{"time":"%d","level":"%p","logger":"%c","message":"%m"}%n'
   ```

2. **Filebeat configuration:**

   ```yaml
   filebeat.inputs:
     - type: log
       paths:
         - /var/log/sixdegrees/*.log
       json.keys_under_root: true

   output.elasticsearch:
     hosts: ["elasticsearch:9200"]
   ```

## Security Considerations

### HTTPS/TLS

**Always use HTTPS in production:**

```nginx
# Force HTTPS redirect
server {
    listen 80;
    server_name example.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name example.com;

    ssl_certificate /etc/ssl/certs/example.com.crt;
    ssl_certificate_key /etc/ssl/private/example.com.key;

    # ... rest of configuration
}
```

### Authentication

**Current setup uses HTTP Basic Auth:**

- Default credentials: `admin` / `admin`
- **CRITICAL**: Change default password in production
- Credentials should only be transmitted over HTTPS

**Future considerations:**

- OAuth 2.0 / OpenID Connect
- JWT tokens
- Session management

### Database Security

**Neo4j security:**

```properties
# Enable auth
dbms.security.auth_enabled=true

# Use strong password
# Set via: neo4j-admin dbms set-initial-password strong_password

# Restrict network access
dbms.connector.bolt.listen_address=127.0.0.1:7687  # Localhost only
```

**Use firewalls:**

```bash
# Allow only backend to access Neo4j
sudo ufw allow from <backend-ip> to any port 7687
sudo ufw allow from <backend-ip> to any port 7474
```

### Environment Variables

**Never commit secrets to version control:**

```bash
# Use .env files (gitignored)
# Or cloud secret managers:
# - AWS Secrets Manager
# - Google Cloud Secret Manager
# - Azure Key Vault
# - HashiCorp Vault
```

## Troubleshooting

### Backend Won't Start

**Check logs:**

```bash
docker-compose logs backend
# or
journalctl -u sixdegrees -f
```

**Common issues:**

1. Neo4j not accessible
   - Verify `NEO4J_URI` is correct
   - Check network connectivity: `telnet neo4j-host 7687`

2. Missing environment variables
   - Verify all required vars are set
   - Check with: `docker-compose config`

3. Port already in use
   - Check with: `lsof -i :8080`
   - Kill conflicting process or change port

### Frontend Shows Blank Page

**Check browser console:**

- Look for JavaScript errors
- Check network tab for failed asset loads

**Common issues:**

1. API URL misconfigured
   - Verify `VITE_API_URL` environment variable
   - Check network requests in browser DevTools

2. CORS errors
   - Verify backend CORS configuration
   - Check allowed origins include frontend domain

3. Assets not loading
   - Verify Nginx is serving files from correct directory
   - Check file permissions: `ls -la /var/www/sixdegrees/`

### Database Connection Errors

**Neo4j not responding:**

```bash
# Check if Neo4j is running
docker ps | grep neo4j
# or
sudo systemctl status neo4j

# Check logs
docker logs neo4j
# or
sudo journalctl -u neo4j -f

# Test connection
cypher-shell -a bolt://localhost:7687 -u neo4j -p password
```

**Common issues:**

1. Authentication failure
   - Verify username/password are correct
   - Reset password: `neo4j-admin dbms set-initial-password new_password`

2. Connection timeout
   - Check firewall rules
   - Verify Neo4j is listening on correct interface

3. Out of memory
   - Check Neo4j heap size configuration
   - Increase memory limits in Docker or systemd

### Performance Issues

**Backend slow:**

1. Check JVM metrics: `curl localhost:8080/actuator/metrics/jvm.memory.used`
2. Enable GC logging
3. Profile with JProfiler or YourKit
4. Consider increasing heap size

**Frontend slow:**

1. Run Lighthouse audit
2. Check bundle size: `npm run build`
3. Enable CDN for static assets
4. Optimize images

**Database slow:**

1. Check query performance: `PROFILE MATCH ...`
2. Add indexes: `CREATE INDEX ON :Person(id)`
3. Tune memory settings
4. Monitor with Neo4j browser

## Support Resources

- **Vite Deployment**: <https://vitejs.dev/guide/static-deploy.html>
- **Spring Boot Deployment**: <https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html>
- **Neo4j Operations**: <https://neo4j.com/docs/operations-manual/current/>
- **Docker Documentation**: <https://docs.docker.com/>
- **Kubernetes Documentation**: <https://kubernetes.io/docs/>

## Backup and Recovery

### Neo4j Backup

**Using neo4j-admin:**

```bash
# Stop Neo4j
sudo systemctl stop neo4j

# Backup
neo4j-admin database dump neo4j --to=/backup/neo4j-$(date +%Y%m%d).dump

# Restore
neo4j-admin database load neo4j --from=/backup/neo4j-20240101.dump --force

# Start Neo4j
sudo systemctl start neo4j
```

**Docker volume backup:**

```bash
# Backup
docker run --rm \
  -v sixdegrees_neo4j_data:/data \
  -v $(pwd):/backup \
  alpine tar czf /backup/neo4j-backup.tar.gz /data

# Restore
docker run --rm \
  -v sixdegrees_neo4j_data:/data \
  -v $(pwd):/backup \
  alpine sh -c "cd / && tar xzf /backup/neo4j-backup.tar.gz"
```

### Automated Backups

**Cron job:**

```bash
# Add to crontab
0 2 * * * /opt/scripts/backup-neo4j.sh

# /opt/scripts/backup-neo4j.sh
#!/bin/bash
BACKUP_DIR=/backup/neo4j
DATE=$(date +%Y%m%d)
docker exec sixdegrees-neo4j neo4j-admin database dump neo4j --to=/tmp/neo4j-$DATE.dump
docker cp sixdegrees-neo4j:/tmp/neo4j-$DATE.dump $BACKUP_DIR/
# Upload to S3 or other storage
aws s3 cp $BACKUP_DIR/neo4j-$DATE.dump s3://backups/neo4j/
# Clean up old backups (keep 30 days)
find $BACKUP_DIR -name "neo4j-*.dump" -mtime +30 -delete
```
