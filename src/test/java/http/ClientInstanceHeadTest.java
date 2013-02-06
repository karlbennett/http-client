package http;

import java.net.URL;

import static http.AbstractClientRequestMethodTest.METHOD_TYPE.INSTANCE;
import static http.Client.HEAD;

/**
 * @author Karl Bennett
 */
public class ClientInstanceHeadTest extends AbstractClientRequestMethodTest {

    public ClientInstanceHeadTest() {
        super(HEAD, INSTANCE,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return new Client().head(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return new Client().head(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return new Client().head(input);
                    }
                }
        );
    }
}
