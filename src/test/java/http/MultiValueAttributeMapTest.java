package http;

import http.header.Header;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static http.Attributes.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MultiValueAttributeMapTest {

    @Test
    public void testAddWithOneValue() throws Exception {

        MultiValueAttributeMap<MultiValueAttribute<String>> map = new MultiValueAttributeMap<>();

        map.add(new Header<>(TEST_ATTRIBUTE_NAME_ONE, (String) null));

        assertNotNull("the header should have been added to the map.", map.get(TEST_ATTRIBUTE_NAME_ONE));
        assertNull("the header should not contain a value.", map.get(TEST_ATTRIBUTE_NAME_ONE).getValue());
        assertEquals("the header should contain the correct values.",
                Collections.singletonList(null), map.get(TEST_ATTRIBUTE_NAME_ONE).getValues());

        map.add(new Header<>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE));

        assertNotNull("the header should have been added to the map.", map.get(TEST_ATTRIBUTE_NAME_ONE));
        assertEquals("the header should contain the correct value.", TEST_ATTRIBUTE_VALUE_ONE,
                map.get(TEST_ATTRIBUTE_NAME_ONE).getValue());
        assertEquals("the header should contain the correct values.",
                Collections.singletonList(TEST_ATTRIBUTE_VALUE_ONE), map.get(TEST_ATTRIBUTE_NAME_ONE).getValues());
    }

    @Test
    public void testAddWithMultipleValues() throws Exception {

        MultiValueAttributeMap<MultiValueAttribute<String>> map = new MultiValueAttributeMap<>();

        map.add(new Header<>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE));

        assertNotNull("the header should have been added to the map.", map.get(TEST_ATTRIBUTE_NAME_ONE));
        assertEquals("the headers first value should correct.", TEST_ATTRIBUTE_VALUE_ONE,
                map.get(TEST_ATTRIBUTE_NAME_ONE).getValue());
        assertEquals("the header should contain the correct values.",
                Collections.singletonList(TEST_ATTRIBUTE_VALUE_ONE), map.get(TEST_ATTRIBUTE_NAME_ONE).getValues());

        map.add(new Header<>(TEST_ATTRIBUTE_NAME_ONE, Arrays.asList(TEST_ATTRIBUTE_VALUE_TWO, null)));

        assertNotNull("the header should still be in the map.", map.get(TEST_ATTRIBUTE_NAME_ONE));
        assertEquals("the headers first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                map.get(TEST_ATTRIBUTE_NAME_ONE).getValue());
        assertEquals("the header should contain the correct values.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO
                ), map.get(TEST_ATTRIBUTE_NAME_ONE).getValues());

        map.add(new Header<>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_THREE));

        assertNotNull("the header should still be in the map.", map.get(TEST_ATTRIBUTE_NAME_ONE));
        assertEquals("the headers first value should still be correct.", TEST_ATTRIBUTE_VALUE_ONE,
                map.get(TEST_ATTRIBUTE_NAME_ONE).getValue());
        assertEquals("the header should contain the correct values.",
                Arrays.asList(
                        TEST_ATTRIBUTE_VALUE_ONE,
                        TEST_ATTRIBUTE_VALUE_TWO,
                        TEST_ATTRIBUTE_VALUE_THREE
                ), map.get(TEST_ATTRIBUTE_NAME_ONE).getValues());
    }
}
