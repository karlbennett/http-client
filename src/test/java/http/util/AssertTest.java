package http.util;

import org.junit.Test;

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
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullName() throws Exception {

        assertNotEmpty(null, " ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithEmptyValue() throws Exception {

        assertNotEmpty("value", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullValue() throws Exception {

        assertNotEmpty("value", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotEmptyWithNullNameAndValue() throws Exception {

        assertNotEmpty(null, null);
    }
}
