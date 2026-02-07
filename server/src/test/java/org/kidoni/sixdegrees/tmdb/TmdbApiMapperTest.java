package org.kidoni.sixdegrees.tmdb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200ResponseCastInner;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200ResponseCrewInner;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200ResponseResultsInner;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200ResponseResultsInnerKnownForInner;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Director;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonCreditsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TmdbApiMapperTest {

    @Test
    void mapSearchResultToPerson_mapsKnownForToActor() {
        SearchPerson200ResponseResultsInnerKnownForInner knownForItem = new SearchPerson200ResponseResultsInnerKnownForInner();
        knownForItem.setId(100);
        knownForItem.setTitle("Famous Movie");
        knownForItem.setOverview("A great movie");
        knownForItem.setReleaseDate("1999-03-31");
        knownForItem.setPosterPath("/poster.jpg");
        knownForItem.setBackdropPath("/backdrop.jpg");
        knownForItem.setMediaType("movie");
        knownForItem.setPopularity(new BigDecimal("99.5"));
        knownForItem.setVoteAverage(new BigDecimal("8.7"));
        knownForItem.setVoteCount(15000);
        knownForItem.setGenreIds(List.of(28, 878));

        SearchPerson200ResponseResultsInner apiPerson = new SearchPerson200ResponseResultsInner();
        apiPerson.setId(123);
        apiPerson.setName("Tom Hanks");
        apiPerson.setKnownForDepartment("Acting");
        apiPerson.setKnownFor(new ArrayList<>(List.of(knownForItem)));

        Person result = TmdbApiMapper.mapSearchResultToPerson(apiPerson);

        assertNotNull(result);
        assertTrue(result instanceof Actor);
        assertEquals(123, result.id());
        assertEquals("Tom Hanks", result.name());
        assertNotNull(result.knownFor());
        assertEquals(1, result.knownFor().size());

        var knownFor = result.knownFor().getFirst();
        assertEquals(100, knownFor.id());
        assertEquals("Famous Movie", knownFor.title());
        assertEquals("A great movie", knownFor.overview());
        assertEquals("1999-03-31", knownFor.releaseDate());
        assertEquals("/poster.jpg", knownFor.posterPath());
        assertEquals("/backdrop.jpg", knownFor.backdropPath());
        assertEquals("movie", knownFor.mediaType());
        assertEquals(99.5f, knownFor.popularity(), 0.01);
        assertEquals(8.7f, knownFor.voteAverage(), 0.01);
        assertEquals(15000, knownFor.voteCount());
        assertEquals(List.of(28, 878), knownFor.genreIds());
    }

    @Test
    void mapSearchResultToPerson_mapsKnownForToDirector() {
        SearchPerson200ResponseResultsInnerKnownForInner knownForItem = new SearchPerson200ResponseResultsInnerKnownForInner();
        knownForItem.setId(200);
        knownForItem.setTitle("Directed Movie");
        knownForItem.setMediaType("movie");

        SearchPerson200ResponseResultsInner apiPerson = new SearchPerson200ResponseResultsInner();
        apiPerson.setId(456);
        apiPerson.setName("Steven Spielberg");
        apiPerson.setKnownForDepartment("Directing");
        apiPerson.setKnownFor(new ArrayList<>(List.of(knownForItem)));

        Person result = TmdbApiMapper.mapSearchResultToPerson(apiPerson);

        assertNotNull(result);
        assertTrue(result instanceof Director);
        assertEquals(456, result.id());
        assertNotNull(result.knownFor());
        assertEquals(1, result.knownFor().size());
        assertEquals(200, result.knownFor().getFirst().id());
    }

    @Test
    void mapToCreditsResponse_mapsCastAndCrew() {
        PersonCombinedCredits200ResponseCastInner castItem = new PersonCombinedCredits200ResponseCastInner();
        castItem.setId(111);
        castItem.setTitle("Cast Movie");
        castItem.setOverview("A movie they acted in");
        castItem.setReleaseDate("2020-01-15");
        castItem.setPosterPath("/cast-poster.jpg");
        castItem.setBackdropPath("/cast-backdrop.jpg");
        castItem.setCharacter("Hero");
        castItem.setCreditId("cast-credit-123");
        castItem.setOrder(0);
        castItem.setMediaType("movie");
        castItem.setVoteAverage(new BigDecimal("7.5"));
        castItem.setVoteCount(5000);
        castItem.setPopularity(new BigDecimal("50.0"));

        PersonCombinedCredits200ResponseCrewInner crewItem = new PersonCombinedCredits200ResponseCrewInner();
        crewItem.setId(222);
        crewItem.setTitle("Crew Movie");
        crewItem.setOverview("A movie they worked on");
        crewItem.setReleaseDate("2021-06-20");
        crewItem.setPosterPath("/crew-poster.jpg");
        crewItem.setBackdropPath("/crew-backdrop.jpg");
        crewItem.setCreditId("crew-credit-456");
        crewItem.setMediaType("movie");
        crewItem.setVoteAverage(new BigDecimal("8.0"));
        crewItem.setVoteCount(3000);
        crewItem.setPopularity(new BigDecimal("40.0"));
        crewItem.setDepartment("Production");
        crewItem.setJob("Producer");

        PersonCombinedCredits200Response apiCredits = new PersonCombinedCredits200Response();
        apiCredits.setId(789);
        apiCredits.setCast(new ArrayList<>(List.of(castItem)));
        apiCredits.setCrew(new ArrayList<>(List.of(crewItem)));

        PersonCreditsResponse result = TmdbApiMapper.mapToCreditsResponse(789, apiCredits);

        assertNotNull(result);
        assertEquals(789, result.id());

        assertEquals(1, result.cast().size());
        var cast = result.cast().getFirst();
        assertEquals(111, cast.id());
        assertEquals("Cast Movie", cast.title());
        assertEquals("Hero", cast.character());
        assertEquals("cast-credit-123", cast.creditId());
        assertEquals(0, cast.order());
        assertEquals("movie", cast.mediaType());

        assertEquals(1, result.crew().size());
        var crew = result.crew().getFirst();
        assertEquals(222, crew.id());
        assertEquals("Crew Movie", crew.title());
        assertEquals("Production", crew.department());
        assertEquals("Producer", crew.job());
        assertEquals("crew-credit-456", crew.creditId());
    }

    @Test
    void mapToCreditsResponse_handlesNullCredits() {
        PersonCreditsResponse result = TmdbApiMapper.mapToCreditsResponse(123, null);

        assertNotNull(result);
        assertEquals(123, result.id());
        assertTrue(result.cast().isEmpty());
        assertTrue(result.crew().isEmpty());
    }

    @Test
    void mapToCreditsResponse_handlesEmptyLists() {
        PersonCombinedCredits200Response apiCredits = new PersonCombinedCredits200Response();
        apiCredits.setId(456);
        apiCredits.setCast(null);
        apiCredits.setCrew(null);

        PersonCreditsResponse result = TmdbApiMapper.mapToCreditsResponse(456, apiCredits);

        assertNotNull(result);
        assertEquals(456, result.id());
        assertTrue(result.cast().isEmpty());
        assertTrue(result.crew().isEmpty());
    }
}
