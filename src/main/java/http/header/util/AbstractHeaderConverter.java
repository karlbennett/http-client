package http.header.util;

import http.header.Header;

/**
 * Abstract class that provides the common logic required for converting one {@link Header} type into another.
 *
 * @param <O> the origin {@link Header} type that will be converted.
 * @param <D> the destination {@code Header} type that will be converted to.
 * @author Karl Bennett
 */
public abstract class AbstractHeaderConverter<O extends Header, D extends Header> {

    private final Class<D> type;
    private final O header;


    /**
     * Create a new {@code AbstractHeaderConverter} that can be used to convert the supplied origin {@code Header} type into the
     * required destination {@code Header} type.
     *
     * @param type   the required destination header type.
     * @param header the original header instance.
     * @param name   the name value of the destination header type should have.
     */
    protected AbstractHeaderConverter(Class<D> type, O header, String name) {

        if (!name.equals(header.getName())) {

            throw new IllegalArgumentException("An " + type.getName() +
                    " object can only be created from an \"" + name + "\" header.");
        }

        this.type = type;
        this.header = header;
    }


    /**
     * Convert the origin {@code Header} into the destination {@code Header}.
     *
     * @return the converted {@code Header}.
     */
    @SuppressWarnings("unchecked")
    public D convert() {

        if (type.isAssignableFrom(header.getClass())) {

            return (D) header;
        }

        return create(header);
    }


    /**
     * This method must be implemented to create the requested {@code Header} type.
     *
     * @param header the original header instance.
     * @return the converted {@code Header}.
     */
    protected abstract D create(O header);
}
