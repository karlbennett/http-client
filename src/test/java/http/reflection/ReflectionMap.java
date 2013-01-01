package http.reflection;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A map that will contain all the requested reflective members from a supplied {@link Class} mapped to their respective
 * name. Static member keys contain the {@link Class#getName()} prefixed and delimited with a dot ('.').
 * <p/>
 * This map will be end up populated with all the members from the supplied type and all it's inherited classes and
 * interfaces. Regardless of whether a {@code Class#get*} or {@code Class#getDeclared*} method is in the
 * {@link PropertiesInvoker}.
 * <p/>
 * When there is an instance member name clash the member at the lowest inheritance level will take precedence.
 * <p/>
 * The key type for the {@code ReflectionMap} is assumed to be {@link String} and still needs to be generically set to
 * it. This has been done to allow the modification of the key type by subclasses by overriding the
 * {@link ReflectionMap#buildEntry(Member)} method. The default {@code String} behaviour is fine for mapping
 * {@link java.lang.reflect.Field}s and and {@link java.lang.reflect.Constructor}s.
 * <p/>
 * Example:
 * <code>
 *      interface TestInterface {
 * <p/>
 *           public static final String TEST = "Test";
 *           public static final int ZERO = 0;
 * <p/>
 *           String getTest();
 *      }
 * <p/>
 *      class TestOne implements TestInterface {
 * <p/>
 *           public static final String TEST = "Test One";
 *           public static final int ONE = 1;
 * <p/>
 *           private String test;
 * <p/>
 *           TestOne(String test) {
 *                this.test = test;
 *           }
 *
 *           @Override public String getTest() {
 *                return test;
 *           }
 *      }
 * <p/>
 *      class TestTwo extends TestOne {
 *           public static final String TEST = "Test Two";
 *           public static final int TWO = 2;
 * <p/>
 *           private String test;
 * <p/>
 *           TestTwo(String test) {
 *                super(test);
 *                this.test = test;
 *           }
 * <p/>
 *           public String getTest() {
 *                return test;
 *           }
 *      }
 * <p/>
 *      Map<String, Field> fieldMap = new ReflectionMap<String, Field>(new ReflectionMap.PropertiesInvoker<Field>() {
 *           @Override public Field[] invoke(Class type) {
 *                return type.getDeclaredFields();
 *           }
 *      }, TestTwo.class);
 * <p/>
 *      // fieldMap {
 *      //     "test.TestInterface.TEST" => Field(test.TestInterface.TEST),
 *      //     "test.TestOne.TEST" => Field(test.TestOne.TEST),
 *      //     "test.TestTwo.TEST" => Field(test.TestTwo.TEST),
 *      //     "test.TestInterface.ZERO" => Field(test.TestInterface.ZERO),
 *      //     "test.TestOne.ONE" => Field(test.TestOne.ONE),
 *      //     "test.TestTwo.TWO" => Field(test.TestTwo.TWO),
 *      //     "test" => Field(test.TestTwo.test)
 *      // }
 * <p/>
 *      Map<String, Method> methodMap = new ReflectionMap<Method>(new ReflectionMap.PropertiesInvoker<Method>() {
 *           @Override
 *           public Method[] invoke(Class type) {
 *                return type.getDeclaredMethods();
 *           }
 *      }, TestTwo.class);
 * <p/>
 *      // methodMap {
 *      //     "getTest" => Field(test.TestTwo.getTest())
 *      // }
 * </code>
 *
 * @author Karl Bennett
 */
public class ReflectionMap<K, M extends Member> extends AbstractMap<K, M> {

    /**
     * Check if the supplied type has any super classes. This will fail if the types super class is either
     * {@link Object} or {@code null}.
     *
     * @param type the class type to check.
     * @return {@code true} if the class has a super class other than {@code Object} or {@code null}, otherwise
     *         {@code false}.
     */
    public static boolean hasNoSuperClass(Class type) {

        return null == type.getSuperclass() || Object.class.equals(type.getSuperclass());
    }

    /**
     * An invoker interface that is used decouple the call to reflection methods
     * ({@link Class#getFields()}, {@link Class#getDeclaredFields()}, {@link Class#getMethods()}...) from any
     * surrounding logic.
     *
     * @param <R> the type of reflective property class that will be produced by the invoker e.g.
     *            ({@link java.lang.reflect.Field}, {@link java.lang.reflect.Method},
     *            {@link java.lang.reflect.Constructor}...)
     */
    public static interface PropertiesInvoker<R extends Member> {

        public R[] invoke(Class type);

    }


    private PropertiesInvoker<M> invoker;
    private Set<Entry<K, M>> entries;


    /**
     * Create a new {@code ReflectionMap} with the supplied invoker and type.
     *
     * @param invoker the invoker that will carry out the reflective member request e.g. {@link Class#getFields()},
     *                {@link Class#getDeclaredFields()}, {@link Class#getMethods()}...
     * @param type    the class type that will have it's reflective members extracted.
     */
    public ReflectionMap(PropertiesInvoker<M> invoker, Class type) {

        this.invoker = invoker;

        entries = extractClassReflectiveObjects(type);
    }

    @Override
    public Set<Entry<K, M>> entrySet() {

        return entries;
    }

    /**
     * The map entity for each reflective member is built with this method, it can be overridden to customise the key
     * type the map, by default it is assumed to be a string.
     *
     * @param member the reflective member that will be added to the map as an entity.
     * @return the new map entity.
     */
    protected Entry<K, M> buildEntry(M member) {

        return (Entry<K, M>) new SimpleEntry<String, M>(
                Modifier.isStatic(member.getModifiers()) ?
                        member.getDeclaringClass().getName() + "." + member.getName() :
                        member.getName(),
                member);
    }

    /**
     * Extract all the reflective members from the supplied class using the invoker and then add them to a {@link Set}
     * as {@link Entry}s containing with the member name as the key and the member as the value. If the member is static
     * the members declaring {@link Class#getName()} will be prefixed and delimited with a dot ('.').
     *
     * @param type the class type that will have it's reflective members extracted.
     * @return a {@code Set} contain reflective member {@code Entry}s.
     */
    private Set<Entry<K, M>> extractReflectiveObjects(Class type) {

        Set<Entry<K, M>> entries = new HashSet<Entry<K, M>>();

        for (final M member : invoker.invoke(type)) {

            entries.add(buildEntry(member));
        }

        return entries;
    }

    /**
     * Extract all the reflected members using the {@link ReflectionMap}'s invoker from the supplied type. This method
     * will extract private and public members from the type and all it's parent classes.
     *
     * @param type
     * @return
     */
    private Set<Entry<K, M>> extractClassReflectiveObjects(Class type) {

        Set<Entry<K, M>> entries = new HashSet<Entry<K, M>>();

        for (Class interfaceType : type.getInterfaces()) {

            entries.addAll(extractReflectiveObjects(interfaceType));
        }

        entries.addAll(extractReflectiveObjects(type));

        if (hasNoSuperClass(type)) return entries;

        entries.addAll(extractClassReflectiveObjects(type.getSuperclass()));

        return entries;
    }
}
