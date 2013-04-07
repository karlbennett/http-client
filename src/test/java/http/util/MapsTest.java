package http.util;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static http.util.Maps.populateMap;
import static java.util.AbstractMap.SimpleEntry;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class MapsTest {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    private static final String ONE_STRING = "one";
    private static final String TWO_STRING = "two";
    private static final String THREE_STRING = "three";

    public static final Map<Integer, String> TEST_MAP;

    static {

        Map<Integer, String> testMap = new HashMap<Integer, String>();
        testMap.put(ONE, ONE_STRING);
        testMap.put(TWO, TWO_STRING);
        testMap.put(THREE, THREE_STRING);

        TEST_MAP = testMap;
    }

    @Test
    public void testPopulateMap() throws Exception {

        assertEquals("the map should be populated", TEST_MAP, populateMap(new HashMap<Integer, String>(),
                new SimpleEntry<Integer, String>(ONE, ONE_STRING),
                new SimpleEntry<Integer, String>(TWO, TWO_STRING),
                new SimpleEntry<Integer, String>(THREE, THREE_STRING)
        ));
    }

    @Test
    public void testPopulateMapWithNoEntries() throws Exception {

        assertEquals("the map should be empty", Collections.emptyMap(), populateMap(new HashMap()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPopulateMapWithNullEntries() throws Exception {

        populateMap(new HashMap(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPopulateMapWithNullMap() throws Exception {

        populateMap(null);
    }
}
