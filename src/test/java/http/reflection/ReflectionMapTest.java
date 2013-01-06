package http.reflection;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.Map;
import java.util.Set;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Map.Entry;
import static http.reflection.ReflectionMap.Key;

import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class ReflectionMapTest {

    private static interface TestInterface {

        public static final String TEST = "Test";
        public static final int ZERO = 0;

        public class InnerTestClass {
        }

        String getTest();
    }

    private static class TestOne implements TestInterface {

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

    private static class TestTwo extends TestOne {

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


    private static final SimpleEntry<String, Field> TEST_INTERFACE_TEST = new FieldEntity(
            TestInterface.class, "TEST");
    private static final SimpleEntry<String, Field> TEST_INTERFACE_ZERO = new FieldEntity(
            TestInterface.class, "ZERO");
    private static final SimpleEntry<String, Class> TEST_INTERFACE_INNER_TEST_CLASS = new ClassEntity(
            TestInterface.class, 0);
    private static final SimpleEntry<Key, Method> TEST_INTERFACE_GET_TEST = new MethodEntity(
            TestInterface.class, "getTest");

    private static final SimpleEntry<String, Field> TEST_ONE_TEST = new FieldEntity(TestOne.class, "TEST");
    private static final SimpleEntry<String, Field> TEST_ONE_ONE = new FieldEntity(TestOne.class, "ONE");
    private static final SimpleEntry<String, Class> TEST_ONE_INNER_TEST_CLASS = new ClassEntity(TestOne.class, 0);
    private static final SimpleEntry<String, Class> INNER_TEST_CLASS_ONE = new ClassEntity(TestOne.class, 1);
    private static final SimpleEntry<String, Field> TEST_ONE_INSTANCE_TEST = new FieldEntity(TestOne.class, "test");
    private static final SimpleEntry<Key, Constructor<TestOne>> TEST_ONE_DEFAULT_CONSTRUCTOR =
            new ConstructorEntity<TestOne>(TestOne.class);
    private static final SimpleEntry<Key, Constructor<TestOne>> TEST_ONE_CONSTRUCTOR = new ConstructorEntity<TestOne>(
            TestOne.class, String.class);
    private static final SimpleEntry<Key, Method> TEST_ONE_GET_TEST = new MethodEntity(
            TestOne.class, "getTest");

    private static final SimpleEntry<String, Field> TEST_TWO_TEST = new FieldEntity(TestTwo.class, "TEST");
    private static final SimpleEntry<String, Field> TEST_TWO_TWO = new FieldEntity(TestTwo.class, "TWO");
    private static final SimpleEntry<String, Class> TEST_TWO_INNER_TEST_CLASS = new ClassEntity(TestTwo.class, 0);
    private static final SimpleEntry<String, Class> INNER_TEST_CLASS_TWO = new ClassEntity(TestTwo.class, 1);
    private static final SimpleEntry<String, Field> TEST_TWO_INSTANCE_TEST = new FieldEntity(TestTwo.class, "test");
    private static final SimpleEntry<Key, Constructor<TestTwo>> TEST_TWO_CONSTRUCTOR = new ConstructorEntity<TestTwo>(
            TestTwo.class, String.class);
    private static final SimpleEntry<Key, Method> TEST_TWO_GET_TEST = new MethodEntity(
            TestTwo.class, "getTest");
    private static final SimpleEntry<Key, Method> TEST_TWO_SET_TEST = new MethodEntity(
            TestTwo.class, "setTest", String.class);


    @Test
    public void testClassReflectionMapWithTestInterface() throws Exception {

        Map<String, Class> classMap = new ClassRelfectionMap<TestInterface>(TestInterface.class);

        Set<Entry<String, Class>> entries = classMap.entrySet();

        assertEquals("classMap should only contain one entry.", 1, classMap.size());
        assertTrue("classMap should contain the TestInterface InnerTestClass class.",
                entries.contains(TEST_INTERFACE_INNER_TEST_CLASS));
    }

    @Test
    public void testFieldReflectionMapWithTestInterface() throws Exception {

        Map<String, Field> fieldMap = new FieldRelfectionMap<TestInterface>(TestInterface.class);

        Set<Entry<String, Field>> entries = fieldMap.entrySet();

        assertEquals("fieldMap should only contain two entries.", 2, fieldMap.size());
        assertTrue("fieldMap should contain the TestInterface TEST constant.", entries.contains(TEST_INTERFACE_TEST));
        assertTrue("fieldMap should contain the TestInterface ZERO constant.", entries.contains(TEST_INTERFACE_ZERO));
    }

    @Test
    public void testConstructorReflectionMapWithTestInterface() throws Exception {

        Map<Key, Constructor<TestInterface>> constructorMap = new ConstructorRelfectionMap<TestInterface>(
                TestInterface.class);

        assertEquals("constructorMap should contain no entries.", 0, constructorMap.size());
    }

    @Test
    public void testMethodReflectionMapWithTestInterface() throws Exception {

        Map<Key, Method> methodMap = new MethodRelfectionMap<TestInterface>(TestInterface.class);

        Set<Entry<Key, Method>> entries = methodMap.entrySet();

        assertEquals("methodMap should only contain one entry.", 1, methodMap.size());
        assertTrue("methodMap should contain the TestInterface getTest method.",
                entries.contains(TEST_INTERFACE_GET_TEST));
    }

    @Test
    public void testClassReflectionMapWithTestOne() throws Exception {

        Map<String, Class> classMap = new ClassRelfectionMap<TestOne>(TestOne.class);

        Set<Entry<String, Class>> entries = classMap.entrySet();

        assertEquals("classMap should only contain two entries.", 3, classMap.size());
        assertTrue("classMap should contain the TestInterface InnerTestClass class.",
                entries.contains(TEST_INTERFACE_INNER_TEST_CLASS));
        assertTrue("classMap should contain the TestOne InnerTestClass class.",
                entries.contains(TEST_ONE_INNER_TEST_CLASS));
        assertTrue("classMap should contain the TestOne InnerTestOneClass class.",
                entries.contains(INNER_TEST_CLASS_ONE));
    }

    @Test
    public void testFieldReflectionMapWithTestOne() throws Exception {

        Map<String, Field> fieldMap = new FieldRelfectionMap<TestOne>(TestOne.class);

        Set<Entry<String, Field>> entries = fieldMap.entrySet();

        assertEquals("fieldMap should only contain two entries.", 5, fieldMap.size());
        assertTrue("fieldMap should contain the TestInterface TEST constant.", entries.contains(TEST_INTERFACE_TEST));
        assertTrue("fieldMap should contain the TestInterface ZERO constant.", entries.contains(TEST_INTERFACE_ZERO));
        assertTrue("fieldMap should contain the TestOne TEST constant.", entries.contains(TEST_ONE_TEST));
        assertTrue("fieldMap should contain the TestOne ONE constant.", entries.contains(TEST_ONE_ONE));
        assertTrue("fieldMap should contain the TestOne test instance field.", entries.contains(TEST_ONE_INSTANCE_TEST));
    }

    @Test
    public void testConstructorReflectionMapWithTestOne() throws Exception {

        Map<Key, Constructor<TestOne>> constructorMap = new ConstructorRelfectionMap<TestOne>(
                TestOne.class);

        Set<Entry<Key, Constructor<TestOne>>> entries = constructorMap.entrySet();

        assertEquals("constructorMap should contain one entry.", 2, constructorMap.size());
        assertTrue("constructorMap should contain the TestOne() constructor.",
                entries.contains(TEST_ONE_DEFAULT_CONSTRUCTOR));
        assertTrue("constructorMap should contain the TestOne(String) constructor.",
                entries.contains(TEST_ONE_CONSTRUCTOR));
    }

    @Test
    public void testMethodReflectionMapWithTestOne() throws Exception {

        Map<Key, Method> methodMap = new MethodRelfectionMap<TestOne>(TestOne.class);

        Set<Entry<Key, Method>> entries = methodMap.entrySet();

        assertEquals("methodMap should only contain one entry.", 1, methodMap.size());
        assertTrue("methodMap should contain the TestOne getTest method.",
                entries.contains(TEST_ONE_GET_TEST));
    }

    @Test
    public void testClassReflectionMapWithTestTwo() throws Exception {

        Map<String, Class> classMap = new ClassRelfectionMap<TestTwo>(TestTwo.class);

        Set<Entry<String, Class>> entries = classMap.entrySet();

        assertEquals("classMap should only contain two entries.", 5, classMap.size());
        assertTrue("classMap should contain the TestInterface InnerTestClass class.",
                entries.contains(TEST_INTERFACE_INNER_TEST_CLASS));
        assertTrue("classMap should contain the TestOne InnerTestClass class.",
                entries.contains(TEST_ONE_INNER_TEST_CLASS));
        assertTrue("classMap should contain the TestOne InnerTestOneClass class.",
                entries.contains(INNER_TEST_CLASS_ONE));
        assertTrue("classMap should contain the TestTwo InnerTestClass class.",
                entries.contains(TEST_TWO_INNER_TEST_CLASS));
        assertTrue("classMap should contain the TestTwo InnerTestTwoClass class.",
                entries.contains(INNER_TEST_CLASS_TWO));
    }

    @Test
    public void testFieldReflectionMapWithTestTwo() throws Exception {

        Map<String, Field> fieldMap = new FieldRelfectionMap<TestTwo>(TestTwo.class);

        Set<Entry<String, Field>> entries = fieldMap.entrySet();

        assertEquals("fieldMap should only contain seven entries.", 7, fieldMap.size());
        assertTrue("fieldMap should contain the TestInterface TEST constant.", entries.contains(TEST_INTERFACE_TEST));
        assertTrue("fieldMap should contain the TestInterface ZERO constant.", entries.contains(TEST_INTERFACE_ZERO));
        assertTrue("fieldMap should contain the TestOne TEST constant.", entries.contains(TEST_ONE_TEST));
        assertTrue("fieldMap should contain the TestOne ONE constant.", entries.contains(TEST_ONE_ONE));
        assertTrue("fieldMap should contain the TestTwo TEST constant.", entries.contains(TEST_TWO_TEST));
        assertTrue("fieldMap should contain the TestTwo TWO constant.", entries.contains(TEST_TWO_TWO));
        assertTrue("fieldMap should contain the TestTwo test instance field.", entries.contains(TEST_TWO_INSTANCE_TEST));
    }

    @Test
    public void testConstructorReflectionMapWithTestTwo() throws Exception {

        Map<Key, Constructor<TestTwo>> constructorMap = new ConstructorRelfectionMap<TestTwo>(
                TestTwo.class);

        Set<Entry<Key, Constructor<TestTwo>>> entries = constructorMap.entrySet();

        assertEquals("constructorMap should contain one entry.", 3, constructorMap.size());
        assertTrue("constructorMap should contain the TestOne() constructor.",
                entries.contains(TEST_ONE_DEFAULT_CONSTRUCTOR));
        assertTrue("constructorMap should contain the TestOne(String) constructor.",
                entries.contains(TEST_ONE_CONSTRUCTOR));
        assertTrue("constructorMap should contain the TestTwo(String) constructor.",
                entries.contains(TEST_TWO_CONSTRUCTOR));
    }

    @Test
    public void testMethodReflectionMapWithTestTwo() throws Exception {

        Map<Key, Method> methodMap = new MethodRelfectionMap<TestTwo>(TestTwo.class);

        Set<Entry<Key, Method>> entries = methodMap.entrySet();

        assertEquals("methodMap should only contain one entry.", 2, methodMap.size());
        assertTrue("methodMap should contain the TestTwo getTest method.",
                entries.contains(TEST_TWO_GET_TEST));
        assertTrue("methodMap should contain the TestTwo setTest method.",
                entries.contains(TEST_TWO_SET_TEST));
    }


    private static abstract class TestEntity<K, M> extends SimpleEntry<K, M> {

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


        public TestEntity(Getter<K, M> getter) {
            super(getter.getKey(), getter.getValueQuietly());
        }
    }

    private static class ClassEntity extends TestEntity<String, Class> {

        public ClassEntity(final Class type, final int i) {
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

    private static class FieldEntity extends TestEntity<String, Field> {

        public static String buildKey(Field field) {

            return Modifier.isStatic(field.getModifiers()) ?
                    field.getDeclaringClass().getName() + "." + field.getName() :
                    field.getName();
        }

        public FieldEntity(final Class type, final String name) {
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

    private static class ConstructorEntity<T> extends TestEntity<Key, Constructor<T>> {

        public ConstructorEntity(final Class type, final Class... parameterTypes) {
            super(new Getter<Key, Constructor<T>>() {

                @Override
                protected Key getKey() {

                    return new Key(type.getName(), parameterTypes);
                }

                @Override
                protected Constructor<T> getValue() throws NoSuchFieldException, NoSuchMethodException {

                    return type.getDeclaredConstructor(parameterTypes);
                }
            });
        }
    }

    private static class MethodEntity extends TestEntity<Key, Method> {

        public MethodEntity(final Class type, final String name, final Class... parameterTypes) {
            super(new Getter<Key, Method>() {

                @Override
                protected Key getKey() {

                    return new Key(name, parameterTypes);
                }

                @Override
                protected Method getValue() throws NoSuchFieldException, NoSuchMethodException {

                    return type.getDeclaredMethod(name, parameterTypes);
                }
            });
        }
    }

    private static class ClassRelfectionMap<T> extends ReflectionMap<T, String, Class> {

        public ClassRelfectionMap(Class<T> type) {
            super(
                    new PropertiesInvoker<Class>() {

                        @Override
                        public Class[] invoke(Class<?> type) throws NoSuchFieldException, NoSuchMethodException {

                            return type.getDeclaredClasses();
                        }
                    },

                    new EntryBuilder<String, Class>() {

                        @Override
                        public Entry<String, Class> buildEntry(Class member) {

                            return new SimpleEntry<String, Class>(member.getName(), member);
                        }
                    },

                    type
            );
        }
    }

    private static class FieldRelfectionMap<T> extends ReflectionMap<T, String, Field> {

        public FieldRelfectionMap(Class<T> type) {
            super(
                    new PropertiesInvoker<Field>() {

                        @Override
                        public Field[] invoke(Class<?> type) throws NoSuchFieldException, NoSuchMethodException {

                            return type.getDeclaredFields();
                        }
                    },

                    new EntryBuilder<String, Field>() {

                        @Override
                        public Entry<String, Field> buildEntry(Field member) {

                            return new SimpleEntry<String, Field>(FieldEntity.buildKey(member), member);
                        }
                    },

                    type
            );
        }
    }

    private static class ConstructorRelfectionMap<T> extends ReflectionMap<T, Key, Constructor<T>> {

        public ConstructorRelfectionMap(Class<T> type) {
            super(
                    new PropertiesInvoker<Constructor<T>>() {

                        @Override
                        public Constructor<T>[] invoke(Class<?> type) throws NoSuchFieldException, NoSuchMethodException {

                            return (Constructor<T>[]) type.getDeclaredConstructors();
                        }
                    },

                    new EntryBuilder<Key, Constructor<T>>() {

                        @Override
                        public Entry<Key, Constructor<T>> buildEntry(Constructor<T> member) {

                            return new SimpleEntry<Key, Constructor<T>>(new Key(member.getDeclaringClass().getName(),
                                    member.getParameterTypes()), member);
                        }
                    },

                    type
            );
        }
    }

    private static class MethodRelfectionMap<T> extends ReflectionMap<T, Key, Method> {

        public MethodRelfectionMap(final Class<T> type) {
            super(
                    new PropertiesInvoker<Method>() {

                        @Override
                        public Method[] invoke(Class<?> type) throws NoSuchFieldException, NoSuchMethodException {

                            return type.getDeclaredMethods();
                        }
                    },

                    new EntryBuilder<Key, Method>() {

                        @Override
                        public Entry<Key, Method> buildEntry(Method member) {

                            return new SimpleEntry<Key, Method>(new Key(member.getName(),
                                    member.getParameterTypes()), member);
                        }
                    },

                    type
            );
        }
    }
}
