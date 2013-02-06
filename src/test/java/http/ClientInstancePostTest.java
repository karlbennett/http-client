package http;

import java.net.URL;

import static http.AbstractClientRequestMethodTest.METHOD_TYPE.INSTANCE;
import static http.Client.POST;

/**
 * @author Karl Bennett
 */
public class ClientInstancePostTest extends AbstractClientRequestMethodTest {

    public ClientInstancePostTest() {
        super(POST, INSTANCE,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return new Client().post(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return new Client().post(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return new Client().post(input);
                    }
                }
        );
    }
}
