package http.util;

import org.junit.Test;

import static http.util.Assert.assertNotNull;

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
}
