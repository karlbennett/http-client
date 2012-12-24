package http.header;

import http.Header;

import javax.activation.MimeType;

/**
 * An HTTP {@code Content-Type} header.
 *
 * @author Karl Bennett
 */
public class ContentType extends Header<MimeType> {

    public static final String NAME = "Content-Type";

    /**
     * Create a new {@code ContentType} header with the supplied MIME type.
     *
     * @param mimeType the MIME type to be used for the HTTP {@code Content-Type}.
     */
    public ContentType(MimeType mimeType) {
        super(NAME, mimeType);
    }
}
