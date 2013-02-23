package http.attribute;

import java.util.List;

/**
 * An abstract child of the {@link AbstractAttributeCollectionMap} that has it's value typed to a List. It can be used to
 * polymorphicly reference different implementations with {@link List} typed value collections.
 *
 * @author Karl Bennett
 */
public interface AttributeListMap<A extends Attribute> extends AttributeCollectionMap<A, List<A>> {
}
