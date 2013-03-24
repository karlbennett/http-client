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
     * Convert the supplied {@code Header} into an {@code Accept} instance.
     *
     * @param header the {@code Header} to convert.
     * @return the new {@code Accept} instance built from the {@code Header}.
     * @throws IllegalArgumentException if the supplied header doesn't have a name of "Accept" or the value isn't a
     *                                  valid MIME type.
     */
    private static Accept convert(Header header) {

        if (ACCEPT.equals(header.getName())) {

            return new Accept(quietMimeType(header.getValue().toString()));
        }

        throw new IllegalArgumentException("An " + Accept.class.getName() +
                " object can only be created from an \"Accept\" header.");
    }


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
        this(convert(header));
    }
}
