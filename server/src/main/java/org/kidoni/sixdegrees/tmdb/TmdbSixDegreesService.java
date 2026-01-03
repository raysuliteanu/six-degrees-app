package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.SixDegreesService;
import org.kidoni.sixdegrees.tmdb.graph.ConnectionPath;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.kidoni.sixdegrees.tmdb.model.TvShow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import java.util.Collections;
import java.util.List;

public class TmdbSixDegreesService implements SixDegreesService {
    private static final Logger LOG = LoggerFactory.getLogger(TmdbSixDegreesService.class);

    private final TmdbClient tmdbClient;
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;
    private final TaskExecutor taskExecutor;

    public TmdbSixDegreesService(final TmdbClient tmdbClient, final ActorRepository actorRepository,
            final MovieRepository movieRepository, final TvShowRepository tvShowRepository, final TaskExecutor taskExecutor) {
        this.tmdbClient = tmdbClient;
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public PersonSearchResult searchPerson(final String name) {
        final var searchResult = tmdbClient.searchPersonByName(name);
        if (searchResult.getResults() != null) {
            for (final var person : searchResult.getResults()) {
                taskExecutor.execute(() -> {
                    var details = tmdbClient.findPersonById(person.id());
                    if (details instanceof Actor actor) {
                        LOG.debug("saving actor {} (id: {})", actor.name(), actor.id());
                        actorRepository.save(actor);
                    } else {
                        LOG.debug("skipping non-actor {} (id: {})", details.name(), details.id());
                    }
                });
            }
        }
        return searchResult;
    }

    @Override
    public Person findPerson(final int id) {
        LOG.debug("looking for person id: {}", id);
        var existingActor = actorRepository.findById(id);
        if (existingActor.isPresent()) {
            return existingActor.get();
        }

        var person = tmdbClient.findPersonById(id);
        if (person instanceof Actor actor) {
            LOG.debug("saving actor {} (id: {})", actor.name(), actor.id());
            actorRepository.save(actor);
        } else {
            LOG.debug("person {} (id: {}) is not an actor, not persisting", person.name(), person.id());
        }
        return person;
    }

    @Override
    public List<Credit> getPersonCredits(final int id) {
        LOG.debug("looking for credits for person id: {}", id);
        return tmdbClient.getPersonCombinedCredits(id);
    }

    @Override
    public MovieSearchResult movieSearch(final String name) {
        final var searchResult = tmdbClient.searchMovieByName(name);
        if (searchResult.getResults() != null) {
            for (final var movie : searchResult.getResults()) {
                taskExecutor.execute(() -> {
                    var details = tmdbClient.findMovieById(movie.id());
                    if (details != null) {
                        LOG.debug("saving movie {} (id: {})", details.title(), details.id());
                        movieRepository.save(details);
                    }
                });
            }
        }
        return searchResult;
    }

    @Override
    public Movie findMovie(final int id) {
        LOG.debug("looking for movie id: {}", id);
        return movieRepository.findById(id).orElseGet(() -> {
            var movie = tmdbClient.findMovieById(id);
            LOG.debug("saving movie {} (id: {})", movie.title(), movie.id());
            movieRepository.save(movie);
            return movie;
        });
    }

    @Override
    public List<ConnectionPath> findConnections(Integer actor1Id, Integer actor2Id, Integer maxDegrees) {
        LOG.debug("Finding connections between actor {} and actor {} (max degrees: {})", actor1Id, actor2Id,
                maxDegrees);
        // TODO:

        return Collections.emptyList();
    }
}
