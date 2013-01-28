package http;


import java.io.InputStream;
import java.net.URL;

/**
 * A simple {@code HTTP} client API that can be used to make request to any {@code HTTP 1.0/1.1} server.
 * <p/>
 * The most commonly used types have been defined statically within this class so in most cases only one import is
 * required to use the API.
 * <p/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;import static http.Client.*
 * </code>
 * <p/>
 * This will provide access to all the static request methods
 * ({@link #GET(String)}, {@link #POST(String)}, {@link #PUT(String)}...) as well as the  ({@link Request} and
 * {@link Response} objects.
 * <p/>
 * The simplest way to use the client is through the static methods, these use a singleton instance of the client. This
 * singleton isn't instantiated until one of the static methods are used.
 * <p/>
 * Usage:<br/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println(GET("http://yoursite.com"))}
 * </code>
 * <p/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;Request request = new Request("http://yourservice.com/post");<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;request.addHeader(new ContentType("application/x-www-form-urlencoded"));<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;request.setBody(new Parameter("some", "thing"));<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;POST(request);
 * </code>
 * <p/>
 * Proxy:<br/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;http.proxyHost<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;http.proxyPort<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;http.nonProxyHosts<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;https.proxyHost<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;https.proxyPort<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;https.nonProxyHosts<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;socksProxyHost<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;socksProxyPort<br/>
 * </code>
 * <p/>
 * SSL:<br/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;javax.net.ssl.trustStore<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;javax.net.ssl.trustStoreType<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;javax.net.ssl.trustStorePassword<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;javax.net.ssl.keyStore<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;javax.net.ssl.keyStoreType<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;javax.net.ssl.keyStorePassword<br/>
 * </code>
 * <p/>
 * Usage:<br/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println(new Client().get("http://yoursite.com"))}
 * </code>
 * <p/>
 * <code>
 * &nbsp;&nbsp;&nbsp;&nbsp;Request request = new Request("http://yourservice.com/post");<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;request.addHeader(new ContentType("application/x-www-form-urlencoded"));<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;request.setBody(new Parameter("some", "thing"));<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;new Client().post(request);<br/>
 * </code>
 *
 * @author Karl Bennett
 */
public class Client {

    /**
     * HTTP method string constants. These represent the names of all the valid methods within the HTTP protocol.
     */
    public static final String OPTIONS = "OPTIONS";
    public static final String GET = "GET";
    public static final String HEAD = "HEAD";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";
    public static final String TRACE = "TRACE";
    public static final String CONNECT = "CONNECT";

    private static class SingletonHolder {
        public static final Client INSTANCE = new Client();
    }

    private static Client getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * Static method that behaves exactly the same as the {@link #options(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> OPTIONS(String url) {

        return getInstance().options(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #options(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> OPTIONS(URL url) {

        return getInstance().options(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #options(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> OPTIONS(Request request) {

        return getInstance().options(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #get(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> GET(String url) {

        return getInstance().get(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #get(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> GET(URL url) {

        return getInstance().get(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #get(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> GET(Request request) {

        return getInstance().get(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #head(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> HEAD(String url) {

        return getInstance().head(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #head(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> HEAD(URL url) {

        return getInstance().head(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #head(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> HEAD(Request request) {

        return getInstance().head(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #post(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> POST(String url) {

        return getInstance().post(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #post(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> POST(URL url) {

        return getInstance().post(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #post(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> POST(Request request) {

        return getInstance().post(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #put(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> PUT(String url) {

        return getInstance().put(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #put(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> PUT(URL url) {

        return getInstance().put(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #put(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> PUT(Request request) {

        return getInstance().put(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #delete(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> DELETE(String url) {

        return getInstance().delete(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #delete(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> DELETE(URL url) {

        return getInstance().delete(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #delete(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> DELETE(Request request) {

        return getInstance().delete(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #trace(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> TRACE(String url) {

        return getInstance().trace(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #trace(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> TRACE(URL url) {

        return getInstance().trace(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #trace(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> TRACE(Request request) {

        return getInstance().trace(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #connect(String)} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> CONNECT(String url) {

        return getInstance().connect(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #connect(java.net.URL)} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response<InputStream> CONNECT(URL url) {

        return getInstance().connect(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #connect(http.Request)} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response<InputStream> CONNECT(Request request) {

        return getInstance().connect(request);
    }


    public Client() {
    }


    /**
     * Sends an {@code OPTIONS} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> options(String url) {

        return options(new Request(url));
    }

    /**
     * Sends an {@code OPTIONS} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> options(URL url) {

        return options(new Request(url));
    }

    /**
     * Sends an {@code OPTIONS} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> options(Request request) {

        return null;
    }

    /**
     * Sends a {@code GET} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> get(String url) {

        return get(new Request(url));
    }

    /**
     * Sends an {@code GET} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> get(URL url) {

        return get(new Request(url));
    }

    /**
     * Sends an {@code GET} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> get(Request request) {

        return null;
    }

    /**
     * Sends a {@code HEAD} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> head(String url) {

        return head(new Request(url));
    }

    /**
     * Sends an {@code HEAD} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> head(URL url) {

        return head(new Request(url));
    }

    /**
     * Sends an {@code HEAD} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> head(Request request) {

        return null;
    }

    /**
     * Sends a {@code POST} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> post(String url) {

        return post(new Request(url));
    }

    /**
     * Sends an {@code POST} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> post(URL url) {

        return post(new Request(url));
    }

    /**
     * Sends an {@code POST} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> post(Request request) {

        return null;
    }

    /**
     * Sends a {@code PUT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> put(String url) {

        return put(new Request(url));
    }

    /**
     * Sends an {@code PUT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> put(URL url) {

        return put(new Request(url));
    }

    /**
     * Sends an {@code PUT} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> put(Request request) {

        return null;
    }

    /**
     * Sends a {@code DELETE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> delete(String url) {

        return delete(new Request(url));
    }

    /**
     * Sends an {@code DELETE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> delete(URL url) {

        return delete(new Request(url));
    }

    /**
     * Sends an {@code DELETE} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> delete(Request request) {

        return null;
    }

    /**
     * Sends a {@code TRACE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> trace(String url) {

        return trace(new Request(url));
    }

    /**
     * Sends an {@code TRACE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> trace(URL url) {

        return trace(new Request(url));
    }

    /**
     * Sends an {@code TRACE} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> trace(Request request) {

        return null;
    }

    /**
     * Sends a {@code CONNECT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> connect(String url) {

        return connect(new Request(url));
    }

    /**
     * Sends an {@code CONNECT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response<InputStream> connect(URL url) {

        return connect(new Request(url));
    }

    /**
     * Sends an {@code CONNECT} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link http.header.Header}'s, {@link http.parameter.Parameter}'s, and body that have been populated in the
     * request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response<InputStream> connect(Request request) {

        return null;
    }
}
