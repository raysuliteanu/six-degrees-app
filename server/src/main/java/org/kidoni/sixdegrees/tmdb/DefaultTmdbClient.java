package org.kidoni.sixdegrees.tmdb;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

public class DefaultTmdbClient implements TmdbClient {
    private static final String SEARCH_PERSON_PATH = "/3/search/person";
    private static final String PERSON_DETAIL_PATH = "/3/person";

    RestClient restClient;

    public DefaultTmdbClient(final TmdbConfigurationProperties tmdbConfigurationProperties) {
        this.restClient = RestClient.builder()
                .baseUrl(tmdbConfigurationProperties.getUrl())
                .requestFactory(new JdkClientHttpRequestFactory())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tmdbConfigurationProperties.getAccessToken())
                .build();
    }

    @Override
    public PersonSearchResult findPerson(String name) {
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
    public Person findPersonById(int id) {
        return restClient.method(HttpMethod.GET)
                .uri(PERSON_DETAIL_PATH + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Person.class);
    }
}
