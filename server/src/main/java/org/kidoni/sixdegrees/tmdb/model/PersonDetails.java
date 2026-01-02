package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@JsonPropertyOrder({
        PersonDetails.JSON_PROPERTY_ADULT,
        PersonDetails.JSON_PROPERTY_ALSO_KNOWN_AS,
        PersonDetails.JSON_PROPERTY_BIOGRAPHY,
        PersonDetails.JSON_PROPERTY_BIRTHDAY,
        PersonDetails.JSON_PROPERTY_DEATHDAY,
        PersonDetails.JSON_PROPERTY_GENDER,
        PersonDetails.JSON_PROPERTY_HOMEPAGE,
        PersonDetails.JSON_PROPERTY_ID,
        PersonDetails.JSON_PROPERTY_IMDB_ID,
        PersonDetails.JSON_PROPERTY_KNOWN_FOR_DEPARTMENT,
        PersonDetails.JSON_PROPERTY_NAME,
        PersonDetails.JSON_PROPERTY_PLACE_OF_BIRTH,
        PersonDetails.JSON_PROPERTY_POPULARITY,
        PersonDetails.JSON_PROPERTY_PROFILE_PATH
})
@Node
public class PersonDetails {
    public static final String JSON_PROPERTY_ADULT = "adult";
    @jakarta.annotation.Nullable
    private Boolean adult = true;

    public static final String JSON_PROPERTY_ALSO_KNOWN_AS = "also_known_as";
    @jakarta.annotation.Nullable
    private List<String> alsoKnownAs = new ArrayList<>();

    public static final String JSON_PROPERTY_BIOGRAPHY = "biography";
    @jakarta.annotation.Nullable
    private String biography;

    public static final String JSON_PROPERTY_BIRTHDAY = "birthday";
    @jakarta.annotation.Nullable
    private String birthday;

    public static final String JSON_PROPERTY_DEATHDAY = "deathday";
    @jakarta.annotation.Nullable
    private String deathday = null;

    public static final String JSON_PROPERTY_GENDER = "gender";
    @jakarta.annotation.Nullable
    private Integer gender = 0;

    public static final String JSON_PROPERTY_HOMEPAGE = "homepage";
    @jakarta.annotation.Nullable
    private String homepage = null;

    public static final String JSON_PROPERTY_ID = "id";
    @Id
    private Integer id = 0;

    public static final String JSON_PROPERTY_IMDB_ID = "imdb_id";
    @jakarta.annotation.Nullable
    private String imdbId;

    public static final String JSON_PROPERTY_KNOWN_FOR_DEPARTMENT = "known_for_department";
    @jakarta.annotation.Nullable
    private String knownForDepartment;

    public static final String JSON_PROPERTY_NAME = "name";
    @jakarta.annotation.Nullable
    private String name;

    public static final String JSON_PROPERTY_PLACE_OF_BIRTH = "place_of_birth";
    @jakarta.annotation.Nullable
    private String placeOfBirth;

    public static final String JSON_PROPERTY_POPULARITY = "popularity";
    @jakarta.annotation.Nullable
    private BigDecimal popularity = new BigDecimal("0");

    public static final String JSON_PROPERTY_PROFILE_PATH = "profile_path";
    @jakarta.annotation.Nullable
    private String profilePath;

    public PersonDetails() {
    }

    public PersonDetails adult(@jakarta.annotation.Nullable Boolean adult) {
        this.adult = adult;
        return this;
    }

