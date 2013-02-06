package http;

import java.net.URL;

import static http.AbstractClientRequestMethodTest.METHOD_TYPE.STATIC;
import static http.Client.POST;

/**
 * @author Karl Bennett
 */
public class ClientStaticPostTest extends AbstractClientRequestMethodTest {

    public ClientStaticPostTest() {
        super(POST, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return POST(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return POST(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return POST(input);
                    }
                }
        );
    }
}
