package org.kidoni.sixdegrees.tmdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.kidoni.sixdegrees.SixDegreesService;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.kidoni.sixdegrees.tmdb.api.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.graph.ConnectionEdge;
import org.kidoni.sixdegrees.tmdb.graph.ConnectionNode;
import org.kidoni.sixdegrees.tmdb.graph.ConnectionPath;
import org.kidoni.sixdegrees.tmdb.model.ActedInRelationship;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.kidoni.sixdegrees.tmdb.model.Credit;
import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.kidoni.sixdegrees.tmdb.model.MovieSearchResult;
import org.kidoni.sixdegrees.tmdb.model.Person;
import org.kidoni.sixdegrees.tmdb.model.PersonSearchResult;
import org.kidoni.sixdegrees.tmdb.model.TvShow;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

public class TmdbSixDegreesService implements SixDegreesService {
    private static final Logger LOG = LoggerFactory.getLogger(TmdbSixDegreesService.class);

    private final TmdbClient tmdbClient;
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;
    private final TaskExecutor taskExecutor;
    private final Neo4jClient neo4jClient;

    public TmdbSixDegreesService(final TmdbClient tmdbClient, final ActorRepository actorRepository,
            final MovieRepository movieRepository, final TvShowRepository tvShowRepository,
            final TaskExecutor taskExecutor, final Neo4jClient neo4jClient) {
        this.tmdbClient = tmdbClient;
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;
        this.taskExecutor = taskExecutor;
        this.neo4jClient = neo4jClient;
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
        LOG.debug("Finding connections between actor {} and actor {} (max degrees: {})",
                  actor1Id, actor2Id, maxDegrees);

        // Validate inputs
        if (actor1Id == null || actor2Id == null) {
            throw new IllegalArgumentException("Actor IDs cannot be null");
        }
        if (actor1Id.equals(actor2Id)) {
            return Collections.emptyList();  // Same actor - no path needed
        }
        if (maxDegrees == null || maxDegrees < 1 || maxDegrees > 6) {
            throw new IllegalArgumentException("Max degrees must be between 1 and 6");
        }

        // Ensure both actors are loaded with their credits
        try {
            ensureActorLoaded(actor1Id);
            ensureActorLoaded(actor2Id);
        } catch (Exception e) {
            LOG.error("Failed to load actors from TMDB", e);
            throw new RuntimeException("Actor not found: " + e.getMessage(), e);
        }

        // Calculate max hops (each degree = 2 hops: actor->movie->actor)
        int maxHops = maxDegrees * 2;

        // Query for shortest paths using Neo4jClient to get raw Path objects
        String cypherQuery = """
            MATCH (a1:Actor {id: $actor1Id}), (a2:Actor {id: $actor2Id})
            MATCH paths = allShortestPaths((a1)-[:ACTED_IN*]-(a2))
            WHERE length(paths) <= $maxHops
            WITH paths, length(paths) as pathLen
            WITH collect({path: paths, len: pathLen}) as allPaths, min(pathLen) as minLen
            UNWIND allPaths as pathData
            WITH pathData.path as path, pathData.len as len, minLen
            WHERE len = minLen
            RETURN path
            LIMIT $limit
            """;

        List<Path> paths = neo4jClient
            .query(cypherQuery)
            .bind(actor1Id).to("actor1Id")
            .bind(actor2Id).to("actor2Id")
            .bind(maxHops).to("maxHops")
            .bind(10).to("limit")
            .fetch()
            .all()
            .stream()
            .map(record -> (Path) record.get("path"))
            .toList();

        if (paths.isEmpty()) {
            LOG.info("No connection found between {} and {} within {} degrees",
                     actor1Id, actor2Id, maxDegrees);
            return Collections.emptyList();
        }

        // Convert paths to ConnectionPath objects
        return paths.stream()
            .map(this::mapPathToConnectionPath)
            .toList();
    }

