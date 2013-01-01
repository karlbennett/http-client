package http.reflection;

import java.util.Arrays;

import static http.util.Assert.assertNotNull;

/**
 * This class can be used as the type for {@link java.lang.reflect.Method}s and {@link java.lang.reflect.Constructor}s
 * in the {@link ReflectionMap}.
 *
 * @author Karl Bennett
 */
public class ReflectionKey {

    private final String name;
    private final Class[] parameterTypes;


    /**
     * Create a new {@code ReflectionKey} with the supplied name and parameters.
     *
     * @param name the name of the {@code ReflectionMap} value.
     * @param parameterTypes the {@code parameterTypes} for the {@code ReflectionMap} value.
     */
    public ReflectionKey(String name, Class... parameterTypes) {

        assertNotNull("name", name);
        assertNotNull("parameterTypes", parameterTypes);

        this.name = name;
        this.parameterTypes = parameterTypes;
    }


    public String getName() {

        return name;
    }

    public Class[] getParameterTypes() {
        // Try and keep the internal array immutable.
        return Arrays.copyOf(parameterTypes, parameterTypes.length);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ReflectionKey that = (ReflectionKey) o;

        return name.equals(that.name) && Arrays.equals(parameterTypes, that.parameterTypes);

    }

    @Override
    public int hashCode() {

        int result = name.hashCode();

        result = 31 * result + Arrays.hashCode(parameterTypes);

        return result;
    }
}
