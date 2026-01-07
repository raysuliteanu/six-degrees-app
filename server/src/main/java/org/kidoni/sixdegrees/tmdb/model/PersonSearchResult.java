package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonPropertyOrder({
        PersonSearchResult.JSON_PROPERTY_PAGE,
        PersonSearchResult.JSON_PROPERTY_RESULTS,
        PersonSearchResult.JSON_PROPERTY_TOTAL_PAGES,
        PersonSearchResult.JSON_PROPERTY_TOTAL_RESULTS
})
public class PersonSearchResult {

    public static final String JSON_PROPERTY_PAGE = "page";
    @jakarta.annotation.Nullable
    private Integer page = 0;

    public static final String JSON_PROPERTY_RESULTS = "results";
    @jakarta.annotation.Nullable
    private List<Person> results = new ArrayList<>();

    public static final String JSON_PROPERTY_TOTAL_PAGES = "total_pages";
    @jakarta.annotation.Nullable
    private Integer totalPages = 0;

    public static final String JSON_PROPERTY_TOTAL_RESULTS = "total_results";
    @jakarta.annotation.Nullable
    private Integer totalResults = 0;

    public PersonSearchResult() {
    }

    public PersonSearchResult page(@jakarta.annotation.Nullable Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Get page
     *
     * @return page
     */
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

    public PersonSearchResult results(@jakarta.annotation.Nullable List<Person> results) {
        this.results = results;
        return this;
    }

    public PersonSearchResult addResultsItem(Person resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     *
     * @return results
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_RESULTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Person> getResults() {
        return results;
    }

    @JsonProperty(JSON_PROPERTY_RESULTS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setResults(@jakarta.annotation.Nullable List<Person> results) {
        this.results = results;
    }

    public PersonSearchResult totalPages(@jakarta.annotation.Nullable Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * Get totalPages
     *
     * @return totalPages
     */
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

    public PersonSearchResult totalResults(@jakarta.annotation.Nullable Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    /**
     * Get totalResults
     *
     * @return totalResults
     */
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
        PersonSearchResult personSearchResult = (PersonSearchResult) o;
        return Objects.equals(this.page, personSearchResult.page) &&
                Objects.equals(this.results, personSearchResult.results) &&
                Objects.equals(this.totalPages, personSearchResult.totalPages) &&
                Objects.equals(this.totalResults, personSearchResult.totalResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, results, totalPages, totalResults);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PersonSearchResult {\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
        sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
        sb.append("    totalResults: ").append(toIndentedString(totalResults)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
