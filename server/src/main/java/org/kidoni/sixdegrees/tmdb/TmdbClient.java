package org.kidoni.sixdegrees.tmdb;

import org.kidoni.sixdegrees.tmdb.model.PersonCombinedCredits200Response;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails200Response;
import org.kidoni.sixdegrees.tmdb.model.SearchPerson200Response;

public interface TmdbClient {
    SearchPerson200Response searchPersonByName(String name);
    PersonDetails200Response findPersonById(Integer id);
    PersonCombinedCredits200Response getPersonCombinedCredits(Integer id);
}
