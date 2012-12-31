package http.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Reflection utility that contains an internal cache to transparently speed up reflective operations.
 *
 * @author Karl Bennett
 */
public final class Reflections {

    /**
     * If the supplied class or interface is a member of another class, returns the {@link Class} object representing
     * the class in which it was declared. This method returns {@code null} if the supplied class or interface is not a
     * member of any other class. If the supplied {@code Class} object represents an array class, a primitive type, or
     * void, then this method returns null.
     *
     * @param type the {@code Class} to get the declaring {@code Class} for.
     * @return the declaring {@code Class} for the supplied class.
     */
    public static Class<?> getDeclaringClass(Class<?> type) {

        return null;
    }

    /**
     * Returns the immediately enclosing {@link Class} of the supplied underlying {@code Class}. If the supplied
     * underlying {@code Class} is a top level {@code Class} this method returns {@code null}.
     *
     * @param type the underlying {@code Class} to get the enclosing {@code Class} for.
     * @return the immediately enclosing {@code Class} of the supplied underlying {@code Class}.
     */
    public static Class<?> getEnclosingClass(Class<?> type) {
        // TODO: Work out what the hell this actually does.
        return null;
    }

    /**
     * If the supplied {@link Class} object represents a local or anonymous class within a constructor, returns a
     * {@link Constructor} object representing the immediately enclosing constructor of the underlying class. Returns
     * {@code null} otherwise. In particular, this method returns {@code null} if the underlying class is a local or
     * anonymous class immediately enclosed by a type declaration, instance initialiser or static initialiser.
     *
     * @param type the {@code Class} to get the enclosing {@code Constructor} for.
     * @return the immediately enclosing constructor of the supplied underlying class, if that class is a local or
     *         anonymous class; otherwise {@code null}.
     */
    public static Constructor<?> getEnclosingConstructor(Class<?> type) {

        return null;
    }

    /**
     * If the supplied {@link Class} object represents a local or anonymous class within a method, returns a
     * {@link Method} object representing the immediately enclosing method of the underlying class. Returns {@code null}
     * otherwise. In particular, this method returns {@code null} if the underlying class is a local or anonymous class
     * immediately enclosed by a type declaration, instance initialiser or static initialiser.
     *
     * @param type the {@code Class} to get the enclosing {@code Method} for.
     * @return the immediately enclosing method of the supplied underlying class, if that class is a local or anonymous
     *         class; otherwise {@code null}.
     */
    public static Method getEnclosingMethod(Class<?> type) {

        return null;
    }

    /**
     * Returns a {@link Field} object that reflects the specified public member field of the class or interface
     * represented by the supplied {@link Class} object. The {@code name} parameter is a {@link String} specifying the
     * simple name of the desired field.
     * <p/>
     * This method will also check all implemented interfaces and inherited classes for a public field with the supplied
     * {@code name}.
     *
     * @param type the type of the class that is to have it's field retrieved.
     * @param name the field name.
     * @return the {@code Field} object of this class specified by {@code name}.
     * @throws NoSuchFieldException if a field with the specified {@code name} is not found.
     * @throws NullPointerException if {@code name} is {@code null}.
     */
    public static Field getField(Class<?> type, String name) {

        return null;
    }

    /**
     * Returns an array containing {@link Field} objects reflecting all the accessible public fields of the class or
     * interface represented by the supplied {@link Class} object. The elements in the array returned are not sorted and
     * are not in any particular order. This method returns an array of length 0 if the supplied class or interface has
     * no accessible public fields, or if it represents an array class, a primitive type, or void.
     * TODO: Confirm the paragraph below, it seems a little off.
     * Specifically, if the supplied {@link Class} object represents a class, this method returns the public fields of
     * this class and of all its superclasses. If the supplied {@code Class} object represents an interface, this method
     * returns the fields of this interface and of all its superinterfaces.
     *
     * @param type the type of the class that is to have it's fields retrieved.
     * @return the array of {@code Field} objects representing the public fields.
     */
    public static Field[] getFields(Class<?> type) {

        return null;
    }

    /**
     * Returns a {@link Field} object that reflects the specified declared field of the class or interface represented
     * by the supplied {@link Class} object. The name parameter is a {@link String} that specifies the simple name of
     * the desired field. Note that this method will not reflect the length field of an array class.
     *
     * @param type the type of the class that is to have it's declared field retrieved.
     * @param name the name of the field
     * @return the {@code Field} object for the specified field in this class
     * @throws NoSuchFieldException if a field with the specified {@code name} is not found.
     * @throws NullPointerException if {@code name} is {@code null}.
     */
    public static Field getDeclaredField(Class<?> type, String name) {

        return null;
    }

