package http;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

import static http.Bodies.*;
import static http.Client.Request;
import static http.Client.Response;
import static http.Cookies.COOKIES;
import static http.Headers.HEADERS;
import static http.Parameters.PARAMETERS;
import static http.Urls.*;
import static org.junit.Assert.*;

/**
 * This class is implemented to test the different request methods. This has be done because the request method tests
 * are all very similar for the different HTTP methods, the only real difference is in the HTTP method string that is
 * produced.
 *
 * @author Karl Bennett
 */
public abstract class AbstractClientRequestMethodTest implements RequestHandler {

    /**
     * Denotes the type of a request method, that is static method like {@link Client#GET(String)},
     * {@link Client#POST(String)}, {@link Client#PUT(String)}, or an instance method like {@link Client#get(String)},
     * {@link Client#post(String)}, {@link Client#put(String)}.
     */
    public static enum METHOD_TYPE {
        STATIC,
        INSTANCE
    }

    /**
     * This interface is used to allow the switching of request methods within the same peace of code. It's used to allow
     * code reuse within the HTTP request methods tests which are all very similar.
     *
     * @param <T> the type of input that will be used for the request method, this will usually be the {@link String},
     * {@link java.net.URL}, or {@link http.Client.Request} type.
     *
     * @author Karl Bennett
     */
    public interface RequestExecutor<T> {

        /**
         * Execute the wrapped request method method. This could be a static or instance method e.g.
         * {@link Client#GET(String)}, {@link Client#POST(String)}, {@link Client#PUT(String)}, or
         * {@link Client#get(String)}, {@link Client#post(String)}, {@link Client#put(String)}...
         *
         * @param input the input for the request method, it's type is set generically for the class.
         * @return the response object returned from the request method.
         */
        public <B> Response<B> execute(T input);
    }


    public static final String TEST_RESPONSE_STRING = "test response";
    public static final InputStream TEST_RESPONSE_INPUT_STREAM = new ByteArrayInputStream("test response".getBytes());
    public static final Response<InputStream> TEST_RESPONSE = new Response<InputStream>(HttpURLConnection.HTTP_OK,
            TEST_RESPONSE_INPUT_STREAM);


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
    private URL url;
    private Collection<Header> headers;
    private Collection<Parameter> parameters;
    private Collection<Cookie> cookies;
    private InputStream body;

    @Override
    public Response<InputStream> handleRequest(String method, URL url, Collection<Header> headers,
                                               Collection<Cookie> cookies, Collection<Parameter> parameters,
                                               InputStream body) {

        this.url = url;
        this.method = method;
        this.headers = headers;
        this.parameters = parameters;
        this.cookies = cookies;
        this.body = body;

        return TEST_RESPONSE;
    }

    @Test
    public void testSimpleStringRequest() throws Exception {

        simpleRequestTest(TEST_URL, stringRequestExecutor.<InputStream>execute(TEST_URL_STRING));
    }

    @Test
    public void testSimpleUrlRequest() throws Exception {

        simpleRequestTest(TEST_URL, urlRequestExecutor.<InputStream>execute(TEST_URL));
    }

    @Test
    public void testSimpleRequest() throws Exception {

        simpleRequestTest(TEST_URL, requestExecutor.<InputStream>execute(new Request(TEST_URL)));
    }

    @Test
    public void testStringRequestWithQueryString() throws Exception {

        requestWithParametersTest(TEST_URL_WITH_QUERY, PARAMETERS,
                stringRequestExecutor.<InputStream>execute(TEST_URL_STRING_WITH_QUERY_STRING));
    }

    @Test
    public void testUrlRequestWithQueryString() throws Exception {

        requestWithParametersTest(TEST_URL_WITH_QUERY, PARAMETERS,
                urlRequestExecutor.<InputStream>execute(TEST_URL_WITH_QUERY));
    }

    @Test
    public void testRequestWithQueryString() throws Exception {

        requestWithParametersTest(TEST_URL_WITH_QUERY, PARAMETERS,
                requestExecutor.<InputStream>execute(new Request(TEST_URL_WITH_QUERY)));
    }

    @Test
    public void testRequestWithHeaders() throws Exception {

        Request request = new Request(TEST_URL);
        request.setHeaders(HEADERS);

        requestWithHeadersTest(TEST_URL, HEADERS, requestExecutor.<InputStream>execute(request));
    }

    @Test
    public void testRequestWithCookies() throws Exception {

        Request request = new Request(TEST_URL);
        request.setCookies(COOKIES);

        requestWithCookiesTest(TEST_URL, COOKIES, requestExecutor.<InputStream>execute(request));
    }

