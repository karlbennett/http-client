package http.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Karl Bennett
 */
public class ReflectionsTest {

    private static interface TestInterfaceOne {

        public static final int ONE = 1;

        public Object testInterfaceMethodOne(String two);

        public void testInterfaceMethodTwo();

        public static interface TestInterfaceTwo {
        }

        public interface TestInterfaceThree {
        }
    }

    private static interface TestInterfaceTwo {

        public static final int ONE = 1;

        public Object testInterfaceMethodOne(String two);

        public void testInterfaceMethodTwo();

        public static interface TestInterfaceThree {
        }

        public interface TestInterfaceFour {
        }
    }

    private static class TestClassOne {

        private int one;

        private TestClassOne() {
        }

        private TestClassOne(int one) {

            this.one = one;
        }

        public int getOne() {
            return one;
        }

        public void setOne(int one) {
            this.one = one;
        }
    }

    private static class TestClassTwo {

        public static String blah() {
            return "two";
        }

        private String two;

        private TestClassTwo(String two) {

            this.two = two;
        }

        public String getTwo() {
            return two;
        }
    }

    private static class TestClassThree extends TestClassTwo implements TestInterfaceOne, TestInterfaceTwo {

        public static final int ONE = 1;

        public static String blah() {
            return "three";
        }

        private Long three;

        private TestClassThree(String two, Long three) {
            super(two);

            this.three = three;
        }

        @Override
        public Object testInterfaceMethodOne(String two) {

            return two;
        }

        @Override
        public void testInterfaceMethodTwo() {
            System.out.println("inter three");
        }

        public static class TestClassFour {
        }

        public class TestClassFive {
        }

        public TestClassFive classMethodOne() {

            class TestClassSix extends TestClassFive {
            }

            return new TestClassSix();
        }

        @Override
        public String getTwo() {
            return three.toString();
        }

        public Long getThree() {
            return three;
        }
    }


    private static final int ONE = 1;
    private static final String TWO = "two";
    private static final Long THREE = 1L;

    private static final Map<String, Field> TEST_INTERFACE_ONE_FIELDS = extractFields(TestInterfaceOne.class);
    private static final Map<String, Field> TEST_CLASS_ONE_FIELDS = extractFields(TestClassOne.class);
    private static final Map<String, Field> TEST_CLASS_TWO_FIELDS = extractFields(TestClassTwo.class);
    private static final Map<String, Field> TEST_CLASS_THREE_FIELDS = extractFields(TestClassThree.class);
    private static final Map<String, Field> TEST_CLASS_FOUR_FIELDS = extractFields(TestClassThree.TestClassFour.class);
    private static final Map<String, Field> TEST_CLASS_FIVE_FIELDS = extractFields(TestClassThree.TestClassFive.class);
    private static final Map<String, Field> TEST_CLASS_SIX_FIELDS = extractFields(new TestClassThree(TWO, THREE).classMethodOne().getClass());

    private static final Map<String, Method> TEST_INTERFACE_ONE_METHODS = extractMethods(TestInterfaceOne.class);
    private static final Map<String, Method> TEST_CLASS_ONE_METHODS = extractMethods(TestClassOne.class);
    private static final Map<String, Method> TEST_CLASS_TWO_METHODS = extractMethods(TestClassTwo.class);
    private static final Map<String, Method> TEST_CLASS_THREE_METHODS = extractMethods(TestClassThree.class);
    private static final Map<String, Method> TEST_CLASS_FOUR_METHODS = extractMethods(TestClassThree.TestClassFour.class);
    private static final Map<String, Method> TEST_CLASS_FIVE_METHODS = extractMethods(TestClassThree.TestClassFive.class);
    private static final Map<String, Method> TEST_CLASS_SIX_METHODS = extractMethods(new TestClassThree(TWO, THREE).classMethodOne().getClass());


    @Test
    public void testGetDeclaringClass() throws Exception {

    }

    @Test
    public void testGetEnclosingClass() throws Exception {

    }

    @Test
    public void testGetEnclosingConstructor() throws Exception {

    }

    @Test
    public void testGetEnclosingMethod() throws Exception {

    }

    @Test
    public void testGetField() throws Exception {

    }

    @Test
    public void testGetFields() throws Exception {

    }

    @Test
    public void testGetDeclaredField() throws Exception {

    }

    @Test
    public void testGetDeclaredFields() throws Exception {

    }

    @Test
    public void testGetConstructor() throws Exception {

    }

    @Test
    public void testGetConstructors() throws Exception {

    }

    @Test
    public void testGetDeclaredConstructor() throws Exception {

    }

    @Test
    public void testGetDeclaredConstructors() throws Exception {

    }

    @Test
    public void testGetMethod() throws Exception {

    }

    @Test
    public void testGetMethods() throws Exception {

    }

    @Test
    public void testGetDeclaredMethod() throws Exception {

    }

    @Test
    public void testGetDeclaredMethods() throws Exception {

    }

    @Test
    public void testGetClasses() throws Exception {

    }

    @Test
    public void testGetDeclaredClasses() throws Exception {

    }


    /**
     * An invoker interface that is used decouple the call to reflection methods
     * ({@link Class#getFields()}, {@link Class#getDeclaredFields()}, {@link Class#getMethods()}...) from any
     * surrounding logic.
     *
     * @param <R> the type of reflective property class that will be produced by the invoker e.g. ({@link Field},
     *            {@link Method}, {@link java.lang.reflect.Constructor}...)
     */
    private static interface PropertiesInvoker<R extends Member> {

        public R[] invoke(Class type);

    }

    /**
     * Check if the supplied type has any super classes. This will fail if the types super class is either
     * {@link Object} or {@code null}.
     *
     * @param type the class type to check.
     * @return {@code true} if the class has a super class other than {@code Object} or {@code null}, otherwise
     *         {@code false}.
     */
    private static boolean hasNoSuperClass(Class type) {

        return null == type.getSuperclass() || Object.class.equals(type.getSuperclass());
    }

    private static <R extends Member> Map<String, R> extractReflectiveObjects(Class type, PropertiesInvoker<R> invoker) {

        Map<String, R> fieldMap = new HashMap<String, R>();

        for (R reflectiveObject : invoker.invoke(type)) {

            fieldMap.put(reflectiveObject.getName(), reflectiveObject);
        }

        if (hasNoSuperClass(type)) return fieldMap;

        fieldMap.putAll(extractReflectiveObjects(type.getSuperclass(), invoker));

        return fieldMap;
    }

    private static Map<String, Field> extractFields(Class type) {

        return extractReflectiveObjects(type, new PropertiesInvoker<Field>() {

            @Override
            public Field[] invoke(Class type) {

                return type.getDeclaredFields();
            }
        });
    }

    private static Map<String, Method> extractMethods(Class type) {

        return extractReflectiveObjects(type, new PropertiesInvoker<Method>() {

            @Override
            public Method[] invoke(Class type) {

                return type.getDeclaredMethods();
            }
        });
    }
}
