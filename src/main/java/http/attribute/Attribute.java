package http.attribute;

import http.util.Parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import static http.util.Asserts.assertNotEmpty;
import static http.util.Checks.isEmpty;

/**
 * Represents a generic attribute with a name and a value. The type of the value can be defined on instantiation.
 *
 * @author Karl Bennett
 */
public class Attribute<T> {

    protected static abstract class AttributeParser<A extends Attribute> extends Parser {

        private final String operator;
        private final Collection<A> attributes;

        public AttributeParser(String string, String operator, String delimiter) {
            super(string, delimiter);

            this.operator = operator;

            this.attributes = new HashSet<A>();
        }

        protected abstract A nextPair(String name, String value);

        @Override
        protected void next(String part) {

            String[] pair = part.split(operator);

            if (2 == pair.length) {

                attributes.add(nextPair(pair[0], pair[1]));

                return;
            }

            throw new IllegalArgumentException("Part (" + part + ") is not in the correct format e.g. \"name" +
                    operator + "value\"");
        }

        @Override
        public void parse() {

            // Clear the attribute map so that subsequent calls to parse don't keep appending values.
            attributes.clear();

            super.parse();
        }

        public Collection<A> getAttributes() {

            return new HashSet<A>(attributes);
        }
    }

    /**
     * Concatenate the {@link #toString()} values of a {@link Collection} of {@code Attribute}s. Each value will be
     * delimited by the supplied {@code delimiter} {@link String}.
     *
     * @param attributes the attributes that will have their string representations concatenated.
     * @param delimiter  the delimiter that should be appended between the attributs.
     * @return the concatenated parameter string.
     */
    public static <T, A extends Attribute<T>> String toString(Collection<A> attributes, String delimiter) {

        if (isEmpty(attributes)) return "";

        Iterator<A> iterator = attributes.iterator();

        // Add the first attribute to the string.
        StringBuilder toStringHolder = new StringBuilder(iterator.next().toString());

        A attribute;
        while (iterator.hasNext()) {

            attribute = iterator.next();

            toStringHolder.append(delimiter).append(attribute);
        }

        return toStringHolder.toString();
    }

    protected static <A extends Attribute<String>> Collection<A> parse(AttributeParser<A> pasrer) {

        pasrer.parse();

        return new HashSet<>(pasrer.getAttributes());
    }

    private final String name;
    private final T value;
    private final String operator;


    /**
     * Create an {@code Attribute} with a name and value.
     *
     * @param name  the name of the attribute.
     * @param value the value for the attribute.
     * @throws IllegalArgumentException if the {@code Attribute}'s name is empty or the values are null.
     */
    public Attribute(String name, T value, String operator) {

        assertNotEmpty("name", name);

        this.name = name;
        this.value = value;
        this.operator = operator;
    }


    /**
     * @return the attributes name.
     */
    public String getName() {

        return name;
    }

    /**
     * @return the attributes value.
     */
    public T getValue() {

        return value;
    }

    /**
     * @return the attributes operator.
     */
    public String getOperator() {
        return operator;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        return name.equals(attribute.name) &&
                (operator == null ? attribute.operator == null : operator.equals(attribute.operator)) &&
                (value == null ? attribute.value == null : value.equals(attribute.value));

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();

        result = 31 * result + (value == null ? 0 : value.hashCode());

        result = 31 * result + (operator == null ? 0 : operator.hashCode());

        return result;
    }

    @Override
    public String toString() {

        return getName() + getOperator() + getValue();
    }
}
