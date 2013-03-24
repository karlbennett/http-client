package http.util;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

/**
 * Utility class that contains convenience method for working with the {@link MimeType} class.
 *
 * @author Karl Bennett
 */
public final class MimeTypes {

    /**
     * A comparable {@link MimeType} that can also be used within a {@link java.util.Map} or {@link java.util.Set}.
     */
    public static class ComparableMimeType extends MimeType implements Comparable<MimeType> {

        public ComparableMimeType(String rawdata) throws MimeTypeParseException {
            super(rawdata);
        }

        public ComparableMimeType(String primary, String sub) throws MimeTypeParseException {
            super(primary, sub);
        }

        @Override
        public int compareTo(MimeType that) {

            // First compare the primary type.
            int primaryCompare = getPrimaryType().compareTo(that.getPrimaryType());

            // If the primary types are the same then we need to rely on the sub-type comparison.
            if (0 == primaryCompare) return getSubType().compareTo(that.getSubType());

            // Otherwise the primary comparison is good enough.
            return primaryCompare;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            ComparableMimeType that = (ComparableMimeType) object;

            return 0 == this.compareTo(that);
        }

        @Override
        public int hashCode() {

            return getPrimaryType().hashCode() + getSubType().hashCode();
        }
    }

    /**
     * Common primary mime types.
     */
    public static final String APPLICATION = "application";
    public static final String MULTIPART = "multipart";

    /**
     * Common mime sub-types.
     */
    public static final String X_WWW_FORM_URL_ENCODED = "x-www-form-urlencoded";
    public static final String JSON = "json";
    public static final String FORM_DATA = "form-data";
    public static final String XML = "xml";

    /**
     * The {@link MimeType} object for {@code JSON} content.
     */
    public static final MimeType APPLICATION_JSON = quietMimeType(APPLICATION, JSON);
    /**
     * The {@link MimeType} object for {@code XML} content.
     */
    public static final MimeType APPLICATION_XML = quietMimeType(APPLICATION, XML);
    /**
     * The {@link MimeType} object for Form {@code URL} encoded content.
     */
    public static final MimeType APPLICATION_X_WWW_FORM_URL_ENCODED = quietMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED);
    /**
     * The {@link javax.activation.MimeType} object for Form data encoded content.
     */
    public static final MimeType MULTIPART_FORM_DATA = quietMimeType(MULTIPART, FORM_DATA);


    private MimeTypes() {
    }


    /**
     * Instantiate a new {@link MimeType} object without having to explicitly catch the checked
     * {@link MimeTypeParseException}.
     *
     * @param rawdata the MIME type string.
     * @return the new {@code ComparableMimeType} object if it can be created.
     * @throws IllegalStateException if the {@code MimeType} object creation fails.
     */
    public static MimeType quietMimeType(String rawdata) {

        try {

            return new ComparableMimeType(rawdata);

        } catch (MimeTypeParseException e) {

            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Instantiate a new {@link MimeType} object without having to explicitly catch the checked
     * {@link MimeTypeParseException}.
     *
     * @param primary the primary MIME type.
     * @param sub     the MIME sub-type.
     * @return the new {@code ComparableMimeType} object if it can be created.
     * @throws IllegalStateException if the {@code MimeType} object creation fails.
     */
    public static MimeType quietMimeType(String primary, String sub) {

        try {

            return new ComparableMimeType(primary, sub);

        } catch (MimeTypeParseException e) {

            throw new IllegalArgumentException(e);
        }
    }
}
