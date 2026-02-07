package org.kidoni.sixdegrees.tmdb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Director implements Person {
    private Integer id;

    private String name;

    private String originalName;

    private Boolean adult;

    private String biography;

    private String placeOfBirth;

    private String homepage;

    private Float popularity;

    private Integer gender;

    private Date birthday;

    private Date deathday;

    private String profilePath;

    private String knownForDepartment;

    private String imdbId;

    private List<String> alsoKnownAs = new ArrayList<>();

    private List<KnownFor> knownFor = new ArrayList<>();

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
    public String originalName() {
        return originalName;
    }

    @Override
    public Boolean adult() {
        return adult;
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

    @Override
    public String profilePath() {
        return profilePath;
    }

    @Override
    public String knownForDepartment() {
        return knownForDepartment;
    }

    @Override
    public String imdbId() {
        return imdbId;
    }

    @Override
    public List<String> alsoKnownAs() {
        return alsoKnownAs;
    }

    @Override
    public List<KnownFor> knownFor() {
        return knownFor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
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

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public void setKnownFor(List<KnownFor> knownFor) {
        this.knownFor = knownFor;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }
}
