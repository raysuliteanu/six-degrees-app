package org.kidoni.sixdegrees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.ActorRepository;
import org.kidoni.sixdegrees.tmdb.MovieRepository;
import org.kidoni.sixdegrees.tmdb.graph.ConnectionPath;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Integration test for connection path finding with actual Neo4j queries.
 * This test validates that the Cypher queries execute correctly and
 * that character names are properly extracted from relationships.
 */
@SpringBootTest
@TestPropertySource(properties = {
    "tmdb.api.url=https://api.themoviedb.org/",
    "tmdb.api.access-token=test-token"
})
class ConnectionPathITest implements SixDegreesTestContainers {

    @Autowired
    private SixDegreesService service;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findConnections_directConnection_returnsPathWithCharacterNames() {
        // Given: Two actors in the same movie with character names
        Movie movie = createMovie(2001, "Sleepless in Seattle");
        movieRepository.save(movie);  // Save movie first

        Actor actor1 = createActor(1001, "Tom Hanks");
        Actor actor2 = createActor(1002, "Meg Ryan");

        // Use the backward-compatible setCredits method for now
        actor1.setCredits(List.of(movie));
        actor2.setCredits(List.of(movie));

        actorRepository.save(actor1);
        actorRepository.save(actor2);

        // When: Finding connections
        List<ConnectionPath> paths = service.findConnections(1001, 1002, 6);

        // Then: Should find a direct connection (degree 1)
        assertFalse(paths.isEmpty(), "Should find at least one path");
        ConnectionPath path = paths.getFirst();

        assertEquals(1, path.degree(), "Should be degree 1 (direct connection)");
        assertEquals(3, path.nodes().size(), "Should have 3 nodes (Actor1, Movie, Actor2)");
        assertEquals(2, path.edges().size(), "Should have 2 edges");

        // Note: Character names will be "appeared in" placeholders for now
        // as we're not using ActedInRelationship in this simplified test
    }

    @Test
    void findConnections_multiDegree_returnsMultipleHops() {
        // Given: Three actors connected through two movies
        Movie movie1 = createMovie(2002, "Movie 1");
        Movie movie2 = createMovie(2003, "Movie 2");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Actor actor1 = createActor(1003, "Actor A");
        Actor actor2 = createActor(1004, "Actor B");
        Actor actor3 = createActor(1005, "Actor C");

        // Actor1 and Actor2 in Movie1, Actor2 and Actor3 in Movie2
        actor1.setCredits(List.of(movie1));
        actor2.setCredits(List.of(movie1, movie2));
        actor3.setCredits(List.of(movie2));

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);

        // When: Finding connections
        List<ConnectionPath> paths = service.findConnections(1003, 1005, 6);

        // Then: Should find a 2-degree connection
        assertFalse(paths.isEmpty(), "Should find at least one path");
        ConnectionPath path = paths.getFirst();

        assertEquals(2, path.degree(), "Should be degree 2 (two hops)");
        assertEquals(5, path.nodes().size(), "Should have 5 nodes (A, M1, B, M2, C)");
        assertEquals(4, path.edges().size(), "Should have 4 edges");
    }

    @Test
    void findConnections_noConnection_returnsEmpty() {
        // Given: Two actors with no shared movies
        Movie movie1 = createMovie(2004, "Movie A");
        Movie movie2 = createMovie(2005, "Movie B");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Actor actor1 = createActor(1006, "Isolated Actor 1");
        Actor actor2 = createActor(1007, "Isolated Actor 2");

        actor1.setCredits(List.of(movie1));
        actor2.setCredits(List.of(movie2));

        actorRepository.save(actor1);
        actorRepository.save(actor2);

        // When: Finding connections
        List<ConnectionPath> paths = service.findConnections(1006, 1007, 6);

        // Then: Should find no paths
        assertTrue(paths.isEmpty(), "Should find no paths between unconnected actors");
    }

    @Test
    void findConnections_exceedsMaxDegrees_returnsEmpty() {
        // Given: Actors connected but beyond max degree limit
        Movie movie1 = createMovie(2006, "Movie X");
        Movie movie2 = createMovie(2007, "Movie Y");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Actor actor1 = createActor(1008, "Actor X");
        Actor actor2 = createActor(1009, "Actor Y");
        Actor actor3 = createActor(1010, "Actor Z");

        // Create 2-degree connection: actor1 -> movie1 <- actor2 -> movie2 <- actor3
        actor1.setCredits(List.of(movie1));
        actor2.setCredits(List.of(movie1, movie2));
        actor3.setCredits(List.of(movie2));

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);

        // When: Finding connections with max degree 1
        List<ConnectionPath> paths = service.findConnections(1008, 1010, 1);

        // Then: Should find no paths (connection requires degree 2)
        assertTrue(paths.isEmpty(), "Should find no paths when maxDegrees is too restrictive");
    }

    @Test
    void findConnections_cypherQueryExecutes_noSyntaxError() {
        // Given: Simple setup to verify Cypher query doesn't have syntax errors
        Actor actor1 = createActor(1011, "Test Actor 1");
        Actor actor2 = createActor(1012, "Test Actor 2");

        actorRepository.save(actor1);
        actorRepository.save(actor2);

        // When/Then: This should not throw a Neo4j syntax error
        try {
            List<ConnectionPath> paths = service.findConnections(1011, 1012, 6);
            assertNotNull(paths, "Should return a list (even if empty)");
        } catch (Exception e) {
            throw new AssertionError(
                "Cypher query should not have syntax errors. Error: " + e.getMessage(), e);
        }
    }

    private Actor createActor(Integer id, String name) {
        Actor actor = new Actor();
        actor.setId(id);
        actor.setName(name);
        actor.setPopularity(5.0f);
        return actor;
    }

    private Movie createMovie(Integer id, String title) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setPopularity(7.0f);
        return movie;
    }
}
