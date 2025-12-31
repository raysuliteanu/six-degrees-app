package org.kidoni.sixdegrees.tmdb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import org.springframework.data.neo4j.core.schema.Relationship;

@JsonPropertyOrder({
        Person.JSON_PROPERTY_ADULT,
        Person.JSON_PROPERTY_GENDER,
        Person.JSON_PROPERTY_ID,
        Person.JSON_PROPERTY_KNOWN_FOR_DEPARTMENT,
        Person.JSON_PROPERTY_NAME,
        Person.JSON_PROPERTY_ORIGINAL_NAME,
        Person.JSON_PROPERTY_POPULARITY,
        Person.JSON_PROPERTY_PROFILE_PATH,
        Person.JSON_PROPERTY_KNOWN_FOR
})
public class Person {
    public static final String JSON_PROPERTY_ADULT = "adult";
    @jakarta.annotation.Nullable
    private Boolean adult = true;

    public static final String JSON_PROPERTY_GENDER = "gender";
    @jakarta.annotation.Nullable
    private Integer gender = 0;

    public static final String JSON_PROPERTY_ID = "id";
    @jakarta.annotation.Nullable
    private Integer id = 0;

    public static final String JSON_PROPERTY_KNOWN_FOR_DEPARTMENT = "known_for_department";
    @jakarta.annotation.Nullable
    private String knownForDepartment;

    public static final String JSON_PROPERTY_NAME = "name";
    @jakarta.annotation.Nullable
    private String name;

    public static final String JSON_PROPERTY_ORIGINAL_NAME = "original_name";
    @jakarta.annotation.Nullable
    private String originalName;

    public static final String JSON_PROPERTY_POPULARITY = "popularity";
    @jakarta.annotation.Nullable
    private BigDecimal popularity = new BigDecimal("0");

    public static final String JSON_PROPERTY_PROFILE_PATH = "profile_path";
    @jakarta.annotation.Nullable
    private String profilePath;

    public static final String JSON_PROPERTY_KNOWN_FOR = "known_for";
    @jakarta.annotation.Nullable
    @Relationship(type = "KNOWN_FOR", direction = Relationship.Direction.OUTGOING)
    private List<KnownFor> knownFor = new ArrayList<>();

    public Person() {
    }

