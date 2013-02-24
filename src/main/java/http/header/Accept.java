package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.quietMimeType;

/**
 * An HTTP {@code Accept} header.
 *
 * @author Karl Bennett
 */
public class Accept extends Header<MimeType> {

    public static final String ACCEPT = "Accept";

    /**
     * Create a new {@code Accept} header with the supplied {@code MIME} type.
     *
     * @param mimeType the {@code MIME} type to be used for the HTTP {@code Content-Type}.
     */
    public Accept(MimeType mimeType) {
        super(ACCEPT, mimeType);
    }

    /**
     * Create a new {@code Accept} header with the supplied {@code MIME} type components.
     *
     * @param primary the primary part of the {@code MIME} type e.g. "application" from "application/json".
     * @param sub     the sub type of the {@code MIME} type e.g. "json" from "application/json".
     */
    public Accept(String primary, String sub) {
        super(ACCEPT, quietMimeType(primary, sub));
    }
}
