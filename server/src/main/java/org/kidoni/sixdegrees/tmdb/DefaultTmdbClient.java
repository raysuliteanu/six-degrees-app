package org.kidoni.sixdegrees.tmdb;

import java.util.List;
import org.kidoni.sixdegrees.tmdb.api.model.MovieDetails200Response;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.api.model.PersonDetails200Response;
import org.kidoni.sixdegrees.tmdb.api.model.SearchMovie200Response;
import org.kidoni.sixdegrees.tmdb.api.model.SearchPerson200Response;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

public class DefaultTmdbClient implements TmdbClient {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultTmdbClient.class);

    private static final String SEARCH_PERSON_PATH = "/3/search/person";
    private static final String PERSON_DETAIL_PATH = "/3/person/";
    private static final String SEARCH_MOVIE_PATH = "/3/search/movie";
    private static final String MOVIE_DETAIL_PATH = "/3/movie/";

    private final RestClient restClient;

    public DefaultTmdbClient(final TmdbConfigurationProperties tmdbConfigurationProperties) {
        LOG.debug("Initializing TmdbClient: URL {}", tmdbConfigurationProperties.getUrl());
        this.restClient = RestClient.builder()
                .baseUrl(tmdbConfigurationProperties.getUrl())
                .requestFactory(new JdkClientHttpRequestFactory())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tmdbConfigurationProperties.getAccessToken())
                .build();
    }

    @Override
    public PersonSearchResult searchPersonByName(String name) {
        LOG.debug("Finding people with name {}", name);
        SearchPerson200Response result = restClient.method(HttpMethod.GET)
                .uri((uriBuilder -> uriBuilder
                        .path(SEARCH_PERSON_PATH)
                        .queryParam("query", name)
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(SearchPerson200Response.class);

        if (result != null) {
            var personSearchResult = new PersonSearchResult()
                    .page(result.getPage())
                    .totalPages(result.getTotalPages())
                    .totalResults(result.getTotalResults());
            if (result.getResults() != null) {
                List<Person> mappedPersons = result.getResults().stream()
                        .map(TmdbApiMapper::mapSearchResultToPerson)
                        .toList();
                personSearchResult.results(mappedPersons);
            }
            return personSearchResult;
        }
        return null;
    }

    @Override
    public Person findPersonById(Integer id) {
        LOG.debug("fetching person by id {}", id);
        PersonDetails200Response apiPerson = restClient.method(HttpMethod.GET)
                .uri(PERSON_DETAIL_PATH + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PersonDetails200Response.class);
        return TmdbApiMapper.mapToPerson(apiPerson);
    }

    @Override
    public List<Credit> getPersonCombinedCredits(final Integer id) {
        LOG.debug("fetching credits for person by id {}", id);
        PersonCombinedCredits200Response apiCredits = getPersonCombinedCreditsRaw(id);
        return TmdbApiMapper.mapToCreditsList(apiCredits);
    }

    @Override
    public PersonCombinedCredits200Response getPersonCombinedCreditsRaw(final Integer id) {
        LOG.debug("fetching raw combined credits for person by id {}", id);
        return restClient.method(HttpMethod.GET)
                .uri(PERSON_DETAIL_PATH + id + "/combined_credits")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PersonCombinedCredits200Response.class);
    }

    @Override
    public MovieSearchResult searchMovieByName(final String name) {
        LOG.debug("Finding movies with name {}", name);
        SearchMovie200Response result = restClient.method(HttpMethod.GET)
                .uri((uriBuilder -> uriBuilder
                        .path(SEARCH_MOVIE_PATH)
                        .queryParam("query", name)
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(SearchMovie200Response.class);

        if (result != null) {
            var movieSearchResult = new MovieSearchResult()
                    .page(result.getPage())
                    .totalPages(result.getTotalPages())
                    .totalResults(result.getTotalResults());
            if (result.getResults() != null) {
                List<Movie> mappedMovies = result.getResults().stream()
                        .map(TmdbApiMapper::mapSearchResultToMovie)
                        .toList();
                movieSearchResult.results(mappedMovies);
            }
            return movieSearchResult;
        }
        return null;
    }

    @Override
    public Movie findMovieById(final Integer id) {
        LOG.debug("fetching movie by id {}", id);
        MovieDetails200Response apiMovie = restClient.method(HttpMethod.GET)
                .uri(MOVIE_DETAIL_PATH + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MovieDetails200Response.class);
        return TmdbApiMapper.mapToMovie(apiMovie);
    }
}
