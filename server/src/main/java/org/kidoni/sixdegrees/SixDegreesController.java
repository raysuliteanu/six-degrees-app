package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.MovieDetails;
import org.kidoni.sixdegrees.tmdb.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.PersonDetails;
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

    @GetMapping(path = "/search/person/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonSearchResult personSearch(@PathVariable final String name) {
        return  tmdbClient.searchPersonByName(name);
    }

    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDetails findPerson(@PathVariable final int id) {
        return  tmdbClient.findPersonById(id);
    }

    @GetMapping(path = "/person/{id}/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonCombinedCredits getPersonCredits(@PathVariable final int id) {
        return  tmdbClient.getPersonCombinedCredits(id);
    }

    @GetMapping(path = "/search/movie/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieSearchResult movieSearch(@PathVariable final String name) {
        return  tmdbClient.searchMovieByName(name);
    }

    @GetMapping(path = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDetails findMovie(@PathVariable final int id) {
        return  tmdbClient.findMovieById(id);
    }

}
