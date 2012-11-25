package http;

import java.util.Collection;

/**
 * @author Karl Bennett
 */
public class MessageHeaderTest extends AbstractMessageAttributeTest<Header> {

    public MessageHeaderTest() {

        super(new MessageExecutor<Header>() {

            @Override
            public Header newAttribute(String name, Object value) {

                return new Header<Object>(name, value);
            }

            @Override
            public Header getAttribute(Message message, String name) {

                return message.getHeader(name);
            }

            @Override
            public <T> Collection<Header> getAttributes(Message<T> message) {

                return message.getHeaders();
            }

            @Override
            public <T> void setAttributes(Message<T>  message, Collection<Header> attributes) {

                message.setHeaders(attributes);
            }

            @Override
            public <T> void addAttribute(Message<T> message, String name, String value) {

                message.addHeader(name, value);
            }

            @Override
            public <T> void addAttribute(Message<T> message, Header attribute) {

                message.addHeader(attribute);
            }
        });
    }
}
