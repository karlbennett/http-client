package http.parameter;

import http.attribute.MultiValueAttribute;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static http.util.Checks.isEmpty;

/**
 * Represents an HTTP parameter which can have a name and a single or multiple values. The type of the value can be
 * defined on instantiation.
 *
 * @author Karl Bennett
 */
public class Parameter<T> extends MultiValueAttribute<T> {

    public static String toString(Collection<Parameter> parameters) {

        if (isEmpty(parameters)) return "";

        StringBuilder toStringHolder = new StringBuilder();

        for (Parameter parameter : parameters) {

            toStringHolder.append(parameter).append('&');
        }

        toStringHolder.replace(toStringHolder.length() - 1, toStringHolder.length(), "");

        return toStringHolder.toString();
    }

    public static Collection<Parameter> parse(String query) {

        if (isEmpty(query)) return Collections.emptySet();

        Collection<Parameter>  parameters = new HashSet<>();

        String[] parameterParts;
        for (String parameter : query.split("&")) {

            parameterParts = parameter.split("=");

            if (2 == parameterParts.length) {

                parameters.add(new Parameter<>(parameterParts[0], parameterParts[0]));

            } else {

                throw new IllegalStateException("Invalid parameter format (" + parameter + ")");
            }
        }

        return parameters;
    }

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


    @Override
    public String toString() {

        return super.toString("&");
    }
}
