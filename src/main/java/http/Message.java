package http;

import http.attribute.*;
import http.header.*;
import http.util.NullSafeForEach;

import javax.activation.MimeType;
import java.util.*;

import static http.util.Checks.isNotNull;
import static http.util.MimeTypes.*;

/**
 * Represents a generic HTTP message and supplies accessor methods for retrieving and populating the common HTTP message
 * components.
 *
 * @author Karl Bennett
 */
public class Message<T> {

    /**
     * An interface that allows the creation of a collection of classes through the use of a single value.
     *
     * @param <T> the type of the objects that will be returned in the collection.
     * @param <A> the type of the value that will be passed to the {@link #create(Object)} method.
     */
    private static interface Creator<T, A> {

        /**
         * Create a collection of class instances.
         *
         * @param value the value that will be used in creating the instances.
         * @return a collection containing an unknown number of new object instances.
         */
        Collection<T> create(A value);
    }

    /**
     * A map containing {@link Creator}s that will be used to create specific HTTP header types.
     */
    private static final Map<String, Creator<Header, Header>> HEADER_CREATION_MAP;

    static {

        /**
         * An implementation of the {@link Creator} interface that is used to create the cookie header objects
         * {@link SetCookie} and {@link http.header.Cookie}.
         */
        abstract class CookieHeaderCreator<C extends Header> implements Creator<Header, Header> {

            private final Class<C> cookieType;


            /**
             * Create a new {@code CookieHeaderCreator} that creates the supplied cookie header type.
             * @param cookieType the type of cookie header that will be created.
             */
            protected CookieHeaderCreator(Class<C> cookieType) {

                this.cookieType = cookieType;
            }


            /**
             * This method is implemented to produce the new cookie header instances.
             *
             * @param cookie the {@link Cookie} that will be placed in the cookie header.
             * @return the cookie header that was created.
             */
            protected abstract C createCookieHeader(Cookie cookie);

            /**
             * Create the cookie headers.
             *
             * @param value the value that will be used in creating the instances.
             *
             * @return the new cookie header instances.
             */
            @Override
            public Collection<Header> create(Header value) {

                if (cookieType.isAssignableFrom(value.getClass())) return Collections.singleton(value);

                Collection<Header> setCookies = new HashSet<Header>();

                for (Cookie cookie : Cookie.parse(value.getValue().toString())) {

                    setCookies.add(createCookieHeader(cookie));
                }

                return setCookies;
            }
        }


        Map<String, Creator<Header, Header>> headerCreationMap = new HashMap<String, Creator<Header, Header>>();

        headerCreationMap.put(SetCookie.SET_COOKIE, new CookieHeaderCreator<SetCookie>(SetCookie.class) {

            @Override
            protected SetCookie createCookieHeader(Cookie cookie) {

                return new SetCookie(cookie);
            }
        });

        headerCreationMap.put(http.header.Cookie.COOKIE,
                new CookieHeaderCreator<http.header.Cookie>(http.header.Cookie.class) {

                    @Override
                    protected http.header.Cookie createCookieHeader(Cookie cookie) {

                        return new http.header.Cookie(cookie);
                    }
                });

        headerCreationMap.put(ContentType.CONTENT_TYPE, new Creator<Header, Header>() {

            @Override
            public Collection<Header> create(Header value) {

                MimeType mimeType = quietMimeType(value.toString());

                Collection<Header> contentTypes;

                if (APPLICATION_X_WWW_FORM_URL_ENCODED.equals(mimeType)) {

                    contentTypes = Arrays.<Header>asList(new FormUrlEncodedContentType());

                } else if (APPLICATION_JSON.equals(mimeType)) {

                    contentTypes = Arrays.<Header>asList(new JsonContentType());

                } else if (MULTIPART_FORM_DATA.equals(mimeType)) {

                    contentTypes = Arrays.<Header>asList(new MultipartFormDataContentType());

                } else if (APPLICATION_XML.equals(mimeType)) {

                    contentTypes = Arrays.<Header>asList(new XmlContentType());

                } else {

                    contentTypes = Arrays.<Header>asList(new ContentType(mimeType));
                }

                return contentTypes;
            }
        });

        HEADER_CREATION_MAP = headerCreationMap;
    }


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


    private final String cookieHeaderName;
    private final AttributeSetMap<Header> headers;
    private final AttributeMap<Cookie> cookies;
    private T body;


    /**
     * Create a new Message with an {@link http.attribute.AttributeMap} of headers.
     *
     * @param headers the headers that will be contained in this message.
     */
    public Message(String cookieHeaderName, Collection<Header> headers, AttributeMap<Cookie> cookies, T body) {

        this.cookieHeaderName = cookieHeaderName;
        this.headers = new AttributeHashSetMap<Header>();
        this.cookies = cookies;
        this.body = body;

        addHeaders(headers);
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
     * {@code null}.
     *
     * This method will return headers that contain values of any type. It should also be noted that if a header has
     * been added with a value of one type it cannot be guaranteed to be retrieved through this method with the same
     * type.
     *
     * This is because the common HTTP header types have their own object definitions and value types. Any header that
     * is added to the {@code Message} with the name of a common HTTP header will be converted into one of these object
     * types.
     *
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

    /**
     * Set the headers for this {@code Message}, overwriting any that might have previously been set.
     *
     * @param headers the new headers for the message.
     */
    public void setHeaders(Collection<Header> headers) {

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

        if (isNotNull(header)) {

            // Check to see if there is a custom creator for the supplied header type and use it if there is.
            Creator<Header, Header> creator = HEADER_CREATION_MAP.get(header.getName());

            if (isNotNull(creator)) {

                headers.addAll(creator.create(header));

            } else {

                headers.add(header);
            }

        }
    }

    /**
     * Add the supplied headers to the current {@code Message}.
     *
     * @param headers the headers to add.
     */
    public void addHeaders(Collection<Header> headers) {

        new NullSafeForEach<Header>(headers) {

            @Override
            protected void next(Header header) {

                addHeader(header);
            }
        };
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
     * @param name  the name of the {@code Cookie} to remove.
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
