package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Integer> {
}
