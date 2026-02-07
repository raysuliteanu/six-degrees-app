package org.kidoni.sixdegrees.tmdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonSearchResult {

    private Integer page = 0;
    private List<Person> results = new ArrayList<>();
    private Integer totalPages = 0;
    private Integer totalResults = 0;

    public PersonSearchResult() {
    }

    public PersonSearchResult page(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public PersonSearchResult results(List<Person> results) {
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

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }

    public PersonSearchResult totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public PersonSearchResult totalResults(Integer totalResults) {
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

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
