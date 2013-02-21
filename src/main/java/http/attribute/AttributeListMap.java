package http.attribute;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Karl Bennett
 */
public abstract class AttributeListMap<A extends Attribute> extends MultiAttributeMap<A, List<A>> {

    public AttributeListMap(Map<String, List<A>> backingMap) {
        super(backingMap);
    }

    public AttributeListMap() {
    }

    public AttributeListMap(Map<String, List<A>> backingMap, MultiAttributeMap<A, List<A>> attributes) {
        super(backingMap, attributes);
    }

    public AttributeListMap(MultiAttributeMap<A, List<A>> attributes) {
        super(attributes);
    }

    public AttributeListMap(Map<String, List<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    public AttributeListMap(Collection<A> attributes) {
        super(attributes);
    }
}
