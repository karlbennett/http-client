package http.util;

import static http.util.Checks.isNotNull;
import static http.util.Asserts.assertNotNull;

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
    private T conversion;


    /**
     * Create a new {@code AbstractConversion} that can be used to convert the supplied origin type into the required
     * destination type.
     *
     * @param type   the required destination object type.
     * @param object the original object instance.
     * @throws IllegalArgumentException if either the {@code type} or {@code object} arguments are {@code null}.
     */
    protected AbstractConversion(Class<T> type, O object) {

        assertNotNull("type", type);
        assertNotNull("object", object);

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

        // Check to see if the conversion has already been run and if it has return the converted value.
        if (isNotNull(conversion)) return conversion;

        // Otherwise convert, cache, and return the converted value.
        if (type.isAssignableFrom(object.getClass())) {

            return (conversion = (T) object);
        }

        return (conversion = convert(object));
    }
}
