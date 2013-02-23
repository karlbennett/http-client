package http.attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A map that contains an {@link ArrayList} of attributes mapped to the same name.
 *
 * @author Karl Bennett
 */
public class AttributeArrayListMap<A extends Attribute> extends AbstractAttributeCollectionMap<A, List<A>> implements
        AttributeListMap<A> {

    /**
     * Create a new {@code AttributeArrayListMap} with the supplied backing map.
     *
     * @param backingMap the map to be used internally for all the default {@link Map} methods.
     */
    public AttributeArrayListMap(Map<String, List<A>> backingMap) {
        super(backingMap);
    }

    /**
     * Create a new {@code AttributeArrayListMap} that uses a {@link java.util.HashMap} as it's backing map.
     */
    public AttributeArrayListMap() {
    }

    /**
     * Create a new {@code AttributeArrayListMap} with the supplied backing map and that is populated from the supplied
     * {@code AttributeArrayListMap}.
     *
     * @param backingMap the map to be used internally for all the default {@link Map} methods.
     * @param attributes the map that will be copied to produce this map.
     */
    public AttributeArrayListMap(Map<String, List<A>> backingMap, AbstractAttributeCollectionMap<A, List<A>> attributes) {
        super(backingMap, attributes);
    }

    /**
     * Create a new {@code AttributeArrayListMap} as a copy of the supplied {@code AttributeArrayListMap}. This is not a
     * deep copy so will point to the same attribute instances that are contained in the supplied map.
     *
     * @param attributes the map that will be copied to produce this map.
     */
    public AttributeArrayListMap(AbstractAttributeCollectionMap<A, List<A>> attributes) {
        super(attributes);
    }

    /**
     * Create a new {@code AttributeArrayListMap} with the supplied backing map and that is populated with the
     * attributes in the supplied {@link Collection}.
     *
     * @param backingMap the map to be used internally for all the default {@link Map} methods.
     * @param attributes the attributes that will be contained in the new map.
     */
    public AttributeArrayListMap(Map<String, List<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    /**
     * Create a new {@code AttributeArrayListMap} that uses a {@link java.util.HashMap} as it's backing map and that is
     * populated with the attributes in the supplied {@link Collection}.
     *
     * @param attributes the attributes that will be contained in the new map.
     */
    public AttributeArrayListMap(Collection<A> attributes) {
        super(attributes);
    }


    /**
     * Create a new instance of an {@link ArrayList}.
     *
     * @return a new {@code ArrayList}.
     */
    @Override
    protected List<A> newCollection() {

        return new ArrayList<A>();
    }
}
