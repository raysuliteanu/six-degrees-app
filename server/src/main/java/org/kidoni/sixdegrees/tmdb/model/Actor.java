package org.kidoni.sixdegrees.tmdb.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import com.fasterxml.jackson.annotation.JsonProperty;

@Node
public class Actor implements Person {
    @Id
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("popularity")
    private Float popularity;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("birthday")
    private Date birthday;

    @JsonProperty("deathday")
    private Date deathday;

    @Relationship
    @JsonProperty("credits")
    private List<Credit> credits;

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Credit> credits() {
        return credits;
    }

    @Override
    public String biography() {
        return biography;
    }

    @Override
    public String placeOfBirth() {
        return placeOfBirth;
    }

    @Override
    public String homepage() {
        return homepage;
    }

    @Override
    public Float popularity() {
        return popularity;
    }

    @Override
    public Integer gender() {
        return gender;
    }

    @Override
    public Date birthday() {
        return birthday;
    }

    @Override
    public Date deathday() {
        return deathday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }
}
