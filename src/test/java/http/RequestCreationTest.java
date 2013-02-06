package http;

import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.util.HashSet;

import static http.Urls.*;
import static http.parameter.Parameters.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public class RequestCreationTest {

    @Test
    public void testCreateRequestWithUrlString() throws Exception {

        createRequestTest(new Request(TEST_URL.toString()));
    }

    @Test
    public void testCreateRequestWithUrl() throws Exception {

        createRequestTest(new Request(TEST_URL));
    }

    @Test
    public void testCreateRequestWithUrlStringAndQueryString() throws Exception {

        createRequestWithQuery(new Request<Object>(TEST_URL_STRING_WITH_QUERY_STRING));
    }

    @Test
    public void testCreateRequestWithUrlAndQuery() throws Exception {

        createRequestWithQuery(new Request<Object>(TEST_URL_WITH_QUERY));
    }


    public void createRequestTest(Request request) {

        assertEquals("the request URL should be correct.", TEST_URL, request.getUrl());
    }

    public <T> void createRequestWithQuery(Request<T> request) throws Exception {

        URL url = new URI(TEST_URL_STRING_WITH_QUERY_STRING).toURL();

        assertEquals("the request URL length should be correct.", url.toString().length(),
                request.getUrl().toString().length());
        assertTrue("the request url query string should contain parameter one.",
                request.getUrl().toString().contains(PARAMETER_ONE.toString()));
        assertTrue("the request url query string should contain parameter two.",
                request.getUrl().toString().contains(PARAMETER_TWO.toString()));
        assertTrue("the request url query string should contain parameter three.",
                request.getUrl().toString().contains(PARAMETER_THREE.toString()));

        assertEquals("the correct parameters are included in the request.", new HashSet<>(PARAMETERS),
                request.getParameters());
    }
}
