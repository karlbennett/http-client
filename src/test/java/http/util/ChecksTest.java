package http.util;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static http.util.Checks.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class ChecksTest {

    @Test
    public void testIsNull() throws Exception {

        assertTrue("a null value should return true.", isNull(null));
        assertFalse("an Object instance should return false.", isNull(new Object()));
        assertFalse("an int instance should return false.", isNull(0));
        assertFalse("a boolean instance should return false.", isNull(true));
        assertFalse("an empty String should return false.", isNull(""));
    }

    @Test
    public void testIsNotNull() throws Exception {

        assertFalse("a null value should return false.", isNotNull(null));
        assertTrue("an Object instance should return true.", isNotNull(new Object()));
        assertTrue("an int instance should return true.", isNotNull(0));
        assertTrue("a boolean instance should return true.", isNotNull(true));
        assertTrue("an empty String should return true.", isNotNull(""));
    }

    @Test
    public void testIsEmptyString() throws Exception {

        assertTrue("a null String should return true.", isEmpty((String) null));
        assertTrue("an empty String should return true.", isEmpty(""));
        assertFalse("a non empty String should return false.", isEmpty("not empty"));
        assertFalse("a String containing only white space should return false.", isEmpty(" "));
    }

    @Test
    public void testIsNotEmptyString() throws Exception {

        assertFalse("a null String should return false.", isNotEmpty((String) null));
        assertFalse("an empty String should return false.", isNotEmpty(""));
        assertTrue("a non empty String should return true.", isNotEmpty("not empty"));
        assertTrue("a String containing only white space should return true.", isNotEmpty(" "));
    }

    @Test
    public void testIsEmptyCollection() throws Exception {

        assertTrue("a null Collection should return true.", isEmpty((Collection) null));
        assertTrue("an empty Set should return true.", isEmpty(Collections.emptySet()));
        assertTrue("an empty List should return true.", isEmpty(Collections.emptyList()));
        assertFalse("a non empty Set should return false.", isEmpty(Collections.singleton(new Object())));
        assertFalse("a non empty List should return false.", isEmpty(Collections.singletonList(new Object())));
        assertFalse("a Collection containing only null values should return false.", isEmpty(Collections.singletonList(null)));
    }

    @Test
    public void testIsNotEmptyCollection() throws Exception {

        assertFalse("a null Collection should return false.", isNotEmpty((Collection) null));
        assertFalse("an empty Set should return false.", isNotEmpty(Collections.emptySet()));
        assertFalse("an empty List should return false.", isNotEmpty(Collections.emptyList()));
        assertTrue("a non empty Set should return true.", isNotEmpty(Collections.singleton(new Object())));
        assertTrue("a non empty List should return true.", isNotEmpty(Collections.singletonList(new Object())));
        assertTrue("a Collection containing only null values should return true.", isNotEmpty(Collections.singletonList(null)));
    }

    @Test
    public void testIsEmptyMap() throws Exception {

        assertTrue("a null Map should return true.", isEmpty((Map) null));
        assertTrue("an empty Map should return true.", isEmpty(Collections.emptyMap()));
        assertFalse("a non empty Map should return false.", isEmpty(Collections.singletonMap(new Object(), new Object())));
        assertFalse("a Map containing only null values should return false.", isEmpty(Collections.singletonMap(null, null)));
    }

    @Test
    public void testIsNotEmptyMap() throws Exception {

        assertFalse("a null Map should return false.", isNotEmpty((Map) null));
        assertFalse("an empty Map should return false.", isNotEmpty(Collections.emptyMap()));
        assertTrue("a non empty Map should return true.", isNotEmpty(Collections.singletonMap(new Object(), new Object())));
        assertTrue("a Map containing only null values should return true.", isNotEmpty(Collections.singletonMap(null, null)));
    }
}
