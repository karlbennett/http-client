package http.reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static http.reflection.ReflectionTestData.*;
import static java.util.Map.Entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public class FieldMapTest {

    @Test
    public void testFieldReflectionMapWithTestInterface() throws Exception {

        Map<String, Field> fieldMap = null;

        Set<Entry<String, Field>> entries = fieldMap.entrySet();

        assertEquals("fieldMap should only contain two entries.", 2, fieldMap.size());
        assertTrue("fieldMap should contain the TestInterface TEST constant.", entries.contains(TEST_INTERFACE_TEST));
        assertTrue("fieldMap should contain the TestInterface ZERO constant.", entries.contains(TEST_INTERFACE_ZERO));
    }

    @Test
    public void testFieldReflectionMapWithTestOne() throws Exception {

        Map<String, Field> fieldMap = null;

        Set<Entry<String, Field>> entries = fieldMap.entrySet();

        assertEquals("fieldMap should only contain two entries.", 5, fieldMap.size());
        assertTrue("fieldMap should contain the TestInterface TEST constant.", entries.contains(TEST_INTERFACE_TEST));
        assertTrue("fieldMap should contain the TestInterface ZERO constant.", entries.contains(TEST_INTERFACE_ZERO));
        assertTrue("fieldMap should contain the TestOne TEST constant.", entries.contains(TEST_ONE_TEST));
        assertTrue("fieldMap should contain the TestOne ONE constant.", entries.contains(TEST_ONE_ONE));
        assertTrue("fieldMap should contain the TestOne test instance field.", entries.contains(TEST_ONE_INSTANCE_TEST));
    }

    @Test
    public void testFieldReflectionMapWithTestTwo() throws Exception {

        Map<String, Field> fieldMap = null;

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
}
