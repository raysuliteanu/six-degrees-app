package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kidoni.sixdegrees.tmdb.MovieDetailsRepository;
import org.kidoni.sixdegrees.tmdb.PersonDetailsRepository;
import org.kidoni.sixdegrees.tmdb.TmdbClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// TODO: refactor business logic into a service e.g. how personSearch() is doing several things,
// it should be done in the service; the controller should only deal with the web request aspects.

@RestController
public class SixDegreesController {
    private static final Logger LOG = LoggerFactory.getLogger(SixDegreesController.class);

    private final TmdbClient tmdbClient;
    private final PersonDetailsRepository personDetailsRepository;
    private final MovieDetailsRepository movieDetailsRepository;

    public SixDegreesController(final TmdbClient tmdbClient, PersonDetailsRepository personDetailsRepository,
            MovieDetailsRepository movieDetailsRepository) {
        this.tmdbClient = tmdbClient;
        this.personDetailsRepository = personDetailsRepository;
        this.movieDetailsRepository = movieDetailsRepository;
    }

    @GetMapping(path = "/search/person/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonSearchResult personSearch(@PathVariable final String name) {
        final var searchResult = tmdbClient.searchPersonByName(name);
        if (searchResult.getResults() != null) {
            for (final var person : searchResult.getResults()) {
                Thread.ofVirtual().start(() -> {
                    var details = tmdbClient.findPersonById(person.getId());
                    if (details != null) {
                        LOG.debug("saving {} (id: {})", details.getName(), details.getId());
                        personDetailsRepository.save(details);
                    }
                });
            }
        }
        return searchResult;
    }

    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDetails findPerson(@PathVariable final int id) {
        LOG.debug("looking for person id: {}", id);
        return personDetailsRepository.findById(id).orElseGet(() -> {
            var person = tmdbClient.findPersonById(id);
            LOG.debug("saving {} (id: {})", person.getName(), person.getId());
            personDetailsRepository.save(person);
            return person;
        });
    }

    @GetMapping(path = "/person/{id}/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonCombinedCredits getPersonCredits(@PathVariable final int id) {
        LOG.debug("looking for credits for person id: {}", id);
        return tmdbClient.getPersonCombinedCredits(id);
    }

    @GetMapping(path = "/search/movie/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieSearchResult movieSearch(@PathVariable final String name) {
        final var searchResult = tmdbClient.searchMovieByName(name);
        if (searchResult.getResults() != null) {
            for (final var movie : searchResult.getResults()) {
                Thread.ofVirtual().start(() -> {
                    var details = tmdbClient.findMovieById(movie.getId());
                    if (details != null) {
                        LOG.debug("saving {} (id: {})", details.getTitle(), details.getId());
                        movieDetailsRepository.save(details);
                    }
                });
            }
        }
        return searchResult;
    }

    @GetMapping(path = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDetails findMovie(@PathVariable final int id) {
        LOG.debug("looking for movie id: {}", id);
        return movieDetailsRepository.findById(id).orElseGet(() -> {
            var movie = tmdbClient.findMovieById(id);
            LOG.debug("saving {} (id: {})", movie.getTitle(), movie.getId());
            movieDetailsRepository.save(movie);
            return movie;
        });
    }

}
