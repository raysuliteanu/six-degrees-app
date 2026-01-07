package org.kidoni.sixdegrees.tmdb;

import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.SixDegreesTestContainers;
import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.neo4j.test.autoconfigure.DataNeo4jTest;

import static org.junit.jupiter.api.Assertions.*;

@DataNeo4jTest
public class PersonDetailsRepositoryITest implements SixDegreesTestContainers {
    @Autowired
    private ActorRepository actorRepository;

    @Test
    void saveAndFindActor() {
        Actor actor = new Actor();
        actor.setId(123);
        actor.setName("Test Actor");

        actorRepository.save(actor);

        var found = actorRepository.findById(123);
        assertTrue(found.isPresent());
        assertEquals("Test Actor", found.get().name());
    }
}
