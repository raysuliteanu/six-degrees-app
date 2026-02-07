package org.kidoni.sixdegrees.tmdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieSearchResult {
    private Integer page = 0;
    private List<Movie> results = new ArrayList<>();
    private Integer totalPages = 0;
    private Integer totalResults = 0;

    public MovieSearchResult() {
    }

    public MovieSearchResult page(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public MovieSearchResult results(List<Movie> results) {
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

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public MovieSearchResult totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MovieSearchResult totalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
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
        MovieSearchResult that = (MovieSearchResult) o;
        return Objects.equals(this.page, that.page) &&
                Objects.equals(this.results, that.results) &&
                Objects.equals(this.totalPages, that.totalPages) &&
                Objects.equals(this.totalResults, that.totalResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, results, totalPages, totalResults);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MovieSearchResult {\n");
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
