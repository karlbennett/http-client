package http;

import java.util.Collection;

import static http.Cookies.SET_COOKIE;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * @author Karl Bennett
 */
public class ClientResponseCookieNameAndValueTest extends AbstractMessageCookieTest<Response<Object>> {


    public ClientResponseCookieNameAndValueTest() {
        super(new MessageExecutor<Response<Object>, Cookie>() {

            @Override
            public Response<Object> newMessage() {

                return new Response<Object>(HTTP_OK);
            }

            @Override
            public Cookie getProperty(Response<Object> message, String name) {

                return message.getCookie(name);
            }

            @Override
            public void addProperty(Response<Object> message, Cookie property) {

                message.addCookie(property.getName(), property.getValue());
            }

            @Override
            public Collection<Cookie> getProperties(Response<Object> message) {

                return message.getCookies();
            }

            @Override
            public void setProperties(Response<Object> message, Collection<Cookie> properties) {

                message.setCookies(properties);
            }

        }, SET_COOKIE);
    }
}
