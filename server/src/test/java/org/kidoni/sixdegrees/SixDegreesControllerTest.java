package org.kidoni.sixdegrees;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class SixDegreesControllerTest {
    SixDegreesService sixDegreesService;
    SixDegreesController controller;

    @BeforeEach
    void setUp() {
        sixDegreesService = mock(SixDegreesService.class);
        controller = new SixDegreesController(sixDegreesService);
    }

    @Test
    void searchPerson() {
        final var expected = new PersonSearchResult()
            .page(1)
            .results(List.of(new Person().id(123).name("Tom Hanks")))
            .totalPages(1)
            .totalResults(1);
        when(sixDegreesService.searchPerson("hanks")).thenReturn(expected);

        var result = controller.searchPerson("hanks");

        assertSame(expected, result);
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());
        assertEquals("Tom Hanks", result.getResults().get(0).getName());

        verify(sixDegreesService).searchPerson("hanks");
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void searchPersonWithNoResults() {
        final var expected = new PersonSearchResult()
            .page(1)
            .results(List.of())
            .totalPages(0)
            .totalResults(0);
        when(sixDegreesService.searchPerson("unknown")).thenReturn(expected);

        var result = controller.searchPerson("unknown");

        assertSame(expected, result);
        assertEquals(0, result.getResults().size());

        verify(sixDegreesService).searchPerson("unknown");
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void findPerson() {
        final var expected = new PersonDetails()
            .id(456)
            .name("Meryl Streep")
            .biography("Award-winning actress");
        when(sixDegreesService.findPerson(456)).thenReturn(expected);

        var result = controller.findPerson(456);

        assertSame(expected, result);
        assertEquals(456, result.getId());
        assertEquals("Meryl Streep", result.getName());
        assertEquals("Award-winning actress", result.getBiography());

        verify(sixDegreesService).findPerson(456);
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void getPersonCredits() {
        final var expected = new PersonCombinedCredits().id(789);
        when(sixDegreesService.getPersonCredits(789)).thenReturn(expected);

        var result = controller.getPersonCredits(789);

        assertSame(expected, result);
        assertEquals(789, result.getId());

        verify(sixDegreesService).getPersonCredits(789);
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void movieSearch() {
        final var expected = new MovieSearchResult()
            .page(1)
            .results(List.of(new Movie().id(999).title("The Matrix")))
            .totalPages(1)
            .totalResults(1);
        when(sixDegreesService.movieSearch("matrix")).thenReturn(expected);

        var result = controller.movieSearch("matrix");

        assertSame(expected, result);
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());
        assertEquals("The Matrix", result.getResults().get(0).getTitle());

        verify(sixDegreesService).movieSearch("matrix");
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void movieSearchWithNoResults() {
        final var expected = new MovieSearchResult()
            .page(1)
            .results(List.of())
            .totalPages(0)
            .totalResults(0);
        when(sixDegreesService.movieSearch("unknown")).thenReturn(expected);

        var result = controller.movieSearch("unknown");

        assertSame(expected, result);
        assertEquals(0, result.getResults().size());

        verify(sixDegreesService).movieSearch("unknown");
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void findMovie() {
        final var expected = new MovieDetails()
            .id(111)
            .title("Inception")
            .overview("A mind-bending thriller");
        when(sixDegreesService.findMovie(111)).thenReturn(expected);

        var result = controller.findMovie(111);

        assertSame(expected, result);
        assertEquals(111, result.getId());
        assertEquals("Inception", result.getTitle());
        assertEquals("A mind-bending thriller", result.getOverview());

        verify(sixDegreesService).findMovie(111);
        verifyNoMoreInteractions(sixDegreesService);
    }
}
