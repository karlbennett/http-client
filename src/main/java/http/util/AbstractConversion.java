package http.util;

/**
 * Abstract class that provides the common logic required for converting one object type into another.
 *
 * @param <T> the type to convert into.
 * @param <O> the origin type that will be converted.
 *
 * @author Karl Bennett
 */
public abstract class AbstractConversion<T, O> implements Conversion<T, O> {

    private final Class<T> type;
    private final O object;


    /**
     * Create a new {@code AbstractConversion} that can be used to convert the supplied origin type into the required
     * destination type.
     *
     * @param type   the required destination object type.
     * @param object the original object instance.
     */
    protected AbstractConversion(Class<T> type, O object) {

        this.type = type;
        this.object = object;
    }


    /**
     * Convert the contained object into the required type.
     *
     * @return the required object type.
     */
    @SuppressWarnings("unchecked")
    public T convert() {

        if (type.isAssignableFrom(object.getClass())) {

            return (T) object;
        }

        return convert(object);
    }
}
