package http.header.util;

import http.header.AbstractMimeTypeHeader;
import http.header.Header;

import javax.activation.MimeType;

import static http.util.MimeTypes.quietMimeType;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMimeTypeHeaderConverter<O extends Header, D extends AbstractMimeTypeHeader>
        extends AbstractHeaderConverter<O, D> {

    /**
     * Create a new {@code AbstractMimeTypeHeaderConverter} that can be used to convert the supplied origin
     * {@code Header} type into the required destination MIME type {@code Header}.
     *
     * @param type   the required destination header type.
     * @param header the original header instance.
     * @param name   the name value of the destination header type should have.
     */
    protected AbstractMimeTypeHeaderConverter(Class<D> type, O header, String name) {
        super(type, header, name);
    }


    @Override
    protected D create(O header) {

        Object value = header.getValue();

        if (value instanceof MimeType) {

            return createWithMimeType((MimeType) value);
        }

        return createWithMimeType(quietMimeType(value.toString()));
    }


    protected abstract D createWithMimeType(MimeType mimeType);
}