    /**
     * Get adult
     *
     * @return adult
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ADULT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Boolean getAdult() {
        return adult;
    }

    @JsonProperty(JSON_PROPERTY_ADULT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAdult(@jakarta.annotation.Nullable Boolean adult) {
        this.adult = adult;
    }

    public PersonDetails alsoKnownAs(@jakarta.annotation.Nullable List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
        return this;
    }

    public PersonDetails addAlsoKnownAsItem(String alsoKnownAsItem) {
        if (this.alsoKnownAs == null) {
            this.alsoKnownAs = new ArrayList<>();
        }
        this.alsoKnownAs.add(alsoKnownAsItem);
        return this;
    }

    /**
     * Get alsoKnownAs
     *
     * @return alsoKnownAs
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ALSO_KNOWN_AS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    @JsonProperty(JSON_PROPERTY_ALSO_KNOWN_AS)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setAlsoKnownAs(@jakarta.annotation.Nullable List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public PersonDetails biography(@jakarta.annotation.Nullable String biography) {
        this.biography = biography;
        return this;
    }

    /**
     * Get biography
     *
     * @return biography
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_BIOGRAPHY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getBiography() {
        return biography;
    }

    @JsonProperty(JSON_PROPERTY_BIOGRAPHY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBiography(@jakarta.annotation.Nullable String biography) {
        this.biography = biography;
    }

    public PersonDetails birthday(@jakarta.annotation.Nullable String birthday) {
        this.birthday = birthday;
        return this;
    }

    /**
     * Get birthday
     *
     * @return birthday
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_BIRTHDAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getBirthday() {
        return birthday;
    }

    @JsonProperty(JSON_PROPERTY_BIRTHDAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setBirthday(@jakarta.annotation.Nullable String birthday) {
        this.birthday = birthday;
    }

    public PersonDetails deathday(@jakarta.annotation.Nullable String deathday) {
        this.deathday = deathday;
        return this;
    }

    /**
     * Get deathday
     *
     * @return deathday
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_DEATHDAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getDeathday() {
        return deathday;
    }

    @JsonProperty(JSON_PROPERTY_DEATHDAY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setDeathday(@jakarta.annotation.Nullable String deathday) {
        this.deathday = deathday;
    }

    public PersonDetails gender(@jakarta.annotation.Nullable Integer gender) {
        this.gender = gender;
        return this;
    }

    /**
     * Get gender
     *
     * @return gender
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_GENDER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getGender() {
        return gender;
    }

    @JsonProperty(JSON_PROPERTY_GENDER)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setGender(@jakarta.annotation.Nullable Integer gender) {
        this.gender = gender;
    }

    public PersonDetails homepage(@jakarta.annotation.Nullable String homepage) {
        this.homepage = homepage;
        return this;
    }

    /**
     * Get homepage
     *
     * @return homepage
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_HOMEPAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getHomepage() {
        return homepage;
    }

    @JsonProperty(JSON_PROPERTY_HOMEPAGE)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setHomepage(@jakarta.annotation.Nullable String homepage) {
        this.homepage = homepage;
    }

    public PersonDetails id(@jakarta.annotation.Nullable Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public Integer getId() {
        return id;
    }

    @JsonProperty(JSON_PROPERTY_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setId(@jakarta.annotation.Nullable Integer id) {
        this.id = id;
    }

    public PersonDetails imdbId(@jakarta.annotation.Nullable String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    /**
     * Get imdbId
     *
     * @return imdbId
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_IMDB_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty(JSON_PROPERTY_IMDB_ID)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setImdbId(@jakarta.annotation.Nullable String imdbId) {
        this.imdbId = imdbId;
    }

    public PersonDetails knownForDepartment(@jakarta.annotation.Nullable String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
        return this;
    }

    /**
     * Get knownForDepartment
     *
     * @return knownForDepartment
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_KNOWN_FOR_DEPARTMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    @JsonProperty(JSON_PROPERTY_KNOWN_FOR_DEPARTMENT)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setKnownForDepartment(@jakarta.annotation.Nullable String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public PersonDetails name(@jakarta.annotation.Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getName() {
        return name;
    }

    @JsonProperty(JSON_PROPERTY_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setName(@jakarta.annotation.Nullable String name) {
        this.name = name;
    }

    public PersonDetails placeOfBirth(@jakarta.annotation.Nullable String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    /**
     * Get placeOfBirth
     *
     * @return placeOfBirth
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PLACE_OF_BIRTH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    @JsonProperty(JSON_PROPERTY_PLACE_OF_BIRTH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPlaceOfBirth(@jakarta.annotation.Nullable String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public PersonDetails popularity(@jakarta.annotation.Nullable BigDecimal popularity) {
        this.popularity = popularity;
        return this;
    }

    /**
     * Get popularity
     *
     * @return popularity
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_POPULARITY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public BigDecimal getPopularity() {
        return popularity;
    }

    @JsonProperty(JSON_PROPERTY_POPULARITY)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setPopularity(@jakarta.annotation.Nullable BigDecimal popularity) {
        this.popularity = popularity;
    }

    public PersonDetails profilePath(@jakarta.annotation.Nullable String profilePath) {
        this.profilePath = profilePath;
        return this;
    }

    /**
     * Get profilePath
     *
     * @return profilePath
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_PROFILE_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getProfilePath() {
        return profilePath;
    }

    @JsonProperty(JSON_PROPERTY_PROFILE_PATH)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setProfilePath(@jakarta.annotation.Nullable String profilePath) {
        this.profilePath = profilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDetails personDetails = (PersonDetails) o;
        return Objects.equals(this.adult, personDetails.adult) &&
                Objects.equals(this.alsoKnownAs, personDetails.alsoKnownAs) &&
                Objects.equals(this.biography, personDetails.biography) &&
                Objects.equals(this.birthday, personDetails.birthday) &&
                Objects.equals(this.deathday, personDetails.deathday) &&
                Objects.equals(this.gender, personDetails.gender) &&
                Objects.equals(this.homepage, personDetails.homepage) &&
                Objects.equals(this.id, personDetails.id) &&
                Objects.equals(this.imdbId, personDetails.imdbId) &&
                Objects.equals(this.knownForDepartment, personDetails.knownForDepartment) &&
                Objects.equals(this.name, personDetails.name) &&
                Objects.equals(this.placeOfBirth, personDetails.placeOfBirth) &&
                Objects.equals(this.popularity, personDetails.popularity) &&
                Objects.equals(this.profilePath, personDetails.profilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adult, alsoKnownAs, biography, birthday, deathday, gender, homepage, id, imdbId,
                knownForDepartment, name, placeOfBirth, popularity, profilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PersonDetails {\n");
        sb.append("    adult: ").append(toIndentedString(adult)).append("\n");
        sb.append("    alsoKnownAs: ").append(toIndentedString(alsoKnownAs)).append("\n");
        sb.append("    biography: ").append(toIndentedString(biography)).append("\n");
        sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
        sb.append("    deathday: ").append(toIndentedString(deathday)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    homepage: ").append(toIndentedString(homepage)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    imdbId: ").append(toIndentedString(imdbId)).append("\n");
        sb.append("    knownForDepartment: ").append(toIndentedString(knownForDepartment)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    placeOfBirth: ").append(toIndentedString(placeOfBirth)).append("\n");
        sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
        sb.append("    profilePath: ").append(toIndentedString(profilePath)).append("\n");
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
