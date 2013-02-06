package http;

import java.net.URL;

import static http.AbstractClientRequestMethodTest.METHOD_TYPE.STATIC;
import static http.Client.DELETE;

/**
 * @author Karl Bennett
 */
public class ClientStaticDeleteTest extends AbstractClientRequestMethodTest {

    public ClientStaticDeleteTest() {
        super(DELETE, STATIC,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return DELETE(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return DELETE(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return DELETE(input);
                    }
                }
        );
    }
}
