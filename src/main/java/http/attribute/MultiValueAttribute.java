package http.attribute;



import java.util.*;

import static http.util.Asserts.assertNotNull;
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
     * Concatenate the {@link #toString()} values of a {@link Collection} of {@code MultiValueAttribute}. Each value
     * will be delimited by the supplied {@code delimiter} {@link String}.
     *
     * @param attributes the attributes that will have their string representations concatenated.
     * @param delimiter the delimiter that will be placed between each attribute string value.
     * @return the concatenated parameter string.
     */
    public static <T, A extends MultiValueAttribute<T>> String toString(Collection<A> attributes, String delimiter) {

        if (isEmpty(attributes)) return "";

        StringBuilder toStringHolder = new StringBuilder();

        for (A attribute : attributes) {

            toStringHolder.append(attribute).append(delimiter);
        }

        // Remove delimiter that would have been appended to the end of the string.
        toStringHolder.replace(toStringHolder.length() - delimiter.length(), toStringHolder.length(), "");

        return toStringHolder.toString();
    }

    public static interface Creator<A extends MultiValueAttribute<String>> {

        public A create(String name, String value);
    }

    /**
     * Parse a {@link String} containing any number of name/value pairs into a collection of
     * {@link MultiValueAttribute}s. The names and values must be delimited by the supplied {@code operator}
     * {@link String} and each name/value pair must be delimited by the supplied {@code delimiter} {@code String}.
     *
     * Any name value pairs that share the same name will be stored in the same {@code MultiValueAttribute} instance.
     *
     * A {@link Creator} must also be implemented with a means of creating the {@code MultiValueAttribute} or a
     * subclass.
     *
     * Example:
     * <code>
     *     List<MultiValueAttribute<String>> parameters = new ArrayList(
     *         MultiValueAttribute.parse("nameOne=valueOne&nameOne=valueTwo&nameThree=valueThree", "=", "&",
     *             new Creator<MultiValueAttribute<String>>() {
     *
     *                 public MultiValueAttribute<String> create(String name, String value) {
     *
     *                     return new MultiValueAttribute<String>(name, value);
     *                 }
     *             })
     *     );
     *     parameters.size(); // 2
     *     parameters.get(0).getValues(); // ["valueOne", "valueTwo"]
     *     parameters.get(1).getValues(); // ["valueThree"]
     * </code>
     *
     * @param attributeString the string to parse.
     * @return the
     */
    public static <A extends MultiValueAttribute<String>> Collection<A> parse(String attributeString, String operator,
                                                                              String delimiter, Creator<A> creator) {

        if (isEmpty(attributeString)) return Collections.emptySet();

        Map<String, A> attributes = new HashMap<>();

        String[] attributeParts;
        String name, value;
        for (String attribute : attributeString.split(delimiter)) {

            attributeParts = attribute.split(operator);

            if (2 == attributeParts.length) {

                name = attributeParts[0];
                value = attributeParts[1];

                if (attributes.containsKey(name)) {

                    attributes.get(name).addValue(value);

                } else {

                    attributes.put(name, creator.create(name, value));
                }

            } else {

                throw new IllegalArgumentException("Invalid attribute format (" + attribute + ")");
            }
        }

        return new HashSet<>(attributes.values());
    }

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


    /**
     * This is a helper method that can be used to produce a {@link String} representation of the
     * {@code MultiValueAttribute}. The string is made up of the {@code MultiValueAttribute} {@code name} paired with
     * each individual {@code value}. The supplied {@code operator} is placed in between the {@code name} and
     * {@code value} and the {@code delimiter} is placed between each {@code name}/{@code value} pair.
     *
     * Example:
     * <code>
     *     MultiValueAttribute<String> attribute = new MultiValueAttribute<String>("name",
     *         Arrays.asList("one", "two", "three"));
     *     attribute.toString("=", "\n");
     *     // name=one
     *     // name=two
     *     // name=three
     * </code>
     *
     * @param operator
     * @param delimiter
     * @return
     */
    protected String toString(String operator, String delimiter) {

        StringBuilder toStringHolder = new StringBuilder();

        int i = 0;
        for (; i < (values.size() - 1); i++) {

            toStringHolder.append(getName()).append(operator).append(values.get(i)).append(delimiter);
        }

        toStringHolder.append(getName()).append(operator).append(values.get(i));

        return toStringHolder.toString();
    }


    @Override
    public String toString() {

        return toString("=", "\n");
    }
}
