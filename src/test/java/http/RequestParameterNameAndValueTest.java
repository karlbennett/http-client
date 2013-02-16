package http;

import http.parameter.Parameter;

import java.util.Collection;

import static http.Urls.TEST_URL;

/**
 * @author Karl Bennett
 */
public class RequestParameterNameAndValueTest extends AbstractClientRequestParameterTest {

    public RequestParameterNameAndValueTest() {
        super(new MessageExecutor<Request<Object>, Parameter<String>>() {

            @Override
            public Request<Object> newMessage() {

                return new Request<Object>(TEST_URL);
            }

            @Override
            public Parameter<String> getProperty(Request<Object> message, String name) {

                return message.getParameter(name);
            }

            @Override
            public void addProperty(Request<Object> message, Parameter<String> property) {

                message.addParameter(property.getName(), property.getValue());
            }

            @Override
            public void addProperties(Request<Object> message, Collection<Parameter<String>> properties) {

                message.addParameters(properties);
            }

            @Override
            public Collection<Parameter<String>> getProperties(Request<Object> message) {

                return message.getParameters();
            }

            @Override
            public void setProperties(Request<Object> message, Collection<Parameter<String>> properties) {

                message.setParameters(properties);
            }

            @Override
            public Parameter<String> removeProperty(Request<Object> message, Parameter<String> property) {

                return message.removeParameter(property.getName(), property.getValue());
            }

            @Override
            public Collection<Parameter<String>> removeProperties(Request<Object> message, Collection<Parameter<String>> properties) {

                return message.removeParamters(properties);
            }
        });
    }
}
