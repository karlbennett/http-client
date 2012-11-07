package http;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MessageTest {

    public static final String HEADER_NAME_ONE = "header_name_one";
    public static final String HEADER_VALUE_ONE = "header_value_one";
    public static final String HEADER_NAME_TWO = "header_name_two";
    public static final String HEADER_VALUE_TWO = "header_value_two";
    public static final String HEADER_NAME_THREE = "header_name_three";
    public static final String HEADER_VALUE_THREE = "header_value_three";

    public static final String PARAMETER_NAME_ONE = "parameter_name_one";
    public static final String PARAMETER_VALUE_ONE = "parameter_value_one";
    public static final String PARAMETER_NAME_TWO = "parameter_name_two";
    public static final String PARAMETER_VALUE_TWO = "parameter_value_two";
    public static final String PARAMETER_NAME_THREE = "parameter_name_three";
    public static final String PARAMETER_VALUE_THREE = "parameter_value_three";

    public static final String SET_COOKIE = "Set-Cookie";
    public static final String COOKIE = "Cookie";

    public static final String COOKIE_NAME_ONE = "cookie_name_one";
    public static final String COOKIE_VALUE_ONE = "cookie_value_one";
    public static final String COOKIE_NAME_TWO = "cookie_name_two";
    public static final String COOKIE_VALUE_TWO = "cookie_value_two";
    public static final String COOKIE_NAME_THREE = "cookie_name_three";
    public static final String COOKIE_VALUE_THREE = "cookie_value_three";

    public static final Header<String> HEADER_ONE = new Header<String>(HEADER_NAME_ONE, HEADER_VALUE_ONE);
    public static final Header<String> HEADER_TWO = new Header<String>(HEADER_NAME_TWO, HEADER_VALUE_TWO);
    public static final Header<String> HEADER_THREE = new Header<String>(HEADER_NAME_THREE, HEADER_VALUE_THREE);

    public static final Collection<Header> HEADERS = Collections.unmodifiableCollection(
            Arrays.<Header>asList((Header) HEADER_ONE, (Header) HEADER_TWO, (Header) HEADER_THREE));

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

    public static final Parameter<String> PARAMETER_ONE = new Parameter<String>(PARAMETER_NAME_ONE,
            PARAMETER_VALUE_ONE);
    public static final Parameter<String> PARAMETER_TWO = new Parameter<String>(PARAMETER_NAME_TWO,
            PARAMETER_VALUE_TWO);
    public static final Parameter<String> PARAMETER_THREE = new Parameter<String>(PARAMETER_NAME_THREE,
            PARAMETER_VALUE_THREE);

    public static final Collection<Parameter> PARAMETERS = Collections.unmodifiableCollection(
            Arrays.<Parameter>asList((Parameter) PARAMETER_ONE, (Parameter) PARAMETER_TWO, (Parameter) PARAMETER_THREE));

    public static final Cookie COOKIE_ONE = new Cookie(COOKIE_NAME_ONE, COOKIE_VALUE_ONE);
    public static final Cookie COOKIE_TWO = new Cookie(COOKIE_NAME_TWO, COOKIE_VALUE_TWO);
    public static final Cookie COOKIE_THREE = new Cookie(COOKIE_NAME_THREE, COOKIE_VALUE_THREE);

    public static final Collection<Cookie> COOKIES = Collections.unmodifiableCollection(
            Arrays.asList(COOKIE_ONE, COOKIE_TWO, COOKIE_THREE));

    @Test
    public void testGetHeader() throws Exception {

        Message message = new Message();
        message.setHeaders(new HashSet<Header>(HEADERS));

        assertEquals("header one is retrieved correctly.", HEADER_ONE, message.getHeader(HEADER_NAME_ONE));
        assertEquals("header two is retrieved correctly.", HEADER_TWO, message.getHeader(HEADER_NAME_TWO));
        assertEquals("header three is retrieved correctly.", HEADER_THREE, message.getHeader(HEADER_NAME_THREE));
    }

    @Test
    public void testGetHeaderThatDoesNotExist() throws Exception {

        Message<Object> message = new Message<Object>();

        assertNull("retrieving a header when no headers exist should return null.", message.getHeader(HEADER_NAME_ONE));

        message.setHeaders(new HashSet<Header>(HEADERS));

        assertNull("retrieving a header that does not exist should return null.", message.getHeader("not here"));
    }

    @Test
    public void testAddHeaderWithNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(HEADER_NAME_ONE, HEADER_VALUE_ONE);

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should have been added", HEADER_ONE, message.getHeader(HEADER_NAME_ONE));

        message.addHeader(HEADER_NAME_TWO, HEADER_VALUE_TWO);

        assertEquals("two headers should exist", 2, message.getHeaders().size());
        assertEquals("header two should have been added", HEADER_TWO, message.getHeader(HEADER_NAME_TWO));

        message.addHeader(HEADER_NAME_THREE, HEADER_VALUE_THREE);

        assertEquals("three headers should exist", 3, message.getHeaders().size());
        assertEquals("header three should have been added", HEADER_THREE, message.getHeader(HEADER_NAME_THREE));

        message.addHeader(HEADER_NAME_ONE, HEADER_VALUE_TWO);

        assertEquals("three headers should exist when a value has been added to header one", 3,
                message.getHeaders().size());
        assertEquals("header one should have value one.", HEADER_VALUE_ONE,
                message.getHeader(HEADER_NAME_ONE).getValue());
        assertEquals("header one should have value two.", HEADER_VALUE_TWO,
                message.getHeader(HEADER_NAME_ONE).getValues().get(1));
    }

    @Test
    public void testAddHeaderWithNameAndEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(HEADER_NAME_ONE, "");

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should have one value", 1, message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have an empty value", "", message.getHeader(HEADER_NAME_ONE).getValue());

        message.addHeader(HEADER_NAME_ONE, "");

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should still only have have one value", 1,
                message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have an empty value", "", message.getHeader(HEADER_NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithNameAndNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(HEADER_NAME_ONE, null);

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should have one value", 1, message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have a null value", null, message.getHeader(HEADER_NAME_ONE).getValue());

        message.addHeader(HEADER_NAME_ONE, null);

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should still only have have one value", 1,
                message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have a null value", null, message.getHeader(HEADER_NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithEmptyNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader("", HEADER_VALUE_ONE);

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithNullNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(null, HEADER_VALUE_ONE);

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithEmptyNameAndEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader("", "");

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithNullNameAndNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(null, null);

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeader() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(HEADER_ONE);

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should have been added", HEADER_ONE, message.getHeader(HEADER_NAME_ONE));

        message.addHeader(HEADER_TWO);

        assertEquals("two headers should exist", 2, message.getHeaders().size());
        assertEquals("header two should have been added", HEADER_TWO, message.getHeader(HEADER_NAME_TWO));

        message.addHeader(HEADER_THREE);

        assertEquals("three headers should exist", 3, message.getHeaders().size());
        assertEquals("header three should have been added", HEADER_THREE, message.getHeader(HEADER_NAME_THREE));

        message.addHeader(new Header<String>(HEADER_NAME_ONE, HEADER_VALUE_TWO));

        assertEquals("three headers should exist when a value has been added to header one", 3,
                message.getHeaders().size());
        assertEquals("header one should have value one.", HEADER_VALUE_ONE,
                message.getHeader(HEADER_NAME_ONE).getValue());
        assertEquals("header one should have value two.", HEADER_VALUE_TWO,
                message.getHeader(HEADER_NAME_ONE).getValues().get(1));
    }

    @Test
    public void testAddHeaderWithEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(new Header<String>(HEADER_NAME_ONE, ""));

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should have one value", 1, message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have an empty value", "", message.getHeader(HEADER_NAME_ONE).getValue());

        message.addHeader(new Header<String>(HEADER_NAME_ONE, ""));

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should still only have have one value", 1,
                message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have an empty value", "", message.getHeader(HEADER_NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(new Header<Object>(HEADER_NAME_ONE, null));

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should have one value", 1, message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have an empty value", "", message.getHeader(HEADER_NAME_ONE).getValue());

        message.addHeader(new Header<Object>(HEADER_NAME_ONE, null));

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header one should still only have have one value", 1,
                message.getHeader(HEADER_NAME_ONE).getValues().size());
        assertEquals("header one should have an empty value", "", message.getHeader(HEADER_NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithEmptyName() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(new Header<String>("", HEADER_VALUE_ONE));

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithNullName() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(new Header<String>(null, HEADER_VALUE_ONE));

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithEmptyValues() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(new Header<String>("", ""));

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithNullValues() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(new Header<Object>(null, null));

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddHeaderWithNullHeader() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addHeader(null);

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testGetParameter() throws Exception {

    }

    @Test
    public void testGetParameterThatDoesNotExist() throws Exception {

    }

    @Test
    public void testAddParameterWithNameAndValue() throws Exception {

    }

    @Test
    public void testAddParameterWithNameAndNullValue() throws Exception {

    }

    @Test
    public void testAddParameterWithNullNameAndValue() throws Exception {

    }

    @Test
    public void testAddParameterWithNullNameAndNullValue() throws Exception {

    }

    @Test
    public void testAddParameter() throws Exception {

    }

    @Test
    public void testAddParameterEmptyValue() throws Exception {

    }

    @Test
    public void testAddParameterNullValue() throws Exception {

    }

    @Test
    public void testAddParameterEmptyName() throws Exception {

    }

    @Test
    public void testAddParameterNullName() throws Exception {

    }

    @Test
    public void testAddParameterEmptyNameAndValue() throws Exception {

    }

    @Test
    public void testAddParameterNullNameAndValue() throws Exception {

    }

    @Test
    public void testAddParameterWithNullParameter() throws Exception {

    }

    @Test
    public void testGetCookies() throws Exception {

    }

    @Test
    public void testGetCookiesWhenNoCookiesHaveBeenAdded() throws Exception {

    }

    @Test
    public void testGetCookie() throws Exception {

    }

    @Test
    public void testGetCookieThatDoesNotExist() throws Exception {

    }

    @Test
    public void testSetCookies() throws Exception {

    }

    @Test
    public void testSetCookiesWithEmptyCollection() throws Exception {

    }

    @Test
    public void testSetCookiesWithNull() throws Exception {

    }

    @Test
    public void testAddCookieWithNameAndValue() throws Exception {

    }

    @Test
    public void testAddCookieWithNameAndNullValue() throws Exception {

    }

    @Test
    public void testAddCookieWithNullNameAndValue() throws Exception {

    }

    @Test
    public void testAddCookieWithNullNameAndNullValue() throws Exception {

    }

    @Test
    public void testAddCookie() throws Exception {

    }

    @Test
    public void testAddCookieEmptyValue() throws Exception {

    }

    @Test
    public void testAddCookieNullValue() throws Exception {

    }

    @Test
    public void testAddCookieEmptyName() throws Exception {

    }

    @Test
    public void testAddCookieNullName() throws Exception {

    }

    @Test
    public void testAddCookieEmptyNameAndValue() throws Exception {

    }

    @Test
    public void testAddCookieNullNameAndValue() throws Exception {

    }

    @Test
    public void testAddCookieWithNullCookie() throws Exception {

    }
}
