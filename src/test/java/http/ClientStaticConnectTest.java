package http;

import java.net.URL;

import static http.Client.*;
import static http.AbstractClientRequestMethodTest.METHOD_TYPE.STATIC;

/**
 * @author Karl Bennett
 */
public class ClientStaticConnectTest extends AbstractClientRequestMethodTest {

    public ClientStaticConnectTest() {
        super(CONNECT, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return CONNECT(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return CONNECT(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return CONNECT(input);
                    }
                }
        );
    }
}
