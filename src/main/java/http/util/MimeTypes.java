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


    private MimeTypes() {
    }


    /**
     * Instantiate a new {@link MimeType} object without having to explicitly catch the checked
     * {@link MimeTypeParseException}.
     *
     * @param rawdata the MIME type string.
     * @return the new {@code MimeType} object if it can be created.
     * @throws IllegalStateException if the {@code MimeType} object creatione fails.
     */
    public static MimeType quietMimeType(String rawdata) {

        try {

            return new MimeType(rawdata);

        } catch (MimeTypeParseException e) {

            throw new IllegalStateException(e);
        }
    }

    /**
     * Instantiate a new {@link MimeType} object without having to explicitly catch the checked
     * {@link MimeTypeParseException}.
     *
     * @param primary the primary MIME type.
     * @param sub     the MIME sub-type.
     * @return the new {@code MimeType} object if it can be created.
     * @throws IllegalStateException if the {@code MimeType} object creatione fails.
     */
    public static MimeType quietMimeType(String primary, String sub) {

        try {

            return new MimeType(primary, sub);

        } catch (MimeTypeParseException e) {

            throw new IllegalStateException(e);
        }
    }
}
