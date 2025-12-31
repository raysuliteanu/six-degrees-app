package org.kidoni.sixdegrees.tmdb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.SixDegreesTestContainers;
import org.kidoni.sixdegrees.tmdb.model.MovieCollection;
import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieDetailsGenres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.neo4j.test.autoconfigure.DataNeo4jTest;

import static org.junit.jupiter.api.Assertions.*;

@DataNeo4jTest
public class MovieDetailsRepositoryITest implements SixDegreesTestContainers {
	@Autowired
	private MovieDetailsRepository movieDetailsRepository;

	@Test
	void testSaveAndFindMovie() {
		// Arrange
		MovieDetails movie = new MovieDetails();
		movie.setId(1);
		movie.setTitle("The Matrix");
		movie.setOverview("A computer hacker learns about the true nature of reality");
		movie.setReleaseDate("1999-03-31");
		movie.setPopularity(new BigDecimal("42.5"));

		// Act
		MovieDetails saved = movieDetailsRepository.save(movie);

		// Assert
		assertNotNull(saved);
		assertEquals(1, saved.getId());

		Optional<MovieDetails> found = movieDetailsRepository.findById(1);
		assertTrue(found.isPresent());
		MovieDetails foundMovie = found.get();
		assertEquals("The Matrix", foundMovie.getTitle());
		assertEquals("A computer hacker learns about the true nature of reality", foundMovie.getOverview());
		assertEquals("1999-03-31", foundMovie.getReleaseDate());
		assertEquals(new BigDecimal("42.5"), foundMovie.getPopularity());
	}

	@Test
	void testSaveMovieWithNullableFields() {
		// Arrange
		MovieDetails movie = new MovieDetails();
		movie.setId(2);
		movie.setTitle("Test Movie");
		movie.setOverview(null);
		movie.setTagline(null);
		movie.setHomepage(null);
		movie.setBackdropPath(null);
		movie.setPosterPath(null);

		// Act
		MovieDetails saved = movieDetailsRepository.save(movie);

		// Assert
		Optional<MovieDetails> found = movieDetailsRepository.findById(2);
		assertTrue(found.isPresent());
		MovieDetails foundMovie = found.get();
		assertEquals("Test Movie", foundMovie.getTitle());
		assertNull(foundMovie.getOverview());
		assertNull(foundMovie.getTagline());
		assertNull(foundMovie.getHomepage());
		assertNull(foundMovie.getBackdropPath());
		assertNull(foundMovie.getPosterPath());
	}

	@Test
	void testUpdateMovie() {
		// Arrange
		MovieDetails movie = new MovieDetails();
		movie.setId(3);
		movie.setTitle("Original Title");
		movie.setOverview("Original overview");
		movieDetailsRepository.save(movie);

		// Act - Update
		movie.setTitle("Updated Title");
		movie.setOverview("Updated overview");
		movie.setReleaseDate("2025-01-01");
		MovieDetails updated = movieDetailsRepository.save(movie);

		// Assert
		Optional<MovieDetails> found = movieDetailsRepository.findById(3);
		assertTrue(found.isPresent());
		MovieDetails foundMovie = found.get();
		assertEquals("Updated Title", foundMovie.getTitle());
		assertEquals("Updated overview", foundMovie.getOverview());
		assertEquals("2025-01-01", foundMovie.getReleaseDate());
	}

	@Test
	void testDeleteMovie() {
		// Arrange
		MovieDetails movie = new MovieDetails();
		movie.setId(4);
		movie.setTitle("Movie to Delete");
		movieDetailsRepository.save(movie);

		// Verify it exists
		assertTrue(movieDetailsRepository.findById(4).isPresent());

		// Act
		movieDetailsRepository.deleteById(4);

		// Assert
		Optional<MovieDetails> found = movieDetailsRepository.findById(4);
		assertFalse(found.isPresent());
	}

	@Test
	void testTransientFieldsNotPersisted() {
		// Arrange
		MovieDetails movie = new MovieDetails();
		movie.setId(5);
		movie.setTitle("Movie with Transient Fields");

		// Set @Transient fields - these should NOT be persisted
		MovieCollection collection = new MovieCollection(100, "Test Collection", "/poster.jpg", "/backdrop.jpg");
		movie.setBelongsToCollection(collection);

		List<MovieDetailsGenres> genres = new ArrayList<>();
		MovieDetailsGenres genre = new MovieDetailsGenres();
		genre.setId(28);
		genre.setName("Action");
		genres.add(genre);
		movie.setGenres(genres);

		// Act
		movieDetailsRepository.save(movie);

		// Assert
		Optional<MovieDetails> found = movieDetailsRepository.findById(5);
		assertTrue(found.isPresent());
		MovieDetails foundMovie = found.get();

		// @Transient fields should not be persisted to Neo4j
		// belongsToCollection is initialized to null, so it remains null
		assertNull(foundMovie.getBelongsToCollection(), "belongsToCollection should not be persisted");
		// List fields are initialized to empty ArrayLists, so they remain empty (not null)
		assertNotNull(foundMovie.getGenres(), "genres list should be initialized");
		assertTrue(foundMovie.getGenres().isEmpty(), "genres should be empty (not persisted)");
		assertNotNull(foundMovie.getProductionCompanies(), "productionCompanies list should be initialized");
		assertTrue(foundMovie.getProductionCompanies().isEmpty(), "productionCompanies should be empty (not persisted)");
		assertNotNull(foundMovie.getProductionCountries(), "productionCountries list should be initialized");
		assertTrue(foundMovie.getProductionCountries().isEmpty(), "productionCountries should be empty (not persisted)");
		assertNotNull(foundMovie.getSpokenLanguages(), "spokenLanguages list should be initialized");
		assertTrue(foundMovie.getSpokenLanguages().isEmpty(), "spokenLanguages should be empty (not persisted)");
	}

	@Test
	void testFindAll() {
		// Arrange
		MovieDetails movie1 = new MovieDetails();
		movie1.setId(10);
		movie1.setTitle("Movie 1");

		MovieDetails movie2 = new MovieDetails();
		movie2.setId(11);
		movie2.setTitle("Movie 2");

		MovieDetails movie3 = new MovieDetails();
		movie3.setId(12);
		movie3.setTitle("Movie 3");

		// Act
		movieDetailsRepository.save(movie1);
		movieDetailsRepository.save(movie2);
		movieDetailsRepository.save(movie3);

		// Assert
		Iterable<MovieDetails> all = movieDetailsRepository.findAll();
		assertNotNull(all);

		List<MovieDetails> movieList = new ArrayList<>();
		all.forEach(movieList::add);

		assertTrue(movieList.size() >= 3, "Should have at least 3 movies");
		assertTrue(movieList.stream().anyMatch(m -> "Movie 1".equals(m.getTitle())));
		assertTrue(movieList.stream().anyMatch(m -> "Movie 2".equals(m.getTitle())));
		assertTrue(movieList.stream().anyMatch(m -> "Movie 3".equals(m.getTitle())));
	}
}
