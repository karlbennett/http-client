package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.quietMimeType;

/**
 * An HTTP {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class ContentType extends Header<MimeType> {

    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * Convert the supplied {@link Header} into an {@code ContentType} instance.
     *
     * @param header the {@code Header} to convert.
     * @return the new {@code ContentType} instance built from the {@code Header}.
     * @throws IllegalArgumentException if the supplied header doesn't have a name of "ContentType" or the value isn't a
     *                                  valid MIME type.
     */
    private static ContentType convert(Header header) {

        if (header instanceof ContentType) {

            return (ContentType) header;
        }

        if (CONTENT_TYPE.equals(header.getName())) {

            return new ContentType(quietMimeType(header.getValue().toString()));
        }

        throw new IllegalArgumentException("An " + ContentType.class.getName() +
                " object can only be created from an \"ContentType\" header.");
    }


    /**
     * Create a new {@code ContentType} header with the supplied {@code MIME} type.
     *
     * @param mimeType the {@code MIME} type to be used for the HTTP {@code Content-Type}.
     */
    public ContentType(MimeType mimeType) {
        super(CONTENT_TYPE, mimeType);
    }

    /**
     * Create a new {@code ContentType} header with the supplied {@code MIME} type components.
     *
     * @param primary the primary part of the {@code MIME} type e.g. "application" from "application/json".
     * @param sub     the sub type of the {@code MIME} type e.g. "json" from "application/json".
     */
    public ContentType(String primary, String sub) {
        super(CONTENT_TYPE, quietMimeType(primary, sub));
    }

    /**
     * Create a new {@code ContentType} header with the supplied {@code MIME} type {@link String}.
     *
     * @param rawData a {@link String} containing a MIME type value.
     */
    public ContentType(String rawData) {
        super(CONTENT_TYPE, quietMimeType(rawData));
    }

    /**
     * {@code ContentType} header conversion constructor, converts a {@code "ContentType"} header into a
     * {@code ContentType} object.
     *
     * @param header the header to convert.
     */
    public ContentType(Header header) {
        super(convert(header));
    }
}
