package http.util;

import java.util.Map;

import static http.util.Asserts.assertNotNull;

/**
 * Converter for converting one type into another.
 *
 * @author Karl Bennett
 */
public class Converter {

    private final Map<Class, Conversion> conversions;


    /**
     * Create a new {@code Converter} with the supplied conversion.
     *
     * @param conversions the conversions that will be used internally to carry out the object conversions.
     */
    public Converter(Map<Class, Conversion> conversions) {

        assertNotNull("conversions", conversions);

        this.conversions = conversions;
    }


    /**
     * Convert the supplied object into the requested type.
     *
     * @param type   the type of object to convert to.
     * @param object the object to convert.
     * @param <T>    the generic type of the object to convert to.
     * @param <O>    the generic type of the object to convert.
     * @return the converted attribute.
     * @throws IllegalArgumentException if there is no converter for the requested type.
     */
    public <T, O> T convert(Class type, O object) {

        Conversion<T, O> conversion = conversions.get(type);

        assertNotNull(type.getName(), conversion);

        return conversion.convert(object);
    }
}
