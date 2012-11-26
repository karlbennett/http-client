package http;

import java.util.Collection;

/**
 * @author Karl Bennett
 */
public class MessageHeaderTest extends AbstractMessageAttributeTest<Header> {

    public MessageHeaderTest() {

        super(new MessageExecutor<Header>() {

            @Override
            public Header newProperty(String name, Object value) {

                return new Header<Object>(name, value);
            }

            @Override
            public Header getProperty(Message message, String name) {

                return message.getHeader(name);
            }

            @Override
            public <T> Collection<Header> getProperties(Message<T> message) {

                return message.getHeaders();
            }

            @Override
            public <T> void setProperties(Message<T> message, Collection<Header> attributes) {

                message.setHeaders(attributes);
            }

            @Override
            public <T> void addProperty(Message<T> message, String name, Object value) {

                message.addHeader(name, value);
            }

            @Override
            public <T> void addProperty(Message<T> message, Header attribute) {

                message.addHeader(attribute);
            }
        });
    }
}
