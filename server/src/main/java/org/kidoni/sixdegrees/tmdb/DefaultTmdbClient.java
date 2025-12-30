package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
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

    RestClient restClient;

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
        return restClient.method(HttpMethod.GET)
                .uri((uriBuilder -> uriBuilder
                        .path(SEARCH_PERSON_PATH)
                        .queryParam("query", name)
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PersonSearchResult.class);
    }

    @Override
    public PersonDetails findPersonById(Integer id) {
        LOG.debug("fetching person by id {}", id);
        return restClient.method(HttpMethod.GET)
                .uri(PERSON_DETAIL_PATH + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PersonDetails.class);
    }

    @Override
    public PersonCombinedCredits getPersonCombinedCredits(final Integer id) {
        LOG.debug("fetching credits for person by id {}", id);
        return restClient.method(HttpMethod.GET)
                .uri(PERSON_DETAIL_PATH + id + "/combined_credits")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PersonCombinedCredits.class);
    }

    @Override
    public MovieSearchResult searchMovieByName(final String name) {
        LOG.debug("Finding movies with name {}", name);
        return restClient.method(HttpMethod.GET)
                .uri((uriBuilder -> uriBuilder
                        .path(SEARCH_MOVIE_PATH)
                        .queryParam("query", name)
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MovieSearchResult.class);
    }

    @Override
    public MovieDetails findMovieById(final Integer id) {
        LOG.debug("fetching movie by id {}", id);
        return restClient.method(HttpMethod.GET)
                .uri(MOVIE_DETAIL_PATH + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(MovieDetails.class);
    }
}
