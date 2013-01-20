package http.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static java.util.AbstractMap.SimpleEntry;

/**
 * @author Karl Bennett
 */
public class ReflectionTestData {

    public static interface TestInterface {

        public static final String TEST = "Test";
        public static final int ZERO = 0;

        public class InnerTestClass {
        }

        String getTest();
    }

    public static class TestOne implements TestInterface {

        public static final String TEST = "Test One";
        public static final int ONE = 1;

        private class InnerTestClass {
        }

        public class InnerTestOneClass {
        }

        private String test;

        private TestOne() {
        }

        public TestOne(String test) {
            this.test = test;
        }

        @Override
        public String getTest() {

            return test;
        }
    }

    public static class TestTwo extends TestOne {

        public static final String TEST = "Test Two";
        public static final int TWO = 2;

        private class InnerTestClass {
        }

        public class InnerTestTwoClass {
        }

        private String test;

        public TestTwo(String test) {
            super(test);

            this.test = test;
        }

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }
    }


    public static final SimpleEntry<String, Field> TEST_INTERFACE_TEST = new FieldEntry(
            TestInterface.class, "TEST");
    public static final SimpleEntry<String, Field> TEST_INTERFACE_ZERO = new FieldEntry(
            TestInterface.class, "ZERO");
    public static final SimpleEntry<String, Class> TEST_INTERFACE_INNER_TEST_CLASS = new ClassEntry(
            TestInterface.class, 0);
    public static final SimpleEntry<Object[], Method> TEST_INTERFACE_GET_TEST = new MethodEntry(
            TestInterface.class, "getTest");

    public static final SimpleEntry<String, Field> TEST_ONE_TEST = new FieldEntry(TestOne.class, "TEST");
    public static final SimpleEntry<String, Field> TEST_ONE_ONE = new FieldEntry(TestOne.class, "ONE");
    public static final SimpleEntry<String, Class> TEST_ONE_INNER_TEST_CLASS = new ClassEntry(TestOne.class, 0);
    public static final SimpleEntry<String, Class> INNER_TEST_CLASS_ONE = new ClassEntry(TestOne.class, 1);
    public static final SimpleEntry<String, Field> TEST_ONE_INSTANCE_TEST = new FieldEntry(TestOne.class, "test");
    public static final SimpleEntry<Object[], Constructor<TestOne>> TEST_ONE_DEFAULT_CONSTRUCTOR =
            new ConstructorEntry<TestOne>(TestOne.class);
    public static final SimpleEntry<Object[], Constructor<TestOne>> TEST_ONE_CONSTRUCTOR = new ConstructorEntry<TestOne>(
            TestOne.class, String.class);
    public static final SimpleEntry<Object[], Method> TEST_ONE_GET_TEST = new MethodEntry(
            TestOne.class, "getTest");

    public static final SimpleEntry<String, Field> TEST_TWO_TEST = new FieldEntry(TestTwo.class, "TEST");
    public static final SimpleEntry<String, Field> TEST_TWO_TWO = new FieldEntry(TestTwo.class, "TWO");
    public static final SimpleEntry<String, Class> TEST_TWO_INNER_TEST_CLASS = new ClassEntry(TestTwo.class, 0);
    public static final SimpleEntry<String, Class> INNER_TEST_CLASS_TWO = new ClassEntry(TestTwo.class, 1);
    public static final SimpleEntry<String, Field> TEST_TWO_INSTANCE_TEST = new FieldEntry(TestTwo.class, "test");
    public static final SimpleEntry<Object[], Constructor<TestTwo>> TEST_TWO_CONSTRUCTOR = new ConstructorEntry<TestTwo>(
            TestTwo.class, String.class);
    public static final SimpleEntry<Object[], Method> TEST_TWO_GET_TEST = new MethodEntry(
            TestTwo.class, "getTest");
    public static final SimpleEntry<Object[], Method> TEST_TWO_SET_TEST = new MethodEntry(
            TestTwo.class, "setTest", String.class);


    private static abstract class TestEntry<K, M> extends SimpleEntry<K, M> {

        protected static abstract class Getter<K, M> {

            public M getValueQuietly() {

                try {

                    return getValue();

                } catch (NoSuchFieldException e) {

                    throw new IllegalStateException(e);

                } catch (NoSuchMethodException e) {

                    throw new IllegalStateException(e);
                }
            }

            protected abstract K getKey();

            protected abstract M getValue() throws NoSuchFieldException, NoSuchMethodException;
        }


        public TestEntry(Getter<K, M> getter) {
            super(getter.getKey(), getter.getValueQuietly());
        }
    }

    private static class ClassEntry extends TestEntry<String, Class> {

        public ClassEntry(final Class type, final int i) {
            super(new Getter<String, Class>() {

                private Class innerClass;

                @Override
                protected String getKey() {

                    return getValueQuietly().getName();
                }

                @Override
                protected Class getValue() throws NoSuchFieldException, NoSuchMethodException {

                    if (null == innerClass) innerClass = type.getDeclaredClasses()[i];

                    return innerClass;
                }
            });
        }
    }

    private static class FieldEntry extends TestEntry<String, Field> {

        public static String buildKey(Field field) {

            return Modifier.isStatic(field.getModifiers()) ?
                    field.getDeclaringClass().getName() + "." + field.getName() :
                    field.getName();
        }

        public FieldEntry(final Class type, final String name) {
            super(new Getter<String, Field>() {

                private Field field;

                @Override
                protected String getKey() {

                    return buildKey(getValueQuietly());
                }

                @Override
                protected Field getValue() throws NoSuchFieldException, NoSuchMethodException {

                    if (null == field) field = type.getDeclaredField(name);

                    return field;
                }
            });
        }
    }

    private static class ConstructorEntry<T> extends TestEntry<Object[], Constructor<T>> {

        public ConstructorEntry(final Class type, final Class... parameterTypes) {
            super(new Getter<Object[], Constructor<T>>() {

                @Override
                protected Object[] getKey() {

                    return new Object[]{type.getName(), parameterTypes};
                }

                @Override
                protected Constructor<T> getValue() throws NoSuchFieldException, NoSuchMethodException {

                    return type.getDeclaredConstructor(parameterTypes);
                }
            });
        }
    }

    private static class MethodEntry extends TestEntry<Object[], Method> {

        public MethodEntry(final Class type, final String name, final Class... parameterTypes) {
            super(new Getter<Object[], Method>() {

                @Override
                protected Object[] getKey() {

                    return new Object[]{name, parameterTypes};
                }

                @Override
                protected Method getValue() throws NoSuchFieldException, NoSuchMethodException {

                    return type.getDeclaredMethod(name, parameterTypes);
                }
            });
        }
    }
}
