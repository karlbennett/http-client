package http;

import http.attribute.Attribute;
import http.attribute.AttributeArrayListMap;
import http.attribute.AttributeCollectionMap;
import http.attribute.AttributeListMap;
import http.header.*;
import http.header.Cookie;
import http.util.Converter;
import http.util.Mapper;

import java.util.*;

import static http.util.Asserts.assertNotNull;
import static http.util.Checks.isNotNull;

import http.util.Conversion;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message<T> {

    /**
     * A map of conversions that will be used to convert from generic {@link Header} types to specific sub types.
     */
    private static final Map<Class, Conversion> CONVERSIONS = new HashMap<Class, Conversion>() {{
        put(Accept.class, new Conversion<Collection<Accept>, AttributeListMap<Header>>() {

            @Override
            public Collection<Accept> convert(AttributeListMap<Header> headers) {

                return new Mapper<Header, Accept>(headers.get(Accept.ACCEPT)) {

                    @Override
                    protected Accept next(Header header) {

                        return new Accept(header);
                    }

                }.results();
            }
        }
        );
        put(ContentType.class, new Conversion<Collection<ContentType>, AttributeListMap<Header>>() {

            @Override
            public Collection<ContentType> convert(AttributeListMap<Header> headers) {

                return new Mapper<Header, ContentType>(headers.get(ContentType.CONTENT_TYPE)) {

                    @Override
                    protected ContentType next(Header header) {

                        return new ContentType(header);
                    }

                }.results();
            }
        }
        );
        put(Cookie.class, new Conversion<Collection<Cookie>, AttributeListMap<Header>>() {

            @Override
            public Collection<Cookie> convert(AttributeListMap<Header> headers) {

                return new Mapper<Header, Cookie>(headers.get(Cookie.COOKIE)) {

                    @Override
                    protected Cookie next(Header header) {

                        addAll(Cookie.convert(header));

                        return null;
                    }

                }.results();
            }
        }
        );
        put(SetCookie.class, new Conversion<Collection<SetCookie>, AttributeListMap<Header>>() {

            @Override
            public Collection<SetCookie> convert(AttributeListMap<Header> headers) {

                return new Mapper<Header, SetCookie>(headers.get(SetCookie.SET_COOKIE)) {

                    @Override
                    protected SetCookie next(Header header) {

                        addAll(SetCookie.convert(header));

                        return null;
                    }

                }.results();
            }
        }
        );
    }};

    private static final Converter CONVERTER = new Converter(CONVERSIONS);

    /**
     * Get all the values within the supplied multi value map by concatenating the value collections together.
     *
     * @param map the map that will have it's values concatenated.
     * @param <K> the type of the maps keys.
     * @param <V> the type of the maps values.
     * @return all the values concatenated together into a single {@link List}.
     */
    protected static <K, V> List<V> getAllValues(Map<K, List<V>> map) {

        return new Mapper<List<V>, V>(map.values()) {

            @Override
            protected V next(List<V> element) {

                addAll(element);

                return null;
            }

        }.results(map.size() * 2);
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
     * @return a list containing only those attributes that were removed.
     */
    protected static <A extends Attribute, C extends Collection<A>> List<A> removeAll(
            AttributeCollectionMap<A, C> map, Collection<A> attributes) {

        List<A> removedAttributes = new ArrayList<A>();

        for (A attribute : attributes) {

            if (isNotNull(remove(map, attribute))) removedAttributes.add(attribute);
        }

        return removedAttributes;
    }


    private final AttributeListMap<Header> headers;
    private T body;


    /**
     * Create a new Message with an {@link http.attribute.AttributeMap} of headers and a body.
     *
     * @param headers the headers that will be contained in this message.
     * @param body    an object that represents to body of the message.
     */
    public Message(Collection<Header> headers, T body) {

        this.headers = new AttributeArrayListMap<Header>(headers);
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
    public List<Header> getHeaders() {

        return getAllValues(headers);
    }

    /**
     * Get all instances of the {@link Header} with the supplied name. If no instances exist this method will return
     * {@code null}.
     *
     * @param name the name of the header to retrieve.
     * @return the instances of the requested header if any exists otherwise {@code null}.
     */
    public List<Header> getHeaders(String name) {

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
    public <T extends Header> List<T> getHeaders(Class<T> headerType) {

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
    public List<Header> removeHeaders(Collection<Header> headers) {

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
