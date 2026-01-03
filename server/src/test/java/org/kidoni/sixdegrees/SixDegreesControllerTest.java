package org.kidoni.sixdegrees;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
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
        Actor actor = new Actor();
        actor.setId(123);
        actor.setName("Tom Hanks");

        final var expected = new PersonSearchResult()
            .page(1)
            .results(List.of(actor))
            .totalPages(1)
            .totalResults(1);
        when(sixDegreesService.searchPerson("hanks")).thenReturn(expected);

        var result = controller.searchPerson("hanks");

        assertSame(expected, result);
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());
        assertEquals("Tom Hanks", result.getResults().get(0).name());

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
        final var actor = new Actor();
        actor.setId(456);
        actor.setName("Meryl Streep");
        actor.setBiography("Award-winning actress");
        when(sixDegreesService.findPerson(456)).thenReturn(actor);

        var result = controller.findPerson(456);

        assertSame(actor, result);
        assertEquals(456, result.id());
        assertEquals("Meryl Streep", result.name());
        assertEquals("Award-winning actress", result.biography());

        verify(sixDegreesService).findPerson(456);
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void getPersonCredits() {
        final Movie movie = new Movie();
        movie.setId(111);
        movie.setTitle("The Matrix");
        final List<Credit> expected = List.of(movie);
        when(sixDegreesService.getPersonCredits(789)).thenReturn(expected);

        var result = controller.getPersonCredits(789);

        assertSame(expected, result);
        assertEquals(1, result.size());
        assertEquals(111, result.get(0).id());

        verify(sixDegreesService).getPersonCredits(789);
        verifyNoMoreInteractions(sixDegreesService);
    }

    @Test
    void movieSearch() {
        Movie movie = new Movie();
        movie.setId(999);
        movie.setTitle("The Matrix");

        final var expected = new MovieSearchResult()
            .page(1)
            .results(List.of(movie))
            .totalPages(1)
            .totalResults(1);
        when(sixDegreesService.movieSearch("matrix")).thenReturn(expected);

        var result = controller.movieSearch("matrix");

        assertSame(expected, result);
        assertNotNull(result.getResults());
        assertEquals(1, result.getResults().size());
        assertEquals("The Matrix", result.getResults().get(0).title());

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
        final var movie = new Movie();
        movie.setId(111);
        movie.setTitle("Inception");
        movie.setOverview("A mind-bending thriller");
        when(sixDegreesService.findMovie(111)).thenReturn(movie);

        var result = controller.findMovie(111);

        assertSame(movie, result);
        assertEquals(111, result.id());
        assertEquals("Inception", result.title());
        assertEquals("A mind-bending thriller", result.overview());

        verify(sixDegreesService).findMovie(111);
        verifyNoMoreInteractions(sixDegreesService);
    }
}
