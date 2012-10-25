package http;

import static http.Client.*;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Karl Bennett
 */
public class ClientTest {

    private static final String URL_STRING = "http://localhost/";

    private static final URL URL;
    static {

        try {

            URL = new URI(URL_STRING).toURL();

        } catch (MalformedURLException e) {

            throw new IllegalStateException(e);

        } catch (URISyntaxException e) {

            throw new IllegalStateException(e);
        }
    }

    private static final Request REQUEST = new Request(URL);


    @Test
    public void testOPTIONSUrlString() throws Exception {

        OPTIONS(URL_STRING);
    }

    @Test
    public void testOPTIONSUrl() throws Exception {

        OPTIONS(URL);
    }

    @Test
    public void testOPTIONSRequest() throws Exception {

        OPTIONS(REQUEST);
    }

    @Test
    public void testGETUrlString() throws Exception {

        GET(URL_STRING);
    }

    @Test
    public void testGETUrl() throws Exception {

        GET(URL);
    }

    @Test
    public void testGETRequest() throws Exception {

        GET(REQUEST);
    }

    @Test
    public void testHEADUrlString() throws Exception {

        HEAD(URL_STRING);
    }

    @Test
    public void testHEADUrl() throws Exception {

        HEAD(URL);
    }

    @Test
    public void testHEADRequest() throws Exception {

        HEAD(REQUEST);
    }

    @Test
    public void testPOSTUrlString() throws Exception {

        POST(URL_STRING);
    }

    @Test
    public void testPOSTUrl() throws Exception {

        POST(URL);
    }

    @Test
    public void testPOSTRequest() throws Exception {

        POST(REQUEST);
    }

    @Test
    public void testPUTUrlString() throws Exception {

        PUT(URL_STRING);
    }

    @Test
    public void testPUTUrl() throws Exception {

        PUT(URL_STRING);
    }

    @Test
    public void testPUTRequest() throws Exception {

        PUT(URL_STRING);
    }

    @Test
    public void testDELETEUrlString() throws Exception {

        DELETE(URL_STRING);
    }

    @Test
    public void testDELETEUrl() throws Exception {

        DELETE(URL);
    }

    @Test
    public void testDELETERequest() throws Exception {

        DELETE(REQUEST);
    }

    @Test
    public void testTRACEUrlString() throws Exception {

        TRACE(URL_STRING);
    }

    @Test
    public void testTRACEUrl() throws Exception {

        TRACE(URL);
    }

    @Test
    public void testTRACERequest() throws Exception {

        TRACE(REQUEST);
    }

    @Test
    public void testCONNECTUrlString() throws Exception {

        CONNECT(URL_STRING);
    }

    @Test
    public void testCONNECTUrl() throws Exception {

        CONNECT(URL);
    }

    @Test
    public void testCONNECTRequest() throws Exception {

        CONNECT(REQUEST);
    }

    @Test
    public void testOptionsUrlString() throws Exception {

        new Client().options(URL_STRING);
    }

    @Test
    public void testOptionsUrl() throws Exception {

        new Client().options(URL);
    }

    @Test
    public void testOptionsRequest() throws Exception {

        new Client().options(REQUEST);
    }

    @Test
    public void testGetUrlString() throws Exception {

        new Client().get(URL_STRING);
    }

    @Test
    public void testGetUrl() throws Exception {

        new Client().get(URL);
    }

    @Test
    public void testGetRequest() throws Exception {

        new Client().get(REQUEST);
    }

    @Test
    public void testHeadUrlString() throws Exception {

        new Client().head(URL_STRING);
    }

    @Test
    public void testHeadUrl() throws Exception {

        new Client().head(URL);
    }

    @Test
    public void testHeadRequest() throws Exception {

        new Client().head(REQUEST);
    }

    @Test
    public void testPostUrlString() throws Exception {

        new Client().post(URL_STRING);
    }

    @Test
    public void testPostUrl() throws Exception {

        new Client().post(URL);
    }

    @Test
    public void testPostRequest() throws Exception {

        new Client().post(REQUEST);
    }

    @Test
    public void testPutUrlString() throws Exception {

        new Client().put(URL_STRING);
    }

    @Test
    public void testPutUrl() throws Exception {

        new Client().put(URL);
    }

    @Test
    public void testPutRequest() throws Exception {

        new Client().put(REQUEST);
    }

    @Test
    public void testDeleteUrlString() throws Exception {

        new Client().delete(URL_STRING);
    }

    @Test
    public void testDeleteUrl() throws Exception {

        new Client().delete(URL);
    }

    @Test
    public void testDeleteRequest() throws Exception {

        new Client().delete(REQUEST);
    }

    @Test
    public void testTraceUrlString() throws Exception {

        new Client().trace(URL_STRING);
    }

    @Test
    public void testTraceUrl() throws Exception {

        new Client().trace(URL);
    }

    @Test
    public void testTraceRequest() throws Exception {

        new Client().trace(REQUEST);
    }

    @Test
    public void testConnectUrlString() throws Exception {

        new Client().connect(URL_STRING);
    }

    @Test
    public void testConnectUrl() throws Exception {

        new Client().connect(URL);
    }

    @Test
    public void testConnectRequest() throws Exception {

        new Client().connect(REQUEST);
    }
}
