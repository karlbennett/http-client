package http;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static http.util.Assert.assertNotNull;

/**
 * This is a map that can contain {@link Attribute}'s and it's sub classes. It provides convenience constructors and
 * methods for adding {@code Attribute} instances.
 *
 * @author Karl Bennett
 */
public class AttributeMap<A extends Attribute> implements Map<String, A> {

    private final Map<String, A> backingMap;


    /**
     * Create a new {@code AttributeMap} using the supplied backing map. This will be the map that is used internally to
     * supply all the {@link Map} support.
     *
     * @param backingMap the backing map for the new {@code AttributeMap}.
     */
    public AttributeMap(Map<String, A> backingMap) {

        assertNotNull("backingMap", backingMap);

        this.backingMap = backingMap;
    }

    /**
     * Create an empty {@code AttributeMap} that uses a {@link java.util.HashMap} for it's backing map.
     */
    public AttributeMap() {

        this(new HashMap<String, A>());
    }

    /**
     * Copy the supplied {@code AttributeMap} and use the supplied backing map.
     *
     * @param backingMap the backing map for the new {@code AttributeMap}.
     * @param attributes the {@code AttributeMap} to copy.
     */
    public AttributeMap(Map<String, A> backingMap, AttributeMap<A> attributes) {

        this(backingMap);

        this.backingMap.putAll(attributes);
    }

    /**
     * Copy the supplied {@code AttributeMap} and use a {@link java.util.HashMap} for it's backing map.
     *
     * @param attributes the {@code AttributeMap} to copy.
     */
    public AttributeMap(AttributeMap<A> attributes) {

        this(new HashMap<String, A>(), attributes);
    }

    /**
     * Populate the new {@code AttributeMap} from the supplied collection of {@link Attribute}'s and use the supplied
     * backing map. The attributes names will be used as the maps keys.
     *
     * @param attributes the collection of {@code Attribute}'s to use to populate the new {@code AttributeMap}.
     */
    public AttributeMap(Map<String, A> backingMap, Collection<A> attributes) {

        this(backingMap);

        for (A attribute : attributes) add(attribute);
    }

    /**
     * Populate the new {@code AttributeMap} from the supplied collection of {@link Attribute}'s and use a
     * {@link java.util.HashMap} for it's backing map. The attributes names will be used as the maps keys.
     *
     * @param attributes the collection of {@code Attribute}'s to use to populate the new {@code AttributeMap}.
     */
    public AttributeMap(Collection<A> attributes) {

        this(new HashMap<String, A>(), attributes);
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
    public A get(Object key) {

        return backingMap.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A put(String key, A value) {

        return backingMap.put(key, value);
    }

    /**
     * Add the supplied {@link Attribute} to this {@code AttributeMap}. The attributes name will be used as the key.
     *
     * @param value the {@code Attribute} to add to this {@code AttributeMap}.
     * @return the newly added {@code Attribute}.
     */
    public A add(A value) {

        return put(value.getName(), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A remove(Object key) {

        return backingMap.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends String, ? extends A> map) {

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
    public Set<String> keySet() {

        return backingMap.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<A> values() {

        return backingMap.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<String, A>> entrySet() {

        return backingMap.entrySet();
    }
}
