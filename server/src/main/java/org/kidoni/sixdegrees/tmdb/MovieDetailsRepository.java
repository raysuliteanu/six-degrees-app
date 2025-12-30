package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.MovieDetails;
import org.springframework.data.repository.CrudRepository;

public interface MovieDetailsRepository extends CrudRepository<MovieDetails, Integer> {
}
