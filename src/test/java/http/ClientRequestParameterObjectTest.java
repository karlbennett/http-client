package http;

import http.parameter.Parameter;

import java.util.Collection;

import static http.Urls.TEST_URL;

/**
 * @author Karl Bennett
 */
public class ClientRequestParameterObjectTest extends AbstractClientRequestParameterTest {

    public ClientRequestParameterObjectTest() {
        super(new MessageExecutor<Request<Object>, Parameter>() {

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
            public void addProperty(Request<Object> message, Parameter property) {

                message.addParameter(property);
            }

            @Override
            public Collection<Parameter> getProperties(Request<Object> message) {

                return message.getParameters();
            }

            @Override
            public void setProperties(Request<Object> message, Collection<Parameter> properties) {

                message.setParameters(properties);
            }
        });
    }
}
