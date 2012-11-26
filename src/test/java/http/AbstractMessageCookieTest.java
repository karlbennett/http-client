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
public abstract class AbstractMessageCookieTest<T, R extends Message<T>> extends AbstractMessagePropertyTest<Cookie> {

    public interface RequestCreator<T, R extends Message<T>> {

        public R newRequest();
    }


    private String cookieHeaderName;
    private RequestCreator<T, R> requestCreator;
    private MessageExecutor<Cookie> messageExecutor;
    private Cookie cookieOne;
    private Cookie cookieTwo;
    private Cookie cookieThree;
    private Collection<Cookie> cookies;


    public AbstractMessageCookieTest(String cookieHeaderName, RequestCreator<T, R> requestCreator) {

        super(new MessageExecutor<Cookie>() {

            @Override
            public Cookie newProperty(String name, Object value) {

                return new Cookie(name, value.toString());
            }

            @Override
            public Cookie getProperty(Message message, String name) {

                return message.getCookie(name);
            }

            @Override
            public <T> Collection<Cookie> getProperties(Message<T> message) {

                return message.getCookies();
            }

            @Override
            public <T> void setProperties(Message<T> message, Collection<Cookie> attributes) {

                message.setCookies(attributes);
            }

            @Override
            public <T> void addProperty(Message<T> message, String name, Object value) {

                message.addCookie(name, value.toString());
            }

            @Override
            public <T> void addProperty(Message<T> message, Cookie attribute) {

                message.addCookie(attribute);
            }
        });

        this.cookieHeaderName = cookieHeaderName;
        this.requestCreator = requestCreator;
    }

    @Override
    protected void exposeProperties(Cookie cookieOne, Cookie cookieTwo, Cookie cookieThree,
                                    Collection<Cookie> cookies) {

        this.cookieOne = cookieOne;
        this.cookieTwo = cookieTwo;
        this.cookieThree = cookieThree;
        this.cookies = cookies;
    }

    @Test
    public void testGetCookies() throws Exception {

        Collection<Header> headers = Arrays.asList(
                (Header) new Header<String>(cookieHeaderName, NAME_ONE + "=" + VALUE_ONE),
                (Header) new Header<String>(cookieHeaderName, NAME_TWO + "=" + VALUE_TWO),
                (Header) new Header<String>(cookieHeaderName, NAME_THREE + "=" + VALUE_THREE)
        );

        Message<T> message = requestCreator.newRequest();
        message.setHeaders(new HashSet<Header>(headers));

        Collection<Cookie> cookies = message.getCookies();

        assertEquals("the number of set cookies should be correctly.", 3, cookies.size());
        assertTrue("set cookie one is retrieved correctly.", cookies.contains(cookieOne));
        assertTrue("set cookie two is retrieved correctly.", cookies.contains(cookieTwo));
        assertTrue("set cookie three is retrieved correctly.", cookies.contains(cookieThree));
    }

