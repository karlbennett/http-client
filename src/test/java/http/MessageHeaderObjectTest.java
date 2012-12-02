package http;

import java.util.Collection;

/**
 * @author Karl Bennett
 */
public class MessageHeaderObjectTest extends AbstractMessageAttributeTest<Message<Object>, Header> {

    public MessageHeaderObjectTest() {

        super(
                new PropertyExecutor<Header>() {
                    @Override
                    public <T> Header newProperty(String name, T value) {

                        return new Header<T>(name, value);
                    }

                    @Override
                    public String getName(Header property) {

                        return property.getName();
                    }

                    @Override
                    public <T> T getValue(Header property) {

                        return (T) property.getValue();
                    }
                },
                new MessageExecutor<Message<Object>, Header>() {

                    @Override
                    public Message<Object> newMessage() {

                        return new Message<Object>();
                    }

                    @Override
                    public Header getProperty(Message<Object> message, String name) {

                        return message.getHeader(name);
                    }

                    @Override
                    public void addProperty(Message<Object> message, Header property) {

                        message.addHeader(property);
                    }

                    @Override
                    public Collection<Header> getProperties(Message<Object> message) {

                        return message.getHeaders();
                    }

                    @Override
                    public void setProperties(Message<Object> message, Collection<Header> properties) {

                        message.setHeaders(properties);
                    }
                }
        );
    }
}
