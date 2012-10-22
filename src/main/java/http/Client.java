package http;

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

    public static Response GET(String url) {

        return getInstance().get(url);
    }

    public static Response HEAD(String url) {

        return getInstance().head(url);
    }

    public static Response POST(String url) {

        return getInstance().post(url);
    }

    public static Response PUT(String url) {

        return getInstance().put(url);
    }

    public static Response DELETE(String url) {

        return getInstance().delete(url);
    }

    public static Response TRACE(String url) {

        return getInstance().trace(url);
    }

    public static Response CONNECT(String url) {

        return getInstance().connect(url);
    }


    public Client() {
    }


    public Response options(String url) {

        return null;
    }

    public Response get(String url) {

        return null;
    }
    
    public Response head(String url) {

        return null;
    }

    public Response post(String url) {

        return null;
    }

    public Response put(String url) {

        return null;
    }

    public Response delete(String url) {

        return null;
    }

    public Response trace(String url) {

        return null;
    }

    public Response connect(String url) {

        return null;
    }

    public static class Request {

    }

    public static class Response {

    }
}
