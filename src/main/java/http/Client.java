package http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

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
 * The singleton client can be configured with the {@link Configuration} instance returned or set by the
 * {@link #GET_CONFIGURATION} or {@link #SET_CONFIGURATION} methods respectively. It also automatically imports and uses
 * the JVM SSL and proxy properties.
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
 * If multiple client configuration profiles are required a new instance of the {@code Client} class can be created for
 * each and configured individually using the instance {@link Client#getConfiguration} and
 * {@link Client#setConfiguration} methods. The instance HTTP request methods would then be used
 * ({@link Client#get(String)}, {@link Client#post(String)}, {@link Client#put(String)}...).
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

    private static class SingletonHolder {
        public static final Client INSTANCE = new Client();
    }

    private static Client getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * Static method that behaves exactly the same as the {@link #options(String)} method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response OPTIONS(String url) {

        return getInstance().options(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #options(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response OPTIONS(URL url) {

        return getInstance().options(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #options(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response OPTIONS(Request request) {

        return getInstance().options(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #get(String)} method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response GET(String url) {

        return getInstance().get(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #get(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response GET(URL url) {

        return getInstance().get(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #get(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response GET(Request request) {

        return getInstance().get(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #head(String)} method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response HEAD(String url) {

        return getInstance().head(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #head(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response HEAD(URL url) {

        return getInstance().head(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #head(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response HEAD(Request request) {

        return getInstance().head(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #post(String)}  method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response POST(String url) {

        return getInstance().post(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #post(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response POST(URL url) {

        return getInstance().post(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #post(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response POST(Request request) {

        return getInstance().post(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #put(String)}  method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response PUT(String url) {

        return getInstance().put(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #put(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response PUT(URL url) {

        return getInstance().put(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #put(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response PUT(Request request) {

        return getInstance().put(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #delete(String)}  method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response DELETE(String url) {

        return getInstance().delete(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #delete(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response DELETE(URL url) {

        return getInstance().delete(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #delete(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response DELETE(Request request) {

        return getInstance().delete(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #trace(String)}  method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response TRACE(String url) {

        return getInstance().trace(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #trace(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response TRACE(URL url) {

        return getInstance().trace(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #trace(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response TRACE(Request request) {

        return getInstance().trace(request);
    }

    /**
     * Static method that behaves exactly the same as the {@link #connect(String)}  method except that it is configure
     * with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response CONNECT(String url) {

        return getInstance().connect(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #connect(java.net.URL)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public static Response CONNECT(URL url) {

        return getInstance().connect(url);
    }

    /**
     * Static method that behaves exactly the same as the {@link #connect(http.Client.Request)} method except that it is
     * configure with the application global {@link #GET_CONFIGURATION} method.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public static Response CONNECT(Request request) {

        return getInstance().connect(request);
    }


    /**
     * Static method that behaves the same as the {@link #getConfiguration} method except that it returns a
     * {@link Configuration} instance that can be used to apply configuration for the application global static request
     * methods e.g. {@link #GET(java.net.URL) GET}, {@link #GET(java.net.URL) POST}, {@link #GET(java.net.URL) PUT}...
     *
     * @return the current static global {@code Configuration} instance.
     */
    public static Configuration GET_CONFIGURATION() {

        return getInstance().getConfiguration();
    }

    /**
     * Static method that behaves the same as the {@link #getConfiguration} method except that it returns a
     * {@link Configuration} instance that can be used to apply configuration for the application global static request
     * methods e.g. {@link #GET(java.net.URL) GET}, {@link #GET(java.net.URL) POST}, {@link #GET(java.net.URL) PUT}...
     *
     * @param configuration a new {@code Configuration} instance.
     */
    public static void SET_CONFIGURATION(Configuration configuration) {

        getInstance().setConfiguration(configuration);
    }


    public Client() {
    }

    /**
     * Sends an {@code OPTIONS} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response options(String url) {

        return options(new Request(url));
    }

    /**
     * Sends an {@code OPTIONS} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response options(URL url) {

        return options(new Request(url));
    }

    /**
     * Sends an {@code OPTIONS} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response options(Request request) {

        return null;
    }

    /**
     * Sends a {@code GET} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response get(String url) {

        return get(new Request(url));
    }

    /**
     * Sends an {@code GET} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response get(URL url) {

        return get(new Request(url));
    }

    /**
     * Sends an {@code GET} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response get(Request request) {

        return null;
    }

    /**
     * Sends a {@code HEAD} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response head(String url) {

        return head(new Request(url));
    }

    /**
     * Sends an {@code HEAD} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response head(URL url) {

        return head(new Request(url));
    }

    /**
     * Sends an {@code HEAD} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response head(Request request) {

        return null;
    }

    /**
     * Sends a {@code POST} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response post(String url) {

        return post(new Request(url));
    }

    /**
     * Sends an {@code POST} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response post(URL url) {

        return post(new Request(url));
    }

    /**
     * Sends an {@code POST} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response post(Request request) {

        return null;
    }

    /**
     * Sends a {@code PUT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response put(String url) {

        return put(new Request(url));
    }

    /**
     * Sends an {@code PUT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response put(URL url) {

        return put(new Request(url));
    }

    /**
     * Sends an {@code PUT} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response put(Request request) {

        return null;
    }

    /**
     * Sends a {@code DELETE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response delete(String url) {

        return delete(new Request(url));
    }

    /**
     * Sends an {@code DELETE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response delete(URL url) {

        return delete(new Request(url));
    }

    /**
     * Sends an {@code DELETE} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response delete(Request request) {

        return null;
    }

    /**
     * Sends a {@code TRACE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response trace(String url) {

        return trace(new Request(url));
    }

    /**
     * Sends an {@code TRACE} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response trace(URL url) {

        return trace(new Request(url));
    }

    /**
     * Sends an {@code TRACE} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response trace(Request request) {

        return null;
    }

    /**
     * Sends a {@code CONNECT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response connect(String url) {

        return connect(new Request(url));
    }

    /**
     * Sends an {@code CONNECT} request to the {@code HTTP} server at the provided {@code URL}.
     *
     * @param url the {@code URL} for the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     */
    public Response connect(URL url) {

        return connect(new Request(url));
    }

    /**
     * Sends an {@code CONNECT} request to the {@code HTTP} server defined within the provided {@link Request}.
     * <p/>
     * Any {@link Header}'s, {@link Parameter}'s, and body that have been populated in the request will be sent.
     *
     * @param request the {@code Request} that will be sent to the {@code HTTP} server.
     * @return the {@link Response} sent back by the {@code HTTP} server.
     * @throws IllegalStateException if a converter hasn't been registered that can convert the request body's type into
     *                               an {@link java.io.InputStream}.
     */
    public Response connect(Request request) {

        return null;
    }


    /**
     * Returns the current {@link Configuration} instance for this {@link Client}.
     * This can be used to configure all the different aspects of the client.
     *
     * @return the current {@code Configuration} instance.
     */
    public Configuration getConfiguration() {

        return null;
    }

    /**
     * Overrides the clients {@link Configuration} object. This will override all the previous client configuration.
     *
     * @param configuration a new {@code Configuration} instance.
     */
    public void setConfiguration(Configuration configuration) {

    }

    /**
     * Represents an {@code HTTP} request and can be populated with all the standard request components.
     *
     * @author Karl Bennett
     */
    public static class Request<T> extends Message<T> {

        /**
         * Generate a new {@code java.net.URL} instance from a {@code java.lang.String} without throwing a checked
         * exception.
         *
         * @param url the url string to use to create the new {@code java.net.URL} instance.
         * @return a new {@code java.net.URL} instance.
         * @throws URIException if an invalid url string is given.
         */
        private static URL quietUrl(String url) {

            try {

                return new URI(url).toURL();

            } catch (MalformedURLException e) {

                throw new URIException(e);

            } catch (URISyntaxException e) {

                throw new URIException(e);
            }
        }


        /**
         * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
         *
         * @param url a {@code java.lang.String} containing the {@code URL} for the {@code HTTP} server.
         */
        public Request(String url) {

            this(quietUrl(url));
        }

        /**
         * Create a new {@code Request} that will be sent to the {@code HTTP} server at the supplied {@link URL}.
         *
         * @param url a {@code java.lang.String} containing the {@code java.net.URL} for the {@code HTTP} server.
         */
        public Request(URL url) {
        }

        /**
         * Get all the parameters set for the current {@code Message}.
         *
         * @return the message parameters.
         */
        public Collection<Parameter> getParameters() {

            return null;
        }

        /**
         * Get the {@link Parameter} with the supplied name. If the parameter does not exist this method will return null.
         * <p/>
         * Care must be taken with this method because it will implicitly cast the generic type of the {@code Parameter} so
         * can produce {@link ClassCastException} exceptions at runtime.
         * <p/>
         * <code>
         * request.addParameter(new Parameter&lt;String&gt;("number", "one"));
         * Parameter&lt;Integer&gt; parameter = request.getParameter("number"); // This will not produce an unchecked warning.
         * int number = parameter.getValue(); // This will compile and fail at runtime with a ClassCastException.
         * </code>
         *
         * @param name the name of the parameter to retrieve.
         * @param <T>  the type of the parameters value.
         * @return the requested parameter if it exists otherwise null.
         */
        public <T> Parameter<T> getParameter(String name) {

            return null;
        }

        /**
         * Set the parameters for this {@code Message}, overwriting any that might have previously been set.
         *
         * @param parameters the new parameters for the message.
         */
        public void setParameters(Collection<Parameter> parameters) {

        }

        /**
         * Add a parameter to the {@code Message} made up of the supplied name and value. If a parameter with the supplied
         * name already exists then the supplied value will be added to the existing parameters values.
         *
         * @param name  the name of the new parameter.
         * @param value the value for the new parameter.
         * @param <T>   the type of the parameters value.
         */
        public <T> void addParameter(String name, T value) {

            addParameter(new Parameter<T>(name, value));
        }

        /**
         * Add a {@link Parameter} to the {@code Message} appending it to any added previously. If a parameter with the a
         * matching name already exists then the new parameters value will be added to the existing parameters values.
         *
         * @param parameter the new parameter to add to the message.
         * @param <T>       the type of the parameters value.
         */
        public <T> void addParameter(Parameter<T> parameter) {

        }
    }

    /**
     * Represents an {@code HTTP} response and provides access to all the standard response components.
     *
     * @author Karl Bennett
     */
    public static class Response<T> extends Message<T> {

        /**
         * Create a {@code Response} with the supplied HTTP status code.
         *
         * @param status the status code for the response.
         */
        public Response(int status) {
        }

        /**
         * Create a {@code Response} with the supplied HTTP status code and body.
         *
         * @param status the status code for the response.
         * @param body   the body of te response.
         */
        public Response(int status, T body) {
        }

        /**
         * Create a {@code Response} with the supplied HTTP status code, headers, and body.
         *
         * @param status  the status code for the response.
         * @param headers the headers for the response.
         * @param body    the body of te response.
         */
        public Response(int status, Collection<Header> headers, T body) {
        }

        /**
         * Create a {@code Response} with the supplied HTTP status code, headers, and body.
         *
         * @param status  the status code for the response.
         * @param headers the headers for the response.
         * @param cookies the cookies for the response.
         * @param body    the body of te response.
         */
        public Response(int status, Collection<Header> headers, Collection<Cookie> cookies, T body) {
        }

        /**
         * @return the status code for the response.
         */
        public int getStatus() {

            return 0;
        }

        /**
         * Set the status code for the response.
         *
         * @param status the status code for the response.
         */
        public void setStatus(int status) {

        }
    }
}
