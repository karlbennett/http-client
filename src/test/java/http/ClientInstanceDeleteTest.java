package http;

import java.net.URL;

import static http.AbstractClientRequestMethodTest.METHOD_TYPE.INSTANCE;
import static http.Client.DELETE;

/**
 * @author Karl Bennett
 */
public class ClientInstanceDeleteTest extends AbstractClientRequestMethodTest {

    public ClientInstanceDeleteTest() {
        super(DELETE, INSTANCE,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return new Client().delete(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return new Client().delete(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return new Client().delete(input);
                    }
                }
        );
    }
}
