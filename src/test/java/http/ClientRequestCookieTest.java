package http;

import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.util.Collection;

import static http.Client.Request;
import static http.Cookies.*;
import static http.Parameters.*;
import static http.Urls.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class ClientRequestCookieTest extends AbstractMessageCookieTest<Request<Object>> {


    public ClientRequestCookieTest() {
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
            public Collection<Cookie> getProperties(Request<Object> message) {

                return message.getCookies();
            }

            @Override
            public void setProperties(Request<Object> message, Collection<Cookie> properties) {

                message.setCookies(properties);
            }

        }, COOKIE);
    }

    @Test
    public void testCreateRequestWithUrlString() throws Exception {

        assertEquals("the request URL should be correct.", TEST_URL, new Request(TEST_URL.toString()).getUrl());
    }

    @Test
    public void testCreateRequestWithUrlStringAndQueryString() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL_STRING + TEST_QUERY_STRING);

        URL url = new URI(TEST_URL_STRING_WITH_QUERY_STRING).toURL();

        assertEquals("the request URL should be correct.", url, request.getUrl());

        Collection<Parameter> parameters = request.getParameters();

        assertEquals("the correct parameters are included in the request.", PARAMETERS, parameters);
    }
}
