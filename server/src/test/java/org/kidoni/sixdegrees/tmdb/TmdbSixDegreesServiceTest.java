package org.kidoni.sixdegrees.tmdb;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.springframework.core.task.SyncTaskExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class TmdbSixDegreesServiceTest {
    TmdbClient tmdbClient;
    PersonDetailsRepository personDetailsRepository;
    MovieDetailsRepository movieDetailsRepository;
    TmdbSixDegreesService tmdbService;

    @BeforeEach
    void setUp() {
        tmdbClient = mock(TmdbClient.class);
        personDetailsRepository = mock(PersonDetailsRepository.class);
        movieDetailsRepository = mock(MovieDetailsRepository.class);
        tmdbService = new TmdbSixDegreesService(
            tmdbClient,
            personDetailsRepository,
            movieDetailsRepository,
            new SyncTaskExecutor());
    }

    @Test
    void searchForPerson() {
        final var personSearchResult = new PersonSearchResult().page(1).results(List.of(new Person().id(666))).totalPages(1).totalResults(1);
        when(tmdbClient.searchPersonByName("smith")).thenReturn(personSearchResult);
        final var personDetails = new PersonDetails().id(666);
        when(tmdbClient.findPersonById(666)).thenReturn(personDetails);
        when(personDetailsRepository.save(any(PersonDetails.class))).thenReturn(personDetails);

        var result = tmdbService.searchPerson("smith");
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());

        verify(tmdbClient).searchPersonByName("smith");
        verify(tmdbClient).findPersonById(666);
        verify(personDetailsRepository).save(any(PersonDetails.class));
        verifyNoInteractions(movieDetailsRepository);
        verifyNoMoreInteractions(tmdbClient, personDetailsRepository);
    }

    @Test
    void searchForPersonWithNullResults() {
        final var personSearchResult = new PersonSearchResult().page(1).results(null).totalPages(0).totalResults(0);
        when(tmdbClient.searchPersonByName("unknown")).thenReturn(personSearchResult);

        var result = tmdbService.searchPerson("unknown");
        assertEquals(null, result.getResults());

        verify(tmdbClient).searchPersonByName("unknown");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(personDetailsRepository, movieDetailsRepository);
    }

    @Test
    void searchForPersonWithEmptyResults() {
        final var personSearchResult = new PersonSearchResult().page(1).results(List.of()).totalPages(0).totalResults(0);
        when(tmdbClient.searchPersonByName("nobody")).thenReturn(personSearchResult);

        var result = tmdbService.searchPerson("nobody");
        assertNotNull(result.getResults());
        assertEquals(0, result.getResults().size());

        verify(tmdbClient).searchPersonByName("nobody");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(personDetailsRepository, movieDetailsRepository);
    }

    @Test
    void findPersonWhenExistsInRepository() {
        final var personDetails = new PersonDetails().id(123).name("John Doe");
        when(personDetailsRepository.findById(123)).thenReturn(Optional.of(personDetails));

        var result = tmdbService.findPerson(123);
        assertNotNull(result);
        assertEquals(123, result.getId());
        assertEquals("John Doe", result.getName());

        verify(personDetailsRepository).findById(123);
        verifyNoMoreInteractions(personDetailsRepository);
        verifyNoInteractions(tmdbClient, movieDetailsRepository);
    }

    @Test
    void findPersonWhenNotInRepository() {
        final var personDetails = new PersonDetails().id(456).name("Jane Smith");
        when(personDetailsRepository.findById(456)).thenReturn(Optional.empty());
        when(tmdbClient.findPersonById(456)).thenReturn(personDetails);
        when(personDetailsRepository.save(personDetails)).thenReturn(personDetails);

        var result = tmdbService.findPerson(456);
        assertNotNull(result);
        assertEquals(456, result.getId());
        assertEquals("Jane Smith", result.getName());

        verify(personDetailsRepository).findById(456);
        verify(tmdbClient).findPersonById(456);
        verify(personDetailsRepository).save(personDetails);
        verifyNoMoreInteractions(personDetailsRepository, tmdbClient);
        verifyNoInteractions(movieDetailsRepository);
    }

    @Test
    void getPersonCredits() {
        final var credits = new PersonCombinedCredits().id(789);
        when(tmdbClient.getPersonCombinedCredits(789)).thenReturn(credits);

        var result = tmdbService.getPersonCredits(789);
        assertNotNull(result);
        assertEquals(789, result.getId());

        verify(tmdbClient).getPersonCombinedCredits(789);
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(personDetailsRepository, movieDetailsRepository);
    }

    @Test
    void searchForMovie() {
        final var movieSearchResult = new MovieSearchResult().page(1).results(List.of(new Movie().id(999))).totalPages(1).totalResults(1);
        when(tmdbClient.searchMovieByName("matrix")).thenReturn(movieSearchResult);
        final var movieDetails = new MovieDetails().id(999);
        when(tmdbClient.findMovieById(999)).thenReturn(movieDetails);
        when(movieDetailsRepository.save(any(MovieDetails.class))).thenReturn(movieDetails);

        var result = tmdbService.movieSearch("matrix");
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());

        verify(tmdbClient).searchMovieByName("matrix");
        verify(tmdbClient).findMovieById(999);
        verify(movieDetailsRepository).save(any(MovieDetails.class));
        verifyNoInteractions(personDetailsRepository);
        verifyNoMoreInteractions(tmdbClient, movieDetailsRepository);
    }

    @Test
    void searchForMovieWithNullResults() {
        final var movieSearchResult = new MovieSearchResult().page(1).results(null).totalPages(0).totalResults(0);
        when(tmdbClient.searchMovieByName("unknown")).thenReturn(movieSearchResult);

        var result = tmdbService.movieSearch("unknown");
        assertEquals(null, result.getResults());

        verify(tmdbClient).searchMovieByName("unknown");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(personDetailsRepository, movieDetailsRepository);
    }

    @Test
    void searchForMovieWithEmptyResults() {
        final var movieSearchResult = new MovieSearchResult().page(1).results(List.of()).totalPages(0).totalResults(0);
        when(tmdbClient.searchMovieByName("nomovie")).thenReturn(movieSearchResult);

        var result = tmdbService.movieSearch("nomovie");
        assertNotNull(result.getResults());
        assertEquals(0, result.getResults().size());

        verify(tmdbClient).searchMovieByName("nomovie");
        verifyNoMoreInteractions(tmdbClient);
        verifyNoInteractions(personDetailsRepository, movieDetailsRepository);
    }

    @Test
    void findMovieWhenExistsInRepository() {
        final var movieDetails = new MovieDetails().id(111).title("The Matrix");
        when(movieDetailsRepository.findById(111)).thenReturn(Optional.of(movieDetails));

        var result = tmdbService.findMovie(111);
        assertNotNull(result);
        assertEquals(111, result.getId());
        assertEquals("The Matrix", result.getTitle());

        verify(movieDetailsRepository).findById(111);
        verifyNoMoreInteractions(movieDetailsRepository);
        verifyNoInteractions(tmdbClient, personDetailsRepository);
    }

    @Test
    void findMovieWhenNotInRepository() {
        final var movieDetails = new MovieDetails().id(222).title("Inception");
        when(movieDetailsRepository.findById(222)).thenReturn(Optional.empty());
        when(tmdbClient.findMovieById(222)).thenReturn(movieDetails);
        when(movieDetailsRepository.save(movieDetails)).thenReturn(movieDetails);

        var result = tmdbService.findMovie(222);
        assertNotNull(result);
        assertEquals(222, result.getId());
        assertEquals("Inception", result.getTitle());

        verify(movieDetailsRepository).findById(222);
        verify(tmdbClient).findMovieById(222);
        verify(movieDetailsRepository).save(movieDetails);
        verifyNoMoreInteractions(movieDetailsRepository, tmdbClient);
        verifyNoInteractions(personDetailsRepository);
    }

}
