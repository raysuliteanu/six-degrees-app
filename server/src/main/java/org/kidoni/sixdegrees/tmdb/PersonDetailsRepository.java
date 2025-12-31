package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.springframework.data.repository.CrudRepository;

public interface PersonDetailsRepository extends CrudRepository<PersonDetails, Integer> {
}
