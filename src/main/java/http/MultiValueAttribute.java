package http;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static http.util.Assert.assertNotNull;
import static http.util.Checks.isEmpty;

/**
 *
 * Represents a generic attribute with a name and a single or multiple values. The type of the value can be defined on
 * instantiation.
 *
 * @author Karl Bennett
 */
public class MultiValueAttribute<T> extends Attribute<T> {

    private static <T> T getFirst(List<T> values) {

        assertNotNull("values", values);

        return isEmpty(values) ? null : values.get(0);
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

        this.values = new ArrayList<>();
        this.values.add(value);
    }


    /**
     * @return the attributes values.
     */
    public List<T> getValues() {

        return Collections.unmodifiableList(values);
    }

    /**
     * Add an value to the {@code MultiValueAttribute}.
     *
     * @param value the to add.
     * @return the value that was added.
     */
    public T addValue(T value) {

        values.add(value);

        return value;
    }

    /**
     * Add a list of values to the {@code MultiValueAttribute}.
     *
     * @param values the to add.
     * @return the values that were added.
     */
    public List<T> addAllValues(List<T> values) {

        this.values.addAll(values);

        return values;
    }
}
