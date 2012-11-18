package http;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * A utility class containing constant cookie values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Cookies {

    private Cookies() {
    }

    public static final String SET_COOKIE = "Set-Cookie";
    public static final String COOKIE = "Cookie";

    public static final String COOKIE_NAME_ONE = "cookie_name_one";
    public static final String COOKIE_VALUE_ONE = "cookie_value_one";
    public static final String COOKIE_NAME_TWO = "cookie_name_two";
    public static final String COOKIE_VALUE_TWO = "cookie_value_two";
    public static final String COOKIE_NAME_THREE = "cookie_name_three";
    public static final String COOKIE_VALUE_THREE = "cookie_value_three";

    public static final String SET_COOKIE_NAME_ONE = "set_cookie_name_one";
    public static final String SET_COOKIE_VALUE_ONE = "set_cookie_value_one";
    public static final String SET_COOKIE_NAME_TWO = "set_cookie_name_two";
    public static final String SET_COOKIE_VALUE_TWO = "set_cookie_value_two";
    public static final String SET_COOKIE_NAME_THREE = "set_cookie_name_three";
    public static final String SET_COOKIE_VALUE_THREE = "set_cookie_value_three";

    public static final Header<String> SET_COOKIE_HEADER_ONE = new Header<String>(SET_COOKIE,
            COOKIE_NAME_ONE + COOKIE_VALUE_ONE);
    public static final Header<String> SET_COOKIE_HEADER_TWO = new Header<String>(SET_COOKIE,
            COOKIE_NAME_TWO + COOKIE_VALUE_TWO);
    public static final Header<String> SET_COOKIE_HEADER_THREE = new Header<String>(SET_COOKIE,
            COOKIE_NAME_THREE + COOKIE_VALUE_THREE);

    public static final Collection<Header> SET_COOKIE_HEADERS = Collections.unmodifiableCollection(
            Arrays.asList((Header) SET_COOKIE_HEADER_ONE, (Header) SET_COOKIE_HEADER_TWO,
                    (Header) SET_COOKIE_HEADER_THREE));

    public static final Header<String> COOKIE_HEADER_ONE = new Header<String>(COOKIE,
            COOKIE_NAME_ONE + COOKIE_VALUE_ONE);
    public static final Header<String> COOKIE_HEADER_TWO = new Header<String>(COOKIE,
            COOKIE_NAME_TWO + COOKIE_VALUE_TWO);
    public static final Header<String> COOKIE_HEADER_THREE = new Header<String>(COOKIE,
            COOKIE_NAME_THREE + COOKIE_VALUE_THREE);

    public static final Collection<Header> COOKIE_HEADERS = Collections.unmodifiableCollection(
            Arrays.<Header>asList((Header) COOKIE_HEADER_ONE, (Header) COOKIE_HEADER_TWO, (Header) COOKIE_HEADER_THREE));

    public static final Cookie COOKIE_ONE = new Cookie(COOKIE_NAME_ONE, COOKIE_VALUE_ONE);
    public static final Cookie COOKIE_TWO = new Cookie(COOKIE_NAME_TWO, COOKIE_VALUE_TWO);
    public static final Cookie COOKIE_THREE = new Cookie(COOKIE_NAME_THREE, COOKIE_VALUE_THREE);

    public static final Cookie SET_COOKIE_ONE = new Cookie(SET_COOKIE_NAME_ONE, SET_COOKIE_VALUE_ONE);
    public static final Cookie SET_COOKIE_TWO = new Cookie(SET_COOKIE_NAME_TWO, SET_COOKIE_VALUE_TWO);
    public static final Cookie SET_COOKIE_THREE = new Cookie(SET_COOKIE_NAME_THREE, SET_COOKIE_VALUE_THREE);

    public static final Collection<Cookie> COOKIES = Collections.unmodifiableCollection(
            Arrays.asList(COOKIE_ONE, COOKIE_TWO, COOKIE_THREE));
}
