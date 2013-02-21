package http.attribute;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Karl Bennett
 */
public class AttributeTreeSetMap<A extends Attribute> extends AttributeSetMap<A> {

    public AttributeTreeSetMap(Map<String, Set<A>> backingMap) {
        super(backingMap);
    }

    public AttributeTreeSetMap() {
    }

    public AttributeTreeSetMap(Map<String, Set<A>> backingMap, MultiAttributeMap<A, Set<A>> attributes) {
        super(backingMap, attributes);
    }

    public AttributeTreeSetMap(MultiAttributeMap<A, Set<A>> attributes) {
        super(attributes);
    }

    public AttributeTreeSetMap(Map<String, Set<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    public AttributeTreeSetMap(Collection<A> attributes) {
        super(attributes);
    }


    @Override
    protected Set<A> newCollection() {

        return new TreeSet<A>();
    }
}