    @Test
    public void testRequestWithParameters() throws Exception {

        Request request = new Request(TEST_URL);
        request.setParameters(PARAMETERS);

        requestWithParametersTest(TEST_URL_WITH_QUERY, PARAMETERS, requestExecutor.<InputStream>execute(request));
    }

    @Test
    public void testRequestWithStringBody() throws Exception {

        Request<String> request = new Request<String>(TEST_URL);
        request.setBody(TEST_STRING_BODY);

        requestWithBodyTest(TEST_URL, TEST_STRING_BODY, requestExecutor.<InputStream>execute(request));
    }

    @Test
    public void testRequestWithInputStreamBody() throws Exception {

        Request<InputStream> request = new Request<InputStream>(TEST_URL);
        request.setBody(TEST_INPUT_STREAM_BODY);

        requestWithBodyTest(TEST_URL, TEST_INPUT_STREAM_BODY, requestExecutor.<InputStream>execute(request));
    }

    @Test
    public void testRequestWithObjectBody() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL);
        request.setBody(TEST_OBJECT_BODY);

        requestWithBodyTest(TEST_URL, TEST_OBJECT_BODY, requestExecutor.<InputStream>execute(request));
    }


    private void simpleRequestTest(URL url, Response<InputStream> testResponse) throws Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod + " method.",
                TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("the correct URL should have been produced from the " + testMethodType + " " +
                testMethod + " method.", url, this.url);
        assertEquals("no headers should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, headers.size());
        assertEquals("no cookies should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, cookies.size());
        assertEquals("no parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", 0, parameters.size());
        assertNull("no body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);
    }

    private void requestWithHeadersTest(URL url, Collection<Header> headers, Response<InputStream> testResponse) throws
            Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod + " method.",
                TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("the correct URL should have been produced from the " + testMethodType + " " +
                testMethod + " method.", url, this.url);
        assertEquals("the correct headers should have been produced from the " + testMethodType + " " + testMethod +
                " method.", headers, this.headers);
        assertEquals("no cookies should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, cookies.size());
        assertEquals("no parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", 0, parameters.size());
        assertNull("no body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);
    }

    private void requestWithCookiesTest(URL url, Collection<Cookie> cookies, Response<InputStream> testResponse) throws
            Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod + " method.",
                TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("the correct URL should have been produced from the " + testMethodType + " " +
                testMethod + " method.", url, this.url);
        assertEquals("no headers should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, headers.size());
        assertEquals("the correct cookies should have been produced from the " + testMethodType + " " + testMethod +
                " method.", cookies, this.cookies);
        assertEquals("no parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", 0, parameters.size());
        assertNull("no body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);
    }

    private void requestWithParametersTest(URL url, Collection<Parameter> parameters,
                                           Response<InputStream> testResponse) throws Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod +
                " method.", TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("the correct URL should have been produced from the " + testMethodType + " " +
                testMethod + " method.", url, this.url);
        assertEquals("no headers should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, headers.size());
        assertEquals("no cookies should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, cookies.size());
        assertEquals("the correct parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", parameters, this.parameters);
        assertNull("no body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);
    }

    private void requestWithBodyTest(URL url, Object body, Response<InputStream> testResponse) throws Exception {

        assertEquals("the correct response should be returned from the " + testMethodType + " " + testMethod + " method.",
                TEST_RESPONSE, testResponse);

        assertEquals("a " + testMethod + " method string should have been produced from the " + testMethodType + " " +
                testMethod + " method.", testMethod, method);
        assertEquals("the correct URL should have been produced from the " + testMethodType + " " +
                testMethod + " method.", url, this.url);
        assertEquals("no headers should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, headers.size());
        assertEquals("no cookies should have been produced from the " + testMethodType + " " + testMethod + " method.",
                0, cookies.size());
        assertEquals("no parameters should have been produced from the " + testMethodType + " " + testMethod +
                " method.", 0, parameters.size());
        assertNotNull("some body content should have been produced from the " + testMethodType + " " + testMethod +
                " method.", body);

        if (body instanceof String) {

            assertEquals("the correct body content should have been produced from the " + testMethodType + " " +
                    testMethod + " method.", body, IOUtils.toString(this.body));

        } else if (body instanceof InputStream) {

            assertEquals("the correct body content should have been produced from the " + testMethodType + " " +
                    testMethod + " method.", IOUtils.toString((InputStream) body), IOUtils.toString(this.body));
        } else {

            assertEquals("the correct body content should have been produced from the " + testMethodType + " " +
                    testMethod + " method.", body.toString(), IOUtils.toString(this.body));
        }
    }
}
