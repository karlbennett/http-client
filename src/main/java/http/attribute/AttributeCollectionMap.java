package http.attribute;

import java.util.Collection;
import java.util.Map;

/**
 * A map that can contain multiple {@link Attribute}'s and it's sub classes for each key.
 *
 * @author Karl Bennett
 */
public interface AttributeCollectionMap<A extends Attribute, C extends Collection<A>> extends Map<String, C> {

    /**
     * Add a new attribute to the map using the attributes name for the key. If an entry for this attribute already
     * exists it will be added to the {@link Collection} mapped to that attributes name.
     *
     * @param attribute the attribute to add.
     * @return true if adding the attribute modified the map, otherwise false.
     */
    boolean add(A attribute);

    /**
     * Add all the supplied attributes to the map using the attributes names as the keys. If an entry for any of the
     * attributes already exists they will be added to the {@link Collection} mapped to the existing name key.
     *
     * @param attributes the attributes to add.
     * @return true if adding the attributes modified the map, otherwise false.
     */
    boolean addAll(Collection<A> attributes);

    /**
     * Remove the supplied attribute from the map. If removing this attribute causes the collection of attributes that
     * relate to the attributes name to become empty then the entire entry will be removed.
     *
     * @param attribute the attribute to remove.
     * @return true if an attribute was removed from the map, otherwise false.
     */
    boolean remove(A attribute);

    /**
     * Remove all the supplied attributes from the map. If removing any of these attributes causes the collection of
     * attributes that relate to the name key to become empty then the entire entry will be removed.
     *
     * @param attributes the attributes to remove.
     * @return true if an attribute was removed from the map, otherwise false.
     */
    boolean removeAll(Collection<A> attributes);
}
