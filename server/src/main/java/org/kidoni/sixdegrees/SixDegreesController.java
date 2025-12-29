package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.kidoni.sixdegrees.tmdb.TmdbClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// TODO: refactor business logic into a service e.g. how personSearch() is doing several things,
// it should be done in the service; the controller should only deal with the web request aspects.

@RestController
public class SixDegreesController {
    private final TmdbClient tmdbClient;

    public SixDegreesController(final TmdbClient tmdbClient) {
        this.tmdbClient = tmdbClient;
    }

    @GetMapping(path = "/search/person/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonSearchResult personSearch(@PathVariable final String name) {
        final var searchResult = tmdbClient.searchPersonByName(name);
        if (searchResult.getResults() != null) {
            for (final var person : searchResult.getResults()) {
                Thread.ofVirtual().start(() -> {
                   var details = tmdbClient.findPersonById(person.getId());
                   // todo: save details in db
                });
            }
        }
        return searchResult;
    }

    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDetails findPerson(@PathVariable final int id) {
        // todo: look up in db first
        return  tmdbClient.findPersonById(id);
    }

    @GetMapping(path = "/person/{id}/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonCombinedCredits getPersonCredits(@PathVariable final int id) {
        return  tmdbClient.getPersonCombinedCredits(id);
    }

    @GetMapping(path = "/search/movie/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieSearchResult movieSearch(@PathVariable final String name) {
        final var searchResult = tmdbClient.searchMovieByName(name);
        if (searchResult.getResults() != null) {
            for (final var movie : searchResult.getResults()) {
                Thread.ofVirtual().start(() -> {
                    var details = tmdbClient.findMovieById(movie.getId());
                    // todo: save details in db
                });
            }
        }
        return searchResult;
    }

    @GetMapping(path = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDetails findMovie(@PathVariable final int id) {
        // todo: look up in db first
        return tmdbClient.findMovieById(id);
    }

}
