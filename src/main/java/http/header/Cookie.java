package http.header;

import http.util.NullSafeForEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An HTTP {@code Cookie} header.
 *
 * @author Karl Bennett
 */
public class Cookie extends Header<http.Cookie> {

    public static final String COOKIE = "Cookie";

    /**
     * Convert the supplied {@link Header} into a {@link List} of {@code Cookie} instances.
     *
     * @param header the {@code Header} to convert.
     * @return the {@code List} of {@code Cookie} instances built from the {@code Header}.
     * @throws IllegalArgumentException if the supplied header doesn't have a name of "Cookie" or the value doesn't
     *                                  contain valid HTTP cookies.
     */
    public static List<Cookie> convert(Header header) {

        if (header instanceof Cookie) {

            return Collections.singletonList((Cookie) header);
        }

        if (COOKIE.equals(header.getName())) {

            return new ArrayList<Cookie>(
                    new NullSafeForEach<http.Cookie, Cookie>(http.Cookie.parse(header.getValue().toString())) {

                        @Override
                        protected Cookie next(http.Cookie element) {

                            return new Cookie(element);
                        }

                    }.results()
            );
        }

        throw new IllegalArgumentException("An " + Cookie.class.getName() +
                " object can only be created from an \"Cookie\" header.");
    }

    /**
     * Create a {@code Cookie} header with the supplied {@link http.Cookie}.
     *
     * @param cookie the cookie for this header.
     */
    public Cookie(http.Cookie cookie) {
        super(COOKIE, cookie);
    }
}
