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

    protected interface MessageExecutor<P> {

        public abstract <T> Message<T> newMessage();

        public abstract <T> P getProperty(Message<T> message, String name);

        public abstract <T> void addProperty(Message<T> message, P property);

        public abstract <T> Collection<P> getProperties(Message<T> message);

        public abstract <T> void setProperties(Message<T> message, Collection<P> properties);
    }


    private PropertyExecutor<P> propertyExecutor;
    private P propertyOne;
    private P propertyTwo;
    private P propertyThree;
    private Collection<P> properties;


    protected AbstractPropertyProducer(PropertyExecutor<P> propertyExecutor) {

        this.propertyExecutor = propertyExecutor;

        propertyOne = propertyExecutor.newProperty(NAME_ONE, VALUE_ONE);
        propertyTwo = propertyExecutor.newProperty(NAME_TWO, VALUE_TWO);
        propertyThree = propertyExecutor.newProperty(NAME_THREE, VALUE_THREE);

        properties = Arrays.asList(propertyOne, propertyTwo, propertyThree);

        exposeProperties(propertyOne, propertyTwo, propertyThree, properties);
    }

    public abstract void exposeProperties(P propertyOne, P propertyTwo, P propertyThree, Collection<P> properties);
}