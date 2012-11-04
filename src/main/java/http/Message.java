package http;

import java.util.Collection;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message {

    /**
     * Get all the headers set for the current {@code Message}.
     *
     * @return the message headers.
     */
    public Collection<Header<?>> getHeaders() {

        return null;
    }

    public <T> Header<T> getHeader(String name) {

        return null;
    }

    public void setHeaders(Collection<Header<?>> headers) {

    }

    public <T> void addHeader(String name, T value) {

        addHeader(new Header<T>(name, value));
    }

    public <T> void addHeader(Header<T> header) {

    }

    public Collection<Parameter<?>> getParameters() {

        return null;
    }

    public <T> Parameter<T> getParameter(String name) {

        return null;
    }

    public void setParameters(Collection<Parameter<?>> parameters) {

    }

    public <T> void addParameter(String name, T value) {

        addParameter(new Parameter<T>(name, value));
    }

    public void addParameter(Parameter parameter) {

    }

    public Collection<Cookie> getCookies() {

        return null;
    }

    public Cookie getCookie(String name) {

        return null;
    }

    public void setCookies(Collection<Cookie> cookies) {

    }

    public void addCookie(String name, String value) {

        addCookie(new Cookie(name, value));
    }

    public void addCookie(Cookie cookie) {

    }

    public <T> Body<T> getBody() {

        return null;
    }

    public <T> void setBody(Body<T> body) {

    }
}
