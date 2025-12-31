package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.SixDegreesService;
import org.kidoni.sixdegrees.tmdb.model.ConnectionEdge;
import org.kidoni.sixdegrees.tmdb.model.ConnectionNode;
import org.kidoni.sixdegrees.tmdb.model.ConnectionPath;
import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<ConnectionPath> findConnections(Integer actor1Id, Integer actor2Id, Integer maxDegrees) {
        LOG.debug("Finding connections between actor {} and actor {} (max degrees: {})", actor1Id, actor2Id, maxDegrees);

        // Get credits for both actors
        PersonCombinedCredits credits1 = getPersonCredits(actor1Id);
        PersonCombinedCredits credits2 = getPersonCredits(actor2Id);

        PersonDetails person1 = findPerson(actor1Id);
        PersonDetails person2 = findPerson(actor2Id);

        List<ConnectionPath> paths = new ArrayList<>();

        // Find direct connections (shared movies) - degree 1
        if (credits1.getCast() != null && credits2.getCast() != null) {
            for (var credit1 : credits1.getCast()) {
                for (var credit2 : credits2.getCast()) {
                    if (credit1.getId().equals(credit2.getId())) {
                        // Found a shared movie!
                        List<ConnectionNode> nodes = new ArrayList<>();
                        List<ConnectionEdge> edges = new ArrayList<>();

                        // Add actor1 node
                        nodes.add(new ConnectionNode(
                            "actor-" + actor1Id,
                            "actor",
                            person1.getName(),
                            person1.getProfilePath(),
                            Map.of("knownFor", person1.getKnownForDepartment() != null ? person1.getKnownForDepartment() : "")
                        ));

                        // Add movie node
                        nodes.add(new ConnectionNode(
                            "movie-" + credit1.getId(),
                            "movie",
                            credit1.getTitle(),
                            credit1.getPosterPath(),
                            Map.of("releaseDate", credit1.getReleaseDate() != null ? credit1.getReleaseDate() : "")
                        ));

                        // Add actor2 node
                        nodes.add(new ConnectionNode(
                            "actor-" + actor2Id,
                            "actor",
                            person2.getName(),
                            person2.getProfilePath(),
                            Map.of("knownFor", person2.getKnownForDepartment() != null ? person2.getKnownForDepartment() : "")
                        ));

                        // Add edges
                        edges.add(new ConnectionEdge(
                            "actor-" + actor1Id,
                            "movie-" + credit1.getId(),
                            credit1.getCharacter() != null ? "as " + credit1.getCharacter() : null
                        ));

                        edges.add(new ConnectionEdge(
                            "movie-" + credit1.getId(),
                            "actor-" + actor2Id,
                            credit2.getCharacter() != null ? "as " + credit2.getCharacter() : null
                        ));

                        paths.add(new ConnectionPath(nodes, edges, 1));

                        // For now, return just the first connection found
                        // In future, we can return multiple paths or implement BFS for deeper connections
                        if (!paths.isEmpty()) {
                            return paths;
                        }
                    }
                }
            }
        }

        LOG.debug("No direct connection found between actors {} and {}", actor1Id, actor2Id);
        return paths; // Return empty list if no connection found
    }
}
