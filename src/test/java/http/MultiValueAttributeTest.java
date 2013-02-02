package http;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static http.Attributes.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Karl Bennett
 */
public class MultiValueAttributeTest {

    @Test
    public void testCreateAttribute() throws Exception {

        new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, new Object());
        new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, Collections.singletonList(new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndSingleValue() throws Exception {

        new MultiValueAttribute<>(null, new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndValues() throws Exception {

        new MultiValueAttribute<>(null, Collections.singletonList(new Object()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithEmptyNameAndSingleValue() throws Exception {

        new MultiValueAttribute<>("", new Object());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithEmptyNameAndValues() throws Exception {

        new MultiValueAttribute<>("", Collections.singletonList(new Object()));
    }

    @Test
    public void testCreateAttributeWithNullValue() throws Exception {

        MultiValueAttribute attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, (Object) null);

        assertNull("attribute value should be null.", attribute.getValue());
        assertNull("attribute values should be null.", attribute.getValues());
    }

    @Test
    public void testCreateAttributeWithNullValues() throws Exception {

        MultiValueAttribute attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, (List<Object>) null);;

        assertNull("attribute value should be null.", attribute.getValue());
        assertNull("attribute values should be null.", attribute.getValues());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndNullValue() throws Exception {

        new MultiValueAttribute<>(null, (Object) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullNameAndNullValues() throws Exception {

        new MultiValueAttribute<>(null, (List<Object>) null);
    }

    @Test
    public void testGetValues() throws Exception {

        assertEquals("the attribute values should be correct.", TEST_ATTRIBUTE_VALUES,
                new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE,
                        Arrays.asList(
                                TEST_ATTRIBUTE_VALUE_ONE,
                                TEST_ATTRIBUTE_VALUE_TWO,
                                TEST_ATTRIBUTE_VALUE_THREE
                        )
                ).getValues()
        );
    }
}
