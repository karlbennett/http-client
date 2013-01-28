package http;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * This is an abstract class that is used to test the common behaviour between the different type of property methods on
 * a {@link Message} object e.g. {@link Message#addHeader(http.header.Header)},
 * {@link Message#addHeader(String, Object)}, and {@link Message#addCookie(Cookie)}.
 *
 * @author Karl Bennett
 */
public abstract class AbstractMessagePropertyTest<M, P> extends AbstractPropertyProducer<P> {

    /**
     * A wrapper for the {@code Message} object that will be tested. It is used to decouple the method call within the
     * test from the method that is being tested. So that different methods can be called within the same test.
     *
     * @param <M> the type of message object that will be tested.
     * @param <P> the type of property that will be tested.
     */
    protected interface MessageExecutor<M, P> {

        public abstract M newMessage();

        public abstract P getProperty(M message, String name);

        public abstract void addProperty(M message, P property);

        public abstract Collection<P> getProperties(M message);

        public abstract void setProperties(M message, Collection<P> properties);
    }


    private PropertyExecutor<P> propertyExecutor;
    private MessageExecutor<M, P> messageExecutor;
    private P propertyOne;
    private P propertyTwo;
    private P propertyThree;
    private Collection<P> properties;


    /**
     * Create a new {@code AbstractMessagePropertyTest} with a property and message executor that will be used within
     * all the tests.
     *
     * @param propertyExecutor the property executor that will be used to create and inspect properties.
     * @param messageExecutor  the message executor that will be used to create and test a message object.
     */
    protected AbstractMessagePropertyTest(PropertyExecutor<P> propertyExecutor, MessageExecutor<M, P> messageExecutor) {
        super(propertyExecutor);

        this.propertyExecutor = propertyExecutor;
        this.messageExecutor = messageExecutor;
    }


    @Override
    protected void exposeProperties(P propertyOne, P propertyTwo, P propertyThree, Collection<P> properties) {

        this.propertyOne = propertyOne;
        this.propertyTwo = propertyTwo;
        this.propertyThree = propertyThree;
        this.properties = properties;
    }


    @Test
    public void testGetPropertiesWhenNoPropertiesHaveBeenAdded() throws Exception {

        M message = messageExecutor.newMessage();

        Collection<P> properties = messageExecutor.getProperties(message);

        assertNotNull("a collection of properties should be returned.", properties);
        assertEquals("the collection of properties should be empty.", 0, properties.size());
    }

    @Test
    public void testSetProperties() throws Exception {

        M message = messageExecutor.newMessage();

        messageExecutor.setProperties(message, properties);

        Collection<P> properties = messageExecutor.getProperties(message);

        assertEquals("the number of message properties is correct", 3, properties.size());
        assertTrue("the first property has been set correctly", properties.contains(propertyOne));
        assertTrue("the second property has been set correctly", properties.contains(propertyTwo));
        assertTrue("the third property has been set correctly", properties.contains(propertyThree));
    }

    @Test
    public void testSetEmptyProperties() throws Exception {

        addNoPropertiesTest(Collections.<P>emptySet());
    }

    @Test
    public void testSetNullProperties() throws Exception {

        addNoPropertiesTest(null);
    }

    @Test
    public void testGetProperty() throws Exception {

        M message = messageExecutor.newMessage();
        messageExecutor.setProperties(message, properties);

        assertEquals("property one is retrieved correctly.", propertyOne,
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyOne)));
        assertEquals("property two is retrieved correctly.", propertyTwo,
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyTwo)));
        assertEquals("property three is retrieved correctly.", propertyThree,
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyThree)));
    }

    @Test
    public void testGetPropertyThatDoesNotExist() throws Exception {

        M message = messageExecutor.newMessage();

        assertNull("retrieving a property when no properties exist should return null.",
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyOne)));

        messageExecutor.setProperties(message, properties);

        assertNull("retrieving a property that does not exist should return null.",
                messageExecutor.getProperty(message, "not here"));
    }

    @Test
    public void testAddProperty() throws Exception {

        M message = messageExecutor.newMessage();

        assertEquals("no properties should exist", 0, messageExecutor.getProperties(message).size());

        messageExecutor.addProperty(message,
                propertyExecutor.newProperty(propertyExecutor.getName(propertyOne),
                        propertyExecutor.getValue(propertyOne)));

        assertEquals("one property should exist", 1, messageExecutor.getProperties(message).size());
        assertEquals("property one should have been added", propertyOne,
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyOne)));

        messageExecutor.addProperty(message,
                propertyExecutor.newProperty(propertyExecutor.getName(propertyTwo),
                        propertyExecutor.getValue(propertyTwo)));

        assertEquals("two properties should exist", 2, messageExecutor.getProperties(message).size());
        assertEquals("property two should have been added", propertyTwo,
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyTwo)));

        messageExecutor.addProperty(message,
                propertyExecutor.newProperty(propertyExecutor.getName(propertyThree),
                        propertyExecutor.getValue(propertyThree)));

        assertEquals("three properties should exist", 3, messageExecutor.getProperties(message).size());
        assertEquals("property three should have been added", propertyThree,
                messageExecutor.getProperty(message, propertyExecutor.getName(propertyThree)));
    }

    @Test
    public void testAddPropertyWithEmptyValue() throws Exception {

        addPropertyWithBlankValueTest("");
    }

    @Test
    public void testAddPropertyWithNullValue() throws Exception {

        addPropertyWithBlankValueTest(null);
    }

    @Test
    public void testAddPropertyWithEmptyName() throws Exception {

        addPropertyWithBlankNameAndValueTest(propertyExecutor.newProperty("", propertyExecutor.getValue(propertyOne)));
    }

    @Test
    public void testAddPropertyWithNullName() throws Exception {

        addPropertyWithBlankNameAndValueTest(propertyExecutor.newProperty(null, propertyExecutor.getValue(propertyOne)));
    }

    @Test
    public void testAddPropertyWithEmptyValues() throws Exception {

        addPropertyWithBlankNameAndValueTest(propertyExecutor.newProperty("", ""));
    }

    @Test
    public void testAddPropertyWithNullValues() throws Exception {

        addPropertyWithBlankNameAndValueTest(propertyExecutor.newProperty(null, null));
    }

    @Test
    public void testAddPropertyWithNullProperty() throws Exception {

        addPropertyWithBlankNameAndValueTest(null);
    }


    private void addNoPropertiesTest(Collection<P> empty) {

        M message = messageExecutor.newMessage();
        messageExecutor.setProperties(message, empty);

        Collection<P> properties = messageExecutor.getProperties(message);

        assertNotNull("a collection of properties should be returned.", properties);
        assertEquals("the number of properties should be zero.", 0, properties.size());
    }

    private void addPropertyWithBlankValueTest(Object blank) {

        M message = messageExecutor.newMessage();

        P property = propertyExecutor.newProperty(propertyExecutor.getName(propertyOne), blank);

        assertEquals("no properties should exist", 0, messageExecutor.getProperties(message).size());

        messageExecutor.addProperty(message, property);

        assertEquals("one property should exist", 1, messageExecutor.getProperties(message).size());
        assertEquals("property one should have an empty value", property,
                messageExecutor.getProperty(message, propertyExecutor.getName(property)));

        messageExecutor.addProperty(message, property);

        assertEquals("one property should exist", 1, messageExecutor.getProperties(message).size());
        assertEquals("property one should have an empty value", property,
                messageExecutor.getProperty(message, propertyExecutor.getName(property)));
    }

    private void addPropertyWithBlankNameAndValueTest(P property) {

        M message = messageExecutor.newMessage();

        assertEquals("no properties should exist", 0, messageExecutor.getProperties(message).size());

        messageExecutor.addProperty(message, property);

        assertEquals("no properties should have been added.", 0, messageExecutor.getProperties(message).size());
    }
}
