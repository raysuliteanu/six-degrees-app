package org.kidoni.sixdegrees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.graph.ConnectionPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "tmdb.api.url=https://api.themoviedb.org/",
    "tmdb.api.access-token=test-token"
})
class FindConnectionsITest implements SixDegreesTestContainers {

    @Autowired
    private SixDegreesService service;

    @Test
    void contextLoads() {
        // Verify service is wired correctly
        assertNotNull(service);
    }

    @Test
    void findConnections_validatesNullActorIds() {
        try {
            service.findConnections(null, 123, 6);
            throw new AssertionError("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Actor IDs cannot be null", e.getMessage());
        }
    }

    @Test
    void findConnections_validatesSameActor() {
        List<ConnectionPath> paths = service.findConnections(123, 123, 6);
        assertTrue(paths.isEmpty());
    }

    @Test
    void findConnections_validatesMaxDegrees() {
        try {
            service.findConnections(123, 456, 0);
            throw new AssertionError("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Max degrees must be between 1 and 6", e.getMessage());
        }

        try {
            service.findConnections(123, 456, 7);
            throw new AssertionError("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Max degrees must be between 1 and 6", e.getMessage());
        }
    }

    // Note: Testing with real TMDB data requires valid API token and network access
    // These tests would need to be updated with real actor IDs when TMDB_ACCESS_TOKEN is available
}
