package http;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMessageAttributeTest<A extends Attribute> extends AbstractMessagePropertyTest<A> {

    private MessageExecutor<A> messageExecutor;
    private A attributeOne;
    private A attributeTwo;
    private A attributeThree;
    private Collection<A> attributes;

    protected AbstractMessageAttributeTest(MessageExecutor<A> messageExecutor) {
        super(messageExecutor);

        this.messageExecutor = messageExecutor;
    }

    @Override
    protected void exposeProperties(A propertyOne, A propertyTwo, A propertyThree, Collection<A> properties) {

        this.attributeOne = propertyOne;
        this.attributeTwo = propertyTwo;
        this.attributeThree = propertyThree;
        this.attributes = properties;
    }


    @Test
    public void testAddValueToExistingAttribute() throws Exception {

        new AddAttributeValueTester<Object>() {

            @Override
            public void addProperty(Message<Object> message, String name, Object value) {

                messageExecutor.addProperty(message, name, value);
            }
        };
    }

    @Test
    public void testAddEmptyValueToExistingAttribute() throws Exception {

        new AddNameValueBlankAttributeValueTester("");
    }

    @Test
    public void testAddHeaderWithNameAndNullValue() throws Exception {

        new AddNameValueBlankAttributeValueTester(null);
    }

    @Test
    public void testAddHeader() throws Exception {

        new AddAttributeValueTester<Object>() {

            @Override
            public void addProperty(Message<Object> message, String name, Object value) {

                messageExecutor.addProperty(message, messageExecutor.newProperty(name, value));
            }
        };
    }

    @Test
    public void testAddHeaderWithEmptyValue() throws Exception {

        new AddObjectBlankAttributeValueTester<Object>("");
    }

    @Test
    public void testAddHeaderWithNullValue() throws Exception {

        new AddObjectBlankAttributeValueTester<Object>(null);
    }

    private abstract class AddAttributeValueTester<T> implements PropertyAdder<T> {

        protected AddAttributeValueTester() {

            Message<T> message = new Message<T>();

            messageExecutor.setProperties(message, attributes);

            addProperty(message, NAME_ONE, VALUE_TWO);

            assertEquals("three attributes should exist when a value has been added to attribute one", 3,
                    messageExecutor.getProperties(message).size());
            assertEquals("two values should exist when a value has been added to attribute one", 2,
                    messageExecutor.getProperty(message, NAME_ONE).getValues().size());
            assertEquals("attribute one should have value one.", VALUE_ONE,
                    messageExecutor.getProperty(message, NAME_ONE).getValue());
            assertEquals("attribute one should have value two.", VALUE_TWO,
                    messageExecutor.getProperty(message, NAME_ONE).getValues().get(1));
        }
    }

    private abstract class AddBlankAttributeValueTester<T> implements PropertyAdder<T> {

        protected AddBlankAttributeValueTester(Object blank) {

            Message<T> message = new Message<T>();

            addProperty(message, NAME_ONE, blank);

            assertEquals("attribute one should have one value", 1,
                    messageExecutor.getProperty(message, NAME_ONE).getValues().size());

            addProperty(message, NAME_ONE, blank);

            assertEquals("attribute one should still only have have one value", 1,
                    messageExecutor.getProperty(message, NAME_ONE).getValues().size());
        }
    }

    private class AddNameValueBlankAttributeValueTester<T> extends AddBlankAttributeValueTester<T> {

        protected AddNameValueBlankAttributeValueTester(Object blank) {
            super(blank);
        }

        @Override
        public void addProperty(Message<T> message, String name, Object value) {

            messageExecutor.addProperty(message, name, value);
        }
    }

    private class AddObjectBlankAttributeValueTester<T> extends AddBlankAttributeValueTester<T> {

        protected AddObjectBlankAttributeValueTester(Object blank) {
            super(blank);
        }

        @Override
        public void addProperty(Message<T> message, String name, Object value) {

            messageExecutor.addProperty(message, messageExecutor.newProperty(name, value));
        }
    }
}
