package http.util;

import java.util.Collection;
import java.util.Map;

/**
 * Assertion methods that help with verifying class and method arguments.
 *
 * @author Karl Bennett
 */
public final class Asserts {

    /**
     * The {@code Asserts} constructor is private because it is a utility class so does not contain any state and should
     * never be instantiated.
     */
    private Asserts() {
    }

    /**
     * Assert that the supplied value is not null and throw an {@link IllegalArgumentException} if it is. This should be
     * used to check the arguments of constructors and methods.
     *
     * @param name  the name of the variable that is being checked. It will be used in the exception message.
     * @param value the value of the variable that is being checked for null.
     * @throws IllegalArgumentException if the value is null.
     */
    public static void assertNotNull(String name, Object value) {

        if (null == name) {

            throw new IllegalArgumentException(Asserts.class.getName() +
                    ".assertNotNull(String,String) cannot have a null name argument.");
        }

        if (null == value) {

            throw new IllegalArgumentException("The (" + name + ") variable should not be null.");
        }
    }

    /**
     * Assert that the supplied {@link String} value is not empty ({@code null} or the empty string {@code ""}) and
     * throw an {@link IllegalArgumentException} if it is. This should be used to check the arguments of constructors
     * and methods.
     *
     * @param name  the name of the variable that is being checked. It will be used in the exception message.
     * @param value the value of the variable that is being checked to see if it empty.
     * @throws IllegalArgumentException if the value is null.
     */
    public static void assertNotEmpty(String name, String value) {

        assertNotNull(name, value);

        if ("".equals(value)) {

            throw new IllegalArgumentException("The (" + name + ") variable should not be empty.");
        }
    }

    /**
     * Assert that the supplied {@link Collection} is not {@code null} or empty and throw an
     * {@link IllegalArgumentException} if it is. This should be used to check the arguments of constructors and methods.
     *
     * @param name   the name of the variable that is being checked. It will be used in the exception message.
     * @param values the value of the variable that is being checked to see if it empty.
     * @throws IllegalArgumentException if the value is null.
     */
    public static void assertNotEmpty(String name, Collection values) {

        assertNotNull(name, values);

        if (0 >= values.size()) {

            throw new IllegalArgumentException("The (" + name + ") variable should not be empty.");
        }
    }

    /**
     * Assert that the supplied {@link Map} is not {@code null} or empty and throw an {@link IllegalArgumentException}
     * if it is. This should be used to check the arguments of constructors and methods.
     *
     * @param name   the name of the variable that is being checked. It will be used in the exception message.
     * @param values the value of the variable that is being checked to see if it empty.
     * @throws IllegalArgumentException if the value is null.
     */
    public static void assertNotEmpty(String name, Map values) {

        assertNotNull(name, values);

        if (0 >= values.size()) {

            throw new IllegalArgumentException("The (" + name + ") variable should not be empty.");
        }
    }
}
