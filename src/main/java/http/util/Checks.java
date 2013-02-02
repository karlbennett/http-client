package http.util;

import java.util.Collection;
import java.util.Map;

/**
 * Methods for checking the state of {@link Collection}s, {@link Map}s, and {@link String}s.
 *
 * @author Karl Bennett
 */
public final class Checks {

    /**
     * The {@code Checks} constructor is private because it is a utility class so does not contain any state and should
     * never be instantiated.
     */
    private Checks() {
    }


    /**
     * Check if the supplied value is {@code null}.
     *
     * @param value the value to check.
     *
     * @return {@code true} if the value is {@code null}, otherwise {@code false}.
     */
    public static boolean isNull(Object value) {

        return null == value;
    }

    /**
     * Check if the supplied value is not {@code null}.
     *
     * @param value the value to check.
     *
     * @return {@code true} if the value is not {@code null}, otherwise {@code false}.
     */
    public static boolean isNotNull(Object value) {

        return !isNull(value);
    }

    /**
     * Check if the supplied {@link String} is empty (is {@code null} or has a length of {@code 0}).
     *
     * @param value the {@code String} to check.
     *
     * @return {@code true} if the {@code String} is empty, otherwise {@code false}.
     */
    public static boolean isEmpty(String value) {

        return isNull(value) || 0 == value.length();
    }

    /**
     * Check if the supplied {@link String} is not empty (is not {@code null} and has a length greater {@code 0}).
     *
     * @param value the {@code String} to check.
     *
     * @return {@code true} if the {@code String} is not empty, otherwise {@code false}.
     */
    public static boolean isNotEmpty(String value) {

        return !isEmpty(value);
    }

    /**
     * Check if the supplied {@link Collection} is empty (is {@code null} or has a size of {@code 0}).
     *
     * @param value the {@code Collection} to check.
     *
     * @return {@code true} if the {@code Collection} is empty, otherwise {@code false}.
     */
    public static boolean isEmpty(Collection value) {

        return isNull(value) || 0 == value.size();
    }

    /**
     * Check if the supplied {@link Collection} is not empty (is not {@code null} and has a size greater {@code 0}).
     *
     * @param value the {@code Collection} to check.
     *
     * @return {@code true} if the {@code Collection} is not empty, otherwise {@code false}.
     */
    public static boolean isNotEmpty(Collection value) {

        return !isEmpty(value);
    }

    /**
     * Check if the supplied {@link Map} is empty (is {@code null} or has a size of {@code 0}).
     *
     * @param value the {@code Map} to check.
     *
     * @return {@code true} if the {@code Map} is empty, otherwise {@code false}.
     */
    public static boolean isEmpty(Map value) {

        return isNull(value) || 0 == value.size();
    }

    /**
     * Check if the supplied {@link Map} is not empty (is not {@code null} and has a size greater {@code 0}).
     *
     * @param value the {@code Map} to check.
     *
     * @return {@code true} if the {@code Map} is not empty, otherwise {@code false}.
     */
    public static boolean isNotEmpty(Map value) {

        return !isEmpty(value);
    }
}
