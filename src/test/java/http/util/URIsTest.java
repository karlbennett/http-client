package http.util;

import http.URIException;
import org.junit.Test;

import java.net.URI;
import java.net.URL;

import static http.util.URIs.*;

import static http.Urls.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class URIsTest {

    @Test
    public void testQuietUri() throws Exception {

        URI uri = quietUri(TEST_URL_STRING);

        assertNotNull("a URI should be created.", uri);
        assertEquals("the url should be correct.", TEST_URL_STRING, uri.toString());
    }

    @Test(expected = URIException.class)
    public void testQuietUriWithInvalidUri() throws Exception {

        quietUri("this should not work");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuietUriWithBullUri() throws Exception {

        quietUri(null);
    }

    @Test
    public void testQuietUrl() throws Exception {

        URL url = quietUrl(TEST_URL_STRING);

        assertNotNull("a URI should be created.", url);
        assertEquals("the url should be correct.", TEST_URL_STRING, url.toString());
    }

    @Test(expected = URIException.class)
    public void testQuietUrlWithInvalidUrl() throws Exception {

        quietUrl("this should not work");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuietUrlWithNullUrl() throws Exception {

        quietUrl(null);
    }
}
