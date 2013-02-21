package http;

import http.attribute.Attribute;
import http.attribute.MultiAttributeMap;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

import static http.Attributes.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MultiAttributeMapTest {

    private static class TestMultiAttributeMap<T> extends MultiAttributeMap<Attribute<T>,
                Collection<Attribute<T>>> {

        public TestMultiAttributeMap(Map<String, Collection<Attribute<T>>> backingMap) {
            super(backingMap);
        }

        public TestMultiAttributeMap() {
        }

        public TestMultiAttributeMap(Map<String, Collection<Attribute<T>>> backingMap,
                                     MultiAttributeMap<Attribute<T>, Collection<Attribute<T>>> attributes) {
            super(backingMap, attributes);
        }

        public TestMultiAttributeMap(MultiAttributeMap<Attribute<T>, Collection<Attribute<T>>> attributes) {
            super(attributes);
        }

        public TestMultiAttributeMap(Map<String, Collection<Attribute<T>>> backingMap,
                                     Collection<Attribute<T>> attributes) {
            super(backingMap, attributes);
        }

        public TestMultiAttributeMap(Collection<Attribute<T>> attributes) {
            super(attributes);
        }

        @Override
        protected Collection<Attribute<T>> newCollection() {

            return new HashSet<Attribute<T>>();
        }
    };

    @Test
    public void testAdd() throws Exception {

        MultiAttributeMap<Attribute<String>, Collection<Attribute<String>>> map =
                new TestMultiAttributeMap<String>();

        Attribute<String> blankAttribute = new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE, null, TEST_ATTRIBUTE_OPERATOR);

        map.add(blankAttribute);

        Collection<Attribute<String>> attributes = map.get(TEST_ATTRIBUTE_NAME_ONE);

        assertEquals("the map should contain one header type.", 1, map.size());
        assertNotNull("the attribute should have been added to the map.", attributes);
        assertEquals("there should be one instance of the attribute in the map.", 1, attributes.size());
        assertTrue("the blank attribute should be in the map.", attributes.contains(blankAttribute));

        map.add(TEST_ATTRIBUTE_ONE);

        attributes = map.get(TEST_ATTRIBUTE_NAME_ONE);

        assertEquals("the map should still only contain one header type.", 1, map.size());
        assertNotNull("the attribute should have been added to the map.", attributes);
        assertEquals("there should be two instances of the attribute in the map.", 2, attributes.size());
        assertTrue("the blank attribute should still be in the map.", attributes.contains(blankAttribute));
        assertTrue("attribute one should be in the map.", attributes.contains(TEST_ATTRIBUTE_ONE));

        map.add(TEST_ATTRIBUTE_TWO);
        attributes = map.get(TEST_ATTRIBUTE_NAME_TWO);

        assertEquals("the map should contain two attributes type.", 2, map.size());
        assertNotNull("the attribute should have been added to the map.", attributes);
        assertEquals("there should be one instance of the attribute in the map.", 1, attributes.size());
        assertTrue("attribute two should be in the map.", attributes.contains(TEST_ATTRIBUTE_TWO));

        map.add(TEST_ATTRIBUTE_THREE);
        attributes = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertEquals("the map should contain three attributes type.", 3, map.size());
        assertNotNull("the attribute should have been added to the map.", attributes);
        assertEquals("there should be one instance of the attribute in the map.", 1, attributes.size());
        assertTrue("attribute three should be in the map.", attributes.contains(TEST_ATTRIBUTE_THREE));

        map.add(TEST_ATTRIBUTE_THREE);
        attributes = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertEquals("the map should still only contain three attributes type.", 3, map.size());
        assertNotNull("the attribute should still be in the map map.", attributes);
        assertEquals("there should still only be one instance of the attribute in the map.", 1, attributes.size());
        assertTrue("attribute three should be in the map.", attributes.contains(TEST_ATTRIBUTE_THREE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithNull() throws Exception {

        MultiAttributeMap<Attribute<String>, Collection<Attribute<String>>> map =
                new TestMultiAttributeMap<String>();

        map.add(null);
    }

    @Test
    public void testAddAll() throws Exception {

        Attribute<String> blankAttribute = new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE, null, TEST_ATTRIBUTE_OPERATOR);

        Collection<Attribute<String>> attributes = new HashSet<Attribute<String>>(TEST_ATTRIBUTES);
        attributes.add(blankAttribute);

        MultiAttributeMap<Attribute<String>, Collection<Attribute<String>>> map =
                new TestMultiAttributeMap<String>();

        map.addAll(attributes);

        assertEquals("the map should contain three headers.", 3, map.size());

        attributes = map.get(TEST_ATTRIBUTE_NAME_ONE);

        assertNotNull("attribute one should have been added to the map.", attributes);
        assertEquals("there should be two instances of attribute one in the map.", 2, attributes.size());
        assertTrue("the blank attribute should be in the map.", attributes.contains(blankAttribute));
        assertTrue("attribute one should be in the map.", attributes.contains(TEST_ATTRIBUTE_ONE));

        attributes = map.get(TEST_ATTRIBUTE_NAME_TWO);

        assertNotNull("attribute two should have been added to the map.", attributes);
        assertEquals("there should be one instance of attribute two in the map.", 1, attributes.size());
        assertTrue("attribute two should be in the map.", attributes.contains(TEST_ATTRIBUTE_TWO));

        attributes = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertNotNull("attribute three should have been added to the map.", attributes);
        assertEquals("there should be one instance of attribute three in the map.", 1, attributes.size());
        assertTrue("attribute three should be in the map.", attributes.contains(TEST_ATTRIBUTE_THREE));
    }

    @Test
    public void testAddAllWithEmptyCollection() throws Exception {

        MultiAttributeMap<Attribute<String>, Collection<Attribute<String>>> map =
                new TestMultiAttributeMap<String>();

        map.addAll(TEST_ATTRIBUTES);

        map.removeAll(Collections.<Attribute<String>>emptySet());

        assertEquals("the map should still contain all three attributes type.", 3, map.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllWithNull() throws Exception {

        MultiAttributeMap<Attribute<String>, Collection<Attribute<String>>> map =
                new TestMultiAttributeMap<String>();

        map.addAll(null);
    }

    @Test
    public void testRemove() throws Exception {

        MultiAttributeMap<Attribute<String>, Collection<Attribute<String>>> map =
                new TestMultiAttributeMap<String>();

        map.addAll(TEST_ATTRIBUTES);

        Collection<Attribute<String>> attributeOneValues = map.get(TEST_ATTRIBUTE_NAME_ONE);
        Collection<Attribute<String>> attributeTwoValues = map.get(TEST_ATTRIBUTE_NAME_TWO);
        Collection<Attribute<String>> attributeThreeValues = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertEquals("the map should contain three attributes.", 3, map.size());
        assertNotNull("attribute one should have been added to the map.", attributeOneValues);
        assertEquals("there should be one instance of attribute one in the map.", 1, attributeOneValues.size());
        assertTrue("the attribute one instance should be in the values.",
                attributeOneValues.contains(TEST_ATTRIBUTE_ONE));
        assertNotNull("attribute two should have been added to the map.", attributeTwoValues);
        assertEquals("there should be one instance of attribute two in the map.", 1, attributeTwoValues.size());
        assertTrue("the attribute two instance should be in the values.",
                attributeTwoValues.contains(TEST_ATTRIBUTE_TWO));
        assertNotNull("attribute three should have been added to the map.", attributeThreeValues);
        assertEquals("there should be one instance of attribute three in the map.", 1, attributeThreeValues.size());
        assertTrue("the attribute three instance should be in the values.",
                attributeThreeValues.contains(TEST_ATTRIBUTE_THREE));

        map.remove(TEST_ATTRIBUTE_ONE);

        attributeOneValues = map.get(TEST_ATTRIBUTE_NAME_ONE);
        attributeTwoValues = map.get(TEST_ATTRIBUTE_NAME_TWO);
        attributeThreeValues = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertEquals("the map should contain three attributes.", 2, map.size());
        assertNull("attribute one should have been removed.", attributeOneValues);
        assertNotNull("attribute two should have been added to the map.", attributeTwoValues);
        assertEquals("there should be one instance of attribute two in the map.", 1, attributeTwoValues.size());
        assertTrue("the attribute two instance should be in the values.",
                attributeTwoValues.contains(TEST_ATTRIBUTE_TWO));
        assertNotNull("attribute three should have been added to the map.", attributeThreeValues);
        assertEquals("there should be one instance of attribute three in the map.", 1, attributeThreeValues.size());
        assertTrue("the attribute three instance should be in the values.",
                attributeThreeValues.contains(TEST_ATTRIBUTE_THREE));

        map.remove(TEST_ATTRIBUTE_TWO);

        attributeOneValues = map.get(TEST_ATTRIBUTE_NAME_ONE);
        attributeTwoValues = map.get(TEST_ATTRIBUTE_NAME_TWO);
        attributeThreeValues = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertEquals("the map should contain three attributes.", 1, map.size());
        assertNull("attribute one should have been removed.", attributeOneValues);
        assertNull("attribute two should have been removed.", attributeTwoValues);
        assertNotNull("attribute three should have been added to the map.", attributeThreeValues);
        assertEquals("there should be one instance of attribute three in the map.", 1, attributeThreeValues.size());
        assertTrue("the attribute three instance should be in the values.",
                attributeThreeValues.contains(TEST_ATTRIBUTE_THREE));

        map.remove(TEST_ATTRIBUTE_THREE);

        attributeOneValues = map.get(TEST_ATTRIBUTE_NAME_ONE);
        attributeTwoValues = map.get(TEST_ATTRIBUTE_NAME_TWO);
        attributeThreeValues = map.get(TEST_ATTRIBUTE_NAME_THREE);

        assertEquals("the map should contain three attributes.", 0, map.size());
        assertNull("attribute one should have been removed.", attributeOneValues);
        assertNull("attribute two should have been removed.", attributeTwoValues);
        assertNull("attribute three should have been removed.", attributeThreeValues);
    }
}
