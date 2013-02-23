package http.attribute;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A map that contains a {@link HashSet} of attributes mapped to the same name.
 *
 * @author Karl Bennett
 */
public class AttributeHashSetMap<A extends Attribute> extends AbstractAttributeCollectionMap<A, Set<A>> implements
        AttributeSetMap<A> {

    /**
     * Create a new {@code AttributeHashSetMap} with the supplied backing map.
     *
     * @param backingMap the map to be used internally for all the default {@link Map} methods.
     */
    public AttributeHashSetMap(Map<String, Set<A>> backingMap) {
        super(backingMap);
    }

    /**
     * Create a new {@code AttributeHashSetMap} that uses a {@link java.util.HashMap} as it's backing map.
     */
    public AttributeHashSetMap() {
    }

    /**
     * Create a new {@code AttributeHashSetMap} with the supplied backing map and that is populated from the supplied
     * {@code AttributeHashSetMap}.
     *
     * @param backingMap the map to be used internally for all the default {@link Map} methods.
     * @param attributes the map that will be copied to produce this map.
     */
    public AttributeHashSetMap(Map<String, Set<A>> backingMap, AttributeHashSetMap<A> attributes) {
        super(backingMap, attributes);
    }

    /**
     * Create a new {@code AttributeHashSetMap} as a copy of the supplied {@code AttributeHashSetMap}. This is not a
     * deep copy so will point to the same attribute instances that are contained in the supplied map.
     *
     * @param attributes the map that will be copied to produce this map.
     */
    public AttributeHashSetMap(AttributeCollectionMap<A, Set<A>> attributes) {
        super(attributes);
    }

    /**
     * Create a new {@code AttributeHashSetMap} with the supplied backing map and that is populated with the attributes
     * in the supplied {@link Collection}.
     *
     * @param backingMap the map to be used internally for all the default {@link Map} methods.
     * @param attributes the attributes that will be contained in the new map.
     */
    public AttributeHashSetMap(Map<String, Set<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    /**
     * Create a new {@code AttributeHashSetMap} that uses a {@link java.util.HashMap} as it's backing map and that is
     * populated with the attributes in the supplied {@link Collection}.
     *
     * @param attributes the attributes that will be contained in the new map.
     */
    public AttributeHashSetMap(Collection<A> attributes) {
        super(attributes);
    }


    /**
     * Create a new instance of a {@link HashSet}.
     *
     * @return a new {@code HashSet}.
     */
    @Override
    protected HashSet<A> newCollection() {

        return new HashSet<A>();
    }
}
