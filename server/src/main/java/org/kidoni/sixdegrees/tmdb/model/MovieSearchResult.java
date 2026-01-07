package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        MovieSearchResult.JSON_PROPERTY_PAGE,
        MovieSearchResult.JSON_PROPERTY_RESULTS,
        MovieSearchResult.JSON_PROPERTY_TOTAL_PAGES,
        MovieSearchResult.JSON_PROPERTY_TOTAL_RESULTS
})
public class MovieSearchResult {
    public static final String JSON_PROPERTY_PAGE = "page";
    @jakarta.annotation.Nullable
    private Integer page = 0;

    public static final String JSON_PROPERTY_RESULTS = "results";
    @jakarta.annotation.Nullable
    private List<Movie> results = new ArrayList<>();

    public static final String JSON_PROPERTY_TOTAL_PAGES = "total_pages";
    @jakarta.annotation.Nullable
    private Integer totalPages = 0;

    public static final String JSON_PROPERTY_TOTAL_RESULTS = "total_results";
    @jakarta.annotation.Nullable
    private Integer totalResults = 0;

    public MovieSearchResult() {
    }

    public MovieSearchResult page(@jakarta.annotation.Nullable Integer page) {
        this.page = page;
        return this;
    }

    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getPage() {
        return page;
    }

    @JsonProperty(JSON_PROPERTY_PAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPage(@jakarta.annotation.Nullable Integer page) {
        this.page = page;
    }

    public MovieSearchResult results(@jakarta.annotation.Nullable List<Movie> results) {
        this.results = results;
        return this;
    }

    public MovieSearchResult addResultsItem(Movie resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

   @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_RESULTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Movie> getResults() {
        return results;
    }

    @JsonProperty(JSON_PROPERTY_RESULTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setResults(@jakarta.annotation.Nullable List<Movie> results) {
        this.results = results;
    }

    public MovieSearchResult totalPages(@jakarta.annotation.Nullable Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

   @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_PAGES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTotalPages() {
        return totalPages;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_PAGES)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalPages(@jakarta.annotation.Nullable Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MovieSearchResult totalResults(@jakarta.annotation.Nullable Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

   @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_TOTAL_RESULTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getTotalResults() {
        return totalResults;
    }

    @JsonProperty(JSON_PROPERTY_TOTAL_RESULTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setTotalResults(@jakarta.annotation.Nullable Integer totalResults) {
        this.totalResults = totalResults;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieSearchResult searchMovie200Response = (MovieSearchResult) o;
        return Objects.equals(this.page, searchMovie200Response.page) &&
                Objects.equals(this.results, searchMovie200Response.results) &&
                Objects.equals(this.totalPages, searchMovie200Response.totalPages) &&
                Objects.equals(this.totalResults, searchMovie200Response.totalResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, results, totalPages, totalResults);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchMovie200Response {\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
        sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
        sb.append("    totalResults: ").append(toIndentedString(totalResults)).append("\n");
        sb.append("}");
        return sb.toString();
    }

   private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
