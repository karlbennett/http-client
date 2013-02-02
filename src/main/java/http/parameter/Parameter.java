package http.parameter;

import http.MultiValueAttribute;

import java.util.List;

/**
 * Represents an HTTP parameter which can have a name and a single or multiple values. The type of the value can be
 * defined on instantiation.
 *
 * @author Karl Bennett
 */
public class Parameter<T> extends MultiValueAttribute<T> {

    /**
     * Create an {@code Parameter} with a name and multiple values.
     *
     * @param name the name of the attribute.
     * @param values the values for the attribute.
     */
    public Parameter(String name, List<T> values) {
        super(name, values);
    }

    /**
     * Create an {@code Parameter} with a name and a single value.
     *
     * @param name the name of the attribute.
     * @param value the single value for the attribute.
     */
    public Parameter(String name, T value) {
        super(name, value);
    }
}
