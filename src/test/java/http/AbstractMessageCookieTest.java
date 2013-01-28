package http;

import http.header.Header;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public abstract class AbstractMessageCookieTest<M extends Message<Object>> extends AbstractMessagePropertyTest<M, Cookie> {


    private MessageExecutor<M, Cookie> messageExecutor;
    private String cookieHeaderName;
    private Cookie cookieOne;
    private Cookie cookieTwo;
    private Cookie cookieThree;
    private Collection<Cookie> cookies;


    public AbstractMessageCookieTest(MessageExecutor<M, Cookie> messageExecutor, String cookieHeaderName) {
        super(new PropertyExecutor<Cookie>() {

            @Override
            public <T> Cookie newProperty(String name, T value) {

                return new Cookie(name, (String) value);
            }

            @Override
            public String getName(Cookie property) {

                return property.getName();
            }

            @Override
            @SuppressWarnings("unchecked")
            public <T> T getValue(Cookie property) {

                return (T) property.getValue();
            }

        }, messageExecutor);

        this.messageExecutor = messageExecutor;
        this.cookieHeaderName = cookieHeaderName;
    }


    @Override
    public void exposeProperties(Cookie propertyOne, Cookie propertyTwo, Cookie propertyThree, Collection<Cookie> properties) {
        super.exposeProperties(propertyOne, propertyTwo, propertyThree, properties);

        this.cookieOne = propertyOne;
        this.cookieTwo = propertyTwo;
        this.cookieThree = propertyThree;
        this.cookies = properties;
    }


    @Test
    public void testGetCookies() throws Exception {

        Collection<Header> headers = Arrays.asList(
                (Header) new Header<String>(cookieHeaderName, cookieOne.getName() + "=" + cookieOne.getValue()),
                (Header) new Header<String>(cookieHeaderName, cookieTwo.getName() + "=" + cookieTwo.getValue()),
                (Header) new Header<String>(cookieHeaderName, cookieThree.getName() + "=" + cookieThree.getValue())
        );

        M message = messageExecutor.newMessage();
        message.setHeaders(new HashSet<Header>(headers));

        Collection<Cookie> cookies = message.getCookies();

        assertEquals("the number of set cookies should be correctly.", 3, cookies.size());
        assertTrue("set cookie one is retrieved correctly.", cookies.contains(cookieOne));
        assertTrue("set cookie two is retrieved correctly.", cookies.contains(cookieTwo));
        assertTrue("set cookie three is retrieved correctly.", cookies.contains(cookieThree));
    }

    @Test
    public void testSetCookieHeader() throws Exception {

        M message = messageExecutor.newMessage();
        message.setCookies(new HashSet<Cookie>(cookies));

        String cookieHeaderValue = message.<String>getHeader(cookieHeaderName).getValue();

        assertTrue("cookie one should be contained in the header value.",
                cookieHeaderValue.contains(cookieOne.getName() + "=" + cookieOne.getValue()));
        assertTrue("cookie two should be contained in the header value.",
                cookieHeaderValue.contains(cookieTwo.getName() + "=" + cookieTwo.getValue()));
        assertTrue("cookie three should be contained in the header value.",
                cookieHeaderValue.contains(cookieThree.getName() + "=" + cookieThree.getValue()));
    }

    @Test
    public void testSetCookiesWithEmptyCollection() throws Exception {

        addNoCookieHeadersTest(Collections.<Cookie>emptySet());
    }

    @Test
    public void testSetCookiesWithNull() throws Exception {

        addNoCookieHeadersTest(null);
    }

    @Test
    public void testAddCookieHeader() throws Exception {

        M message = messageExecutor.newMessage();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        messageExecutor.addProperty(message, new Cookie(cookieOne.getName(), cookieOne.getValue()));

        assertEquals("one header should exist", 1, message.getHeaders().size());

        messageExecutor.addProperty(message, new Cookie(cookieTwo.getName(), cookieTwo.getValue()));

        assertEquals("one header should exist", 1, message.getHeaders().size());

        messageExecutor.addProperty(message, new Cookie(cookieThree.getName(), cookieThree.getValue()));

        assertEquals("one header should exist", 1, message.getHeaders().size());

        String cookieHeaderValue = message.<String>getHeader(cookieHeaderName).getValue();

        assertTrue("cookie one should be contained in the header value.",
                cookieHeaderValue.contains(cookieOne.getName() + "=" + cookieOne.getValue()));
        assertTrue("cookie two should be contained in the header value.",
                cookieHeaderValue.contains(cookieTwo.getName() + "=" + cookieThree.getValue()));
        assertTrue("cookie three should be contained in the header value.",
                cookieHeaderValue.contains(cookieThree.getName() + "=" + cookieThree.getValue()));

        messageExecutor.addProperty(message, new Cookie(cookieOne.getName(), cookieTwo.getValue()));

        assertEquals("three cookies should exist when a new value has been added to cookie one", 3,
                message.getCookies().size());
        assertEquals("cookie one should have value two.", cookieTwo.getValue(),
                message.getCookie(cookieOne.getName()).getValue());
    }

    @Test
    public void testAddCookieWithEmptyValue() throws Exception {

        addCookieHeaderWithBlankValueTest("");
    }

    @Test
    public void testAddCookieWithNullValue() throws Exception {

        addCookieHeaderWithBlankValueTest(null);
    }

    @Test
    public void testAddCookieHeaderWithEmptyNameAndValue() throws Exception {

        addCookieHeaderWithBlankNameAndValueTest(new Cookie("", cookieOne.getValue()));
    }

    @Test
    public void testAddCookieHeaderWithNullNameAndValue() throws Exception {

        addCookieHeaderWithBlankNameAndValueTest(new Cookie(null, cookieOne.getValue()));
    }

    @Test
    public void testAddCookieHeaderWithEmptyNameAndEmptyValue() throws Exception {

        addCookieHeaderWithBlankNameAndValueTest(new Cookie("", ""));
    }

    @Test
    public void testAddCookieHeaderWithNullNameAndNullValue() throws Exception {

        addCookieHeaderWithBlankNameAndValueTest(new Cookie(null, null));
    }

    @Test
    public void testAddNullCookie() throws Exception {

        addCookieHeaderWithBlankNameAndValueTest(null);
    }


    private void addNoCookieHeadersTest(Collection<Cookie> empty) throws Exception {

        M message = messageExecutor.newMessage();
        message.setCookies(empty);

        Collection<Header> headers = message.getHeaders();

        assertNotNull("a collection of headers should be returned.", headers);
        assertEquals("the number of headers should be zero.", 0, headers.size());
    }

    private void addCookieHeaderWithBlankValueTest(String blank) {

        M message = messageExecutor.newMessage();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        Cookie cookie = new Cookie(cookieOne.getName(), blank);

        messageExecutor.addProperty(message, cookie);

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header should contain the cookie name with no value", cookieOne.getName() + "=",
                message.getHeader(cookieHeaderName).getValue());

        messageExecutor.addProperty(message, cookie);

        assertEquals("one header should exist", 1, message.getHeaders().size());
        assertEquals("header should contain the cookie name with no value", cookieOne.getName() + "=",
                message.getHeader(cookieHeaderName).getValue());
    }

    private void addCookieHeaderWithBlankNameAndValueTest(Cookie cookie) {

        M message = messageExecutor.newMessage();

        assertEquals("no headers should exist", 0, message.getHeaders().size());

        messageExecutor.addProperty(message, cookie);

        assertEquals("no headers should have been added.", 0, message.getHeaders().size());
    }
}
