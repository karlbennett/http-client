package http;

import java.net.URL;

import static http.Client.*;
import static http.AbstractClientRequestMethodTest.METHOD_TYPE.STATIC;

/**
 * @author Karl Bennett
 */
public class ClientStaticOptionsTest extends AbstractClientRequestMethodTest {

    public ClientStaticOptionsTest() {
        super(OPTIONS, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return OPTIONS(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return OPTIONS(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return OPTIONS(input);
                    }
                }
        );
    }
}