    public Person adult(@jakarta.annotation.Nullable Boolean adult) {
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

    public Person gender(@jakarta.annotation.Nullable Integer gender) {
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

    public Person id(@jakarta.annotation.Nullable Integer id) {
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

    public Person knownForDepartment(@jakarta.annotation.Nullable String knownForDepartment) {
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

    public Person name(@jakarta.annotation.Nullable String name) {
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

    public Person originalName(@jakarta.annotation.Nullable String originalName) {
        this.originalName = originalName;
        return this;
    }

    /**
     * Get originalName
     *
     * @return originalName
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_ORIGINAL_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public String getOriginalName() {
        return originalName;
    }

    @JsonProperty(JSON_PROPERTY_ORIGINAL_NAME)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setOriginalName(@jakarta.annotation.Nullable String originalName) {
        this.originalName = originalName;
    }

    public Person popularity(@jakarta.annotation.Nullable BigDecimal popularity) {
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

    public Person profilePath(@jakarta.annotation.Nullable String profilePath) {
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

    public Person knownFor(@jakarta.annotation.Nullable List<KnownFor> knownFor) {
        this.knownFor = knownFor;
        return this;
    }

    public Person addKnownForItem(KnownFor knownForItem) {
        if (this.knownFor == null) {
            this.knownFor = new ArrayList<>();
        }
        this.knownFor.add(knownForItem);
        return this;
    }

    /**
     * Get knownFor
     *
     * @return knownFor
     */
    @jakarta.annotation.Nullable
    @JsonProperty(JSON_PROPERTY_KNOWN_FOR)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    @JsonProperty(JSON_PROPERTY_KNOWN_FOR)
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setKnownFor(@jakarta.annotation.Nullable List<KnownFor> knownFor) {
        this.knownFor = knownFor;
    }

    /**
     * Return true if this search_person_200_response_results_inner object is equal
     * to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person searchPerson200ResponseResultsInner = (Person) o;
        return Objects.equals(this.adult, searchPerson200ResponseResultsInner.adult) &&
                Objects.equals(this.gender, searchPerson200ResponseResultsInner.gender) &&
                Objects.equals(this.id, searchPerson200ResponseResultsInner.id) &&
                Objects.equals(this.knownForDepartment, searchPerson200ResponseResultsInner.knownForDepartment) &&
                Objects.equals(this.name, searchPerson200ResponseResultsInner.name) &&
                Objects.equals(this.originalName, searchPerson200ResponseResultsInner.originalName) &&
                Objects.equals(this.popularity, searchPerson200ResponseResultsInner.popularity) &&
                Objects.equals(this.profilePath, searchPerson200ResponseResultsInner.profilePath) &&
                Objects.equals(this.knownFor, searchPerson200ResponseResultsInner.knownFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adult, gender, id, knownForDepartment, name, originalName, popularity, profilePath,
                knownFor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Person {\n");
        sb.append("    adult: ").append(toIndentedString(adult)).append("\n");
        sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    knownForDepartment: ").append(toIndentedString(knownForDepartment)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    originalName: ").append(toIndentedString(originalName)).append("\n");
        sb.append("    popularity: ").append(toIndentedString(popularity)).append("\n");
        sb.append("    profilePath: ").append(toIndentedString(profilePath)).append("\n");
        sb.append("    knownFor: ").append(toIndentedString(knownFor)).append("\n");
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
        } else {
            // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
            prefix = prefix + "[";
            suffix = "]";
            containerSuffix = "]";
            containerPrefix = "[";
        }

        StringJoiner joiner = new StringJoiner("&");

        // add `adult` to the URL query string
        if (getAdult() != null) {
            joiner.add(String.format("%sadult%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getAdult()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `gender` to the URL query string
        if (getGender() != null) {
            joiner.add(String.format("%sgender%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getGender()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `id` to the URL query string
        if (getId() != null) {
            joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getId()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `known_for_department` to the URL query string
        if (getKnownForDepartment() != null) {
            joiner.add(String.format("%sknown_for_department%s=%s", prefix, suffix,
                    URLEncoder.encode(ModelUtil.valueToString(getKnownForDepartment()), StandardCharsets.UTF_8)
                            .replaceAll("\\+", "%20")));
        }

        // add `name` to the URL query string
        if (getName() != null) {
            joiner.add(String.format("%sname%s=%s", prefix, suffix, URLEncoder
                    .encode(ModelUtil.valueToString(getName()), StandardCharsets.UTF_8).replaceAll("\\+", "%20")));
        }

        // add `original_name` to the URL query string
        if (getOriginalName() != null) {
            joiner.add(String.format("%soriginal_name%s=%s", prefix, suffix,
                    URLEncoder.encode(ModelUtil.valueToString(getOriginalName()), StandardCharsets.UTF_8)
                            .replaceAll("\\+", "%20")));
        }

        // add `popularity` to the URL query string
        if (getPopularity() != null) {
            joiner.add(String.format("%spopularity%s=%s", prefix, suffix,
                    URLEncoder.encode(ModelUtil.valueToString(getPopularity()), StandardCharsets.UTF_8)
                            .replaceAll("\\+", "%20")));
        }

        // add `profile_path` to the URL query string
        if (getProfilePath() != null) {
            joiner.add(String.format("%sprofile_path%s=%s", prefix, suffix,
                    URLEncoder.encode(ModelUtil.valueToString(getProfilePath()), StandardCharsets.UTF_8)
                            .replaceAll("\\+", "%20")));
        }

        // add `known_for` to the URL query string
        if (getKnownFor() != null) {
            for (int i = 0; i < getKnownFor().size(); i++) {
                if (getKnownFor().get(i) != null) {
                    joiner.add(getKnownFor().get(i).toUrlQueryString(String.format("%sknown_for%s%s", prefix, suffix,
                            "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
                }
            }
        }

        return joiner.toString();
    }
}
