package http;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class AttributeTest {

    private static final String TEST_ATTRIBUTE_NAME = "test_attribute_name";
    private static final String TEST_ATTRIBUTE_VALUE_ONE = "test_attribute_value_one";
    private static final String TEST_ATTRIBUTE_VALUE_TWO = "test_attribute_value_two";
    private static final String TEST_ATTRIBUTE_VALUE_THREE = "test_attribute_value_three";
    private static final Collection<String> TEST_ATTRIBUTE_VALUES = Arrays.asList(
            TEST_ATTRIBUTE_VALUE_ONE,
            TEST_ATTRIBUTE_VALUE_TWO,
            TEST_ATTRIBUTE_VALUE_THREE
    );

    @Test
    public void testCreateAttribute() throws Exception {

        new Attribute<Object>(TEST_ATTRIBUTE_NAME, new Object());
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

        new Attribute<Object>(TEST_ATTRIBUTE_NAME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndValue() throws Exception {

        new Attribute<Object>(null, null);
    }

    @Test
    public void testGetName() throws Exception {

        assertEquals("the attribute name should be correct.",
                TEST_ATTRIBUTE_NAME, new Attribute<Object>(TEST_ATTRIBUTE_NAME, new Object()).getName());
    }

    @Test
    public void testGetValues() throws Exception {

        assertEquals("the attribute values should be correct.", TEST_ATTRIBUTE_VALUES,
                new Attribute<String>(TEST_ATTRIBUTE_NAME,
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
                new Attribute<String>(TEST_ATTRIBUTE_NAME, TEST_ATTRIBUTE_VALUE_ONE).getValue());
    }
}
