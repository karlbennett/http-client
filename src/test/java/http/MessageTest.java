package http;

import http.attribute.Attribute;
import http.attribute.AttributeArrayListMap;
import http.attribute.AttributeHashSetMap;
import http.attribute.AttributeListMap;
import http.header.*;
import http.header.Cookie;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static http.Attributes.*;
import static http.Bodies.TEST_STRING_BODY;
import static http.Cookies.COOKIE_ONE;
import static http.Cookies.COOKIE_TWO;
import static http.header.Headers.HEADERS;
import static http.util.MimeTypes.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MessageTest {

    private static final Attribute<String> TEST_ATTRIBUTE_ONE_TWO = new Attribute<String>(TEST_ATTRIBUTE_NAME_ONE,
            TEST_ATTRIBUTE_VALUE_TWO, TEST_ATTRIBUTE_OPERATOR);

    private static final Attribute<String> TEST_ATTRIBUTE_TWO_THREE = new Attribute<String>(TEST_ATTRIBUTE_NAME_TWO,
            TEST_ATTRIBUTE_VALUE_THREE, TEST_ATTRIBUTE_OPERATOR);

    private static final Collection<Attribute<String>> TEST_ATTRIBUTE_ONES = new HashSet<Attribute<String>>(
            Arrays.asList(
                    TEST_ATTRIBUTE_ONE,
                    TEST_ATTRIBUTE_ONE_TWO
            )
    );

    private static final List<Attribute<String>> TEST_ATTRIBUTE_TWOS = Arrays.asList(TEST_ATTRIBUTE_TWO,
            TEST_ATTRIBUTE_TWO_THREE);

    private static final Attribute<String> NON_EXISTENT_ATTRIBUTE_ONE =
            new Attribute<String>("this should", "not exist", ".");
    private static final Attribute<String> NON_EXISTENT_ATTRIBUTE_TWO =
            new Attribute<String>("this also", "should not exist", ".");

    private Set<Attribute<String>> attributes;

    private AttributeListMap<Attribute<String>> attributeMap;


    @Before
    public void setUp() throws Exception {

        attributes = new HashSet<Attribute<String>>() {{
            addAll(TEST_ATTRIBUTE_ONES);
            addAll(TEST_ATTRIBUTE_TWOS);
            add(TEST_ATTRIBUTE_THREE);
        }};

        attributeMap = new AttributeArrayListMap<Attribute<String>>(attributes);
    }


    @Test
    public void testCreateMessage() throws Exception {

        Message<String> message = new Message<String>(HEADERS, TEST_STRING_BODY);

        assertEquals("the headers should be added to the message.", HEADERS, new HashSet<Header>(message.getHeaders()));
        assertEquals("the body should have been added to the message.", TEST_STRING_BODY, message.getBody());
    }

    @Test
    public void testCreateEmptyMessage() throws Exception {

        Message<Void> message = new Message<Void>();

        assertEquals("no headers should be added to the message.", Collections.emptyList(), message.getHeaders());
        assertNull("no body should have been added to the message.", message.getBody());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateMessageWithNullHeadersValue() throws Exception {

        new Message<String>(null, TEST_STRING_BODY);
    }

    @Test
    public void testCreateMessageWithNullBodyValue() throws Exception {

        assertNull("the body should be null.", new Message<String>(HEADERS, null).getBody());
    }

    @Test
    public void testGetAllValues() throws Exception {

        assertEquals("the correct attributes should be returned.", attributes,
                new HashSet<Attribute<String>>(Message.getAllValues(attributeMap)));
    }

    @Test
    public void testGetAllValuesWithEmptyMap() throws Exception {

        assertEquals("an empty should be returned.", Collections.<Attribute>emptyList(),
                Message.getAllValues(new AttributeArrayListMap<Attribute>()));
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllValuesWithNull() throws Exception {

        Message.getAllValues(null);
    }

    @Test
    public void testRemove() throws Exception {

        assertEquals("the attribute map should have three entries.", 3, attributeMap.size());

        assertNull("a non existent attribute should not be removed",
                Message.remove(attributeMap, NON_EXISTENT_ATTRIBUTE_ONE));
        assertEquals("the attribute map should still have three entries.", 3, attributeMap.size());

        assertEquals("attribute one should be removed", TEST_ATTRIBUTE_ONE,
                Message.remove(attributeMap, TEST_ATTRIBUTE_ONE));
        assertEquals("the attribute map should still have three entries.", 3, attributeMap.size());

        assertEquals("attribute two should be removed", TEST_ATTRIBUTE_TWO,
                Message.remove(attributeMap, TEST_ATTRIBUTE_TWO));
        assertEquals("the attribute map should still have three entries.", 3, attributeMap.size());

        assertEquals("attribute one two should be removed", TEST_ATTRIBUTE_ONE_TWO,
                Message.remove(attributeMap, TEST_ATTRIBUTE_ONE_TWO));
        assertEquals("the attribute map should have two entries.", 2, attributeMap.size());

        assertEquals("attribute two three should be removed", TEST_ATTRIBUTE_TWO_THREE,
                Message.remove(attributeMap, TEST_ATTRIBUTE_TWO_THREE));
        assertEquals("the attribute map should have one entry.", 1, attributeMap.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullAttribute() throws Exception {

        Message.remove(attributeMap, null);
    }

    @Test
    public void testRemoveWithEmptyMap() throws Exception {

        assertNull("no attribute should be removed",
                Message.remove(new AttributeHashSetMap<Attribute>(), TEST_ATTRIBUTE_ONE));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNullMap() throws Exception {

        Message.remove(null, TEST_ATTRIBUTE_ONE);
    }

    @Test
    public void testRemoveAll() throws Exception {

        assertEquals("the attribute map should have three entries.", 3, attributeMap.size());

        List<Attribute<String>> attributes = Arrays.asList(NON_EXISTENT_ATTRIBUTE_ONE, NON_EXISTENT_ATTRIBUTE_TWO);

        assertEquals("non existent attributes should not be removed", Collections.emptyList(),
                Message.removeAll(attributeMap, attributes));
        assertEquals("the attribute map should still have three entries.", 3, attributeMap.size());

        attributes = Arrays.asList(TEST_ATTRIBUTE_ONE, TEST_ATTRIBUTE_THREE);

        assertEquals("attributes one and three should be removed", attributes,
                Message.removeAll(attributeMap, attributes));
        assertEquals("the attribute map should still have two entries.", 2, attributeMap.size());

        assertEquals("both attribute two entries should be removed", TEST_ATTRIBUTE_TWOS,
                Message.removeAll(attributeMap, TEST_ATTRIBUTE_TWOS));
        assertEquals("the attribute map should still have one entry.", 1, attributeMap.size());

        attributes = Arrays.asList(TEST_ATTRIBUTE_ONE_TWO);

        assertEquals("the last attribute one entry should be removed", attributes,
                Message.removeAll(attributeMap, attributes));
        assertEquals("the attribute map should have no entries.", 0, attributeMap.size());
    }

    @Test
    public void testGetHeaderWithTypeAndNormalHeaders() throws Exception {

        Message<Void> message = new Message<Void>();

        assertNull("no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
        assertNull("no set cookie headers should be returned.", message.getHeaders(SetCookie.class));

        message.setHeaders(HEADERS);

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));
    }

    @Test
    public void testGetHeaderWithTypeAndAcceptHeaders() throws Exception {

        Message<Void> message = new Message<Void>();
        message.setHeaders(HEADERS);

        Accept acceptFormUrlEncoded = new Accept(APPLICATION_X_WWW_FORM_URL_ENCODED);

        message.addHeader(new Header<Object>(Accept.ACCEPT, acceptFormUrlEncoded.getValue()));

        Collection<Accept> accepts = message.getHeaders(Accept.class);

        assertNotNull("some accept headers should be returned.", accepts);
        assertEquals("one accept header should be returned.", 1, accepts.size());
        assertTrue("a form url encoded accept header should be returned.",
                accepts.contains(acceptFormUrlEncoded));

        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));

        Accept acceptJson = new Accept(APPLICATION_JSON);

        message.addHeader(acceptJson);

        accepts = message.getHeaders(Accept.class);

        assertNotNull("some accept headers should be returned.", accepts);
        assertEquals("two accept headers should be returned.", 2, accepts.size());
        assertTrue("a form url encoded accept header should be returned.",
                accepts.contains(acceptFormUrlEncoded));
        assertTrue("a json accept header should be returned.", accepts.contains(acceptJson));

        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));
    }

    @Test
    public void testGetHeaderWithTypeAndContentTypeHeaders() throws Exception {

        Message<Void> message = new Message<Void>();
        message.setHeaders(HEADERS);

        ContentType contentTypeFormUrlEncoded = new ContentType(APPLICATION_X_WWW_FORM_URL_ENCODED);

        message.addHeader(new Header<Object>(ContentType.CONTENT_TYPE, contentTypeFormUrlEncoded.getValue()));

        Collection<ContentType> contentTypes = message.getHeaders(ContentType.class);

        assertNotNull("some content type headers should be returned.", contentTypes);
        assertEquals("one content type header should be returned.", 1, contentTypes.size());
        assertTrue("a form url encoded content type header should be returned.",
                contentTypes.contains(contentTypeFormUrlEncoded));

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));

        ContentType contentTypeXml = new ContentType(APPLICATION_XML);

        message.addHeader(contentTypeXml);

        contentTypes = message.getHeaders(ContentType.class);

        assertNotNull("some content type headers should be returned.", contentTypes);
        assertEquals("two content type headers should be returned.", 2, contentTypes.size());
        assertTrue("a form url encoded content type header should be returned.",
                contentTypes.contains(contentTypeFormUrlEncoded));
        assertTrue("an xml content type header should be returned.", contentTypes.contains(contentTypeXml));

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));
    }

    @Test
    public void testGetHeaderWithTypeAndCookieHeaders() throws Exception {

        Message<Void> message = new Message<Void>();
        message.setHeaders(HEADERS);

        message.addHeader(new Header<Object>(http.header.Cookie.COOKIE, COOKIE_ONE.toString()));

        Collection<http.header.Cookie> cookies = message.getHeaders(http.header.Cookie.class);

        assertNotNull("some cookie headers should be returned.", cookies);
        assertEquals("one cookie header should be returned.", 1, cookies.size());
        assertTrue("a cookie header containing the test cookie one should be returned.",
                cookies.contains(new http.header.Cookie(COOKIE_ONE)));

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));

        message.addHeader(new http.header.Cookie(COOKIE_TWO));

        cookies = message.getHeaders(http.header.Cookie.class);

        assertNotNull("some cookie headers should be returned.", cookies);
        assertEquals("two cookie headers should be returned.", 2, cookies.size());
        assertTrue("a cookie header containing the test cookie one should be returned.",
                cookies.contains(new http.header.Cookie(COOKIE_ONE)));
        assertTrue("a cookie header containing the test cookie two should be returned.",
                cookies.contains(new Cookie(COOKIE_TWO)));

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no set cookie headers should be returned.", message.getHeaders(SetCookie.class));
    }

    @Test
    public void testGetHeaderWithTypeAndSetCookieHeaders() throws Exception {

        Message<Void> message = new Message<Void>();
        message.setHeaders(HEADERS);

        message.addHeader(new Header<Object>(SetCookie.SET_COOKIE, COOKIE_ONE.toString()));

        Collection<SetCookie> setCookies = message.getHeaders(SetCookie.class);

        assertNotNull("some set cookie headers should be returned.", setCookies);
        assertEquals("one set cookie header should be returned.", 1, setCookies.size());
        assertTrue("a set cookie header containing the test cookie one should be returned.",
                setCookies.contains(new SetCookie(COOKIE_ONE)));

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));

        message.addHeader(new SetCookie(COOKIE_TWO));

        setCookies = message.getHeaders(SetCookie.class);

        assertNotNull("some set cookie headers should be returned.", setCookies);
        assertEquals("two set cookie headers should be returned.", 2, setCookies.size());
        assertTrue("a set cookie header containing the test cookie one should be returned.",
                setCookies.contains(new SetCookie(COOKIE_ONE)));
        assertTrue("a set cookie header containing the test cookie two should be returned.",
                setCookies.contains(new SetCookie(COOKIE_TWO)));

        assertNull("still no accept headers should be returned.", message.getHeaders(Accept.class));
        assertNull("still no content type headers should be returned.", message.getHeaders(ContentType.class));
        assertNull("still no cookie headers should be returned.", message.getHeaders(http.header.Cookie.class));
    }
}
