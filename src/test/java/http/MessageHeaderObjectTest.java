package http;

import http.header.Header;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class MessageHeaderObjectTest extends AbstractMessageHeaderTest<Message<Object>> {

    public MessageHeaderObjectTest() {

        super(new MessageExecutor<Message<Object>, Header>() {

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

    @Test
    public void testAddHeaderWithNull() throws Exception {

        Message message = new Message();

        assertEquals("no properties should exist", 0, message.getHeaders().size());

        message.addHeader(null);

        assertEquals("no properties should have been added.", 0, message.getHeaders().size());
    }
}
