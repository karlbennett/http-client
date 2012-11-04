package http;

import java.util.Collection;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message<T> {

    /**
     * Get all the headers set for the current {@code Message}.
     *
     * @return the message headers.
     */
    public Collection<Header> getHeaders() {

        return null;
    }

    /**
     * Get the {@link Header} with the supplied name. If the header does not exist this method will return null.
     *
     * Care must be taken with this method because it will implicitly cast the generic type of the {@code Header} so can
     * produce ClassCastExceptions exceptions at runtime.
     *
     * <code>
     *     request.addHeader(new Header&lt;String&gt;("number", "one"));
     *     Header&lt;Integer&gt; header = request.getHeader("number"); // This will not produce an unchecked warning.
     *     int number = header.getValue(); // This will compile and fail at runtime with a ClassCastException.
     * </code>
     *
     * @param name the name of the header to retrieve.
     * @param <T> the type of the headers value.
     * @return the requested header if it exists otherwise null.
     */
    public <T> Header<T> getHeader(String name) {

        return null;
    }

    /**
     * Set the headers for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param headers the new headers for the message.
     */
    public void setHeaders(Collection<Header> headers) {

    }

    /**
     * Add a header to the {@code Message} made up of the supplied name and value. If a header with the supplied name
     * already exists then the supplied value will be added to the existing headers values.
     *
     * @param name the name of the new header.
     * @param value the value for the new header.
     * @param <T> the type of the headers value.
     */
    public <T> void addHeader(String name, T value) {

        addHeader(new Header<T>(name, value));
    }

    /**
     * Add a {@link Header} to the {@code Message} appending it to any added previously. If a header with the a matching
     * name already exists then the new headers value will be added to the existing headers values.
     *
     * @param header the new header to add to the message.
     * @param <T> the type of the headers value.
     */
    public <T> void addHeader(Header<T> header) {

    }

    /**
     * Get all the cookies set for the current {@code Message}.
     *
     * @return the message cookies.
     */
    public Collection<Cookie> getCookies() {

        return null;
    }

    /**
     * Get the {@link Cookie} with the supplied name. If the cookie does not exist this method will return null.
     *
     * @param name the name of the cookie to retrieve.
     * @return the requested cookie if it exists otherwise null.
     */
    public Cookie getCookie(String name) {

        return null;
    }

    /**
     * Set the cookies for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param cookies the new cookies for the message.
     */
    public void setCookies(Collection<Cookie> cookies) {

    }

    /**
     * Add a cookie to the {@code Message} made up of the supplied name and value. If a cookie with the supplied
     * name already exists then it will be replaced with the new cookie.
     *
     * @param name the name of the new cookie.
     * @param value the value for the new cookie.
     */
    public void addCookie(String name, String value) {

        addCookie(new Cookie(name, value));
    }

    /**
     * Add a {@link Cookie} to the {@code Message} appending it to any added previously. If a cookie with the a
     * matching name already exists then it will be replaced with the new cookie.
     *
     * @param cookie the new parameter to add to the message.
     */
    public void addCookie(Cookie cookie) {

    }

    /**
     * @return the messages body.
     */
    public T getBody() {

        return null;
    }

    /**
     * Set the body of the current message.
     *
     * @param body the new message body.
     */
    public void setBody(T body) {

    }
}
