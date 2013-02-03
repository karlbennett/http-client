package http.attribute;

import java.util.Collection;
import java.util.Map;

import static http.util.Checks.isNull;

/**
 * This is a map that can contain {@link MultiValueAttribute}'s and it's sub classes. It provides convenience
 * constructors and methods for adding {@code MultiValueAttribute} instances.
 *
 * @author Karl Bennett
 */
public class MultiValueAttributeMap<A extends MultiValueAttribute> extends AttributeMap<A> {

    /**
     * Create a new {@code MultiValueAttributeMap} using the supplied backing map. This will be the map that is used
     * internally to supply all the {@link Map} support.
     *
     * @param backingMap the backing map for the new {@code MultiValueAttributeMap}.
     * @throws IllegalArgumentException if the {@code backingMap} is {@code null}.
     */
    public MultiValueAttributeMap(Map<String, A> backingMap) {
        super(backingMap);
    }

    /**
     * Create an empty {@code MultiValueAttributeMap} that uses a {@link java.util.HashMap} for it's backing map.
     */
    public MultiValueAttributeMap() {
    }

    /**
     * Copy the supplied {@code MultiValueAttributeMap} and use the supplied backing map.
     *
     * @param backingMap the backing map for the new {@code MultiValueAttributeMap}.
     * @param attributes the {@code MultiValueAttributeMap} to copy.
     * @throws IllegalArgumentException if the {@code backingMap} is {@code null}.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public MultiValueAttributeMap(Map<String, A> backingMap, MultiValueAttributeMap<A> attributes) {
        super(backingMap, attributes);
    }

    /**
     * Copy the supplied {@code MultiValueAttributeMap} and use a {@link java.util.HashMap} for it's backing map.
     *
     * @param attributes the {@code MultiValueAttributeMap} to copy.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public MultiValueAttributeMap(MultiValueAttributeMap<A> attributes) {
        super(attributes);
    }

    /**
     * Populate the new {@code MultiValueAttributeMap} from the supplied collection of {@link MultiValueAttribute}'s and
     * use the supplied backing map. The attributes names will be used as the maps keys.
     *
     * @param attributes the collection of {@code MultiValueAttribute}'s to use to populate the new
     *                   {@code MultiValueAttributeMap}.
     * @throws IllegalArgumentException if the {@code backingMap} is {@code null}.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public MultiValueAttributeMap(Map<String, A> backingMap, Collection<A> attributes) {
        super(backingMap, attributes);
    }

    /**
     * Populate the new {@code MultiValueAttributeMap} from the supplied collection of {@link MultiValueAttribute}'s and use a
     * {@link java.util.HashMap} for it's backing map. The attributes names will be used as the maps keys.
     *
     * @param attributes the collection of {@code MultiValueAttribute}'s to use to populate the new
     *                   {@code MultiValueAttributeMap}.
     * @throws IllegalArgumentException if the {@code attributes} argument is {@code null}.
     */
    public MultiValueAttributeMap(Collection<A> attributes) {
        super(attributes);
    }


    /**
     * A a {@link MultiValueAttribute} to the map. If an attribute of the same name already exists in the map then the
     * supplied attributes values will be added to the existing attribute.
     *
     * @param attribute the {@code MultiValueAttribute} to add to this {@code MultiValueAttributeMap}.
     * @return the updated attribute if one of the same name already existed in the map, otherwise the supplied
     *         attribute.
     */
    @Override
    public A add(A attribute) {

        // If the supplied attribute is null let the parent logic handle it.
        if (isNull(attribute)) return super.add(attribute);

        // Check to see if we already have an attribute with the same name.
        A existingValue = get(attribute.getName());

        // If we don't then just add the attribute to the map.
        if (isNull(existingValue)) {

            existingValue = super.add(attribute);

        } else {

            // Otherwise we should add the supplied attributes values to the values of the existing attribute.
            existingValue.addAllValues(attribute.getValues());
        }

        return existingValue;
    }
}
