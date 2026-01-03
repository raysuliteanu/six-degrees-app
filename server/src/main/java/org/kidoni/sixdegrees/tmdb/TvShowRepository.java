package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.TvShow;
import org.springframework.data.repository.CrudRepository;

public interface TvShowRepository extends CrudRepository<TvShow, Integer> {
}
