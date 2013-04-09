package http.header.util;

import http.Cookie;
import http.header.Header;
import http.util.Mapper;

import java.util.Collections;
import java.util.List;

/**
 * Abstract class that contains generic logic for converting {@link Header} classes into {@link http.header.Cookie} and
 * {@link http.header.SetCookie} headers.
 *
 * @author Karl Bennett
 */
public abstract class CookieConversion<O extends Header, D extends Header<Cookie>> {

    private final Class type;
    private final O header;

    public CookieConversion(Class type, O header, String name) {

        if (!name.equals(header.getName())) {

            throw new IllegalArgumentException("An " + type.getName() +
                    " object can only be created from an \"" + name + "\" header.");
        }

        this.type = type;
        this.header = header;
    }


    @SuppressWarnings("unchecked")
    public List<D> convert() {

        if (type.isAssignableFrom(header.getClass())) {

            return Collections.singletonList((D) header);
        }

        return new Mapper<Cookie, D>(Cookie.parse(header.getValue().toString())) {

                    @Override
                    protected D next(Cookie cookie) {

                        return create(cookie);
                    }

                }.results();
    }

    public abstract D create(Cookie cookie);
}
