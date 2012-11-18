package http;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static http.Headers.*;
import static http.Cookies.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MessageTest {

    @Test
    public void testGetHeader() throws Exception {

        Message<Object> message = new Message<Object>();
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

        assertEquals("three headers should exist when a new value has been added to header one", 3,
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
    public void testGetCookies() throws Exception {

        Message<Object> message = new Message<Object>();
        message.setHeaders(new HashSet<Header>(SET_COOKIE_HEADERS));

        Collection<Cookie> cookies = message.getCookies();

        assertEquals("the number of set cookies should be correctly.", 3, cookies.size());
        assertTrue("set cookie one is retrieved correctly.", cookies.contains(SET_COOKIE_ONE));
        assertTrue("set cookie two is retrieved correctly.", cookies.contains(SET_COOKIE_TWO));
        assertTrue("set cookie three is retrieved correctly.", cookies.contains(SET_COOKIE_THREE));
    }

    @Test
    public void testGetCookiesWhenNoCookiesHaveBeenAdded() throws Exception {

        Message<Object> message = new Message<Object>();

        Collection<Cookie> cookies = message.getCookies();

        assertNotNull("a collection of cookies should be returned.", cookies);
        assertEquals("the collection of cookies should be empty.", 0, cookies.size());
    }

    @Test
    public void testGetCookie() throws Exception {

        Message<Object> message = new Message<Object>();
        message.setCookies(new HashSet<Cookie>(COOKIES));

        assertEquals("cookie one is retrieved correctly.", COOKIE_ONE, message.getCookie(COOKIE_NAME_ONE));
        assertEquals("cookie two is retrieved correctly.", COOKIE_TWO, message.getCookie(COOKIE_NAME_TWO));
        assertEquals("cookie three is retrieved correctly.", COOKIE_THREE, message.getCookie(COOKIE_NAME_THREE));
    }

    @Test
    public void testGetCookieThatDoesNotExist() throws Exception {

        Message<Object> message = new Message<Object>();

        assertNull("retrieving a header when no headers exist should return null.", message.getCookie(COOKIE_NAME_ONE));

        message.setCookies(new HashSet<Cookie>(COOKIES));

        assertNull("retrieving a header that does not exist should return null.", message.getCookie("not here"));
    }

    @Test
    public void testSetCookies() throws Exception {

        Message<Object> message = new Message<Object>();
        message.setCookies(new HashSet<Cookie>(COOKIES));

        Collection<Cookie> cookies = message.getCookies();

        assertEquals("the number of cookies should be correctly.", 3, cookies.size());
        assertTrue("cookie one is retrieved correctly.", cookies.contains(COOKIE_ONE));
        assertTrue("cookie two is retrieved correctly.", cookies.contains(COOKIE_TWO));
        assertTrue("cookie three is retrieved correctly.", cookies.contains(COOKIE_THREE));

        assertEquals("the number of headers should be correctly.", 2, message.getHeaders().size());

        String setCookieHeaderValue = message.<String>getHeader(SET_COOKIE).getValue();
        String cookieHeaderValue = message.<String>getHeader(COOKIE).getValue();

        assertTrue("set cookie one should be contained in the header value.",
                setCookieHeaderValue.contains(SET_COOKIE_NAME_ONE + "=" + SET_COOKIE_VALUE_ONE));
        assertTrue("set cookie two should be contained in the header value.",
                setCookieHeaderValue.contains(SET_COOKIE_NAME_TWO + "=" + SET_COOKIE_VALUE_THREE));
        assertTrue("set cookie three should be contained in the header value.",
                setCookieHeaderValue.contains(SET_COOKIE_NAME_THREE + "=" + SET_COOKIE_VALUE_THREE));
        assertTrue("cookie one should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_ONE + "=" + COOKIE_VALUE_ONE));
        assertTrue("cookie two should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_TWO + "=" + COOKIE_VALUE_THREE));
        assertTrue("cookie three should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_THREE + "=" + COOKIE_VALUE_THREE));
    }

    @Test
    public void testSetCookiesWithEmptyCollection() throws Exception {

        Message<Object> message = new Message<Object>();
        message.setCookies(new HashSet<Cookie>());

        Collection<Header> headers = message.getHeaders();

        assertNotNull("a collection of headers should be returned.", headers);
        assertEquals("the number of headers should be zero.", 0, headers.size());
    }

    @Test
    public void testSetCookiesWithNull() throws Exception {

        Message<Object> message = new Message<Object>();
        message.setCookies(null);

        Collection<Header> headers = message.getHeaders();

        assertNotNull("a collection of headers should be returned.", headers);
        assertEquals("the number of headers should be zero.", 0, headers.size());
    }

    @Test
    public void testAddCookieWithNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(COOKIE_NAME_ONE, COOKIE_VALUE_ONE);

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have been added", COOKIE_ONE, message.getCookie(COOKIE_NAME_ONE));

        message.addCookie(COOKIE_NAME_TWO, COOKIE_VALUE_TWO);

        assertEquals("two cookies should exist", 2, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie two should have been added", COOKIE_TWO, message.getCookie(COOKIE_NAME_TWO));

        message.addCookie(COOKIE_NAME_THREE, COOKIE_VALUE_THREE);

        assertEquals("three cookies should exist", 3, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie three should have been added", COOKIE_THREE, message.getCookie(COOKIE_NAME_THREE));

        String cookieHeaderValue = message.<String>getHeader(COOKIE).getValue();

        assertTrue("cookie one should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_ONE + "=" + COOKIE_VALUE_ONE));
        assertTrue("cookie two should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_TWO + "=" + COOKIE_VALUE_THREE));
        assertTrue("cookie three should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_THREE + "=" + COOKIE_VALUE_THREE));

        message.addCookie(COOKIE_NAME_ONE, COOKIE_VALUE_TWO);

        assertEquals("three cookies should exist when a new value has been added to cookie one", 3,
                message.getCookies().size());
        assertEquals("cookie one should have value two.", COOKIE_VALUE_TWO,
                message.getCookie(COOKIE_NAME_ONE).getValue());
    }

    @Test
    public void testAddCookieWithNameAndEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(COOKIE_NAME_ONE, "");

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", "", message.getCookie(COOKIE_NAME_ONE).getValue());

        message.addCookie(COOKIE_NAME_ONE, "");

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", "", message.getCookie(COOKIE_NAME_ONE).getValue());
    }

    @Test
    public void testAddCookieWithNameAndNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(COOKIE_NAME_ONE, null);

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", null, message.getCookie(COOKIE_NAME_ONE).getValue());

        message.addCookie(COOKIE_NAME_ONE, null);

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", "", message.getCookie(COOKIE_NAME_ONE).getValue());
    }

    @Test
    public void testAddCookieWithEmptyNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie("", COOKIE_VALUE_ONE);

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieWithNullNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(null, COOKIE_VALUE_ONE);

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieWithEmptyNameAndEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie("", "");

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieWithNullNameAndNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(null, null);

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookie() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(COOKIE_ONE);

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have been added", COOKIE_ONE, message.getCookie(COOKIE_NAME_ONE));

        message.addCookie(COOKIE_TWO);

        assertEquals("two cookies should exist", 2, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie two should have been added", COOKIE_TWO, message.getCookie(COOKIE_NAME_TWO));

        message.addCookie(COOKIE_THREE);

        assertEquals("three cookies should exist", 3, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie three should have been added", COOKIE_THREE, message.getCookie(COOKIE_NAME_THREE));

        String cookieHeaderValue = message.<String>getHeader(COOKIE).getValue();

        assertTrue("cookie one should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_ONE + "=" + COOKIE_VALUE_ONE));
        assertTrue("cookie two should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_TWO + "=" + COOKIE_VALUE_THREE));
        assertTrue("cookie three should be contained in the header value.",
                cookieHeaderValue.contains(COOKIE_NAME_THREE + "=" + COOKIE_VALUE_THREE));

        message.addCookie(new Cookie(COOKIE_NAME_ONE, COOKIE_VALUE_TWO));

        assertEquals("three cookies should exist when a new value has been added to cookie one", 3,
                message.getCookies().size());
        assertEquals("cookie one should have value two.", COOKIE_VALUE_TWO,
                message.getCookie(COOKIE_NAME_ONE).getValue());
    }

    @Test
    public void testAddCookieEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(new Cookie(COOKIE_NAME_ONE, ""));

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", "", message.getCookie(COOKIE_NAME_ONE).getValue());

        message.addCookie(new Cookie(COOKIE_NAME_ONE, ""));

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", "", message.getCookie(COOKIE_NAME_ONE).getValue());
    }

    @Test
    public void testAddCookieNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(new Cookie(COOKIE_NAME_ONE, null));

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", null, message.getCookie(COOKIE_NAME_ONE).getValue());

        message.addCookie(new Cookie(COOKIE_NAME_ONE, null));

        assertEquals("one cookie should exist", 1, message.getCookies().size());
        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("cookie one should have an empty value", "", message.getCookie(COOKIE_NAME_ONE).getValue());
    }

    @Test
    public void testAddCookieEmptyName() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(new Cookie("", COOKIE_VALUE_ONE));

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieNullName() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(new Cookie(null, COOKIE_VALUE_ONE));

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieEmptyNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(new Cookie("", ""));

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieNullNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(new Cookie(null, null));

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }

    @Test
    public void testAddCookieWithNullCookie() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no cookies should exist", 0, message.getCookies().size());
        assertEquals("no headers should exist", 0, message.getHeaders().size());

        message.addCookie(null);

        assertEquals("no cookies should have been added.", 0, message.getCookies().size());
        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }
}
