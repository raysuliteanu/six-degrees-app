package org.kidoni.sixdegrees.tmdb;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.data.neo4j.core.Neo4jClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class TmdbSixDegreesServiceTest {
    TmdbClient tmdbClient;
    ActorRepository actorRepository;
    MovieRepository movieRepository;
    TvShowRepository tvShowRepository;
    TmdbSixDegreesService tmdbService;
    Neo4jClient neo4jClient;

    @BeforeEach
    void setUp() {
        tmdbClient = mock(TmdbClient.class);
        actorRepository = mock(ActorRepository.class);
        movieRepository = mock(MovieRepository.class);
        tvShowRepository = mock(TvShowRepository.class);
        neo4jClient = mock(Neo4jClient.class);
        tmdbService = new TmdbSixDegreesService(
            tmdbClient,
            actorRepository,
            movieRepository,
            tvShowRepository,
            new SyncTaskExecutor(),
            neo4jClient);
    }

    @Test
    void searchForPerson() {
        Actor actor = new Actor();
        actor.setId(666);
        actor.setName("John Smith");

        final var personSearchResult = new PersonSearchResult()
            .page(1)
            .results(List.of(actor))
            .totalPages(1)
            .totalResults(1);
        when(tmdbClient.searchPersonByName("smith")).thenReturn(personSearchResult);

        final var actorDetails = new Actor();
        actorDetails.setId(666);
        actorDetails.setName("John Smith");
        when(tmdbClient.findPersonById(666)).thenReturn(actorDetails);
        when(actorRepository.save(any(Actor.class))).thenReturn(actorDetails);

        var result = tmdbService.searchPerson("smith");
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());

        verify(tmdbClient).searchPersonByName("smith");
        verify(tmdbClient).findPersonById(666);
        verify(actorRepository).save(any(Actor.class));
        verifyNoInteractions(movieRepository, tvShowRepository);
        verifyNoMoreInteractions(tmdbClient, actorRepository);
    }

    @Test
    void searchForPersonWithNullResults() {
        final var personSearchResult = new PersonSearchResult()
            .page(1)
            .results(null)
            .totalPages(0)
            .totalResults(0);
        when(tmdbClient.searchPersonByName("unknown")).thenReturn(personSearchResult);

        var result = tmdbService.searchPerson("unknown");
        assertNull(result.getResults());

        verify(tmdbClient).searchPersonByName("unknown");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(actorRepository, movieRepository, tvShowRepository);
    }

    @Test
    void searchForPersonWithEmptyResults() {
        final var personSearchResult = new PersonSearchResult()
            .page(1)
            .results(List.of())
            .totalPages(0)
            .totalResults(0);
        when(tmdbClient.searchPersonByName("nobody")).thenReturn(personSearchResult);

        var result = tmdbService.searchPerson("nobody");
        assertNotNull(result.getResults());
        assertEquals(0, result.getResults().size());

        verify(tmdbClient).searchPersonByName("nobody");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(actorRepository, movieRepository, tvShowRepository);
    }

    @Test
    void findPersonWhenExistsInRepository() {
        final var actor = new Actor();
        actor.setId(123);
        actor.setName("John Doe");
        when(actorRepository.findById(123)).thenReturn(Optional.of(actor));

        var result = tmdbService.findPerson(123);
        assertNotNull(result);
        assertEquals(123, result.id());
        assertEquals("John Doe", result.name());

        verify(actorRepository).findById(123);
        verifyNoMoreInteractions(actorRepository);
        verifyNoInteractions(tmdbClient, movieRepository, tvShowRepository);
    }

    @Test
    void findPersonWhenNotInRepository() {
        final var actor = new Actor();
        actor.setId(456);
        actor.setName("Jane Smith");
        when(actorRepository.findById(456)).thenReturn(Optional.empty());
        when(tmdbClient.findPersonById(456)).thenReturn(actor);
        when(actorRepository.save(actor)).thenReturn(actor);

        var result = tmdbService.findPerson(456);
        assertNotNull(result);
        assertEquals(456, result.id());
        assertEquals("Jane Smith", result.name());

        verify(actorRepository).findById(456);
        verify(tmdbClient).findPersonById(456);
        verify(actorRepository).save(actor);
        verifyNoMoreInteractions(actorRepository, tmdbClient);
        verifyNoInteractions(movieRepository, tvShowRepository);
    }

    @Test
    void getPersonCredits() {
        final Movie movie = new Movie();
        movie.setId(111);
        movie.setTitle("Test Movie");
        final List<Credit> credits = List.of(movie);

        when(tmdbClient.getPersonCombinedCredits(789)).thenReturn(credits);

        var result = tmdbService.getPersonCredits(789);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(111, result.getFirst().id());

        verify(tmdbClient).getPersonCombinedCredits(789);
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(actorRepository, movieRepository, tvShowRepository);
    }

    @Test
    void searchForMovie() {
        Movie movie = new Movie();
        movie.setId(999);
        movie.setTitle("The Matrix");

        final var movieSearchResult = new MovieSearchResult()
            .page(1)
            .results(List.of(movie))
            .totalPages(1)
            .totalResults(1);
        when(tmdbClient.searchMovieByName("matrix")).thenReturn(movieSearchResult);

        final var movieDetails = new Movie();
        movieDetails.setId(999);
        movieDetails.setTitle("The Matrix");
        when(tmdbClient.findMovieById(999)).thenReturn(movieDetails);
        when(movieRepository.save(any(Movie.class))).thenReturn(movieDetails);

        var result = tmdbService.movieSearch("matrix");
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());

        verify(tmdbClient).searchMovieByName("matrix");
        verify(tmdbClient).findMovieById(999);
        verify(movieRepository).save(any(Movie.class));
        verifyNoInteractions(actorRepository, tvShowRepository);
        verifyNoMoreInteractions(tmdbClient, movieRepository);
    }

    @Test
    void searchForMovieWithNullResults() {
        final var movieSearchResult = new MovieSearchResult()
            .page(1)
            .results(null)
            .totalPages(0)
            .totalResults(0);
        when(tmdbClient.searchMovieByName("unknown")).thenReturn(movieSearchResult);

        var result = tmdbService.movieSearch("unknown");
        assertNull(result.getResults());

        verify(tmdbClient).searchMovieByName("unknown");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(actorRepository, movieRepository, tvShowRepository);
    }

    @Test
    void searchForMovieWithEmptyResults() {
        final var movieSearchResult = new MovieSearchResult()
            .page(1)
            .results(List.of())
            .totalPages(0)
            .totalResults(0);
        when(tmdbClient.searchMovieByName("nomovie")).thenReturn(movieSearchResult);

        var result = tmdbService.movieSearch("nomovie");
        assertNotNull(result.getResults());
        assertEquals(0, result.getResults().size());

        verify(tmdbClient).searchMovieByName("nomovie");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(actorRepository, movieRepository, tvShowRepository);
    }

    @Test
    void findMovieWhenExistsInRepository() {
        final var movie = new Movie();
        movie.setId(111);
        movie.setTitle("The Matrix");
        when(movieRepository.findById(111)).thenReturn(Optional.of(movie));

        var result = tmdbService.findMovie(111);
        assertNotNull(result);
        assertEquals(111, result.id());
        assertEquals("The Matrix", result.title());

        verify(movieRepository).findById(111);
        verifyNoMoreInteractions(movieRepository);
        verifyNoInteractions(tmdbClient, actorRepository, tvShowRepository);
    }

    @Test
    void findMovieWhenNotInRepository() {
        final var movie = new Movie();
        movie.setId(222);
        movie.setTitle("Inception");
        when(movieRepository.findById(222)).thenReturn(Optional.empty());
        when(tmdbClient.findMovieById(222)).thenReturn(movie);
        when(movieRepository.save(movie)).thenReturn(movie);

        var result = tmdbService.findMovie(222);
        assertNotNull(result);
        assertEquals(222, result.id());
        assertEquals("Inception", result.title());

        verify(movieRepository).findById(222);
        verify(tmdbClient).findMovieById(222);
        verify(movieRepository).save(movie);
        verifyNoMoreInteractions(movieRepository, tmdbClient);
        verifyNoInteractions(actorRepository, tvShowRepository);
    }
}
