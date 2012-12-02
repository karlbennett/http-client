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
public class ClientRequestCookieObjectTest extends AbstractMessageCookieTest<Request<Object>> {


    public ClientRequestCookieObjectTest() {
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
}
