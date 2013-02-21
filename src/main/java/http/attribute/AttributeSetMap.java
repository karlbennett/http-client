package http.attribute;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Karl Bennett
 */
public abstract class AttributeSetMap<A extends Attribute> extends MultiAttributeMap<A, Set<A>> {

    public AttributeSetMap(Map<String, Set<A>> backingMap) {
        super(backingMap);
    }

    public AttributeSetMap() {
    }

    public AttributeSetMap(Map<String, Set<A>> backingMap, MultiAttributeMap<A, Set<A>> attributes) {
        super(backingMap, attributes);
    }

    public AttributeSetMap(MultiAttributeMap<A, Set<A>> attributes) {
        super(attributes);
    }

    public AttributeSetMap(Map<String, Set<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    public AttributeSetMap(Collection<A> attributes) {
        super(attributes);
    }
}
