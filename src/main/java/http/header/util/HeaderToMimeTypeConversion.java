package http.header.util;

import http.header.Header;

import javax.activation.MimeType;

import static http.util.MimeTypes.quietMimeType;

/**
 * Converts a headers value that has the required name into a {@link MimeType} value.
 *
 * @author Karl Bennett
 */
public class HeaderToMimeTypeConversion<O extends Header> extends AbstractHeaderConversion<MimeType, O> {

    /**
     * Create a new {@code HeaderToMimeTypeConversion} that can be used to convert the supplied origin
     * {@code Header}s value type into a {@code MimeType}.
     *
     * @param header the original header instance.
     * @param name   the name value of the destination header type should have.
     */
    public HeaderToMimeTypeConversion(O header, String name) {
        super(MimeType.class, header, name);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public MimeType convert(O header) {

        Object value = header.getValue();

        if (value instanceof MimeType) {

            return (MimeType) value;
        }

        return quietMimeType(value.toString());
    }
}
