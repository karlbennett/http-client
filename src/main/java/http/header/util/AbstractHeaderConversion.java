package http.header.util;

import http.header.Header;
import http.util.AbstractConversion;

/**
 * Abstract class that provides the common logic required for converting one {@link Header} type into another.
 *
 * @param <O> the origin {@link Header} type that will be converted.
 * @param <D> the destination {@code Header} type that will be converted to.
 * @author Karl Bennett
 */
public abstract class AbstractHeaderConversion<D extends Header, O extends Header> extends AbstractConversion<D, O> {

    /**
     * Create a new {@code AbstractHeaderConversion} that can be used to convert the supplied origin {@code Header} type
     * into the required destination {@code Header} type.
     *
     * @param type   the required destination header type.
     * @param header the original header instance.
     * @param name   the name value of the destination header type should have.
     */
    protected AbstractHeaderConversion(Class<D> type, O header, String name) {
        super(type, header);

        if (!name.equals(header.getName())) {

            throw new IllegalArgumentException("An " + type.getName() +
                    " object can only be created from an \"" + name + "\" header.");
        }
    }
}
