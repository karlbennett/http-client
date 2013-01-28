package http;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This is a map that can contain {@link Attribute}'s and it's sub classes. It provides convenience constructors and
 * methods for adding {@code Attribute} instances.
 *
 * @author Karl Bennett
 */
public class AttributeMap<A extends Attribute> implements Map<String, A> {

    /**
     * Create an empty {@code AttributeMap}.
     */
    public AttributeMap() {
    }

    /**
     * Copy the supplied {@code AttributeMap}.
     *
     * @param attributes the {@code AttributeMap} to copy.
     */
    public AttributeMap(AttributeMap<A> attributes) {

    }

    /**
     * Populate the new {@code AttributeMap} from the supplied map.
     *
     * @param attributes the map of attributes to use to populate the new {@code AttributeMap}.
     */
    public AttributeMap(Map<String, A> attributes) {

    }

    /**
     * Populate the new {@code AttributeMap} from the supplied collection of {@link Attribute}'s. The attributes names
     * will be used as the maps keys.
     *
     * @param attributes the collection of {@code Attribute}'s to use to populate the new {@code AttributeMap}.
     */
    public AttributeMap(Collection<A> attributes) {

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {

        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A get(Object key) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A put(String key, A value) {

        return null;
    }

    /**
     * Add the supplied {@link Attribute} to this {@code AttributeMap}. The attributes name will be used as the key.
     *
     * @param value the {@code Attribute} to add to this {@code AttributeMap}.
     * @return the newly added {@code Attribute}.
     */
    public A add(A value) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A remove(Object key) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends String, ? extends A> map) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> keySet() {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<A> values() {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<String, A>> entrySet() {

        return null;
    }
}
