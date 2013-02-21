package http;

import java.util.Arrays;
import java.util.Collection;

import static http.Cookies.COOKIE;
import static http.Urls.TEST_URL;

/**
 * @author Karl Bennett
 */
public class RequestCookieNameAndValueTest extends AbstractMessageCookieTest<Request<Object>> {


    public RequestCookieNameAndValueTest() {
        super(new MessageExecutor<Request<Object>, Cookie>() {

            @Override
            public Request<Object> newMessage() {

                return new Request<Object>(TEST_URL);
            }

            @Override
            public Collection<Cookie> getProperties(Request<Object> message, String name) {

                return Arrays.asList(message.getCookie(name));
            }

            @Override
            public void addProperty(Request<Object> message, Cookie property) {

                message.addCookie(property.getName(), property.getValue());
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

                return message.removeCookie(property.getName(), property.getValue());
            }

            @Override
            public Collection<Cookie> removeProperties(Request<Object> message, Collection<Cookie> properties) {

                return message.removeCookies(properties);
            }

        }, COOKIE);
    }
}
