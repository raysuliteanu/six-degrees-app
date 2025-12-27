package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.Person;
import org.kidoni.sixdegrees.tmdb.PersonSearchResult;
import org.kidoni.sixdegrees.tmdb.TmdbClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SixDegreesController {
    private final TmdbClient tmdbClient;

    public SixDegreesController(final TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @GetMapping(path = "/person/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonSearchResult getPerson(@PathVariable final String name) {
        return  tmdbClient.findPerson(name);
    }
}
