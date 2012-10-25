package http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Karl Bennett
 */
public class Client {

    private static class SingletonHolder {
        public static final Client INSTANCE = new Client();
    }

    private static Client getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public static Response OPTIONS(String url) {

        return getInstance().options(url);
    }

    public static Response OPTIONS(URL url) {

        return getInstance().options(url);
    }

    public static Response OPTIONS(Request request) {

        return getInstance().options(request);
    }

    public static Response GET(String url) {

        return getInstance().get(url);
    }

    public static Response GET(URL url) {

        return getInstance().get(url);
    }

    public static Response GET(Request request) {

        return getInstance().get(request);
    }

    public static Response HEAD(String url) {

        return getInstance().head(url);
    }

    public static Response HEAD(URL url) {

        return getInstance().head(url);
    }

    public static Response HEAD(Request request) {

        return getInstance().head(request);
    }

    public static Response POST(String url) {

        return getInstance().post(url);
    }

    public static Response POST(URL url) {

        return getInstance().post(url);
    }

    public static Response POST(Request request) {

        return getInstance().post(request);
    }

    public static Response PUT(String url) {

        return getInstance().put(url);
    }

    public static Response PUT(URL url) {

        return getInstance().put(url);
    }

    public static Response PUT(Request request) {

        return getInstance().put(request);
    }

    public static Response DELETE(String url) {

        return getInstance().delete(url);
    }

    public static Response DELETE(URL url) {

        return getInstance().delete(url);
    }

    public static Response DELETE(Request request) {

        return getInstance().delete(request);
    }

    public static Response TRACE(String url) {

        return getInstance().trace(url);
    }

    public static Response TRACE(URL url) {

        return getInstance().trace(url);
    }

    public static Response TRACE(Request request) {

        return getInstance().trace(request);
    }

    public static Response CONNECT(String url) {

        return getInstance().connect(url);
    }

    public static Response CONNECT(URL url) {

        return getInstance().connect(url);
    }

    public static Response CONNECT(Request request) {

        return getInstance().connect(request);
    }

    public static Configuration CONFIG() {

        return getInstance().config();
    }

    public static void CONFIG(Configuration configuration) {

        getInstance().config(configuration);
    }


    public Client() {
    }


    public Response options(String url) {

        return options(new Request(url));
    }

    public Response options(URL url) {

        return options(new Request(url));
    }

    public Response options(Request request) {

        return null;
    }

    public Response get(String url) {

        return get(new Request(url));
    }

    public Response get(URL url) {

        return get(new Request(url));
    }

    public Response get(Request request) {

        return null;
    }

    public Response head(String url) {

        return head(new Request(url));
    }

    public Response head(URL url) {

        return head(new Request(url));
    }

    public Response head(Request request) {

        return null;
    }

    public Response post(String url) {

        return post(new Request(url));
    }

    public Response post(URL url) {

        return post(new Request(url));
    }

    public Response post(Request request) {

        return null;
    }

    public Response put(String url) {

        return put(new Request(url));
    }

    public Response put(URL url) {

        return put(new Request(url));
    }

    public Response put(Request request) {

        return null;
    }

    public Response delete(String url) {

        return delete(new Request(url));
    }

    public Response delete(URL url) {

        return delete(new Request(url));
    }

    public Response delete(Request request) {

        return null;
    }

    public Response trace(String url) {

        return trace(new Request(url));
    }

    public Response trace(URL url) {

        return trace(new Request(url));
    }

    public Response trace(Request request) {

        return null;
    }

    public Response connect(String url) {

        return connect(new Request(url));
    }

    public Response connect(URL url) {

        return connect(new Request(url));
    }

    public Response connect(Request request) {

        return null;
    }


    public Configuration config() {

        return null;
    }

    public void config(Configuration configuration) {

    }


    public static class Request {

        private static URL quietUrl(String url) {

            try {

                return new URI(url).toURL();

            } catch (MalformedURLException e) {

                throw new URLException(e);

            } catch (URISyntaxException e) {

                throw new URLException(e);
            }
        }


        public Request(String url) {

            this(quietUrl(url));
        }

        public Request(URL url) {
        }
    }

    public static class Response {

    }

    public static class Configuration {

    }

    public static class URLException extends IllegalArgumentException {

        public URLException() {
        }

        public URLException(String s) {
            super(s);
        }

        public URLException(String message, Throwable cause) {
            super(message, cause);
        }

        public URLException(Throwable cause) {
            super(cause);
        }
    }
}
