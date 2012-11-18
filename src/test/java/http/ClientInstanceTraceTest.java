package http;

import java.net.URL;

import static http.Client.*;
import static http.RequestExecutor.METHOD_TYPE.INSTANCE;

/**
 * @author Karl Bennett
 */
public class ClientInstanceTraceTest extends AbstractClientRequestMethodTest {

    public ClientInstanceTraceTest() {
        super(TRACE, INSTANCE,
                new RequestExecutor<String>() {

                    @Override
                    public Response execute(String input) {

                        return new Client().trace(input);
                    }
                },
                new RequestExecutor<URL>() {

                    @Override
                    public Response execute(URL input) {

                        return new Client().trace(input);
                    }
                },
                new RequestExecutor<Request>() {

                    @Override
                    public Response execute(Request input) {

                        return new Client().trace(input);
                    }
                }
        );
    }
}
