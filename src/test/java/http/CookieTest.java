package http;

import http.date.DateParseException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class CookieTest {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("E',' dd MMM yyyy HH':'mm':'ss z");

    public static final String NAME = "cookie_name";
    public static final String VALUE = "cookie_value";
    public static final Pattern NAME_REGEX = Pattern.compile("(" + NAME + ")=" + VALUE + ";");
    public static final Pattern VALUE_REGEX = Pattern.compile(NAME + "=(" + VALUE + ");");
    public static final Pattern COMMENT_REGEX = Pattern.compile("Comment=(\\w+);");
    public static final String COMMENT = "cookie_comment";
    public static final Pattern DOMAIN_REGEX = Pattern.compile("Domain=(\\w+);");
    public static final String DOMAIN = "www.cookie.com";
    public static final Pattern EXPIRES_REGEX = Pattern.compile("Expires=([\\w\\d,; ]+);");
    public static final String EXPIRES = DATE_FORMATTER.print(DateTime.now());
    public static final Pattern MAX_AGE_REGEX = Pattern.compile("Max-Age=(\\d+);");
    public static final int MAX_AGE = (int) ((DateTime.now().plusDays(1).getMillis() - DateTime.now().getMillis()) / 1000);
    public static final Pattern PATH_REGEX = Pattern.compile("Path=((?:https?://)?[\\w/\\._-]);");
    public static final String PATH_NAME = "Path";
    public static final URI PATH;
    static {

        URI path = null;

        try {

            path = new URI("/cookie");

        } catch (URISyntaxException e) {

            throw new RuntimeException(e);
        }

        PATH = path;
    }
    public static final Pattern SECURE_REGEX = Pattern.compile("Secure=(\\w+);");
    public static final boolean SECURE = true;
    public static final Pattern VERSION_REGEX = Pattern.compile("Version=(\\d+);");
    public static final int VERSION = 1;

    public static final String COOKIE_STRING = NAME + "=" + VALUE + "; Comment=" + COMMENT + "; Domain=" + DOMAIN
            + "; Expires=" + EXPIRES + "; Max-Age=" + MAX_AGE + "; Path=" + PATH + "; Secure=" + SECURE + "; Version=" + VERSION;

    @Test
    public void testCreateCookie() throws Exception {

        String cookieString = new Cookie(NAME, VALUE).toString();

        Matcher matcher = NAME_REGEX.matcher(cookieString);
        assertTrue("the name of the cookie should be in the cookie string.", matcher.find());
        matcher = VALUE_REGEX.matcher(cookieString);
        assertTrue("the value of the cookie should be in the cookie string.", matcher.find());
        matcher = COMMENT_REGEX.matcher(cookieString);
        assertFalse("the comment for the cookie should not be in the cookie string.", matcher.find());
        matcher = DOMAIN_REGEX.matcher(cookieString);
        assertFalse("the domain for the cookie should not be in the cookie string.", matcher.find());
        matcher = EXPIRES_REGEX.matcher(cookieString);
        assertFalse("the expires date for the cookie should not be in the cookie string.", matcher.find());
        matcher = MAX_AGE_REGEX.matcher(cookieString);
        assertFalse("the max-age value for the cookie should not be in the cookie string.", matcher.find());
        matcher = PATH_REGEX.matcher(cookieString);
        assertFalse("the path for the cookie should not be in the cookie string.", matcher.find());
        matcher = SECURE_REGEX.matcher(cookieString);
        assertFalse("the secure state for the cookie should not be in the cookie string.", matcher.find());
        matcher = VERSION_REGEX.matcher(cookieString);
        assertFalse("the verion of the cookie should not be in the cookie string.", matcher.find());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCookieWithNullName() throws Exception {

        new Cookie(null, VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCookieWithEmptyName() throws Exception {

        new Cookie("", VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCookieWithNullValue() throws Exception {

        new Cookie(NAME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCookieWithNullNameAndValue() throws Exception {

        new Cookie(null, null);
    }

    @Test
    public void testSetExpiresWithString() throws Exception {

        Cookie cookie = new Cookie(NAME, VALUE);
        cookie.setExpires(EXPIRES);

        assertEquals("the expries date should be set correctly.", DATE_FORMATTER.parseDateTime(EXPIRES).toDate(),
                cookie.getExpires());

        String cookieString = cookie.toString();

        Matcher matcher = NAME_REGEX.matcher(cookieString);
        assertTrue("the name of the cookie should be in the cookie string.", matcher.find());
        matcher = VALUE_REGEX.matcher(cookieString);
        assertTrue("the value of the cookie should be in the cookie string.", matcher.find());
        matcher = COMMENT_REGEX.matcher(cookieString);
        assertFalse("the comment for the cookie should not be in the cookie string.", matcher.find());
        matcher = DOMAIN_REGEX.matcher(cookieString);
        assertFalse("the domain for the cookie should not be in the cookie string.", matcher.find());
        matcher = EXPIRES_REGEX.matcher(cookieString);
        assertTrue("the expires date for the cookie should be in the cookie string.", matcher.find());
        assertEquals("the expires date for the cookie should be correct.", EXPIRES, matcher.group(1));
        matcher = MAX_AGE_REGEX.matcher(cookieString);
        assertFalse("the max-age value for the cookie should not be in the cookie string.", matcher.find());
        matcher = PATH_REGEX.matcher(cookieString);
        assertFalse("the path for the cookie should not be in the cookie string.", matcher.find());
        matcher = SECURE_REGEX.matcher(cookieString);
        assertFalse("the secure state for the cookie should not be in the cookie string.", matcher.find());
        matcher = VERSION_REGEX.matcher(cookieString);
        assertFalse("the verion of the cookie should not be in the cookie string.", matcher.find());
    }

    @Test(expected = DateParseException.class)
    public void testSetExpiresWithMalformedString() throws Exception {

        Cookie cookie = new Cookie(NAME, VALUE);
        cookie.setExpires("malformed date");
    }

    @Test(expected = DateParseException.class)
    public void testSetExpiresWithNullString() throws Exception {

        Cookie cookie = new Cookie(NAME, VALUE);
        cookie.setExpires((String) null);
    }

    @Test
    public void testSetPath() throws Exception {

        Cookie cookie = new Cookie(NAME, VALUE);
        cookie.setPath(PATH.toString());

        assertEquals("the path URI should be set correctly.", PATH, cookie.getPath());

        String cookieString = cookie.toString();

        Matcher matcher = NAME_REGEX.matcher(cookieString);
        assertTrue("the name of the cookie should be in the cookie string.", matcher.find());
        matcher = VALUE_REGEX.matcher(cookieString);
        assertTrue("the value of the cookie should be in the cookie string.", matcher.find());
        matcher = COMMENT_REGEX.matcher(cookieString);
        assertFalse("the comment for the cookie should not be in the cookie string.", matcher.find());
        matcher = DOMAIN_REGEX.matcher(cookieString);
        assertFalse("the domain for the cookie should not be in the cookie string.", matcher.find());
        matcher = EXPIRES_REGEX.matcher(cookieString);
        assertFalse("the expires date for the cookie should not be in the cookie string.", matcher.find());
        matcher = MAX_AGE_REGEX.matcher(cookieString);
        assertFalse("the max-age value for the cookie should not be in the cookie string.", matcher.find());
        matcher = PATH_REGEX.matcher(cookieString);
        assertTrue("the path for the cookie should be in the cookie string.", matcher.find());
        assertEquals("the path for the cookie should be correct.", PATH.toString(), matcher.group(1));
        matcher = SECURE_REGEX.matcher(cookieString);
        assertFalse("the secure state for the cookie should not be in the cookie string.", matcher.find());
        matcher = VERSION_REGEX.matcher(cookieString);
        assertFalse("the verion of the cookie should not be in the cookie string.", matcher.find());
    }

    @Test(expected = URLException.class)
    public void testSetNullPath() throws Exception {

        Cookie cookie = new Cookie(NAME, VALUE);
        cookie.setPath((String) null);

        assertNull("the path URI should be set ti null correctly.", cookie.getPath());
    }

    @Test
    public void testGetName() throws Exception {

        assertEquals("the name should be set correctly.", NAME, new Cookie(NAME, VALUE).getName());
    }

    @Test
    public void testGetValue() throws Exception {

        assertEquals("the name should be set correctly.", VALUE, new Cookie(NAME, VALUE).getValue());
    }

    @Test
    public void testParse() throws Exception {

        Cookie cookie = new Cookie(NAME, VALUE);
        cookie.setComment(COMMENT);
        cookie.setDomain(DOMAIN);
        cookie.setExpires(EXPIRES);
        cookie.setMaxAge(MAX_AGE);
        cookie.setPath(PATH);
        cookie.setSecure(SECURE);
        cookie.setVersion(VERSION);

        Collection<Cookie> parsedCookies = Cookie.parse(COOKIE_STRING);

        assertNotNull("a collection of cookies should be produced.", parsedCookies);
        assertEquals("a single cookie should be pasred.", 1, parsedCookies.size());
        assertEquals("the pasred cookie should contain the correct values.", cookie, parsedCookies.iterator().next());
    }
}
