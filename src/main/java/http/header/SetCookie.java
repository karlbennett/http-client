package http.header;

import http.Cookie;
import http.util.NullSafeForEach;

import java.util.ArrayList;
import java.util.Collections;
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

        if (header instanceof SetCookie) {

            return Collections.singletonList((SetCookie) header);
        }

        if (SET_COOKIE.equals(header.getName())) {

            return new ArrayList<SetCookie>(
                    new NullSafeForEach<Cookie, SetCookie>(Cookie.parse(header.getValue().toString())) {

                        @Override
                        protected SetCookie next(Cookie element) {

                            return new SetCookie(element);
                        }

                    }.results()
            );
        }

        throw new IllegalArgumentException("An " + SetCookie.class.getName() +
                " object can only be created from an \"Set-Cookie\" header.");
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
