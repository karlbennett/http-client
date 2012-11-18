package http;

import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

import static http.Client.*;
import static http.Urls.*;
import static http.Parameters.*;
import static http.RequestExecutor.METHOD_TYPE;

import static org.junit.Assert.*;

/**
 * This class must be implemented to test the different request methods. This has be done because the request method
 * test are all exactly the same for the different HTTP methods, the only real difference is in the HTTP method string
 * that is produced.
 *
 * @author Karl Bennett
 */
public abstract class AbstractClientRequestMethodTest implements RequestHandler {

    public static final String TEST_RESPONSE_STRING = "test response";
    public static final Response<String> TEST_RESPONSE = new Response<String>(HttpURLConnection.HTTP_OK, TEST_RESPONSE_STRING);

    private String testMethod;
    private METHOD_TYPE testMethodType;
    private RequestExecutor<String> stringRequestExecutor;
    private RequestExecutor<URL> urlRequestExecutor;
    private RequestExecutor<Request> requestExecutor;


    protected AbstractClientRequestMethodTest(String testMethod, METHOD_TYPE testMethodType,
                                              RequestExecutor<String> stringRequestExecutor,
                                              RequestExecutor<URL> urlRequestExecutor,
                                              RequestExecutor<Request> requestExecutor) {
        this.testMethod = testMethod;
        this.testMethodType = testMethodType;
        this.stringRequestExecutor = stringRequestExecutor;
        this.urlRequestExecutor = urlRequestExecutor;
        this.requestExecutor = requestExecutor;
    }


    private String method;
    private Collection<Header> headers;
    private Collection<Parameter> parameters;
    private Collection<Cookie> cookies;
    private InputStream body;

    @Override
    public Response handleRequest(String method, Collection<Header> headers, Collection<Parameter> parameters,
                                  Collection<Cookie> cookies, InputStream body) {

        this.method = method;
        this.headers = headers;
        this.parameters = parameters;
        this.cookies = cookies;
        this.body = body;

        return TEST_RESPONSE;
    }

    @Test
    public void testSimpleStringRequest() throws Exception {

        simpleRequestTest(stringRequestExecutor.execute(TEST_URL_STRING));
    }

    @Test
    public void testSimpleUrlRequest() throws Exception {

        simpleRequestTest(urlRequestExecutor.execute(TEST_URL));
    }

    @Test
    public void testSimpleRequest() throws Exception {

        simpleRequestTest(requestExecutor.execute(new Request(TEST_URL)));
    }

    @Test
    public void testStringRequestWithQueryString() throws Exception {

        requestWithQueryStringTest(stringRequestExecutor.execute(TEST_URL_STRING_WITH_QUERY_STRING));
    }

    @Test
    public void testUrlRequestWithQueryString() throws Exception {

        requestWithQueryStringTest(urlRequestExecutor.execute(TEST_URL_WITH_QUERY_STRING));
    }

    @Test
    public void testRequestWithQueryString() throws Exception {

        requestWithQueryStringTest(requestExecutor.execute(new Request(TEST_URL_WITH_QUERY_STRING)));
    }

    private void simpleRequestTest(Response testResponse) throws Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod + " method.",
                TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("no headers should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, headers.size());
        assertEquals("no cookies should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, cookies.size());
        assertEquals("no parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", 0, parameters.size());
        assertNull("no body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);
    }

    private void requestWithQueryStringTest(Response testResponse) throws Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod +
                " method.", TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("no headers should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, headers.size());
        assertEquals("no cookies should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, cookies.size());
        assertEquals("the correct parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", PARAMETERS, parameters);
        assertNull("no body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);
    }
}
