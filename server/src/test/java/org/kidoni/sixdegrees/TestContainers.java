package org.kidoni.sixdegrees;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.neo4j.Neo4jContainer;

interface TestContainers {
	@Container
    @ServiceConnection
	Neo4jContainer neo4jContainer = new Neo4jContainer("neo4j:5");
}
