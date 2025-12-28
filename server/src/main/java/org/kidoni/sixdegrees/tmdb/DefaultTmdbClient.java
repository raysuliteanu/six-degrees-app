package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails200Response;
import org.kidoni.sixdegrees.tmdb.model.SearchPerson200Response;
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
    public SearchPerson200Response searchPersonByName(String name) {
        LOG.debug("Finding person with name {}", name);
        return restClient.method(HttpMethod.GET)
                .uri((uriBuilder -> uriBuilder
                        .path(SEARCH_PERSON_PATH)
                        .queryParam("query", name)
                        .build()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(SearchPerson200Response.class);
    }

    @Override
    public PersonDetails200Response findPersonById(Integer id){
        return restClient.method(HttpMethod.GET)
                .uri(PERSON_DETAIL_PATH + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PersonDetails200Response.class);
    }

    @Override
    public PersonCombinedCredits200Response getPersonCombinedCredits(final Integer id) {
        return restClient.method(HttpMethod.GET)
            .uri(PERSON_DETAIL_PATH + id + "/combined_credits")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(PersonCombinedCredits200Response.class);
    }
}
