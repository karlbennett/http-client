package http.header.util;

import http.header.AbstractMimeTypeHeader;
import http.header.Header;

import javax.activation.MimeType;

import static http.util.MimeTypes.quietMimeType;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMimeTypeHeaderConversion<O extends Header, D extends AbstractMimeTypeHeader>
        extends AbstractHeaderConversion<D, O> {

    /**
     * Create a new {@code AbstractMimeTypeHeaderConversion} that can be used to convert the supplied origin
     * {@code Header} type into the required destination MIME type {@code Header}.
     *
     * @param type   the required destination header type.
     * @param header the original header instance.
     * @param name   the name value of the destination header type should have.
     */
    protected AbstractMimeTypeHeaderConversion(Class<D> type, O header, String name) {
        super(type, header, name);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public D convert(O header) {

        Object value = header.getValue();

        if (value instanceof MimeType) {

            return createWithMimeType((MimeType) value);
        }

        return createWithMimeType(quietMimeType(value.toString()));
    }


    /**
     * Implement this method to create the required MIME type header.
     *
     * @param mimeType the {@code MimeType} vale to use to create the MIME type header.
     *
     * @return the new MIME type header.
     */
    protected abstract D createWithMimeType(MimeType mimeType);
}