    private void ensureActorLoaded(Integer actorId) {
        // Check if actor exists in Neo4j
        Optional<Actor> existing = actorRepository.findById(actorId);
        if (existing.isPresent()) {
            return;  // Actor already in database
        }

        // Fetch from TMDB
        Person person = tmdbClient.findPersonById(actorId);
        if (!(person instanceof Actor actor)) {
            throw new IllegalArgumentException("Person " + actorId + " is not an actor");
        }

        // Fetch credits with character names
        PersonCombinedCredits200Response rawCredits = tmdbClient.getPersonCombinedCreditsRaw(actorId);
        List<ActedInRelationship> relationships = TmdbApiMapper.mapToActedInRelationships(rawCredits);
        actor.setActedInRelationships(relationships);

        actorRepository.save(actor);
        LOG.debug("Loaded actor {} with {} credits", actor.name(), relationships.size());
    }

    private ConnectionPath mapPathToConnectionPath(Path path) {
        List<ConnectionNode> nodes = new ArrayList<>();
        List<ConnectionEdge> edges = new ArrayList<>();

        // Convert Neo4j nodes to ConnectionNodes
        List<Node> pathNodes = new ArrayList<>();
        path.nodes().forEach(pathNodes::add);

        for (Node node : pathNodes) {
            ConnectionNode connNode = createConnectionNode(node);
            nodes.add(connNode);
        }

        // Create edges from relationships
        path.relationships().forEach(rel -> {
            String fromId = getNodeIdFromElementId(rel.startNodeElementId(), pathNodes);
            String toId = getNodeIdFromElementId(rel.endNodeElementId(), pathNodes);

            // Extract character name from relationship properties
            String character = null;
            try {
                if (rel.containsKey("character")) {
                    character = rel.get("character").asString(null);
                }
            } catch (Exception e) {
                // Character property might not exist, that's okay
                LOG.debug("No character property in relationship", e);
            }

            // Create label with character name if available
            String label;
            if (character != null && !character.trim().isEmpty()) {
                label = "as " + character;
            } else {
                label = "appeared in";
            }

            edges.add(new ConnectionEdge(fromId, toId, label));
        });

        int degree = (nodes.size() - 1) / 2;  // Number of actor-to-actor hops
        return new ConnectionPath(nodes, edges, degree);
    }

    private ConnectionNode createConnectionNode(Node node) {
        boolean isActor = hasLabel(node, "Actor");
        boolean isMovie = hasLabel(node, "Movie");

        String type = isActor ? "actor" : isMovie ? "movie" : "tv";
        Integer id = node.get("id").asInt();
        String nodeId = type + "-" + id;
        String name = node.get("name").asString();
        String imageUrl = isActor ?
            node.get("profilePath").asString(null) :
            node.get("posterPath").asString(null);

        Map<String, Object> metadata = new HashMap<>();
        if (isActor) {
            metadata.put("popularity", Float.parseFloat(node.get("popularity").asString("0.0")));
        } else {
            metadata.put("releaseDate", node.get(isMovie ? "releaseDate" : "firstAirDate").asString(null));
        }

        return new ConnectionNode(nodeId, type, name, imageUrl, metadata);
    }

    private String getNodeIdFromElementId(String elementId, List<Node> pathNodes) {
        // Find node by element ID and construct typed ID
        for (Node node : pathNodes) {
            if (node.elementId().equals(elementId)) {
                boolean isActor = hasLabel(node, "Actor");
                boolean isMovie = hasLabel(node, "Movie");
                String type = isActor ? "actor" : isMovie ? "movie" : "tv";
                return type + "-" + node.get("id").asInt();
            }
        }
        throw new IllegalStateException("Node not found in path with element ID: " + elementId);
    }

    private boolean hasLabel(Node node, String label) {
        for (String nodeLabel : node.labels()) {
            if (nodeLabel.equals(label)) {
                return true;
            }
        }
        return false;
    }
}
