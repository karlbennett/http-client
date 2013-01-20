package http.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;

import static http.reflection.ReflectionTestData.*;
import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public class ConstructorMapTest {

    @Test
    public void testConstructorReflectionMapWithTestInterface() throws Exception {

        Map<Object[], Constructor<TestInterface>> constructorMap = null;

        assertEquals("constructorMap should contain no entries.", 0, constructorMap.size());
    }

    @Test
    public void testConstructorReflectionMapWithTestOne() throws Exception {

        Map<Object[], Constructor<TestOne>> constructorMap = null;

        Set<Entry<Object[], Constructor<TestOne>>> entries = constructorMap.entrySet();

        assertEquals("constructorMap should contain one entry.", 2, constructorMap.size());
        assertTrue("constructorMap should contain the TestOne() constructor.",
                entries.contains(TEST_ONE_DEFAULT_CONSTRUCTOR));
        assertTrue("constructorMap should contain the TestOne(String) constructor.",
                entries.contains(TEST_ONE_CONSTRUCTOR));
    }

    @Test
    public void testConstructorReflectionMapWithTestTwo() throws Exception {

        Map<Object[], Constructor<TestTwo>> constructorMap = null;

        Set<Entry<Object[], Constructor<TestTwo>>> entries = constructorMap.entrySet();

        assertEquals("constructorMap should contain one entry.", 3, constructorMap.size());
        assertTrue("constructorMap should contain the TestOne() constructor.",
                entries.contains(TEST_ONE_DEFAULT_CONSTRUCTOR));
        assertTrue("constructorMap should contain the TestOne(String) constructor.",
                entries.contains(TEST_ONE_CONSTRUCTOR));
        assertTrue("constructorMap should contain the TestTwo(String) constructor.",
                entries.contains(TEST_TWO_CONSTRUCTOR));
    }
}
