package http;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static http.Attributes.*;
import static org.junit.Assert.*;

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
        assertNotNull("attribute values should not be null.", attribute.getValues());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeWithNullValues() throws Exception {

        new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, (List<Object>) null);
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

    @Test(expected = UnsupportedOperationException.class)
    public void testGetValuesIsUnmodifiable() throws Exception {

        List<String> values = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE,
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO,
                        TEST_ATTRIBUTE_VALUE_THREE
                )
        ).getValues();

        values.add("this should break.");
    }

    @Test
    public void testAddValue() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE,
                TEST_ATTRIBUTE_VALUE_ONE);

        assertNotNull("the attribute should contain a value.", attribute.getValue());
        assertEquals("the attributes first value should be correct.", TEST_ATTRIBUTE_VALUE_ONE, attribute.getValue());
        assertEquals("the attributes values should be correct.", Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE),
                attribute.getValues());

        attribute.addValue(TEST_ATTRIBUTE_VALUE_TWO);

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());

        attribute.addValue(null);

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());

        attribute.addValue(TEST_ATTRIBUTE_VALUE_THREE);

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO,
                        TEST_ATTRIBUTE_VALUE_THREE
                ),
                attribute.getValues());

        attribute.addValue("");

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO,
                        TEST_ATTRIBUTE_VALUE_THREE
                ),
                attribute.getValues());
    }

    @Test
    public void testAddValueWhenConstructedWithEmptyValue() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, "");

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addValue("");

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addValue(TEST_ATTRIBUTE_VALUE_ONE);

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.", Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE),
                attribute.getValues());
    }

    @Test
    public void testAddValueWhenConstructedWithNullValue() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, (String) null);

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addValue(null);

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addValue(TEST_ATTRIBUTE_VALUE_ONE);

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.", Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE),
                attribute.getValues());
    }

    @Test
    public void testAddAllValues() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE,
                TEST_ATTRIBUTE_VALUE_ONE);

        assertNotNull("the attribute should contain a value.", attribute.getValue());
        assertEquals("the attributes first value should be correct.", TEST_ATTRIBUTE_VALUE_ONE, attribute.getValue());
        assertEquals("the attributes values should be correct.", Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE),
                attribute.getValues());

        attribute.addAllValues(Arrays.asList(TEST_ATTRIBUTE_VALUE_TWO, null));

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());

        attribute.addAllValues(Collections.singletonList(TEST_ATTRIBUTE_VALUE_THREE));

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO,
                        TEST_ATTRIBUTE_VALUE_THREE
                ),
                attribute.getValues());
    }

    @Test
    public void testAddAllValuesWhenConstructedWithEmptyValue() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, "");

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addAllValues(Arrays.asList("", ""));

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addAllValues(Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE, TEST_ATTRIBUTE_VALUE_TWO));

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());
    }

    @Test
    public void testAddAllValuesWhenConstructedWithNullValue() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE, (String) null);

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addAllValues(Arrays.asList((String) null, null));

        assertNull("the attribute should not contain a value.", attribute.getValue());
        assertEquals("the attributes values should be empty.", Collections.emptyList(), attribute.getValues());

        attribute.addAllValues(Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE, TEST_ATTRIBUTE_VALUE_TWO));

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());
    }

    @Test
    public void testAddValueWhenConstructedWithUnmodifiableList() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE,
                Collections.unmodifiableList(Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE)));

        assertNotNull("the attribute should contain a value.", attribute.getValue());
        assertEquals("the attributes first value should be correct.", TEST_ATTRIBUTE_VALUE_ONE, attribute.getValue());
        assertEquals("the attributes values should be correct.", Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE),
                attribute.getValues());

        attribute.addValue(TEST_ATTRIBUTE_VALUE_TWO);

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());
    }

    @Test
    public void testAddAllValuesWhenConstructedWithUnmodifiableList() throws Exception {

        MultiValueAttribute<String> attribute = new MultiValueAttribute<>(TEST_ATTRIBUTE_NAME_ONE,
                Collections.unmodifiableList(Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE)));

        assertNotNull("the attribute should contain a value.", attribute.getValue());
        assertEquals("the attributes first value should be correct.", TEST_ATTRIBUTE_VALUE_ONE, attribute.getValue());
        assertEquals("the attributes values should be correct.", Arrays.asList(TEST_ATTRIBUTE_VALUE_ONE),
                attribute.getValues());

        attribute.addAllValues(Arrays.asList(TEST_ATTRIBUTE_VALUE_TWO, null));

        assertNotNull("the attribute should still contain a value.", attribute.getValue());
        assertEquals("the attributes first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                attribute.getValue());
        assertEquals("the attributes values should be correct.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ),
                attribute.getValues());
    }
}
