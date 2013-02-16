package http;

import org.junit.Test;

import java.util.Collection;

import static http.Cookies.COOKIE;
import static http.Urls.TEST_URL;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class RequestCookieObjectTest extends AbstractMessageCookieTest<Request<Object>> {


    public RequestCookieObjectTest() {
        super(new MessageExecutor<Request<Object>, Cookie>() {

            @Override
            public Request<Object> newMessage() {

                return new Request<Object>(TEST_URL);
            }

            @Override
            public Cookie getProperty(Request<Object> message, String name) {

                return message.getCookie(name);
            }

            @Override
            public void addProperty(Request<Object> message, Cookie property) {

                message.addCookie(property);
            }

            @Override
            public void addProperties(Request<Object> message, Collection<Cookie> properties) {

                message.addCookies(properties);
            }

            @Override
            public Collection<Cookie> getProperties(Request<Object> message) {

                return message.getCookies();
            }

            @Override
            public void setProperties(Request<Object> message, Collection<Cookie> properties) {

                message.setCookies(properties);
            }

            @Override
            public Cookie removeProperty(Request<Object> message, Cookie property) {

                return message.removeCookie(property);
            }

            @Override
            public Collection<Cookie> removeProperties(Request<Object> message, Collection<Cookie> properties) {

                return message.removeCookies(properties);
            }

        }, COOKIE);
    }


    @Test
    public void testAddNullCookie() throws Exception {

        Request request = new Request(TEST_URL);

        assertEquals("no headers should exist", 0, request.getHeaders().size());

        request.addCookie(null);

        assertEquals("no headers should have been added.", 0, request.getHeaders().size());
    }
}
