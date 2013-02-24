package http.header;

import http.Cookie;

/**
 * An HTTP {@code Set-Cookie} header.
 *
 * @author Karl Bennett
 */
public class SetCookie extends Header<Cookie> {

    public static final String SET_COOKIE = "Set-Cookie";

    /**
     * Create a {@code SetCookie} header with the supplied {@link Cookie} value.
     *
     * @param cookie the cookie for this header.
     */
    public SetCookie(Cookie cookie) {
        super(SET_COOKIE, cookie);
    }
}
