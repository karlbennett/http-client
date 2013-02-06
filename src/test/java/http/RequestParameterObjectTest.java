package http;

import http.parameter.Parameter;

import java.util.Collection;

import static http.Urls.TEST_URL;

/**
 * @author Karl Bennett
 */
public class RequestParameterObjectTest extends AbstractClientRequestParameterTest {

    public RequestParameterObjectTest() {
        super(new MessageExecutor<Request<Object>, Parameter<String>>() {

            @Override
            public Request<Object> newMessage() {

                return new Request<Object>(TEST_URL);
            }

            @Override
            public Parameter getProperty(Request<Object> message, String name) {

                return message.getParameter(name);
            }

            @Override
            @SuppressWarnings("unchecked")
            public void addProperty(Request<Object> message, Parameter<String> property) {

                message.addParameter(property);
            }

            @Override
            public Collection<Parameter<String>> getProperties(Request<Object> message) {

                return message.getParameters();
            }

            @Override
            public void setProperties(Request<Object> message, Collection<Parameter<String>> properties) {

                message.setParameters(properties);
            }
        });
    }
}
