package http;

import java.net.URL;

import static http.Client.*;
import static http.RequestExecutor.METHOD_TYPE.STATIC;

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