    /**
     * Returns an array of {@link Field} objects reflecting all the fields declared by the class or interface
     * represented by the supplied {@link Class} object. This includes public, protected, default (package) access, and
     * private fields, but excludes inherited fields. The elements in the array returned are not sorted and are not in
     * any particular order. This method returns an array of length 0 if the class or interface declares no fields, or
     * if this {@code Class} object represents a primitive type, an array class, or void.
     *
     * @param type the type of the class that is to have it's fields retrieved.
     * @return the array of {@code Field} objects representing all the declared fields of the supplied class.
     */
    public static Field[] getDeclaredFields(Class<?> type) {

        return null;
    }

    /**
     * Returns a {@link Constructor} object that reflects the specified public constructor of the class represented by
     * the supplied {@link Class} object. The {@code parameterTypes} parameter is an array of {@code Class} objects that
     * identify the constructor's formal parameter types, in declared order. If the supplied {@code Class} object
     * represents an inner class declared in a non-static context, the formal parameter types include the explicit
     * enclosing instance as the first parameter.
     * <p/>
     * The constructor to reflect is the public constructor of the class represented by the supplied {@code Class}
     * object whose formal parameter types match those specified by {@code parameterTypes}.
     *
     * @param type           the type of the class that is to have it's constructor retrieved.
     * @param parameterTypes the parameter array.
     * @return the {@code Constructor} object of the public constructor that matches the specified
     *         {@code parameterTypes}.
     * @throws NoSuchMethodException if a matching constructor is not found.
     */
    public static <T> Constructor<T> getConstructor(Class<T> type, Class<?>... parameterTypes) {

        return null;
    }

    /**
     * Returns an array containing {@link Constructor} objects reflecting all the public constructors of the class
     * represented by the supplied {@link Class} object. An array of length 0 is returned if the class has no public
     * constructors, or if the class is an array class, or if the class reflects a primitive type or void. Note that
     * while this method returns an array of {@code Constructor<T>} objects (that is an array of constructors from this
     * class), the return type of this method is {@code Constructor<?>[]} and not {@code Constructor<T>[]} as might be
     * expected. This less informative return type is necessary since after being returned from this method, the array
     * could be modified to hold Constructor objects for different classes, which would violate the type guarantees of
     * {@code Constructor<T>[]}.
     *
     * @param type the type of the class that is to have it's constructors retrieved.
     * @return the array of {@code Constructor} objects representing the public constructors of the supplied class.
     */
    public static Constructor<?>[] getConstructors(Class<?> type) {

        return null;
    }

    /**
     * Returns a {@link Constructor} object that reflects the specified constructor of the class or interface
     * represented by the supplied {@link Class} object. The {@code parameterTypes} parameter is an array of
     * {@code Class} objects that identify the constructor's formal parameter types, in declared order. If this
     * {@code Class} object represents an inner class declared in a non-static context, the formal parameter types
     * include the explicit enclosing instance as the first parameter.
     *
     * @param type           the type of the class that is to have it's constructor retrieved.
     * @param parameterTypes the parameter array.
     * @return the {@code Constructor} object for the constructor with the specified parameter list.
     * @throws NoSuchMethodException if a matching constructor is not found.
     */
    public static <T> Constructor<T> getDeclaredConstructor(Class<T> type, Class<?>... parameterTypes) {

        return null;
    }

    /**
     * Returns an array of {@link Constructor} objects reflecting all the constructors declared by the class represented
     * by the supplied {@link Class} object. These are public, protected, default (package) access, and private
     * constructors. The elements in the array returned are not sorted and are not in any particular order. If the class
     * has a default constructor, it is included in the returned array. This method returns an array of length 0 if this
     * {@code Class} object represents an interface, a primitive type, an array class, or void.
     *
     * @param type the type of the class that is to have it's constructors retrieved.
     * @return the array of {@code Constructor} objects representing all the declared constructors of the supplied
     *         class.
     */
    public static Constructor<?>[] getDeclaredConstructors(Class<?> type) {

        return null;
    }

    /**
     * Returns a {@link Method} object that reflects the specified public member method of the class or interface
     * represented by the supplied {@link Class} object. The {@code name} parameter is a {@link String} specifying the
     * simple name of the desired method. The {@code parameterTypes} parameter is an array of {@code Class} objects that
     * identify the method's formal parameter types, in declared order. If {@code parameterTypes} is {@code null}, it is
     * treated as if it were an empty array.
     *
     * @param type           the type of the class that is to have it's method retrieved.
     * @param name           the name of the method.
     * @param parameterTypes the parameter array.
     * @return the {@code Method} object that matches the specified {@code name} and {@code parameterTypes}.
     * @throws NoSuchMethodException if a matching method is not found or if the {@code name} is either of the
     *                               initialiser names "{@code <init>}"or "{@code <clinit>}".
     * @throws NullPointerException  if {@code name} is {@code null}.
     */
    public static Method getMethod(Class<?> type, String name, Class<?>... parameterTypes) {

        return null;
    }

