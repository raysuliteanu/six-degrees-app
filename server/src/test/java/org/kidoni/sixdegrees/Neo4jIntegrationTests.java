package org.kidoni.sixdegrees;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.boot.test.context.SpringBootTest;

@Testcontainers
@SpringBootTest
class Neo4jIntegrationTests implements TestContainers {
    @Test
    void connectionTest() {
        // TODO
    }

}
