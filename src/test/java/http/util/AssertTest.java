package http.util;

import org.junit.Test;

import java.util.*;

import static http.util.Assert.assertNotNull;
import static http.util.Assert.assertNotEmpty;

/**
 * @author Karl Bennett
 */
public class AssertTest {

    @Test
    public void testAssertNotNullWithNonNullValue() throws Exception {

        assertNotNull("value", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotNullWithNullName() throws Exception {

        assertNotNull(null, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotNullWithNullValue() throws Exception {

        assertNotNull("value", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotNullWithNullNameAndValue() throws Exception {

        assertNotNull(null, null);
    }

    @Test
    public void testAssertNotEmptyWithNonNullValue() throws Exception {

        assertNotEmpty("value", "some text");
        assertNotEmpty("value", " ");
        assertNotEmpty("value", Collections.singleton(new Object()));
        assertNotEmpty("value", Collections.singletonList(new Object()));
        assertNotEmpty("value", Collections.singletonMap(new Object(), new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndString() throws Exception {

        assertNotEmpty(null, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndSet() throws Exception {

        assertNotEmpty(null, Collections.singleton(new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndList() throws Exception {

        assertNotEmpty(null, Collections.singletonList(new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndMap() throws Exception {

        assertNotEmpty(null, Collections.singletonMap(new Object(), new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithEmptyString() throws Exception {

        assertNotEmpty("value", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithEmptySet() throws Exception {

        assertNotEmpty("value", Collections.emptySet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithEmptyList() throws Exception {

        assertNotEmpty("value", Collections.emptyList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithEmptyMap() throws Exception {

        assertNotEmpty("value", Collections.emptyMap());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullString() throws Exception {

        assertNotEmpty("value", (String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullCollection() throws Exception {

        assertNotEmpty("value", (Collection) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullSet() throws Exception {

        assertNotEmpty("value", (Set) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullList() throws Exception {

        assertNotEmpty("value", (List) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullMap() throws Exception {

        assertNotEmpty("value", (Map) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndNullString() throws Exception {

        assertNotEmpty(null, (String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndNullCollection() throws Exception {

        assertNotEmpty(null, (Collection) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndNullSet() throws Exception {

        assertNotEmpty(null, (Set) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndNullList() throws Exception {

        assertNotEmpty(null, (List) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndNullMap() throws Exception {

        assertNotEmpty(null, (Map) null);
    }
}
