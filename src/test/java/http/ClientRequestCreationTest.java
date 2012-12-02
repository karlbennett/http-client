package http;

import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.util.Collection;

import static http.Client.Request;
import static http.Parameters.PARAMETERS;
import static http.Urls.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class ClientRequestCreationTest {

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

        createRequestWithQuery(new Request<Object>(TEST_URL_STRING + TEST_QUERY_STRING));
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

        assertEquals("the request URL should be correct.", url, request.getUrl());

        Collection<Parameter> parameters = request.getParameters();

        assertEquals("the correct parameters are included in the request.", PARAMETERS, parameters);
    }
}
