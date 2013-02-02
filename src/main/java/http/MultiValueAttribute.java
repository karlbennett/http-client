package http;


import java.util.Collections;
import java.util.List;

import static http.util.Assert.assertNotEmpty;

/**
 *
 * Represents a generic attribute with a name and a single or multiple values. The type of the value can be defined on
 * instantiation.
 *
 * @author Karl Bennett
 */
public class MultiValueAttribute<T> extends Attribute<T> {

    private static <T> T getFirst(List<T> values) {

        assertNotEmpty("values", values);

        return values.get(0);
    }

    private final List<T> values;

    /**
     * Create a {@code MultiValueAttribute} with a name and multiple values.
     *
     * @param name  the name of the attribute.
     * @param values the values for the attribute.
     * @throws IllegalArgumentException
     *          if the {@code Attribute}'s name is empty or the values are null.
     */
    public MultiValueAttribute(String name, List<T> values) {
        super(name, getFirst(values));

        this.values = values;
    }


    /**
     * Create a {@code MultiValueAttribute} with a name and a single value.
     *
     * @param name the name of the attribute.
     * @param value the single value for the attribute.
     * @throws IllegalArgumentException if the {@code Attribute}'s name is empty or the value is null.
     */
    public MultiValueAttribute(String name, T value) {
        super(name, value);

        this.values = Collections.singletonList(value);
    }


    /**
     * @return the attributes values.
     */
    public List<T> getValues() {
        return values;
    }
}
