package http;

import java.net.URL;

import static http.Client.*;
import static http.RequestExecutor.METHOD_TYPE.STATIC;

/**
 * @author Karl Bennett
 */
public class ClientStaticHeadTest extends AbstractClientRequestMethodTest {

    public ClientStaticHeadTest() {
        super(HEAD, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return HEAD(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return HEAD(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return HEAD(input);
                    }
                }
        );
    }
}
