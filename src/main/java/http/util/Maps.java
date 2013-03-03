package http.util;

import java.util.Map;

import static java.util.Map.Entry;
import static http.util.Asserts.assertNotNull;

/**
 * This utility class contains helper methods for working with Java {@link Map}s.
 *
 * @author Karl Bennett
 */
public final class Maps {

    private Maps() {
    }

    /**
     * Populates the supplied map with the supplied entries.
     *
     * @param map     the map to populate.
     * @param entries the entries to populate the map with.
     * @param <K>     the type of the maps keys.
     * @param <V>     the type of the maps values.
     * @return the populated map.
     */
    public static <K, V> Map<K, V> populateMap(Map<K, V> map, Entry<K, V>... entries) {

        assertNotNull("map", map);
        assertNotNull("entries", entries);

        for (Entry<K, V> entry : entries) map.put(entry.getKey(), entry.getValue());

        return map;
    }
}
