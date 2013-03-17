package http;

import http.attribute.Attribute;
import http.attribute.AttributeCollectionMap;
import http.attribute.AttributeHashSetMap;
import http.attribute.AttributeSetMap;
import http.header.Accept;
import http.header.ContentType;
import http.header.Header;
import http.header.SetCookie;
import http.util.Converter;

import java.util.*;

import static http.util.Asserts.assertNotNull;
import static http.util.Checks.isNotNull;
import static http.util.Converter.Conversion;
import static http.util.Maps.populateMap;
import static java.util.AbstractMap.SimpleEntry;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message<T> {

    private static final Map<Class, Conversion> CONVERSIONS = populateMap(new HashMap<Class, Conversion>(),

            new SimpleEntry<Class, Conversion>(Accept.class,
                    new Conversion<Collection<Accept>, AttributeSetMap<Header>>() {

                        @Override
                        public Collection<Accept> convert(AttributeSetMap<Header> object) {

                            return null;
                        }
                    }),
            new SimpleEntry<Class, Conversion>(ContentType.class,
                    new Conversion<Collection<ContentType>, AttributeSetMap<Header>>() {

                        @Override
                        public Collection<ContentType> convert(AttributeSetMap<Header> object) {

                            return null;
                        }
                    }
            ),
            new SimpleEntry<Class, Conversion>(http.header.Cookie.class,
                    new Conversion<Collection<http.header.Cookie>, AttributeSetMap<Header>>() {

                        @Override
                        public Collection<http.header.Cookie> convert(AttributeSetMap<Header> object) {

                            return null;
                        }
                    }
            ),
            new SimpleEntry<Class, Conversion>(SetCookie.class,
                    new Conversion<Collection<SetCookie>, AttributeSetMap<Header>>() {

                        @Override
                        public Collection<SetCookie> convert(AttributeSetMap<Header> object) {

                            return null;
                        }
                    }
            )
    );

    private static final Converter CONVERTER = new Converter(CONVERSIONS);

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
     * Create a new Message with an {@link http.attribute.AttributeMap} of headers and a body.
     *
     * @param headers the headers that will be contained in this message.
     * @param body    an object that represents to body of the message.
     */
    public Message(Collection<Header> headers, T body) {

        this.headers = new AttributeHashSetMap<Header>(headers);
        this.body = body;
    }

    /**
     * Create a new Message with an {@link http.attribute.AttributeMap} of headers.
     *
     * @param headers the headers that will be contained in this message.
     */
    public Message(Collection<Header> headers) {

        this(headers, null);
    }

    /**
     * Create a new empty {@code Message}.
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
     *
     * @param name the name of the header to retrieve.
     * @return the instances of the requested header if any exists otherwise {@code null}.
     */
    public Set<Header> getHeaders(String name) {

        return headers.get(name);
    }

    /**
     * Get all the instances of the {@link Header} of the supplied type. This will return any headers that can be
     * converted into any of the {@link http.header} types. e.g. any "Set-Cookie" headers could be retrieved as
     * {@link SetCookie} instances.
     *
     * @param headerType the type of header to retrieve.
     * @param <T>        the type fo the retrieved header.
     * @return the instances of the requested header types if any exists otherwise {@code null}.
     */
    public <T extends Header> Set<T> getHeaders(Class<T> headerType) {

        return CONVERTER.convert(headerType, headers);
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
     * Add a header to the {@code Message} made up of the supplied name and value. Multiple headers of the same name can
     * be added as long as they have different values.
     *
     * @param name  the name of the new header.
     * @param value the value for the new header.
     */
    public void addHeader(String name, Object value) {

        addHeader(new Header<Object>(name, value));
    }

    /**
     * Add a {@link Header} to the {@code Message}. Multiple headers of the same name can be added as long as they have
     * different values.
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
     * Remove the supplied value from the {@link Header} with the supplied name.
     *
     * @param name  the name of the {@code Header} to remove.
     * @param value the value of the {@code Header} to remove.
     * @return a {@code Header} containing the name and value that were removed if it previously existed in the message,
     *         otherwise {@code null}.
     */
    public Header removeHeader(String name, Object value) {

        return removeHeader(new Header<Object>(name, value));
    }

    /**
     * Remove the supplied {@link Header} from the request.
     *
     * @param header the {@code Header} to remove.
     * @return the {@code Header} that was removed if it previously existed in the message, otherwise {@code null}.
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
     * Get the body of the message.
     *
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
