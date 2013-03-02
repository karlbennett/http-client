package http;

import http.header.Header;
import http.header.SetCookie;

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


    public static final Cookie COOKIE_ONE = new Cookie(COOKIE_NAME_ONE, COOKIE_VALUE_ONE);
    public static final Cookie COOKIE_TWO = new Cookie(COOKIE_NAME_TWO, COOKIE_VALUE_TWO);
    public static final Cookie COOKIE_THREE = new Cookie(COOKIE_NAME_THREE, COOKIE_VALUE_THREE);


    public static final Collection<Cookie> COOKIES = Collections.unmodifiableCollection(
            Arrays.asList(COOKIE_ONE, COOKIE_TWO, COOKIE_THREE));


    public static final Header<String> STRING_COOKIE_HEADER_ONE = new Header<String>(http.header.Cookie.COOKIE,
            COOKIE_ONE.getName() + '=' + COOKIE_ONE.getValue() + ';');
    public static final Header<String> STRING_COOKIE_HEADER_TWO = new Header<String>(http.header.Cookie.COOKIE,
            COOKIE_TWO.getName() + '=' + COOKIE_TWO.getValue() + ';');
    public static final Header<String> STRING_COOKIE_HEADER_THREE = new Header<String>(http.header.Cookie.COOKIE,
            COOKIE_THREE.getName() + '=' + COOKIE_THREE.getValue() + ';');

    public static final Header<String> STRING_SET_COOKIE_HEADER_ONE = new Header<String>(SetCookie.SET_COOKIE,
            COOKIE_ONE.getName() + '=' + COOKIE_ONE.getValue() + ';');
    public static final Header<String> STRING_SET_COOKIE_HEADER_TWO = new Header<String>(SetCookie.SET_COOKIE,
            COOKIE_TWO.getName() + '=' + COOKIE_TWO.getValue() + ';');
    public static final Header<String> STRING_SET_COOKIE_HEADER_THREE = new Header<String>(SetCookie.SET_COOKIE,
            COOKIE_THREE.getName() + '=' + COOKIE_THREE.getValue() + ';');


    public static final Collection<Header> STRING_COOKIE_HEADERS = Collections.unmodifiableCollection(
            Arrays.<Header>asList((Header) STRING_COOKIE_HEADER_ONE, (Header) STRING_COOKIE_HEADER_TWO,
                    (Header) STRING_COOKIE_HEADER_THREE));

    public static final Collection<Header> STRING_SET_COOKIE_HEADERS = Collections.unmodifiableCollection(
            Arrays.asList((Header) STRING_SET_COOKIE_HEADER_ONE, (Header) STRING_SET_COOKIE_HEADER_TWO,
                    (Header) STRING_SET_COOKIE_HEADER_THREE));


    public static final http.header.Cookie COOKIE_HEADER_ONE = new http.header.Cookie(COOKIE_ONE);
    public static final http.header.Cookie COOKIE_HEADER_TWO = new http.header.Cookie(COOKIE_TWO);
    public static final http.header.Cookie COOKIE_HEADER_THREE = new http.header.Cookie(COOKIE_THREE);

    public static final SetCookie SET_COOKIE_HEADER_ONE = new SetCookie(COOKIE_ONE);
    public static final SetCookie SET_COOKIE_HEADER_TWO = new SetCookie(COOKIE_TWO);
    public static final SetCookie SET_COOKIE_HEADER_THREE = new SetCookie(COOKIE_THREE);


    public static final Collection<http.header.Cookie> COOKIE_HEADERS = Collections.unmodifiableCollection(
            Arrays.asList(COOKIE_HEADER_ONE, COOKIE_HEADER_TWO, COOKIE_HEADER_THREE));

    public static final Collection<SetCookie> SET_COOKIE_HEADERS = Collections.unmodifiableCollection(
            Arrays.asList(SET_COOKIE_HEADER_ONE, SET_COOKIE_HEADER_TWO, SET_COOKIE_HEADER_THREE));
}
