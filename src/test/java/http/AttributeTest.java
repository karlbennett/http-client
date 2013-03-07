package http;

import http.attribute.Attribute;
import org.junit.Test;

import static http.Attributes.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Karl Bennett
 */
public class AttributeTest {

    @Test
    public void testCreateAttribute() throws Exception {

        Attribute<String> attribute = new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE,
                TEST_ATTRIBUTE_OPERATOR);

        assertEquals("the attributes name should be correct.", TEST_ATTRIBUTE_NAME_ONE, attribute.getName());
        assertEquals("the attributes value should be correct.", TEST_ATTRIBUTE_VALUE_ONE, attribute.getValue());
        assertEquals("the attributes operator should be correct.", TEST_ATTRIBUTE_OPERATOR, attribute.getOperator());
    }

    @Test
    public void testCopyAttribute() throws Exception {

        assertEquals("the attribute should have copied correctly.", TEST_ATTRIBUTE_ONE,
                new Attribute<String>(TEST_ATTRIBUTE_ONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullName() throws Exception {

        new Attribute<Object>(null, new Object(), TEST_ATTRIBUTE_OPERATOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithEmptyName() throws Exception {

        new Attribute<Object>("", new Object(), TEST_ATTRIBUTE_OPERATOR);
    }

    @Test
    public void testCreateAttributeWithNullValue() throws Exception {

        Attribute attribute = new Attribute<Object>(TEST_ATTRIBUTE_NAME_ONE, null, TEST_ATTRIBUTE_OPERATOR);

        assertNull("attribute value should be null.", attribute.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndValue() throws Exception {

        new Attribute<Object>(null, null, TEST_ATTRIBUTE_OPERATOR);
    }

    @Test
    public void testGetName() throws Exception {

        assertEquals("the attribute name should be correct.",
                TEST_ATTRIBUTE_NAME_ONE,
                new Attribute<Object>(TEST_ATTRIBUTE_NAME_ONE, new Object(), TEST_ATTRIBUTE_OPERATOR).getName());
    }

    @Test
    public void testGetValue() throws Exception {

        assertEquals("the attribute value should be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE, TEST_ATTRIBUTE_OPERATOR).getValue());
    }
}
