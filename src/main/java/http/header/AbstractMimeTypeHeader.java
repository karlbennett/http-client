package http.header;

import javax.activation.MimeType;

import static http.util.MimeTypes.quietMimeType;

/**
 * An abstract class that contains the common logic for creating {@link Header}s with {@link MimeType} values.
 *
 * @author Karl Bennett
 */
public class AbstractMimeTypeHeader extends Header<MimeType> {

    /**
     * Create a new {@code AbstractMimeTypeHeader} header with the supplied {@code name} and {@code MIME} type.
     *
     * @param name     the name of the header.
     * @param mimeType the {@code MIME} type to be used for the HTTP {@code Content-Type}.
     */
    public AbstractMimeTypeHeader(String name, MimeType mimeType) {
        super(name, mimeType);
    }

    /**
     * {@code AbstractMimeTypeHeader} copy constructor.
     *
     * @param header the {@code Accept} instance to copy.
     */
    public AbstractMimeTypeHeader(Header<MimeType> header) {
        super(header);
    }

    /**
     * Create a new {@code AbstractMimeTypeHeader} header with the supplied supplied {@code name} and {@code MIME} type
     * components.
     *
     * @param name    the name of the header.
     * @param primary the primary part of the {@code MIME} type e.g. "application" from "application/json".
     * @param sub     the sub type of the {@code MIME} type e.g. "json" from "application/json".
     */
    public AbstractMimeTypeHeader(String name, String primary, String sub) {
        super(name, quietMimeType(primary, sub));
    }

    /**
     * Create a new {@code AbstractMimeTypeHeader} header with the supplied supplied {@code name} and {@code MIME} type
     * {@link String}.
     *
     * @param name    the name of the header.
     * @param rawData a {@link String} containing a MIME type value.
     */
    public AbstractMimeTypeHeader(String name, String rawData) {
        super(name, quietMimeType(rawData));
    }
}
