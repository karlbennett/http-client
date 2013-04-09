package http.header;

import http.header.util.CookieConversion;

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

        return new CookieConversion<Header, Cookie>(Cookie.class, header, COOKIE) {

            @Override
            public Cookie create(http.Cookie cookie) {

                return new Cookie(cookie);
            }

        }.convert();
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
