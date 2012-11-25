package http;

import java.net.URL;

import static http.Client.*;
import static http.AbstractClientRequestMethodTest.METHOD_TYPE.STATIC;

/**
 * @author Karl Bennett
 */
public class ClientStaticGetTest extends AbstractClientRequestMethodTest {

    public ClientStaticGetTest() {
        super(GET, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return GET(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return GET(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return GET(input);
                    }
                }
        );
    }
}
