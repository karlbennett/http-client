package http.attribute;

import org.junit.Test;

import java.util.ArrayList;

import static http.Attributes.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class AttributeArrayListMapTest {

    @Test
    public void testAttributeArrayListMap() throws Exception {

        AttributeListMap<Attribute> map = new AttributeArrayListMap<Attribute>();
        map.add(TEST_ATTRIBUTE_ONE);
        map.add(TEST_ATTRIBUTE_ONE);
        map.add(TEST_ATTRIBUTE_TWO);
        map.add(TEST_ATTRIBUTE_THREE);

        assertEquals("the attribute array list map should contain three attribute entries.", 3, map.size());
        assertEquals("two instances of " + TEST_ATTRIBUTE_NAME_ONE + " should be in the map.", 2,
                map.get(TEST_ATTRIBUTE_NAME_ONE).size());
        assertEquals("one instance of " + TEST_ATTRIBUTE_NAME_TWO + " should be in the map.", 1,
                map.get(TEST_ATTRIBUTE_NAME_TWO).size());
        assertEquals("one instance of " + TEST_ATTRIBUTE_NAME_THREE + " should be in the map.", 1,
                map.get(TEST_ATTRIBUTE_NAME_THREE).size());
    }

    @Test
    public void testNewCollection() throws Exception {

        assertEquals("the attribute array list map new collection method should produce an array list.",
                ArrayList.class, new AttributeArrayListMap().newCollection().getClass());
    }
}
