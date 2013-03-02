package http;

import http.attribute.*;
import http.header.*;
import http.util.NullSafeForEach;

import java.util.*;

import static http.util.Asserts.assertNotNull;
import static http.util.Checks.isNotNull;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message<T> {

    /**
     * Get all the values within the supplied multi value map by concatenating the value collections together.
     *
     * @param map the map that will have it's values concatenated.
     * @param <K> the type of the maps keys.
     * @param <V> the type of the maps values.
     * @return all the values concatenated together into a single {@link Set}.
     */
    protected static <K, V> Set<V> getAllValues(Map<K, Set<V>> map) {

        Set<V> allValues = new HashSet<V>();

        for (Set<V> values : map.values()) allValues.addAll(values);

        return allValues;
    }

    /**
     * Remove the supplied attribute from the map and return the attribute that was removed.
     *
     * @param map       the map that will have it's attribute removed.
     * @param attribute the attribute to remove.
     * @param <A>       the type of the attribute.
     * @param <C>       the type of the maps value collection.
     * @return the attribute if it was removed from the map, otherwise {@code null}.
     */
    protected static <A extends Attribute, C extends Collection<A>> A remove(AttributeCollectionMap<A, C> map,
                                                                             A attribute) {
        if (map.remove(attribute)) return attribute;

        return null;
    }

    /**
     * Remove the supplied attributes from the map and return the attributes that were removed.
     *
     * @param map        the map that will have it's attributes removed.
     * @param attributes the attributes to remove.
     * @param <A>        the type of the attribute.
     * @param <C>        the type of the maps value collection.
     * @return a collection containing only those attributes that were removed.
     */
    protected static <A extends Attribute, C extends Collection<A>> Collection<A> removeAll(
            AttributeCollectionMap<A, C> map, Collection<A> attributes) {

        Collection<A> removedAttributes = new HashSet<A>();

        for (A attribute : attributes) {

            if (isNotNull(remove(map, attribute))) removedAttributes.add(attribute);
        }

        return removedAttributes;
    }


    private final AttributeSetMap<Header> headers;
    private T body;


    /**
     * Create a new Message with an {@link http.attribute.AttributeMap} of headers.
     *
     * @param headers the headers that will be contained in this message.
     */
    public Message(Collection<Header> headers, T body) {

        this.headers = new AttributeHashSetMap<Header>();
        this.body = body;

        addHeaders(headers);
    }

    /**
     * Create a new {@code Message} that doesn't contain any {@link Header}s.
     */
    public Message() {

        this(Collections.<Header>emptySet(), null);
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
     * {@code null}.
     * <p/>
     * This method will return headers that contain values of any type. It should also be noted that if a header has
     * been added with a value of one type it cannot be guaranteed to be retrieved through this method with the same
     * type.
     * <p/>
     * This is because the common HTTP header types have their own object definitions and value types. Any header that
     * is added to the {@code Message} with the name of a common HTTP header will be converted into one of these object
     * types.
     * <p/>
     * <code>
     * Message message = new Message();
     * message.addHeader(new Header("Accept", "application/json"));
     * Collection<Header> headers = message.getHeaders("Accept");
     * Iterator<Header> iterator headers.iterator();
     * Header header = iterator.next()
     * header.getClass().getName() // http.header.JsonAccept
     * header.getValue().getClass().getName() // javax.activation.MimeType
     * </code>
     *
     * @param name the name of the header to retrieve.
     * @return the instances of the requested header if any exists otherwise an empty {@code Set}.
     */
    public Set<Header> getHeaders(String name) {

        return headers.get(name);
    }

    public <T extends Header> Set<T> getHeaders(Class<T> headerType) {

        return null;
    }

    /**
     * Set the headers for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param headers the new headers for the message.
     */
    public <H extends Header> void setHeaders(Collection<H> headers) {

        this.headers.clear();

        addHeaders(headers);
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
     * Add a {@link Header} to the {@code Message} appending it to any added previously. If a header with a matching
     * name already exists then the new headers value will be added to the existing headers values.
     * <p/>
     * If the supplied {@code Header} is a standard HTTP header ("Set-Cookie", "Content-Type", "Accept"...) it will be
     * converted internally into an instance of it's corresponding header object ({@link SetCookie},
     * {@link ContentType}, {@link Accept}...).
     *
     * @param header the new header to add to the message.
     */
    public void addHeader(Header header) {

        if (isNotNull(header)) headers.add(header);
    }

    /**
     * Add the supplied headers to the current {@code Message}.
     *
     * @param headers the headers to add.
     */
    public <H extends Header> void addHeaders(Collection<H> headers) {

        assertNotNull("headers", headers);

        for (Header header : headers) addHeader(header);
    }

    /**
     * Remove the supplied value from the {@link Header} with the supplied name. This will remove all the value from the
     * {@code Header} entry and then if no values are left it will remove the {@code Header} entry completely.
     *
     * @param name  the name of the {@code Header} to remove the value from.
     * @param value the value to remove.
     * @return a {@code Header} containing the name and value that was removed if a value was removed, otherwise
     *         {@code null}.
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
