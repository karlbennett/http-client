package http;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static http.util.Assert.assertNotNull;
import static http.util.Checks.isEmpty;
import static http.util.Checks.isNotEmpty;

/**
 *
 * Represents a generic attribute with a name and a single or multiple values. The type of the value can be defined on
 * instantiation.
 *
 * @author Karl Bennett
 */
public class MultiValueAttribute<T> extends Attribute<T> {

    /**
     * Filter any empty values out of the supplied {@link List}.
     *
     * @param list the {@code List} to filter.
     * @param <T> the type of elements that the {@code List} holds.
     * @return the filtered list.
     */
    private static <T> List<T> filterEmptyElements(List<T> list) {

        List<T> filteredList = new ArrayList<>();

        for (T element : list) {

            if (isNotEmpty(element)) filteredList.add(element);
        }

        return filteredList;
    }


    private final List<T> values;


    /**
     * Create a {@code MultiValueAttribute} with a name and multiple values.
     *
     * @param name  the name of the attribute.
     * @param values the values for the attribute.
     * @throws IllegalArgumentException if the {@code Attribute}'s name is empty or the values are null.
     */
    public MultiValueAttribute(String name, List<T> values) {
        super(name, null);

        assertNotNull("values", values);

        // Cast the supplied list to an ArrayList so that it is definitely mutable.
        // We do this so that the addValue(T) and addAllValues(List<T>) methods definitely work.
        this.values = new ArrayList<>(values);
    }


    /**
     * Create a {@code MultiValueAttribute} with a name and a single value. If the supplied value is {@code null} or
     * empty the {@code MultiValueAttribute} will be constructed without a value.
     *
     * @param name the name of the attribute.
     * @param value the single value for the attribute.
     * @throws IllegalArgumentException if the {@code MultiValueAttribute}'s name is empty or the value is null.
     */
    public MultiValueAttribute(String name, T value) {
        super(name, null);

        this.values = new ArrayList<>();
        if (isNotEmpty(value)) this.values.add(value);
    }


    /**
     * Get the first value in the {@code MultiValueAttribute}s value list, unless the list is empty then {@code null} is
     * returned.
     *
     * @return the first value.
     */
    @Override
    public T getValue() {

        return isEmpty(values) ? null : values.get(0);
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

        if (isNotEmpty(value)) values.add(value);

        return value;
    }

    /**
     * Add a list of values to the {@code MultiValueAttribute}.
     *
     * @param values the to add.
     * @return the values that were added.
     * @throws NullPointerException if the supplied {@code values} list is null.
     */
    public List<T> addAllValues(List<T> values) {

        this.values.addAll(filterEmptyElements(values));

        return values;
    }
}
