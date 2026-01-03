package org.kidoni.sixdegrees.tmdb;

import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.SixDegreesTestContainers;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.neo4j.test.autoconfigure.DataNeo4jTest;

import static org.junit.jupiter.api.Assertions.*;

@DataNeo4jTest
public class MovieDetailsRepositoryITest implements SixDegreesTestContainers {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void saveAndFindMovie() {
        Movie movie = new Movie();
        movie.setId(456);
        movie.setTitle("Test Movie");

        movieRepository.save(movie);

        var found = movieRepository.findById(456);
        assertTrue(found.isPresent());
        assertEquals("Test Movie", found.get().title());
    }
}
