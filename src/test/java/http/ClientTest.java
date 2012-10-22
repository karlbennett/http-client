package http;

import static http.Client.*;

import org.junit.Test;

/**
 * @author Karl Bennett
 */
public class ClientTest {

    private static final String URL = "";


    @Test
    public void testOPTIONS() throws Exception {

        OPTIONS(URL);
    }

    @Test
    public void testGET() throws Exception {

        GET(URL);
    }

    @Test
    public void testHEAD() throws Exception {

        HEAD(URL);
    }

    @Test
    public void testPOST() throws Exception {

        POST(URL);
    }

    @Test
    public void testPUT() throws Exception {

        PUT(URL);
    }

    @Test
    public void testDELETE() throws Exception {

        DELETE(URL);
    }

    @Test
    public void testTRACE() throws Exception {

        TRACE(URL);
    }

    @Test
    public void testCONNECT() throws Exception {

        CONNECT(URL);
    }

    @Test
    public void testOptions() throws Exception {

        new Client().options(URL);
    }

    @Test
    public void testGet() throws Exception {

        new Client().get(URL);
    }

    @Test
    public void testHead() throws Exception {

        new Client().head(URL);
    }

    @Test
    public void testPost() throws Exception {

        new Client().post(URL);
    }

    @Test
    public void testPut() throws Exception {

        new Client().put(URL);
    }

    @Test
    public void testDelete() throws Exception {

        new Client().delete(URL);
    }

    @Test
    public void testTrace() throws Exception {

        new Client().trace(URL);
    }

    @Test
    public void testConnect() throws Exception {

        new Client().connect(URL);
    }
}
