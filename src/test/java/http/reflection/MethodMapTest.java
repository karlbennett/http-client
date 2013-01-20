package http.reflection;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static http.reflection.ReflectionMapTest.*;
import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public class MethodMapTest {

    @Test
    public void testMethodReflectionMapWithTestInterface() throws Exception {

        Map<Object[], Method> methodMap = null;

        Set<Entry<Object[], Method>> entries = methodMap.entrySet();

        assertEquals("methodMap should only contain one entry.", 1, methodMap.size());
        assertTrue("methodMap should contain the TestInterface getTest method.",
                entries.contains(TEST_INTERFACE_GET_TEST));
    }

    @Test
    public void testMethodReflectionMapWithTestOne() throws Exception {

        Map<Object[], Method> methodMap = null;

        Set<Entry<Object[], Method>> entries = methodMap.entrySet();

        assertEquals("methodMap should only contain one entry.", 1, methodMap.size());
        assertTrue("methodMap should contain the TestOne getTest method.",
                entries.contains(TEST_ONE_GET_TEST));
    }

    @Test
    public void testMethodReflectionMapWithTestTwo() throws Exception {

        Map<Object[], Method> methodMap = null;

        Set<Entry<Object[], Method>> entries = methodMap.entrySet();

        assertEquals("methodMap should only contain one entry.", 2, methodMap.size());
        assertTrue("methodMap should contain the TestTwo getTest method.",
                entries.contains(TEST_TWO_GET_TEST));
        assertTrue("methodMap should contain the TestTwo setTest method.",
                entries.contains(TEST_TWO_SET_TEST));
    }
}
