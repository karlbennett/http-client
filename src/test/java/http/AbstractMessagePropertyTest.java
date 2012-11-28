package http;

import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMessagePropertyTest<P> extends AbstractPropertyProducer<P> {

    private PropertyExecutor<P> propertyExecutor;
    private MessageExecutor<P> messageExecutor;
    private P propertyOne;
    private P propertyTwo;
    private P propertyThree;
    private Collection<P> properties;


    protected AbstractMessagePropertyTest(PropertyExecutor<P> propertyExecutor, MessageExecutor<P> messageExecutor) {
        super(propertyExecutor);

        this.propertyExecutor = propertyExecutor;
        this.messageExecutor = messageExecutor;
    }


    @Override
    public void exposeProperties(P propertyOne, P propertyTwo, P propertyThree, Collection<P> properties) {

        this.propertyOne = propertyOne;
        this.propertyTwo = propertyTwo;
        this.propertyThree = propertyThree;
        this.properties = properties;
    }


    @Test
    public void testGetPropertiesWhenNoPropertiesHaveBeenAdded() throws Exception {

        Message<Object> message = messageExecutor.newMessage();

        Collection<P> properties = messageExecutor.getProperties(message);

        assertNotNull("a collection of properties should be returned.", properties);
        assertEquals("the collection of properties should be empty.", 0, properties.size());
    }

    @Test
    public void testSetProperties() throws Exception {

        Message<Object> message = messageExecutor.newMessage();

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

        Message<Object> message = messageExecutor.newMessage();
        messageExecutor.setProperties(message, properties);

        assertEquals("property one is retrieved correctly.", propertyOne,
                messageExecutor.getProperty(message, NAME_ONE));
        assertEquals("property two is retrieved correctly.", propertyTwo,
                messageExecutor.getProperty(message, NAME_TWO));
        assertEquals("property three is retrieved correctly.", propertyThree,
                messageExecutor.getProperty(message, NAME_THREE));
    }

    @Test
    public void testGetPropertyThatDoesNotExist() throws Exception {

        Message<Object> message = messageExecutor.newMessage();

        assertNull("retrieving a property when no properties exist should return null.",
                messageExecutor.getProperty(message, NAME_ONE));

        messageExecutor.setProperties(message, properties);

        assertNull("retrieving a property that does not exist should return null.",
                messageExecutor.getProperty(message, "not here"));
    }

    @Test
    public void testAddProperty() throws Exception {

        Message<Object> message = messageExecutor.newMessage();

        assertEquals("no properties should exist", 0, messageExecutor.getProperties(message).size());

        messageExecutor.addProperty(message, propertyExecutor.newProperty(NAME_ONE, VALUE_ONE));

        assertEquals("one property should exist", 1, messageExecutor.getProperties(message).size());
        assertEquals("property one should have been added", propertyOne,
                messageExecutor.getProperty(message, NAME_ONE));

        messageExecutor.addProperty(message, propertyExecutor.newProperty(NAME_TWO, VALUE_TWO));

        assertEquals("two properties should exist", 2, messageExecutor.getProperties(message).size());
        assertEquals("property two should have been added", propertyTwo,
                messageExecutor.getProperty(message, NAME_TWO));

        messageExecutor.addProperty(message, propertyExecutor.newProperty(NAME_THREE, VALUE_THREE));

        assertEquals("three properties should exist", 3, messageExecutor.getProperties(message).size());
        assertEquals("property three should have been added", propertyThree,
                messageExecutor.getProperty(message, NAME_THREE));
    }

    @Test
    public void testAddPropertyWithEmptyValue() throws Exception {

        addPropertyWithBlankValueTest(propertyExecutor.newProperty(propertyExecutor.getName(propertyOne), ""));
    }

    @Test
    public void testAddPropertyWithNullValue() throws Exception {

        addPropertyWithBlankValueTest(propertyExecutor.newProperty(propertyExecutor.getName(propertyOne), null));
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

        Message<Object> message = messageExecutor.newMessage();
        messageExecutor.setProperties(message, empty);

        Collection<P> properties = messageExecutor.getProperties(message);

        assertNotNull("a collection of properties should be returned.", properties);
        assertEquals("the number of properties should be zero.", 0, properties.size());
    }

    private void addPropertyWithBlankValueTest(P property) {

        Message<Object> message = messageExecutor.newMessage();

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

        Message<Object> message = messageExecutor.newMessage();

        assertEquals("no properties should exist", 0, messageExecutor.getProperties(message).size());

        messageExecutor.addProperty(message, property);

        assertEquals("no properties should have been added.", 0, messageExecutor.getProperties(message).size());
    }
}
