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

@SpringBootTest
@TestPropertySource(properties = {
    "tmdb.api.url=https://api.themoviedb.org/",
    "tmdb.api.access-token=test-token"
})
class FindConnectionsITest implements SixDegreesTestContainers {

    @Autowired
    private SixDegreesService service;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void contextLoads() {
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

    // ========== NEW TESTS WITH REAL DATA ==========

    @Test
    void findConnections_directConnection_returnsPath() {
        // Given: Two actors in the same movie
        Movie movie = createMovie(3001, "Test Movie");
        movieRepository.save(movie);

        Actor actor1 = createActor(2001, "Actor One");
        Actor actor2 = createActor(2002, "Actor Two");

        actor1.setCredits(List.of(movie));
        actor2.setCredits(List.of(movie));

        actorRepository.save(actor1);
        actorRepository.save(actor2);

        // When: Finding connections
        List<ConnectionPath> paths = service.findConnections(2001, 2002, 6);

        // Then: Should find a direct connection (degree 1)
        assertFalse(paths.isEmpty(), "Should find at least one path");
        ConnectionPath path = paths.getFirst();

        assertEquals(1, path.degree(), "Should be degree 1 (direct connection)");
        assertEquals(3, path.nodes().size(), "Should have 3 nodes (Actor1, Movie, Actor2)");
        assertEquals(2, path.edges().size(), "Should have 2 edges");
    }

    @Test
    void findConnections_multiDegree_returnsPath() {
        // Given: Three actors connected through two movies
        Movie movie1 = createMovie(3002, "Movie A");
        Movie movie2 = createMovie(3003, "Movie B");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Actor actor1 = createActor(2003, "First Actor");
        Actor actor2 = createActor(2004, "Middle Actor");
        Actor actor3 = createActor(2005, "Last Actor");

        // Actor1 and Actor2 in Movie1, Actor2 and Actor3 in Movie2
        actor1.setCredits(List.of(movie1));
        actor2.setCredits(List.of(movie1, movie2));
        actor3.setCredits(List.of(movie2));

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);

        // When: Finding connections
        List<ConnectionPath> paths = service.findConnections(2003, 2005, 6);

        // Then: Should find a 2-degree connection
        assertFalse(paths.isEmpty(), "Should find at least one path");
        ConnectionPath path = paths.getFirst();

        assertEquals(2, path.degree(), "Should be degree 2 (two hops)");
        assertEquals(5, path.nodes().size(), "Should have 5 nodes (A1, M1, A2, M2, A3)");
        assertEquals(4, path.edges().size(), "Should have 4 edges");
    }

    @Test
    void findConnections_noConnection_returnsEmpty() {
        // Given: Two actors with no shared movies
        Movie movie1 = createMovie(3004, "Isolated Movie 1");
        Movie movie2 = createMovie(3005, "Isolated Movie 2");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Actor actor1 = createActor(2006, "Isolated Actor 1");
        Actor actor2 = createActor(2007, "Isolated Actor 2");

        actor1.setCredits(List.of(movie1));
        actor2.setCredits(List.of(movie2));

        actorRepository.save(actor1);
        actorRepository.save(actor2);

        // When: Finding connections
        List<ConnectionPath> paths = service.findConnections(2006, 2007, 6);

        // Then: Should find no paths
        assertTrue(paths.isEmpty(), "Should find no paths between unconnected actors");
    }

    @Test
    void findConnections_exceedsMaxDegrees_returnsEmpty() {
        // Given: Actors connected but beyond max degree limit
        Movie movie1 = createMovie(3006, "Movie X");
        Movie movie2 = createMovie(3007, "Movie Y");
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Actor actor1 = createActor(2008, "Actor X");
        Actor actor2 = createActor(2009, "Actor Y");
        Actor actor3 = createActor(2010, "Actor Z");

        // Create 2-degree connection: actor1 -> movie1 <- actor2 -> movie2 <- actor3
        actor1.setCredits(List.of(movie1));
        actor2.setCredits(List.of(movie1, movie2));
        actor3.setCredits(List.of(movie2));

        actorRepository.save(actor1);
        actorRepository.save(actor2);
        actorRepository.save(actor3);

        // When: Finding connections with max degree 1
        List<ConnectionPath> paths = service.findConnections(2008, 2010, 1);

        // Then: Should find no paths (connection requires degree 2)
        assertTrue(paths.isEmpty(), "Should find no paths when maxDegrees is too restrictive");
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
