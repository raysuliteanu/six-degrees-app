package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.SixDegreesService;
import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

public class TmdbSixDegreesService implements SixDegreesService {
    private static final Logger LOG = LoggerFactory.getLogger(TmdbSixDegreesService.class);

    private final TmdbClient tmdbClient;
    private final PersonDetailsRepository personDetailsRepository;
    private final MovieDetailsRepository movieDetailsRepository;
    private final TaskExecutor taskExecutor;

    public TmdbSixDegreesService(final TmdbClient tmdbClient, final PersonDetailsRepository personDetailsRepository, final MovieDetailsRepository movieDetailsRepository, final TaskExecutor taskExecutor) {
        this.tmdbClient = tmdbClient;
        this.personDetailsRepository = personDetailsRepository;
        this.movieDetailsRepository = movieDetailsRepository;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public PersonSearchResult searchPerson(final String name) {
        final var searchResult = tmdbClient.searchPersonByName(name);
        if (searchResult.getResults() != null) {
            for (final var person : searchResult.getResults()) {
                taskExecutor.execute(() -> {
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

    @Override
    public PersonDetails findPerson(final int id) {
        LOG.debug("looking for person id: {}", id);
        return personDetailsRepository.findById(id).orElseGet(() -> {
            var person = tmdbClient.findPersonById(id);
            LOG.debug("saving {} (id: {})", person.getName(), person.getId());
            personDetailsRepository.save(person);
            return person;
        });
    }

    @Override
    public PersonCombinedCredits getPersonCredits(final int id) {
        LOG.debug("looking for credits for person id: {}", id);
        return tmdbClient.getPersonCombinedCredits(id);
    }

    @Override
    public MovieSearchResult movieSearch(final String name) {
        final var searchResult = tmdbClient.searchMovieByName(name);
        if (searchResult.getResults() != null) {
            for (final var movie : searchResult.getResults()) {
                taskExecutor.execute(() -> {
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

    @Override
    public MovieDetails findMovie(final int id) {
        LOG.debug("looking for movie id: {}", id);
        return movieDetailsRepository.findById(id).orElseGet(() -> {
            var movie = tmdbClient.findMovieById(id);
            LOG.debug("saving {} (id: {})", movie.getTitle(), movie.getId());
            movieDetailsRepository.save(movie);
            return movie;
        });
    }
}
