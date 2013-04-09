package http.util;

/**
 * This interface should be implemented to convert the supplied object into the generically set type.
 *
 * @param <T> the type to convert into.
 * @param <O> the origin type that will be converted.
 */
public interface Conversion<T, O> {

    /**
     * Convert the supplied object into the returned type.
     *
     * @param object the object to convert.
     * @return the converted object.
     */
    T convert(O object);
}
