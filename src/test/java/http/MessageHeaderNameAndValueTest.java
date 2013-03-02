package http;

import http.header.Header;

import java.util.Collection;

/**
 * @author Karl Bennett
 */
public class MessageHeaderNameAndValueTest extends AbstractMessageHeaderTest<Message<Object>> {

    public MessageHeaderNameAndValueTest() {

        super(new MessageExecutor<Message<Object>, Header>() {

            @Override
            public Message<Object> newMessage() {

                return new Message<Object>();
            }

            @Override
            public Collection<Header> getProperties(Message<Object> message, String name) {

                return message.getHeaders(name);
            }

            @Override
            public void addProperty(Message<Object> message, Header property) {

                message.addHeader(property.getName(), property.getValue());
            }

            @Override
            public void addProperties(Message<Object> message, Collection<Header> properties) {

                message.addHeaders(properties);
            }

            @Override
            public Collection<Header> getProperties(Message<Object> message) {

                return message.getHeaders();
            }

            @Override
            public void setProperties(Message<Object> message, Collection<Header> properties) {

                message.setHeaders(properties);
            }

            @Override
            public Header removeProperty(Message<Object> message, Header property) {

                return message.removeHeader(property.getName(), property.getValue());
            }

            @Override
            public Collection<Header> removeProperties(Message<Object> message, Collection<Header> properties) {

                return message.removeHeaders(properties);
            }
        }
        );
    }
}
