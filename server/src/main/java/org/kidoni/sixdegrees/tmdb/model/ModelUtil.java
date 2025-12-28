package org.kidoni.sixdegrees.tmdb.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Minimal utility stub for OpenAPI-generated models.
 * <p>
 * The OpenAPI Generator creates model classes that use this utility
 * for URL query parameter serialization. This stub provides the minimal
 * implementation needed by the generated code.
 * <p>
 * Note: Only valueToString() is actually used (3008+ times across models).
 * We don't generate API clients - those are manually implemented.
 */
public abstract class ModelUtil {
    /**
     * Convert the given value to a string suitable for use as a URL query parameter value.
     * Used by generated model classes for serializing fields to query strings.
     *
     * @param value the value to convert
     * @return string representation suitable for URL encoding, or empty string if null
     */
    public static String valueToString(Object value) {
        return switch (value) {
            case null -> "";
            case OffsetDateTime offsetDateTime -> offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            case LocalDate localDate -> localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            default -> value.toString();
        };
    }
}
