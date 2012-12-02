package http;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Karl Bennett
 */
public abstract class AbstractPropertyProducer<P> {

    public static final String NAME_ONE = "name_one";
    public static final String VALUE_ONE = "value_one";
    public static final String NAME_TWO = "name_two";
    public static final String VALUE_TWO = "value_two";
    public static final String NAME_THREE = "name_three";
    public static final String VALUE_THREE = "value_three";


    protected interface PropertyExecutor<P> {

        public <T> P newProperty(String name, T value);

        public String getName(P property);

        public <T> T getValue(P property);
    }

    @SuppressWarnings("unchecked")
    protected AbstractPropertyProducer(PropertyExecutor<P> propertyExecutor) {

        P propertyOne = propertyExecutor.newProperty(NAME_ONE, VALUE_ONE);
        P propertyTwo = propertyExecutor.newProperty(NAME_TWO, VALUE_TWO);
        P propertyThree = propertyExecutor.newProperty(NAME_THREE, VALUE_THREE);

        Collection<P>properties = Arrays.asList(propertyOne, propertyTwo, propertyThree);

        exposeProperties(propertyOne, propertyTwo, propertyThree, properties);
    }


    protected abstract void exposeProperties(P propertyOne, P propertyTwo, P propertyThree, Collection<P> properties);
}
