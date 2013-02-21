package http;

import http.attribute.Attribute;
import org.junit.Test;

import static http.Attributes.TEST_ATTRIBUTE_OPERATOR;
import static http.Attributes.TEST_ATTRIBUTE_NAME_ONE;
import static http.Attributes.TEST_ATTRIBUTE_VALUE_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Karl Bennett
 */
public class AttributeTest {

    @Test
    public void testCreateAttribute() throws Exception {

        new Attribute<>(TEST_ATTRIBUTE_NAME_ONE, new Object(), TEST_ATTRIBUTE_OPERATOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullName() throws Exception {

        new Attribute<>(null, new Object(), TEST_ATTRIBUTE_OPERATOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithEmptyName() throws Exception {

        new Attribute<>("", new Object(), TEST_ATTRIBUTE_OPERATOR);
    }

    @Test
    public void testCreateAttributeWithNullValue() throws Exception {

        Attribute attribute = new Attribute<>(TEST_ATTRIBUTE_NAME_ONE, null, TEST_ATTRIBUTE_OPERATOR);

        assertNull("attribute value should be null.", attribute.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndValue() throws Exception {

        new Attribute<>(null, null, TEST_ATTRIBUTE_OPERATOR);
    }

    @Test
    public void testGetName() throws Exception {

        assertEquals("the attribute name should be correct.",
                TEST_ATTRIBUTE_NAME_ONE,
                new Attribute<>(TEST_ATTRIBUTE_NAME_ONE, new Object(), TEST_ATTRIBUTE_OPERATOR).getName());
    }

    @Test
    public void testGetValue() throws Exception {

        assertEquals("the attribute value should be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                new Attribute<>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE, TEST_ATTRIBUTE_OPERATOR).getValue());
    }
}
