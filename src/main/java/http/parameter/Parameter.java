package http.parameter;

import http.attribute.MultiValueAttribute;

import java.util.*;

import static http.util.Checks.isEmpty;

/**
 * Represents an HTTP parameter which can have a name and a single or multiple values. The type of the value can be
 * defined on instantiation.
 *
 * @author Karl Bennett
 */
public class Parameter<T> extends MultiValueAttribute<T> {

    /**
     * Concatenate the {@link #toString()} values of a {@link Collection} of {@code Parameters}. Each value will be
     * delimited by the '&' character.
     *
     * @param parameters the parameters that will have their string representations concatenated.
     * @return the concatenated parameter string.
     */
    public static String toString(Collection<Parameter> parameters) {

        if (isEmpty(parameters)) return "";

        StringBuilder toStringHolder = new StringBuilder();

        for (Parameter parameter : parameters) {

            toStringHolder.append(parameter).append('&');
        }

        toStringHolder.replace(toStringHolder.length() - 1, toStringHolder.length(), "");

        return toStringHolder.toString();
    }

    /**
     * Parse a {@link String} containing any number of name/value pairs into a collection of {@link Parameter}s. The
     * names and values must be delimited by an '=' character and each name/value pair must be delimited by an '&'
     * character.
     *
     * Example:
     * <code>
     *     nameOne=valueOne&nameTwo=valueTwo&nameThree=valueThree
     * </code>
     *
     * Name value pairs that share the same name will be stored in the same {@code Parameter} instance.
     *
     * Example:
     * <code>
     *     List<Parameter> parameters = new ArrayList(
     *         Parameter.parse("nameOne=valueOne&nameOne=valueTwo&nameThree=valueThree")
     *     );
     *     parameters.size(); // 2
     *     parameters.get(0).getValues(); // ["valueOne", "valueTwo"]
     *     parameters.get(1).getValues(); // ["valueThree"]
     * </code>
     *
     * @param query the string to parse.
     * @return the
     */
    public static Collection<Parameter> parse(String query) {

        if (isEmpty(query)) return Collections.emptySet();

        Map<String, Parameter> parameters = new HashMap<>();

        String[] parameterParts;
        String name, value;
        for (String parameter : query.split("&")) {

            parameterParts = parameter.split("=");

            if (2 == parameterParts.length) {

                name = parameterParts[0];
                value = parameterParts[1];

                if (parameters.containsKey(name)) {

                    parameters.get(name).addValue(value);

                } else {

                    parameters.put(name, new Parameter(name, value));
                }

            } else {

                throw new IllegalArgumentException("Invalid parameter format (" + parameter + ")");
            }
        }

        return new HashSet<>(parameters.values());
    }

    /**
     * Create an {@code Parameter} with a name and multiple values.
     *
     * @param name   the name of the attribute.
     * @param values the values for the attribute.
     */
    public Parameter(String name, List<T> values) {
        super(name, values);
    }

    /**
     * Create an {@code Parameter} with a name and a single value.
     *
     * @param name  the name of the attribute.
     * @param value the single value for the attribute.
     */
    public Parameter(String name, T value) {
        super(name, value);
    }


    @Override
    public String toString() {

        return super.toString("=", "&");
    }
}
