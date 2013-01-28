package http;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static http.Attributes.*;
import static org.junit.Assert.*;


/**
 * @author Karl Bennett
 */
public class AttributeMapTest {

    private AttributeMap<Attribute> emptyAttributes;
    private AttributeMap<Attribute<String>> attributes;


    @Before
    public void setUp() throws Exception {

        emptyAttributes = new AttributeMap<>();
        attributes = new AttributeMap<>(TEST_ATTRIBUTES);
    }


    @Test
    public void testCreateAttributeMap() throws Exception {

        Map<String, Attribute<String>> attributes = new AttributeMap<>();

        assertEquals("a new empty attribute map should have size 0.", 0, emptyAttributes.size());
    }

    @Test
    public void testAttributeMapCopy() throws Exception {

        Map<String, Attribute<String>> attributes = new AttributeMap<>(this.attributes);

        assertEquals(TEST_ATTRIBUTE_ONE + " should be in the copied attribute map.",
                TEST_ATTRIBUTE_ONE, attributes.get(TEST_ATTRIBUTE_NAME_ONE));

        assertEquals(TEST_ATTRIBUTE_TWO + " should be in the copied attribute map.",
                TEST_ATTRIBUTE_TWO, attributes.get(TEST_ATTRIBUTE_NAME_TWO));

        assertEquals(TEST_ATTRIBUTE_THREE + " should be in the copied attribute map.",
                TEST_ATTRIBUTE_THREE, attributes.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testCreateAttributeMapWithBackingMap() throws Exception {

        Map<String, Attribute<String>> backingMap = new TreeMap<>();

        Map<String, Attribute<String>> attributes = new AttributeMap<>(backingMap);
        attributes.putAll(TEST_ATTRIBUTES_MAP);

        assertEquals(TEST_ATTRIBUTE_ONE + " should be in the attribute map that was created from a map.",
                TEST_ATTRIBUTE_ONE, backingMap.get(TEST_ATTRIBUTE_NAME_ONE));

        assertEquals(TEST_ATTRIBUTE_TWO + " should be in the attribute map that was created from a map.",
                TEST_ATTRIBUTE_TWO, backingMap.get(TEST_ATTRIBUTE_NAME_TWO));

        assertEquals(TEST_ATTRIBUTE_THREE + " should be in the attribute map that was created from a map.",
                TEST_ATTRIBUTE_THREE, backingMap.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testCreateAttributeMapFromCollection() throws Exception {

        Map<String, Attribute<String>> attributes = new AttributeMap<>(TEST_ATTRIBUTES);

        assertEquals(TEST_ATTRIBUTE_ONE + " should be in the attribute map that was created from a collection.",
                TEST_ATTRIBUTE_ONE, attributes.get(TEST_ATTRIBUTE_NAME_ONE));

        assertEquals(TEST_ATTRIBUTE_TWO + " should be in the attribute map that was created from a collection.",
                TEST_ATTRIBUTE_TWO, attributes.get(TEST_ATTRIBUTE_NAME_TWO));

        assertEquals(TEST_ATTRIBUTE_THREE + " should be in the attribute map that was created from a collection.",
                TEST_ATTRIBUTE_THREE, attributes.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAttributeMapWithNullBackingMap() throws Exception {

        new AttributeMap<>((Map<String, Attribute>) null);
    }

    @Test
    public void testSize() throws Exception {

        assertEquals("an empty attribute map should have size 0.", 0, emptyAttributes.size());

        assertEquals("an attribute map that has had three values added should have size 3.", 3, attributes.size());
    }

    @Test
    public void testIsEmpty() throws Exception {

        assertTrue("an empty attribute map should be empty.", emptyAttributes.isEmpty());
    }

    @Test
    public void testContainsKey() throws Exception {

        assertTrue("the attribute map should contain the " + TEST_ATTRIBUTE_NAME_ONE + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_ONE));
        assertTrue("the attribute map should contain the " + TEST_ATTRIBUTE_NAME_TWO + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_TWO));
        assertTrue("the attribute map should contain the " + TEST_ATTRIBUTE_NAME_THREE + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testContainsValue() throws Exception {

        assertTrue("the attribute map should contain the " + TEST_ATTRIBUTE_ONE + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_ONE));
        assertTrue("the attribute map should contain the " + TEST_ATTRIBUTE_TWO + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_TWO));
        assertTrue("the attribute map should contain the " + TEST_ATTRIBUTE_THREE + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_THREE));
    }

    @Test
    public void testGet() throws Exception {

        assertEquals("should be able to retrieve " + TEST_ATTRIBUTE_ONE + " from the attribute map.",
                TEST_ATTRIBUTE_ONE, attributes.get(TEST_ATTRIBUTE_NAME_ONE));
        assertEquals("should be able to retrieve " + TEST_ATTRIBUTE_TWO + " from the attribute map.",
                TEST_ATTRIBUTE_TWO, attributes.get(TEST_ATTRIBUTE_NAME_TWO));
        assertEquals("should be able to retrieve " + TEST_ATTRIBUTE_THREE + " from the attribute map.",
                TEST_ATTRIBUTE_THREE, attributes.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testPut() throws Exception {

        emptyAttributes.put(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_ONE);
        assertEquals("should be able to put " + TEST_ATTRIBUTE_ONE + " into the attribute map.",
                TEST_ATTRIBUTE_ONE, emptyAttributes.get(TEST_ATTRIBUTE_NAME_ONE));

        emptyAttributes.put(TEST_ATTRIBUTE_NAME_TWO, TEST_ATTRIBUTE_TWO);
        assertEquals("should be able to put " + TEST_ATTRIBUTE_TWO + " into the attribute map.",
                TEST_ATTRIBUTE_TWO, emptyAttributes.get(TEST_ATTRIBUTE_NAME_TWO));

        emptyAttributes.put(TEST_ATTRIBUTE_NAME_THREE, TEST_ATTRIBUTE_THREE);
        assertEquals("should be able to put " + TEST_ATTRIBUTE_THREE + " into the attribute map.",
                TEST_ATTRIBUTE_THREE, emptyAttributes.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testAdd() throws Exception {

        emptyAttributes.add(TEST_ATTRIBUTE_ONE);
        assertEquals("should be able to add " + TEST_ATTRIBUTE_ONE + " into the attribute map.",
                TEST_ATTRIBUTE_ONE, emptyAttributes.get(TEST_ATTRIBUTE_NAME_ONE));

        emptyAttributes.add(TEST_ATTRIBUTE_TWO);
        assertEquals("should be able to add " + TEST_ATTRIBUTE_TWO + " into the attribute map.",
                TEST_ATTRIBUTE_TWO, emptyAttributes.get(TEST_ATTRIBUTE_NAME_TWO));

        emptyAttributes.add(TEST_ATTRIBUTE_THREE);
        assertEquals("should be able to add " + TEST_ATTRIBUTE_THREE + " into the attribute map.",
                TEST_ATTRIBUTE_THREE, emptyAttributes.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testRemove() throws Exception {

        assertEquals("attribute " + TEST_ATTRIBUTE_ONE + " should be removed.", TEST_ATTRIBUTE_ONE,
                attributes.remove(TEST_ATTRIBUTE_NAME_ONE));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_NAME_ONE + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_ONE));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_ONE + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_ONE));

        assertEquals("attribute " + TEST_ATTRIBUTE_TWO + " should be removed.", TEST_ATTRIBUTE_TWO,
                attributes.remove(TEST_ATTRIBUTE_NAME_TWO));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_NAME_TWO + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_TWO));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_TWO + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_TWO));

        assertEquals("attribute " + TEST_ATTRIBUTE_THREE + " should be removed.", TEST_ATTRIBUTE_THREE,
                attributes.remove(TEST_ATTRIBUTE_NAME_THREE));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_NAME_THREE + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_THREE));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_THREE + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_THREE));
    }

    @Test
    public void testPutAll() throws Exception {

        emptyAttributes.putAll(TEST_ATTRIBUTES_MAP);

        assertEquals(TEST_ATTRIBUTE_ONE + " should be in the map.",
                TEST_ATTRIBUTE_ONE, emptyAttributes.get(TEST_ATTRIBUTE_NAME_ONE));

        assertEquals(TEST_ATTRIBUTE_TWO + " should be in the map.",
                TEST_ATTRIBUTE_TWO, emptyAttributes.get(TEST_ATTRIBUTE_NAME_TWO));

        assertEquals(TEST_ATTRIBUTE_THREE + " should be in the map.",
                TEST_ATTRIBUTE_THREE, emptyAttributes.get(TEST_ATTRIBUTE_NAME_THREE));
    }

    @Test
    public void testClear() throws Exception {

        attributes.clear();

        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_NAME_ONE + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_ONE));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_ONE + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_ONE));

        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_NAME_TWO + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_TWO));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_TWO + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_TWO));

        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_NAME_THREE + " key.",
                attributes.containsKey(TEST_ATTRIBUTE_NAME_THREE));
        assertFalse("the attribute map should no longer contain the " + TEST_ATTRIBUTE_THREE + " value.",
                attributes.containsValue(TEST_ATTRIBUTE_THREE));
    }

    @Test
    public void testKeySet() throws Exception {

        assertEquals("the attribute map keys should be correct.", TEST_ATTRIBUTE_NAMES, attributes.keySet());
    }

    @Test
    public void testValues() throws Exception {

        assertEquals("the attribute map values should be correct.", TEST_ATTRIBUTES, attributes.values());
    }

    @Test
    public void testEntrySet() throws Exception {

        assertEquals("the attribute map entry set should be correct.", TEST_ATTRIBUTE_ENTRY_SET, attributes.entrySet());
    }
}
