package http.attribute;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Karl Bennett
 */
public abstract class AttributeSetMap<A extends Attribute> extends AttributeCollectionMap<A, Set<A>> {

    public AttributeSetMap(Map<String, Set<A>> backingMap) {
        super(backingMap);
    }

    public AttributeSetMap() {
    }

    public AttributeSetMap(Map<String, Set<A>> backingMap, AttributeCollectionMap<A, Set<A>> attributes) {
        super(backingMap, attributes);
    }

    public AttributeSetMap(AttributeCollectionMap<A, Set<A>> attributes) {
        super(attributes);
    }

    public AttributeSetMap(Map<String, Set<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    public AttributeSetMap(Collection<A> attributes) {
        super(attributes);
    }
}
