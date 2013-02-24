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
}
