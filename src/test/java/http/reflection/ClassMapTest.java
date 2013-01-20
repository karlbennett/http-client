package http.reflection;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static http.reflection.ReflectionMapTest.*;
import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public class ClassMapTest {

    @Test
    public void testClassReflectionMapWithTestInterface() throws Exception {

        Map<String, Class> classMap = null;

        Set<Entry<String, Class>> entries = classMap.entrySet();

        assertEquals("classMap should only contain one entry.", 1, classMap.size());
        assertTrue("classMap should contain the TestInterface InnerTestClass class.",
                entries.contains(TEST_INTERFACE_INNER_TEST_CLASS));
    }

    @Test
    public void testClassReflectionMapWithTestOne() throws Exception {

        Map<String, Class> classMap = null;

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
    public void testClassReflectionMapWithTestTwo() throws Exception {

        Map<String, Class> classMap = null;

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
}
