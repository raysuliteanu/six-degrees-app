package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SixDegreesController {
    private final SixDegreesService sixDegreesService;

    public SixDegreesController(final SixDegreesService sixDegreesService) {
        this.sixDegreesService = sixDegreesService;
    }

    @GetMapping(path = "/search/person/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonSearchResult searchPerson(@PathVariable final String name) {
        return sixDegreesService.searchPerson(name);
    }

    @GetMapping(path = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDetails findPerson(@PathVariable final int id) {
        return sixDegreesService.findPerson(id);
    }

    @GetMapping(path = "/person/{id}/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonCombinedCredits getPersonCredits(@PathVariable final int id) {
        return sixDegreesService.getPersonCredits(id);
    }

    @GetMapping(path = "/search/movie/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieSearchResult movieSearch(@PathVariable final String name) {
        return sixDegreesService.movieSearch(name);
    }

    @GetMapping(path = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDetails findMovie(@PathVariable final int id) {
        return sixDegreesService.findMovie(id);
    }
}
