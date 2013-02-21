package http.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static http.util.Asserts.assertNotNull;

/**
 * This is an abstract map that can be used to quickly create a custom {@link Map} implementation. It simply takes an
 * existing map instance and uses it internally to implement all the {@code Map} methods. This way the behaviour of the
 * map can be modified at runtime if necessary.
 *
 * @author Karl Bennett
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    private final Map<K, V> backingMap;


    /**
     * Create a new {@code AbstractMap} that uses the supplied map instance internally to drive the map methods.
     *
     * @param backingMap the map that will be used to provide the default functionality.
     */
    public AbstractMap(Map<K, V> backingMap) {

        assertNotNull("backingMap", backingMap);

        this.backingMap = backingMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {

        return backingMap.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {

        return backingMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {

        return backingMap.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {

        return backingMap.containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(Object key) {

        return backingMap.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {

        return backingMap.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object key) {

        return backingMap.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

        backingMap.putAll(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {

        backingMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {

        return backingMap.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<V> values() {

        return backingMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet() {

        return backingMap.entrySet();
    }
}