    /**
     * Returns an array containing {@link Method} objects reflecting all the public member methods of the class or
     * interface represented by the supplied {@link Class} object, including those declared by the class or interface
     * and those inherited from superclasses and superinterfaces. {@link java.lang.reflect.Array} classes return all the
     * (public) member methods inherited from the Object class. The elements in the array returned are not sorted and
     * are not in any particular order. This method returns an array of length 0 if the supplied {@code Class} object
     * represents a class or interface that has no public member methods, or if this {@code Class} object represents a
     * primitive type or void.
     * <p/>
     * The class initialization method <clinit> is not included in the returned array. If the class declares multiple
     * public member methods with the same parameter types, they are all included in the returned array.
     *
     * @param type the type of the class that is to have it's methods retrieved.
     * @return the array of {@code Method} objects representing the public methods of this class.
     */
    public static Method[] getMethods(Class<?> type) {

        return null;
    }

    /**
     * Returns a {@link Method} object that reflects the specified declared method of the class or interface represented
     * by the supplied {@link Class} object. The name parameter is a {@link String} that specifies the simple name of
     * the desired method, and the {@code parameterTypes} parameter is an array of {@code Class} objects that identify
     * the method's formal parameter types, in declared order. If more than one method with the same parameter types is
     * declared in a class, and one of these methods has a return type that is more specific than any of the others,
     * that method is returned; otherwise one of the methods is chosen arbitrarily. If the name is "<init>"or "<clinit>"
     * a {@link NoSuchMethodException} is raised.
     *
     * @param type           the type of the class that is to have it's method retrieved.
     * @param name           the name of the method.
     * @param parameterTypes the parameter array.
     * @return the {@code Method} object that class matching the specified {@code name} and {@code parameterTypes}.
     * @throws NoSuchMethodException if a matching method is not found.
     * @throws NullPointerException  if {@code name} is {@code null}.
     */
    public static Method getDeclaredMethod(Class<?> type, String name, Class<?>... parameterTypes) {

        return null;
    }

    /**
     * Returns an array of {@link Method} objects reflecting all the methods declared by the class or interface
     * represented by the supplied {@link Class} object. This includes public, protected, default (package) access, and
     * private methods, but excludes inherited methods. The elements in the array returned are not sorted and are not in
     * any particular order. This method returns an array of length 0 if the class or interface declares no methods, or
     * if the supplied {@code Class} object represents a primitive type, an array class, or void. The class
     * initialization method <clinit> is not included in the returned array. If the class declares multiple public
     * member methods with the same parameter types, they are all included in the returned array.
     *
     * @param type the type of the class that is to have it's methods retrieved.
     * @return the array of {@code Method} objects representing all the declared methods of the supplied class.
     */
    public static Method[] getDeclaredMethods(Class<?> type) {

        return null;
    }

    /**
     * Returns an array containing {@link Class} objects representing all the public classes and interfaces that are
     * members of the class represented by the supplied {@code Class} object. This includes public class and interface
     * members inherited from superclasses and public class and interface members declared by the class. This method
     * returns an array of length 0 if the supplied {@code Class} object has no public member classes or interfaces.
     * This method also returns an array of length 0 if the supplied {@code Class} object represents a primitive type,
     * an array class, or void.
     *
     * @param type the type of the class that is to have it's inner classes retrieved.
     * @return the array of {@code Class} objects representing the public members of this class.
     */
    public static Class<?>[] getClasses(Class<?> type) {

        return null;
    }

    /**
     * Returns an array of {@link Class} objects reflecting all the classes and interfaces declared as members of the
     * class represented by the supplied {@code Class} object. This includes public, protected, default (package)
     * access, and private classes and interfaces declared by the class, but excludes inherited classes and interfaces.
     * This method returns an array of length 0 if the class declares no classes or interfaces as members, or if the
     * supplied {@code Class} object represents a primitive type, an array class, or void.
     *
     * @param type the type of the class that is to have it's inner classes retrieved.
     * @return the array of {@code Class} objects representing all the declared members of this class.
     */
    public static Class<?>[] getDeclaredClasses(Class<?> type) {

        return null;
    }
}
