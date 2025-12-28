package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

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

    /**
     * Get results
     *
     * @return results
     */
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


    public MovieSearchResult totalResults(@jakarta.annotation.Nullable Integer totalResults) {
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


    /**
     * Return true if this search_movie_200_response object is equal to o.
     */
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

    /**
     * Convert the instance into URL query string.
     *
     * @return URL query string
     */
    public String toUrlQueryString() {
        return toUrlQueryString(null);
    }

    /**
     * Convert the instance into URL query string.
     *
     * @param prefix prefix of the query string
     * @return URL query string
     */
    public String toUrlQueryString(String prefix) {
        String suffix = "";
        String containerSuffix = "";
        String containerPrefix = "";
        if (prefix == null) {
            // style=form, explode=true, e.g. /pet?name=cat&type=manx
            prefix = "";
        }
        else {
            // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
            prefix = prefix + "[";
            suffix = "]";
            containerSuffix = "]";
            containerPrefix = "[";
        }

        StringJoiner joiner = new StringJoiner("&");

        // add `page` to the URL query string
        if (getPage() != null) {
            joiner.add(String.format("%spage%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getPage()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `results` to the URL query string
        if (getResults() != null) {
            for (int i = 0; i < getResults().size(); i++) {
                if (getResults().get(i) != null) {
                    joiner.add(getResults().get(i).toUrlQueryString(String.format("%sresults%s%s", prefix, suffix,
                        suffix.isEmpty() ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        // add `total_pages` to the URL query string
        if (getTotalPages() != null) {
            joiner.add(String.format("%stotal_pages%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getTotalPages()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `total_results` to the URL query string
        if (getTotalResults() != null) {
            joiner.add(String.format("%stotal_results%s=%s", prefix, suffix, URLEncoder.encode(ModelUtil.valueToString(getTotalResults()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        return joiner.toString();
    }
}
