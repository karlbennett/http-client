package http.header.util;

import http.Cookie;
import http.header.Header;
import http.util.AbstractConversion;
import http.util.Conversion;
import http.util.Mapper;

import java.util.Collections;
import java.util.List;

import static http.util.Checks.isNotNull;

/**
 * Abstract class that contains generic logic for converting {@link Header} classes into {@link http.header.Cookie} and
 * {@link http.header.SetCookie} headers.
 *
 * @author Karl Bennett
 */
public abstract class AbstractCookieHeaderConversion<C extends Header<Cookie>, O extends Header> implements
        Conversion<C, Cookie> {

    private List<C> headers;
    private final AbstractConversion<C, O> conversion;


    /**
     * Create an {@code AbstractCookieHeaderConversion} that converts the supplied {@code Header} into the required
     * {@code type} with the required {@code name}.
     *
     * @param type   the type of cookie header to convert to.
     * @param header the header to convert.
     * @param name   the name of the required cookie header.
     */
    public AbstractCookieHeaderConversion(Class type, O header, String name) {

        conversion = new AbstractHeaderConversion<C, O>(type, header, name) {

            @Override
            public C convert(O header) {

                headers = new Mapper<Cookie, C>(Cookie.parse(header.getValue().toString())) {

                    @Override
                    protected C next(Cookie cookie) {

                        return AbstractCookieHeaderConversion.this.convert(cookie);
                    }

                }.results();

                return null;
            }
        };
    }

    /**
     * Convert the contained header into the required cookie header or headers.
     *
     * @return the required object type.
     */
    @SuppressWarnings("unchecked")
    public List<C> convert() {

        C header = conversion.convert();

        if (isNotNull(header)) return Collections.singletonList(header);

        return headers;
    }
}
