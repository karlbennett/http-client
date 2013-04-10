package http.header;

import http.header.util.HeaderToMimeTypeConversion;

import javax.activation.MimeType;

/**
 * An HTTP {@code Accept} header.
 *
 * @author Karl Bennett
 */
public class Accept extends AbstractMimeTypeHeader {

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
        super(ACCEPT, primary, sub);
    }

    /**
     * Create a new {@code Accept} header with the supplied {@code MIME} type {@link String}.
     *
     * @param rawData a {@link String} containing a MIME type value.
     */
    public Accept(String rawData) {
        super(ACCEPT, rawData);
    }

    /**
     * {@code Accept} copy constructor.
     *
     * @param accept the {@code Accept} instance to copy.
     */
    public Accept(Accept accept) {
        super(accept);
    }

    /**
     * {@code Accept} header conversion constructor, converts an {@code "Accept"} header into an {@code Accept} object.
     *
     * @param header the header to convert.
     */
    public Accept(Header header) {

        this(new HeaderToMimeTypeConversion<Header>(header, ACCEPT).convert());
    }
}
