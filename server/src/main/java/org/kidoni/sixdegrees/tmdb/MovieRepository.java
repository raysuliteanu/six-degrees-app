package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
