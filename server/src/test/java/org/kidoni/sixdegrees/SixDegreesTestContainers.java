package org.kidoni.sixdegrees;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.neo4j.Neo4jContainer;

/**
 * Singleton TestContainer for Neo4j shared across all integration tests.
 * Uses the singleton pattern to ensure only one container instance is created
 * and shared across all test classes.
 */
public interface SixDegreesTestContainers {
    Neo4jContainer neo4jContainer = new Neo4jContainer("neo4j:5")
        .withoutAuthentication()
        .withReuse(true);

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        neo4jContainer.start();
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
    }
}