    @Test
    public void testSetCookieHeader() throws Exception {

        Message<T> message = requestCreator.newRequest();
        message.setCookies(new HashSet<Cookie>(cookies));

        String cookieHeaderValue = message.<String>getHeader(cookieHeaderName).getValue();

        assertTrue("cookie one should be contained in the header value.",
                cookieHeaderValue.contains(NAME_ONE + "=" + VALUE_ONE));
        assertTrue("cookie two should be contained in the header value.",
                cookieHeaderValue.contains(NAME_TWO + "=" + VALUE_TWO));
        assertTrue("cookie three should be contained in the header value.",
                cookieHeaderValue.contains(NAME_THREE + "=" + VALUE_THREE));
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
    public void testAddCookieHeaderWithNameAndValue() throws Exception {

        new AddCookieHeaderTester() {

            @Override
            public void addProperty(Message<T> message, String name, Object value) {

                messageExecutor.addProperty(message, name, value);
            }
        };
    }

    @Test
    public void testAddCookieWithNameAndEmptyValue() throws Exception {

        new AddNameValueCookieHeaderWithBlankValueTester("");
    }

    @Test
    public void testAddCookieWithNameAndNullValue() throws Exception {

        new AddNameValueCookieHeaderWithBlankValueTester(null);
    }

    @Test
    public void testAddCookieHeaderWithEmptyNameAndValue() throws Exception {

        new AddNameValueCookieHeaderWithBlankNameAndValueTester("", VALUE_ONE);
    }

    @Test
    public void testAddCookieHeaderWithNullNameAndValue() throws Exception {

        new AddNameValueCookieHeaderWithBlankNameAndValueTester(null, VALUE_ONE);
    }

    @Test
    public void testAddCookieHeaderWithEmptyNameAndEmptyValue() throws Exception {

        new AddNameValueCookieHeaderWithBlankNameAndValueTester("", "");
    }

    @Test
    public void testAddCookieHeaderWithNullNameAndNullValue() throws Exception {

        new AddNameValueCookieHeaderWithBlankNameAndValueTester(null, null);
    }

    @Test
    public void testAddCookie() throws Exception {

        new AddCookieHeaderTester() {

            @Override
            public void addProperty(Message<T> message, String name, Object value) {

                messageExecutor.addProperty(message, messageExecutor.newProperty(name, value));
            }
        };
    }

    @Test
    public void testAddCookieEmptyValue() throws Exception {

        new AddObjectCookieHeaderWithBlankValueTester("");
    }

    @Test
    public void testAddCookieNullValue() throws Exception {

        new AddObjectCookieHeaderWithBlankValueTester(null);
    }

    @Test
    public void testAddCookieHeaderEmptyName() throws Exception {

        new AddObjectCookieHeaderWithBlankNameAndValueTester("", VALUE_ONE);
    }

    @Test
    public void testAddCookieHeaderNullName() throws Exception {

        new AddObjectCookieHeaderWithBlankNameAndValueTester(null, VALUE_ONE);
    }

    @Test
    public void testAddCookieHeaderEmptyNameAndValue() throws Exception {

        new AddObjectCookieHeaderWithBlankNameAndValueTester("", "");
    }

    @Test
    public void testAddCookieHeaderNullNameAndValue() throws Exception {

        new AddObjectCookieHeaderWithBlankNameAndValueTester(null, null);
    }

    @Test
    public void testAddCookieHeaderWithNullCookie() throws Exception {

        new AddCookieHeaderWithBlankNameAndValueTester(null, null) {

            @Override
            public void addProperty(Message<T> message, String name, Object value) {

                messageExecutor.addProperty(message, null);
            }
        };
    }


    private void addNoCookieHeadersTest(Collection<Cookie> empty) throws Exception {

        Message<Object> message = new Message<Object>();
        message.setCookies(empty);

        Collection<Header> headers = message.getHeaders();

        assertNotNull("a collection of headers should be returned.", headers);
        assertEquals("the number of headers should be zero.", 0, headers.size());
    }

    private abstract class AddCookieHeaderTester implements PropertyAdder<T> {

        protected AddCookieHeaderTester() {

            Message<T> message = requestCreator.newRequest();

            assertEquals("no headers should exist", 0, message.getHeaders().size());

            addProperty(message, NAME_ONE, VALUE_ONE);

            assertEquals("one header should exist", 1, message.getHeaders().size());

            addProperty(message, NAME_TWO, VALUE_TWO);

            assertEquals("one header should exist", 1, message.getHeaders().size());

            addProperty(message, NAME_THREE, VALUE_THREE);

            assertEquals("one header should exist", 1, message.getHeaders().size());

            String cookieHeaderValue = message.<String>getHeader(cookieHeaderName).getValue();

            assertTrue("cookie one should be contained in the header value.",
                    cookieHeaderValue.contains(NAME_ONE + "=" + VALUE_ONE));
            assertTrue("cookie two should be contained in the header value.",
                    cookieHeaderValue.contains(NAME_TWO + "=" + VALUE_THREE));
            assertTrue("cookie three should be contained in the header value.",
                    cookieHeaderValue.contains(NAME_THREE + "=" + VALUE_THREE));

            addProperty(message, NAME_ONE, VALUE_TWO);

            assertEquals("three cookies should exist when a new value has been added to cookie one", 3,
                    message.getCookies().size());
            assertEquals("cookie one should have value two.", VALUE_TWO,
                    message.getCookie(NAME_ONE).getValue());
        }
    }

    private abstract class AddCookieHeaderWithBlankValueTester implements PropertyAdder<T> {

        protected AddCookieHeaderWithBlankValueTester(String blank) {

            Message<T> message = requestCreator.newRequest();

            assertEquals("no headers should exist", 0, message.getHeaders().size());

            addProperty(message, NAME_ONE, blank);

            assertEquals("one header should exist", 1, message.getHeaders().size());
            assertEquals("header should contain the cookie name with no value", NAME_ONE + "=",
                    message.getHeader(cookieHeaderName).getValue());

            addProperty(message, NAME_ONE, blank);

            assertEquals("one header should exist", 1, message.getHeaders().size());
            assertEquals("header should contain the cookie name with no value", NAME_ONE + "=",
                    message.getHeader(cookieHeaderName).getValue());
        }
    }

    private class AddNameValueCookieHeaderWithBlankValueTester extends AddCookieHeaderWithBlankValueTester {

        protected AddNameValueCookieHeaderWithBlankValueTester(String blank) {
            super(blank);
        }

        @Override
        public void addProperty(Message<T> message, String name, Object value) {

            messageExecutor.addProperty(message, name, value);
        }
    }

    private class AddObjectCookieHeaderWithBlankValueTester extends AddCookieHeaderWithBlankValueTester {

        protected AddObjectCookieHeaderWithBlankValueTester(String blank) {
            super(blank);
        }

        @Override
        public void addProperty(Message<T> message, String name, Object value) {

            messageExecutor.addProperty(message, messageExecutor.newProperty(name, value));
        }
    }

    private abstract class AddCookieHeaderWithBlankNameAndValueTester implements PropertyAdder<T> {

        protected AddCookieHeaderWithBlankNameAndValueTester(String name, String value) {

            Message<T> message = requestCreator.newRequest();

            assertEquals("no headers should exist", 0, message.getHeaders().size());

            addProperty(message, name, value);

            assertEquals("no headers should have been added.", 0, message.getHeaders().size());
        }
    }

    private class AddNameValueCookieHeaderWithBlankNameAndValueTester extends AddCookieHeaderWithBlankNameAndValueTester {

        protected AddNameValueCookieHeaderWithBlankNameAndValueTester(String name, String value) {
            super(name, value);
        }

        @Override
        public void addProperty(Message<T> message, String name, Object value) {

            messageExecutor.addProperty(message, name, value);
        }
    }

    private class AddObjectCookieHeaderWithBlankNameAndValueTester extends AddCookieHeaderWithBlankNameAndValueTester {

        protected AddObjectCookieHeaderWithBlankNameAndValueTester(String name, String value) {
            super(name, value);
        }

        @Override
        public void addProperty(Message<T> message, String name, Object value) {

            messageExecutor.addProperty(message, messageExecutor.newProperty(name, value));
        }
    }
}
