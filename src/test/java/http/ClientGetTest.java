package http;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static http.Client.*;

/**
 * @author Karl Bennett
 */
public class ClientGetTest {

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
}
