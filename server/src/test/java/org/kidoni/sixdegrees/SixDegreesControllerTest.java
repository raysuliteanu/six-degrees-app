package org.kidoni.sixdegrees;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.MovieDetailsRepository;
import org.kidoni.sixdegrees.tmdb.PersonDetailsRepository;
import org.kidoni.sixdegrees.tmdb.TmdbClient;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

class SixDegreesControllerTest {
    TmdbClient tmdbClient;
    PersonDetailsRepository personDetailsRepository;
    MovieDetailsRepository movieDetailsRepository;
    SixDegreesController controller;

    @BeforeEach
    void setUp() {
        tmdbClient = mock(TmdbClient.class);
        personDetailsRepository = mock(PersonDetailsRepository.class);
        movieDetailsRepository =  mock(MovieDetailsRepository.class);
        controller = new SixDegreesController(
            tmdbClient,
            personDetailsRepository,
            movieDetailsRepository
        );
    }

    @Test
    void searchForPerson() {
        final var personSearchResult = new PersonSearchResult().page(1).results(List.of(new Person().id(666))).totalPages(1).totalResults(1);
        when(tmdbClient.searchPersonByName("smith")).thenReturn(personSearchResult);
        final var personDetails = new PersonDetails().id(666);
        when(tmdbClient.findPersonById(666)).thenReturn(personDetails);
        when(personDetailsRepository.save(any(PersonDetails.class))).thenReturn(personDetails);

        var result = controller.personSearch("smith");
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());

        verify(tmdbClient).searchPersonByName("smith");
        verify(tmdbClient).findPersonById(666);
        verify(personDetailsRepository).save(any(PersonDetails.class));
        verifyNoInteractions(movieDetailsRepository);
        verifyNoMoreInteractions(tmdbClient, personDetailsRepository);
    }

}
