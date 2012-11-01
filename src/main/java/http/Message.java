package http;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Karl Bennett
 */
public class Message {

    public Collection<Header> getHeaders() {

        return null;
    }

    public void setHeaders(Collection<Header> headers) {

    }

    public void addHeader(String name, Object value) {

        addHeader(new Header(name, Collections.singletonList(value)));
    }

    public void addHeader(Header header) {

    }

    public Collection<Parameter> getParameters() {

        return null;
    }

    public void setParameters(Collection<Parameter> parameters) {

    }

    public void addParameter(String name, Object value) {

        addParameter(new Parameter(name, Collections.singletonList(value)));
    }

    public void addParameter(Parameter parameter) {

    }

    public Collection<Cookie> getCookies() {

        return null;
    }

    public void setCookies(Collection<Cookie> cookies) {

    }

    public void addCookie(String name, String value) {

        addCookie(new Cookie(name, value));
    }

    public void addCookie(Cookie cookie) {

    }

    public Body getBody() {

        return null;
    }

    public void setBody(Body body) {

    }
}
