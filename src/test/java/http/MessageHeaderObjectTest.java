package http;

import java.util.Collection;

/**
 * @author Karl Bennett
 */
public class MessageHeaderObjectTest extends AbstractMessageAttributeTest<Header> {

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
                new MessageExecutor<Header>() {

                    @Override
                    public <T> Message<T> newMessage() {

                        return new Message<T>();
                    }

                    @Override
                    public Header getProperty(Message message, String name) {

                        return message.getHeader(name);
                    }

                    @Override
                    public <T> void addProperty(Message<T> message, Header property) {

                        message.addHeader(property);
                    }

                    @Override
                    public <T> Collection<Header> getProperties(Message<T> message) {

                        return message.getHeaders();
                    }

                    @Override
                    public <T> void setProperties(Message<T> message, Collection<Header> properties) {

                        message.setHeaders(properties);
                    }
                }
        );
    }
}
