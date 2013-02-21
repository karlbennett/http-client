package http.attribute;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Karl Bennett
 */
public class AttributeHashSetMap<A extends Attribute> extends AttributeSetMap<A> {

    public AttributeHashSetMap(Map<String, Set<A>> backingMap) {
        super(backingMap);
    }

    public AttributeHashSetMap() {
    }

    public AttributeHashSetMap(Map<String, Set<A>> backingMap, MultiAttributeMap<A, Set<A>> attributes) {
        super(backingMap, attributes);
    }

    public AttributeHashSetMap(MultiAttributeMap<A, Set<A>> attributes) {
        super(attributes);
    }

    public AttributeHashSetMap(Map<String, Set<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    public AttributeHashSetMap(Collection<A> attributes) {
        super(attributes);
    }


    @Override
    protected Set<A> newCollection() {

        return new HashSet<A>();
    }
}
