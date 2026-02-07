package org.kidoni.sixdegrees;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.CreditWithRole;
import org.kidoni.sixdegrees.tmdb.model.KnownFor;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.PersonCreditsResponse;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonSerializationTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = JsonMapper.builder()
            .propertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
            .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
            .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
            .build();
    }

    @Test
    void actor_serializesToCamelCase() throws Exception {
        Actor actor = new Actor();
        actor.setId(123);
        actor.setName("Tom Hanks");
        actor.setOriginalName("Thomas Jeffrey Hanks");
        actor.setProfilePath("/profile.jpg");
        actor.setKnownForDepartment("Acting");
        actor.setPlaceOfBirth("Concord, California, USA");
        actor.setImdbId("nm0000158");

        String json = objectMapper.writeValueAsString(actor);

        assertTrue(json.contains("\"originalName\""), "Expected camelCase 'originalName'");
        assertTrue(json.contains("\"profilePath\""), "Expected camelCase 'profilePath'");
        assertTrue(json.contains("\"knownForDepartment\""), "Expected camelCase 'knownForDepartment'");
        assertTrue(json.contains("\"placeOfBirth\""), "Expected camelCase 'placeOfBirth'");
        assertTrue(json.contains("\"imdbId\""), "Expected camelCase 'imdbId'");

        assertFalse(json.contains("\"original_name\""), "Should not contain snake_case");
        assertFalse(json.contains("\"profile_path\""), "Should not contain snake_case");
        assertFalse(json.contains("\"known_for_department\""), "Should not contain snake_case");
    }

    @Test
    void movie_serializesToCamelCase() throws Exception {
        Movie movie = new Movie();
        movie.setId(100);
        movie.setTitle("The Matrix");
        movie.setPosterPath("/poster.jpg");
        movie.setBackdropPath("/backdrop.jpg");
        movie.setVoteAverage(8.7f);
        movie.setVoteCount(20000);

        String json = objectMapper.writeValueAsString(movie);

        assertTrue(json.contains("\"posterPath\""), "Expected camelCase 'posterPath'");
        assertTrue(json.contains("\"backdropPath\""), "Expected camelCase 'backdropPath'");
        assertTrue(json.contains("\"voteAverage\""), "Expected camelCase 'voteAverage'");
        assertTrue(json.contains("\"voteCount\""), "Expected camelCase 'voteCount'");

        assertFalse(json.contains("\"poster_path\""), "Should not contain snake_case");
        assertFalse(json.contains("\"backdrop_path\""), "Should not contain snake_case");
        assertFalse(json.contains("\"vote_average\""), "Should not contain snake_case");
    }

    @Test
    void personSearchResult_serializesToCamelCase() throws Exception {
        PersonSearchResult result = new PersonSearchResult()
            .page(1)
            .totalPages(10)
            .totalResults(100)
            .results(List.of());

        String json = objectMapper.writeValueAsString(result);

        assertTrue(json.contains("\"totalPages\""), "Expected camelCase 'totalPages'");
        assertTrue(json.contains("\"totalResults\""), "Expected camelCase 'totalResults'");

        assertFalse(json.contains("\"total_pages\""), "Should not contain snake_case");
        assertFalse(json.contains("\"total_results\""), "Should not contain snake_case");
    }

    @Test
    void actor_serializesDatesAsIsoStrings() throws Exception {
        Actor actor = new Actor();
        actor.setId(123);
        actor.setName("Test Actor");
        actor.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1956-07-09"));

        String json = objectMapper.writeValueAsString(actor);

        assertTrue(json.contains("\"birthday\":\"1956-07-09\""), "Expected ISO date format");
        assertFalse(json.matches(".*\"birthday\":\\s*-?\\d{10,}.*"), "Should not be epoch timestamp");
    }

    @Test
    void movie_serializesReleaseDateAsIsoString() throws Exception {
        Movie movie = new Movie();
        movie.setId(100);
        movie.setTitle("The Matrix");
        movie.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse("1999-03-31"));

        String json = objectMapper.writeValueAsString(movie);

        assertTrue(json.contains("\"1999-03-31\""), "Expected ISO date format");
    }

    @Test
    void personCreditsResponse_hasCorrectStructure() throws Exception {
        CreditWithRole castCredit = new CreditWithRole(
            111, "Movie Title", "Overview", "2020-01-15",
            "/poster.jpg", "/backdrop.jpg", "Character", "credit-123",
            0, "movie", 7.5f, 5000, 50.0f, null, null
        );
        CreditWithRole crewCredit = new CreditWithRole(
            222, "Crew Movie", "Overview", "2021-06-20",
            "/poster2.jpg", "/backdrop2.jpg", null, "credit-456",
            null, "movie", 8.0f, 3000, 40.0f, "Production", "Producer"
        );
        PersonCreditsResponse response = new PersonCreditsResponse(
            789, List.of(castCredit), List.of(crewCredit)
        );

        String json = objectMapper.writeValueAsString(response);

        assertTrue(json.contains("\"id\":789"), "Expected person id");
        assertTrue(json.contains("\"cast\":["), "Expected cast array");
        assertTrue(json.contains("\"crew\":["), "Expected crew array");
        assertTrue(json.contains("\"character\":\"Character\""), "Expected character in cast");
        assertTrue(json.contains("\"department\":\"Production\""), "Expected department in crew");
        assertTrue(json.contains("\"job\":\"Producer\""), "Expected job in crew");
    }

    @Test
    void knownFor_serializesToCamelCase() throws Exception {
        KnownFor knownFor = new KnownFor(
            100, "Famous Movie", "Great overview", "1999-03-31",
            "/poster.jpg", "/backdrop.jpg", "movie",
            List.of(28, 878), 99.5f, 8.7f, 15000
        );
        Actor actor = new Actor();
        actor.setId(123);
        actor.setName("Test Actor");
        actor.setKnownFor(List.of(knownFor));

        String json = objectMapper.writeValueAsString(actor);

        assertTrue(json.contains("\"knownFor\":["), "Expected camelCase 'knownFor'");
        assertTrue(json.contains("\"releaseDate\":\"1999-03-31\""), "Expected camelCase 'releaseDate'");
        assertTrue(json.contains("\"posterPath\":\"/poster.jpg\""), "Expected camelCase 'posterPath'");
        assertTrue(json.contains("\"backdropPath\":\"/backdrop.jpg\""), "Expected camelCase 'backdropPath'");
        assertTrue(json.contains("\"mediaType\":\"movie\""), "Expected camelCase 'mediaType'");
        assertTrue(json.contains("\"genreIds\":[28,878]"), "Expected camelCase 'genreIds'");
        assertTrue(json.contains("\"voteAverage\":8.7"), "Expected camelCase 'voteAverage'");
        assertTrue(json.contains("\"voteCount\":15000"), "Expected camelCase 'voteCount'");
    }

    @Test
    void actor_includesTypeDiscriminator() throws Exception {
        Actor actor = new Actor();
        actor.setId(123);
        actor.setName("Tom Hanks");

        String json = objectMapper.writeValueAsString(actor);

        assertTrue(json.contains("\"type\":\"actor\""), "Expected type discriminator for Actor");
    }

    @Test
    void movie_includesMediaTypeDiscriminator() throws Exception {
        Movie movie = new Movie();
        movie.setId(100);
        movie.setTitle("The Matrix");

        String json = objectMapper.writeValueAsString(movie);

        assertTrue(json.contains("\"mediaType\":\"movie\""), "Expected mediaType discriminator for Movie");
    }
}
