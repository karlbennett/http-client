package http;

import org.junit.Test;

import java.util.Arrays;

import static http.Attributes.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class AttributeTest {

    @Test
    public void testCreateAttribute() throws Exception {

        new Attribute<Object>(TEST_ATTRIBUTE_NAME_ONE, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullName() throws Exception {

        new Attribute<Object>(null, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithEmptyName() throws Exception {

        new Attribute<Object>("", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullValue() throws Exception {

        new Attribute<Object>(TEST_ATTRIBUTE_NAME_ONE, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndValue() throws Exception {

        new Attribute<Object>(null, null);
    }

    @Test
    public void testGetName() throws Exception {

        assertEquals("the attribute name should be correct.",
                TEST_ATTRIBUTE_NAME_ONE, new Attribute<Object>(TEST_ATTRIBUTE_NAME_ONE, new Object()).getName());
    }

    @Test
    public void testGetValues() throws Exception {

        assertEquals("the attribute values should be correct.", TEST_ATTRIBUTE_VALUES,
                new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE,
                        Arrays.asList(
                                TEST_ATTRIBUTE_VALUE_ONE,
                                TEST_ATTRIBUTE_VALUE_TWO,
                                TEST_ATTRIBUTE_VALUE_THREE
                        )
                ).getValues()
        );
    }

    @Test
    public void testGetValue() throws Exception {

        assertEquals("the attribute value should be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE).getValue());
    }
}
