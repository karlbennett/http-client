package http.header;

import http.Header;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

/**
 * An HTTP {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class ContentType extends Header<MimeType> {

    public static final String NAME = "Content-Type";

    /**
     * Convert a {@link String} into a {@link MimeType} object.
     *
     * @param mimeType the {@code String} to convert.
     * @return the {@code MimeType} object created from the supplied {@code String}.
     */
    public static MimeType stringToMimeType(String mimeType) {

        try {

           return new MimeType(mimeType);

        } catch (MimeTypeParseException e) {

            throw new IllegalStateException("Could not create " + mimeType + " MIME type.");
        }
    }

    /**
     * Create a new {@code ContentType} header with the supplied MIME type.
     *
     * @param mimeType the MIME type to be used for the HTTP {@code Content-Type}.
     */
    public ContentType(MimeType mimeType) {
        super(NAME, mimeType);
    }
}
