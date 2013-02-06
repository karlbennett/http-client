package http;

import java.net.URL;

import static http.AbstractClientRequestMethodTest.METHOD_TYPE.STATIC;
import static http.Client.TRACE;

/**
 * @author Karl Bennett
 */
public class ClientStaticTraceTest extends AbstractClientRequestMethodTest {

    public ClientStaticTraceTest() {
        super(TRACE, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return TRACE(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return TRACE(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return TRACE(input);
                    }
                }
        );
    }
}
