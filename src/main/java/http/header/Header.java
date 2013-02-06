package http.header;

import http.attribute.MultiValueAttribute;

import java.util.Collection;
import java.util.List;

/**
 * Represents an HTTP header which can have a name and a single or multiple values. The type of the value can be defined
 * on instantiation.
 *
 * @author Karl Bennett
 */
public class Header<T> extends MultiValueAttribute<T> {

    public static <T> String toString(Collection<Header<T>> headers) {

        return toString(headers, "\n");
    }

    public static Collection<Header<String>> parse(String headers) {

        return parse(headers, ": ", "\n", new Creator<Header<String>>() {

            @Override
            public Header<String> create(String name, String value) {

                return new Header<String>(name, value);
            }
        });
    }


    /**
     * Create an {@code Header} with a name and multiple values.
     *
     * @param name   the name of the attribute.
     * @param values the values for the attribute.
     */
    public Header(String name, List<T> values) {
        super(name, values);
    }

    /**
     * Create an {@code Header} with a name and a single value.
     *
     * @param name  the name of the attribute.
     * @param value the single value for the attribute.
     */
    public Header(String name, T value) {
        super(name, value);
    }


    @Override
    public String toString() {

        return super.toString(": ", "\n");
    }
}