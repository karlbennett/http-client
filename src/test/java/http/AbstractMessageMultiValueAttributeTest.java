package http;

import http.attribute.Attribute;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMessageMultiValueAttributeTest<M, A extends Attribute> extends
        AbstractMessagePropertyTest<M, A> {

    private PropertyExecutor<A> propertyExecutor;
    private MessageExecutor<M, A> messageExecutor;
    private A attributeOne;
    private A attributeTwo;
    private Collection<A> attributes;


    protected AbstractMessageMultiValueAttributeTest(PropertyExecutor<A> propertyExecutor, MessageExecutor<M, A> messageExecutor) {
        super(propertyExecutor, messageExecutor);

        this.propertyExecutor = propertyExecutor;
        this.messageExecutor = messageExecutor;
    }


    @Override
    public void exposeProperties(A propertyOne, A propertyTwo, A propertyThree, Collection<A> properties) {
        super.exposeProperties(propertyOne, propertyTwo, propertyThree, properties);

        this.attributeOne = propertyOne;
        this.attributeTwo = propertyTwo;
        this.attributes = properties;
    }


    @Test
    public void testAddAttributeWithSameNameButDifferentValue() throws Exception {

        M message = messageExecutor.newMessage();

        messageExecutor.setProperties(message, attributes);

        A addedOneAtribute = propertyExecutor.newProperty(attributeOne.getName(), attributeTwo.getValue());

        messageExecutor.addProperty(message, addedOneAtribute);

        assertEquals("four attributes should exist when an attribute with the same name but different value is added.",
                4, messageExecutor.getProperties(message).size());

        Collection<A> oneAttributes = messageExecutor.getProperties(message, attributeOne.getName());

        assertEquals("two attribute one attributes should exist", 2, oneAttributes.size());
        assertTrue("attribute one should still be present.", oneAttributes.contains(attributeOne));
        assertTrue("attribute one with the different value should be present.",
                oneAttributes.contains(addedOneAtribute));
    }

    @Test
    public void testAddEmptyValueToExistingAttribute() throws Exception {

        addBlankValueToExistingAttributeTest("");
    }

    @Test
    public void testAddNullValueToExistingAttribute() throws Exception {

        addBlankValueToExistingAttributeTest(null);
    }

    @Test
    public void testAddEmptyAttributeValueTwice() throws Exception {

        addBlankAttributeValueTwiceTest("");
    }

    @Test
    public void testAddNullAttributeValueTwice() throws Exception {

        addBlankAttributeValueTwiceTest(null);
    }


    private void addBlankValueToExistingAttributeTest(Object blank) {

        M message = messageExecutor.newMessage();

        A blankAttribute = propertyExecutor.newProperty(attributeOne.getName(), blank);

        messageExecutor.addProperty(message, blankAttribute);

        Collection<A> addedAttributes = messageExecutor.getProperties(message, attributeOne.getName());

        assertEquals("one attribute should have been added.", 1, addedAttributes.size());
        assertTrue("the blank attribute should have been added.", addedAttributes.contains(blankAttribute));

        messageExecutor.addProperty(message,
                propertyExecutor.newProperty(attributeOne.getName(), attributeOne.getValue()));

        addedAttributes = messageExecutor.getProperties(message, attributeOne.getName());

        assertEquals("two attributes should have been added.", 2, addedAttributes.size());
        assertTrue("the blank attribute should still exist.", addedAttributes.contains(blankAttribute));
        assertTrue("attribute one should have been added.", addedAttributes.contains(attributeOne));

        messageExecutor.addProperty(message, blankAttribute);

        addedAttributes = messageExecutor.getProperties(message, attributeOne.getName());

        assertEquals("three attributes should have been added.", 3, addedAttributes.size());
        assertTrue("the blank attribute should exist twice.", addedAttributes.contains(blankAttribute));
        assertTrue("attribute one should still exist.", addedAttributes.contains(attributeOne));
    }

    private void addBlankAttributeValueTwiceTest(Object blank) {

        M message = messageExecutor.newMessage();

        A blankAttribute = propertyExecutor.newProperty(attributeOne.getName(), blank);

        messageExecutor.addProperty(message, blankAttribute);

        Collection<A> addedAttributes = messageExecutor.getProperties(message, attributeOne.getName());

        assertEquals("one attribute should have been added.", 1, addedAttributes.size());
        assertTrue("the blank attribute should have been added.", addedAttributes.contains(blankAttribute));

        messageExecutor.addProperty(message, blankAttribute);

        addedAttributes = messageExecutor.getProperties(message, attributeOne.getName());

        assertEquals("two attributes should have been added.", 2, addedAttributes.size());
        assertTrue("the blank attribute should exist twice.", addedAttributes.contains(blankAttribute));
    }
}
