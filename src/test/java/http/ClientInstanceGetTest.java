package http;

import java.net.URL;

import static http.Client.*;
import static http.AbstractClientRequestMethodTest.METHOD_TYPE.INSTANCE;

/**
 * @author Karl Bennett
 */
public class ClientInstanceGetTest extends AbstractClientRequestMethodTest {

    public ClientInstanceGetTest() {
        super(GET, INSTANCE,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return new Client().get(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return new Client().get(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return new Client().get(input);
                    }
                }
        );
    }
}
