package http.attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Karl Bennett
 */
public class AttributeArrayListMap<A extends Attribute> extends AttributeListMap<A> {

    public AttributeArrayListMap(Map<String, List<A>> backingMap) {
        super(backingMap);
    }

    public AttributeArrayListMap() {
    }

    public AttributeArrayListMap(Map<String, List<A>> backingMap, MultiAttributeMap<A, List<A>> attributes) {
        super(backingMap, attributes);
    }

    public AttributeArrayListMap(MultiAttributeMap<A, List<A>> attributes) {
        super(attributes);
    }

    public AttributeArrayListMap(Map<String, List<A>> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    public AttributeArrayListMap(Collection<A> attributes) {
        super(attributes);
    }


    @Override
    protected List<A> newCollection() {

        return new ArrayList<A>();
    }
}
