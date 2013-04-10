package http.header.util;

import http.header.Header;
import http.util.AbstractConversion;
import static http.util.Asserts.assertNotNull;

/**
 * Abstract class that provides the common logic required for converting a specific kind of header e.g. "Accept",
 * "Content-Type", "Set-Cookie".
 *
 * @param <O> the origin {@link Header} type that will be converted.
 * @param <D> the destination type that the headers value will be converted to.
 * @author Karl Bennett
 */
public abstract class AbstractHeaderConversion<D, O extends Header> extends AbstractConversion<D, O> {

    /**
     * Create a new {@code AbstractHeaderConversion} that can be used to convert the supplied origin {@code Header} type
     * into the required destination {@code Header} type.
     *
     * @param type   the required destination header type.
     * @param header the original header instance.
     * @param name   the name that the supplied header should have.
     * @throws IllegalArgumentException if the supplied header does not have the required name.
     */
    protected AbstractHeaderConversion(Class<D> type, O header, String name) {
        super(type, header);

        assertNotNull("name", name);

        if (!name.equals(header.getName())) {

            throw new IllegalArgumentException("An " + type.getName() +
                    " object can only be created from an \"" + name + "\" header.");
        }
    }
}
