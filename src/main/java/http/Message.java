package http;

import http.attribute.Attribute;
import http.attribute.AttributeHashSetMap;
import http.attribute.AttributeMap;
import http.attribute.MultiAttributeMap;
import http.header.Header;
import http.util.NullSafeForEach;

import java.util.*;

import static http.util.Checks.isNotNull;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message<T> {

    protected static <K, V> Set<V> getAllValues(Map<K, Set<V>> map) {

        Set<V> allValues = new HashSet<V>();

        for (Set<V> values : map.values()) allValues.addAll(values);

        return allValues;
    }

    protected static <K, V> Set<V> getNotNullValue(Map<K, Set<V>> map, K key) {

        Set<V> values = map.get(key);

        return isNotNull(values) ? values : Collections.<V>emptySet();
    }

    protected static <V extends Attribute> V remove(MultiAttributeMap<V, Set<V>> map, V attribute) {

        if (map.remove(attribute)) return attribute;

        return null;
    }

    protected static <V extends Attribute, C extends Collection<V>> Collection<V> removeAll(
            MultiAttributeMap<V, C> map, Collection<V> attributes) {

        if (map.removeAll(attributes)) return attributes;

        return Collections.emptySet();
    }


    private final String cookieHeaderName;
    private final MultiAttributeMap<Header, Set<Header>> headers;
    private final AttributeMap<Cookie> cookies;
    private T body;


    /**
     * Create a new Message with an {@link http.attribute.AttributeMap} of headers.
     *
     * @param headers the headers that will be contained in this message.
     */
    public Message(String cookieHeaderName, MultiAttributeMap<Header, Set<Header>> headers,
                   AttributeMap<Cookie> cookies, T body) {

        this.cookieHeaderName = cookieHeaderName;
        this.headers = headers;
        this.cookies = cookies;
        this.body = body;
    }

    /**
     * Create a new {@code Message} that doesn't contain any {@link Header}s.
     */
    public Message(String cookieHeaderName) {

        this.cookieHeaderName = cookieHeaderName;
        this.headers = new AttributeHashSetMap<Header>();
        this.cookies = new AttributeMap<Cookie>();
    }

    /**
     * Get all the headers set for the current {@code Message}.
     *
     * @return the message headers.
     */
    public Set<Header> getHeaders() {

        return getAllValues(headers);
    }

    /**
     * Get all instances of the {@link Header} with the supplied name. If no instances exist this method will return
     * null.
     *
     * @param name the name of the header to retrieve.
     * @return the instances of the requested header if any exists otherwise an empty {@code Set}.
     */
    public Set<Header> getHeaders(String name) {

        return getNotNullValue(headers, name);
    }

    /**
     * Set the headers for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param headers the new headers for the message.
     */
    public void setHeaders(Collection<Header> headers) {

        this.headers.clear();

        this.headers.addAll(headers);
    }

    /**
     * Add a header to the {@code Message} made up of the supplied name and value. If a header with the supplied name
     * already exists then the supplied value will be added to the existing headers values.
     *
     * @param name  the name of the new header.
     * @param value the value for the new header.
     */
    public void addHeader(String name, Object value) {

        addHeader(new Header<>(name, value));
    }

    /**
     * Add a {@link Header} to the {@code Message} appending it to any added previously. If a header with the a matching
     * name already exists then the new headers value will be added to the existing headers values.
     * <p/>
     * If the supplied {@code Header} is a cookie header, that is it's name matches the {@code Message}
     * {@code cookieHeaderName} value then it will be parsed and added to the {@code Message}'s cookies not headers.
     *
     * @param header the new header to add to the message.
     */
    public void addHeader(Header header) {

        if (isNotNull(header)) {

            headers.add(header);
        }
    }

    /**
     * Add the supplied headers to the current {@code Message}.
     *
     * @param headers the headers to add.
     */
    public void addHeaders(Collection<Header> headers) {

        this.headers.addAll(headers);
    }

    /**
     * Remove the supplied value from the {@link Header} with the supplied name. This will remove all the value from the
     * {@code Header} entry and then if no values are left it will remove the {@code Header} entry completely.
     *
     * @param name the name of the {@code Header} to remove the value from.
     * @param value the value to remove.
     * @return a {@code Header} containing the name and value that was removed if a value was removed, otherwise
     *          {@code null}.
     */
    public Header removeHeader(String name, Object value) {

        return removeHeader(new Header<Object>(name, value));
    }

    /**
     * Remove the supplied {@link Header} from the request. This will remove all the values in the supplied
     * {@code Header} and then if no values are left it will remove the {@code Header} entry completely.
     *
     * @param header the {@code Header} to remove.
     * @return the {@code Header} that was removed if one was removed, otherwise {@code null}.
     */
    public Header removeHeader(Header header) {

        return remove(headers, header);
    }

    /**
     * Remove the supplied {@link Header}s.
     *
     * @param headers the {@code Header}s to remove.
     * @return the {@code Header}s that were removed.
     */
    public Collection<Header> removeHeaders(Collection<Header> headers) {

        return removeAll(this.headers, headers);
    }

    /**
     * Get all the cookies set for the current {@code Message}.
     *
     * @return the message cookies.
     */
    public Collection<Cookie> getCookies() {

        return cookies.values();
    }

    /**
     * Get the {@link Cookie} with the supplied name. If the cookie does not exist this method will return null.
     *
     * @param name the name of the cookie to retrieve.
     * @return the requested cookie if it exists otherwise null.
     */
    public Cookie getCookie(String name) {

        return cookies.get(name);
    }

    /**
     * Set the cookies for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param cookies the new cookies for the message.
     */
    public void setCookies(Collection<Cookie> cookies) {

        this.cookies.clear();

        new NullSafeForEach<Cookie>(cookies) {

            @Override
            protected void next(Cookie cookie) {

                addCookie(cookie);
            }
        };
    }

    /**
     * Add a cookie to the {@code Message} made up of the supplied name and value. If a cookie with the supplied
     * name already exists then it will be replaced with the new cookie.
     *
     * @param name  the name of the new cookie.
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

        if (isNotNull(cookie)) {

            this.cookies.add(cookie);
        }
    }

    /**
     * Add the supplied cookies to the current {@code Message}.
     *
     * @param cookies the cookies to add.
     */
    public void addCookies(Collection<Cookie> cookies) {

        new NullSafeForEach<Cookie>(cookies) {

            @Override
            protected void next(Cookie cookie) {

                addCookie(cookie);
            }
        };
    }

    /**
     * Remove the {@link Cookie} with the supplied name and value.
     *
     * @param name the name of the {@code Cookie} to remove.
     * @param value the value of the {@code Cookie} to remove.
     * @return the {@code Cookie} that was removed if one was removed, otherwise {@code null}.
     */
    public Cookie removeCookie(String name, String value) {

        return removeCookie(new Cookie(name, value));
    }

    /**
     * Remove the supplied {@link Cookie} from the request.
     *
     * @param cookie the {@code Cookie} to remove.
     * @return the {@code Cookie} that was removed if one was removed, otherwise {@code null}.
     */
    public Cookie removeCookie(Cookie cookie) {

        Cookie removedCookie = cookies.get(cookie.getName());

        if (isNotNull(removedCookie) && removedCookie.equals(cookie)) {

            cookies.remove(cookie.getName());

            return cookie;
        }

        return null;
    }

    /**
     * Remove the supplied {@link Cookie}s.
     *
     * @param cookies the {@code Cookie}s to remove.
     * @return the {@code Cookie}s that were removed.
     */
    public Collection<Cookie> removeCookies(Collection<Cookie> cookies) {

        Collection<Cookie> removedCookies = new HashSet<Cookie>();

        for (Cookie cookie : cookies) {

            if (isNotNull(removeCookie(cookie))) removedCookies.add(cookie);
        }

        return removedCookies;
    }

    /**
     * @return the messages body.
     */
    public T getBody() {

        return body;
    }

    /**
     * Set the body of the current message.
     *
     * @param body the new message body.
     */
    public void setBody(T body) {

        this.body = body;
    }
}
