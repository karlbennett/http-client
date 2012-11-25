package http;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static http.Cookies.*;
import static http.Headers.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMessageAttributeTest<A extends Attribute> {

    private static final String NAME_ONE = "name_one";
    private static final String VALUE_ONE = "value_one";
    private static final String NAME_TWO = "name_two";
    private static final String VALUE_TWO = "value_two";
    private static final String NAME_THREE = "name_three";
    private static final String VALUE_THREE = "value_three";


    public interface MessageExecutor<A extends Attribute> {

        public abstract A newAttribute(String name, Object value);

        public abstract <T> A getAttribute(Message<T> message, String name);

        public abstract <T> Collection<A> getAttributes(Message<T> message);

        public abstract <T> void setAttributes(Message<T> message, Collection<A> attributes);

        public abstract <T> void addAttribute(Message<T> message, String name, String value);

        public abstract <T> void addAttribute(Message<T> message, A attribute);
    }


    private MessageExecutor<A> messageExecutor;
    private A attributeOne;
    private A attributeTwo;
    private A attributeThree;
    private Collection<A> attributes;


    protected AbstractMessageAttributeTest(MessageExecutor<A> messageExecutor) {

        this.messageExecutor = messageExecutor;

        attributeOne = messageExecutor.newAttribute(NAME_ONE, VALUE_ONE);
        attributeTwo = messageExecutor.newAttribute(NAME_TWO, VALUE_TWO);
        attributeThree = messageExecutor.newAttribute(NAME_THREE, VALUE_THREE);

        attributes = Arrays.asList(attributeOne, attributeTwo, attributeThree);
    }

    @Test
    public void testGetHeader() throws Exception {

        Message<Object> message = new Message<Object>();
        messageExecutor.setAttributes(message, attributes);

        assertEquals("attribute one is retrieved correctly.", attributeOne,
                messageExecutor.getAttribute(message, NAME_ONE));
        assertEquals("attribute two is retrieved correctly.", attributeTwo,
                messageExecutor.getAttribute(message, NAME_TWO));
        assertEquals("attribute three is retrieved correctly.", attributeThree,
                messageExecutor.getAttribute(message, NAME_THREE));
    }

    @Test
    public void testGetHeaderThatDoesNotExist() throws Exception {

        Message<Object> message = new Message<Object>();

        assertNull("retrieving a attribute when no attributes exist should return null.",
                messageExecutor.getAttribute(message, NAME_ONE));

        messageExecutor.setAttributes(message, attributes);

        assertNull("retrieving a attribute that does not exist should return null.",
                messageExecutor.getAttribute(message, "not here"));
    }

    @Test
    public void testAddHeaderWithNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, NAME_ONE, VALUE_ONE);

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have been added", attributeOne,
                messageExecutor.getAttribute(message, NAME_ONE));

        messageExecutor.addAttribute(message, NAME_TWO, VALUE_TWO);

        assertEquals("two attributes should exist", 2, messageExecutor.getAttributes(message).size());
        assertEquals("attribute two should have been added", attributeTwo,
                messageExecutor.getAttribute(message, NAME_TWO));

        messageExecutor.addAttribute(message, NAME_THREE, VALUE_THREE);

        assertEquals("three attributes should exist", 3, messageExecutor.getAttributes(message).size());
        assertEquals("attribute three should have been added", attributeThree,
                messageExecutor.getAttribute(message, NAME_THREE));

        messageExecutor.addAttribute(message, NAME_ONE, VALUE_TWO);

        assertEquals("three attributes should exist when a value has been added to attribute one", 3,
                messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have value one.", VALUE_ONE,
                messageExecutor.getAttribute(message, NAME_ONE).getValue());
        assertEquals("attribute one should have value two.", VALUE_TWO,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().get(1));
    }

    @Test
    public void testAddHeaderWithNameAndEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, NAME_ONE, "");

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have an empty value", "",
                messageExecutor.getAttribute(message, NAME_ONE).getValue());

        messageExecutor.addAttribute(message, NAME_ONE, "");

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should still only have have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have an empty value", "",
                messageExecutor.getAttribute(message, NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithNameAndNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, NAME_ONE, null);

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have a null value", null,
                messageExecutor.getAttribute(message, NAME_ONE).getValue());

        messageExecutor.addAttribute(message, NAME_ONE, null);

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should still only have have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have a null value", null,
                messageExecutor.getAttribute(message, NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithEmptyNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, "", VALUE_ONE);

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithNullNameAndValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, null, VALUE_ONE);

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithEmptyNameAndEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, "", "");

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithNullNameAndNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, null, null);

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeader() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, attributeOne);

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have been added", attributeOne,
                messageExecutor.getAttribute(message, NAME_ONE));

        messageExecutor.addAttribute(message, attributeTwo);

        assertEquals("two attributes should exist", 2, messageExecutor.getAttributes(message).size());
        assertEquals("attribute two should have been added", attributeTwo,
                messageExecutor.getAttribute(message, NAME_TWO));

        messageExecutor.addAttribute(message, attributeThree);

        assertEquals("three attributes should exist", 3, messageExecutor.getAttributes(message).size());
        assertEquals("attribute three should have been added", attributeThree,
                messageExecutor.getAttribute(message, NAME_THREE));

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(NAME_ONE, VALUE_TWO));

        assertEquals("three attributes should exist when a new value has been added to attribute one", 3,
                messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have value one.", VALUE_ONE,
                messageExecutor.getAttribute(message, NAME_ONE).getValue());
        assertEquals("attribute one should have value two.", VALUE_TWO,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().get(1));
    }

    @Test
    public void testAddHeaderWithEmptyValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(NAME_ONE, ""));

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have an empty value", "",
                messageExecutor.getAttribute(message, NAME_ONE).getValue());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(NAME_ONE, ""));

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should still only have have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have an empty value", "",
                messageExecutor.getAttribute(message, NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithNullValue() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(NAME_ONE, null));

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have an empty value", "",
                messageExecutor.getAttribute(message, NAME_ONE).getValue());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(NAME_ONE, null));

        assertEquals("one attribute should exist", 1, messageExecutor.getAttributes(message).size());
        assertEquals("attribute one should still only have have one value", 1,
                messageExecutor.getAttribute(message, NAME_ONE).getValues().size());
        assertEquals("attribute one should have an empty value", "",
                messageExecutor.getAttribute(message, NAME_ONE).getValue());
    }

    @Test
    public void testAddHeaderWithEmptyName() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute("", VALUE_ONE));

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithNullName() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(null, VALUE_ONE));

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithEmptyValues() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute("", ""));

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithNullValues() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, messageExecutor.newAttribute(null, null));

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
    }

    @Test
    public void testAddHeaderWithNullHeader() throws Exception {

        Message<Object> message = new Message<Object>();

        assertEquals("no attributes should exist", 0, messageExecutor.getAttributes(message).size());

        messageExecutor.addAttribute(message, null);

        assertEquals("no attributes should have been added.", 0, messageExecutor.getAttributes(message).size());
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

        assertNull("retrieving a cookie when no cookies exist should return null.", message.getCookie(COOKIE_NAME_ONE));

        message.setCookies(new HashSet<Cookie>(COOKIES));

        assertNull("retrieving a cookie that does not exist should return null.", message.getCookie("not here"));
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
