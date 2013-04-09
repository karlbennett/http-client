package http.header;

import http.Cookie;
import http.header.util.AbstractCookieConversion;

import java.util.List;

/**
 * An HTTP {@code Set-Cookie} header.
 *
 * @author Karl Bennett
 */
public class SetCookie extends Header<Cookie> {

    public static final String SET_COOKIE = "Set-Cookie";

    /**
     * Convert the supplied {@link Header} into a {@link java.util.List} of {@code SetCookie} instances.
     *
     * @param header the {@code Header} to convert.
     * @return the {@code List} of {@code SetCookie} instances built from the {@code Header}.
     * @throws IllegalArgumentException if the supplied header doesn't have a name of "Set-Cookie" or the value doesn't
     *                                  contain valid HTTP cookies.
     */
    public static List<SetCookie> convert(Header header) {

        return new AbstractCookieConversion<SetCookie, Header>(SetCookie.class, header, SET_COOKIE) {

            @Override
            public SetCookie convert(Cookie cookie) {

                return new SetCookie(cookie);
            }

        }.convert();
    }

    /**
     * Create a {@code SetCookie} header with the supplied {@link Cookie} value.
     *
     * @param cookie the cookie for this header.
     */
    public SetCookie(Cookie cookie) {
        super(SET_COOKIE, cookie);
    }
}
