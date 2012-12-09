package http;

import java.util.Arrays;
import java.util.Collection;

/**
 * This is an abstract class that provides some default values and an executor for testing different property type
 * objects.
 *
 * @author Karl Bennett
 */
public abstract class AbstractPropertyProducer<P> {

    public static final String NAME_ONE = "name_one";
    public static final String VALUE_ONE = "value_one";
    public static final String NAME_TWO = "name_two";
    public static final String VALUE_TWO = "value_two";
    public static final String NAME_THREE = "name_three";
    public static final String VALUE_THREE = "value_three";


    /**
     * A wrapper for an object that contains a name and value, it allows different unrelated objects to be tested as
     * long as they have some way of accessing a name and value attribute.
     *
     * @param <P> the type of property object that is contained within this wrapper object.
     */
    protected interface PropertyExecutor<P> {

        public <T> P newProperty(String name, T value);

        public String getName(P property);

        public <T> T getValue(P property);
    }

    /**
     * Create a new {@code AbstractPropertyProducer} with the property executor that will be used within the child
     * class.
     *
     * @param propertyExecutor the executor that will be used within the child class.
     */
    @SuppressWarnings("unchecked")
    protected AbstractPropertyProducer(PropertyExecutor<P> propertyExecutor) {

        P propertyOne = propertyExecutor.newProperty(NAME_ONE, VALUE_ONE);
        P propertyTwo = propertyExecutor.newProperty(NAME_TWO, VALUE_TWO);
        P propertyThree = propertyExecutor.newProperty(NAME_THREE, VALUE_THREE);

        Collection<P> properties = Arrays.asList(propertyOne, propertyTwo, propertyThree);

        exposeProperties(propertyOne, propertyTwo, propertyThree, properties);
    }


    /**
     * Expose some default values to the child class that can be used within unit tests.
     *
     * @param propertyOne   the first default property that contains a default name and value.
     * @param propertyTwo   the first default property that contains a default name and value.
     * @param propertyThree the first default property that contains a default name and value.
     * @param properties    a collection of the three previous properties.
     */
    protected abstract void exposeProperties(P propertyOne, P propertyTwo, P propertyThree, Collection<P> properties);
}
