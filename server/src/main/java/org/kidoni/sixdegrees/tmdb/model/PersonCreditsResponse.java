package org.kidoni.sixdegrees.tmdb.model;

import java.util.List;

public record PersonCreditsResponse(
    Integer id,
    List<CreditWithRole> cast,
    List<CreditWithRole> crew
) {}
