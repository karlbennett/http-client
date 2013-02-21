package http.attribute;

import http.util.AbstractMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static http.util.Asserts.assertNotNull;
import static http.util.Checks.isNull;

/**
 * This is a map that can contain {@link Attribute}'s and it's sub classes. It provides convenience constructors and
 * methods for adding {@code Attribute} instances.
 *
 * @author Karl Bennett
 */
public class AttributeMap<A extends Attribute> extends AbstractMap<String, A> {

    /**
     * Create a new {@code AttributeMap} using the supplied backing map. This will be the map that is used internally to
     * supply all the {@link Map} support.
     *
     * @param backingMap the backing map for the new {@code AttributeMap}.
     * @throws IllegalArgumentException if the {@code backingMap} is {@code null}.
     */
    public AttributeMap(Map<String, A> backingMap) {
        super(backingMap);
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
     * @throws IllegalArgumentException if the {@code backingMap} is {@code null}.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public AttributeMap(Map<String, A> backingMap, AttributeMap<A> attributes) {

        this(backingMap);

        assertNotNull("attributes", attributes);

        putAll(attributes);
    }

    /**
     * Copy the supplied {@code AttributeMap} and use a {@link java.util.HashMap} for it's backing map.
     *
     * @param attributes the {@code AttributeMap} to copy.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public AttributeMap(AttributeMap<A> attributes) {

        this(new HashMap<String, A>(), attributes);
    }

    /**
     * Populate the new {@code AttributeMap} from the supplied collection of {@link Attribute}'s and use the supplied
     * backing map. The attributes names will be used as the maps keys.
     *
     * @param attributes the collection of {@code Attribute}'s to use to populate the new {@code AttributeMap}.
     * @throws IllegalArgumentException if the {@code backingMap} is {@code null}.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public AttributeMap(Map<String, A> backingMap, Collection<A> attributes) {

        this(backingMap);

        assertNotNull("attributes", attributes);

        addAll(attributes);
    }

    /**
     * Populate the new {@code AttributeMap} from the supplied collection of {@link Attribute}'s and use a
     * {@link java.util.HashMap} for it's backing map. The attributes names will be used as the maps keys.
     *
     * @param attributes the collection of {@code Attribute}'s to use to populate the new {@code AttributeMap}.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public AttributeMap(Collection<A> attributes) {

        this(new HashMap<String, A>(), attributes);
    }

    /**
     * Add the supplied {@link Attribute} to this {@code AttributeMap}. The attributes name will be used as the key.
     *
     * @param attribute the {@code Attribute} to add to this {@code AttributeMap}.
     * @return the newly added {@code Attribute}.
     */
    public A add(A attribute) {

        if (isNull(attribute)) return null;

        return put(attribute.getName(), attribute);
    }

    /**
     * Add the supplied {@link Attribute}s to this {@code AttributeMap}. The attribute names will be used as the keys.
     *
     * @param attributes the {@code Attribute}s to add to this {@code AttributeMap}.
     * @return the newly added {@code Attribute}s.
     */
    public Collection<A> addAll(Collection<A> attributes) {

        for (A attribute : attributes) add(attribute);

        return attributes;
    }
}
