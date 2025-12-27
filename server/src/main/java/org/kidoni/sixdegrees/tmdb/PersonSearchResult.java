package org.kidoni.sixdegrees.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PersonSearchResult(
    int page,
    List<Person> results,
    @JsonProperty("total_pages") int totalPages,
    @JsonProperty("total_results") int totalResults
) { }
