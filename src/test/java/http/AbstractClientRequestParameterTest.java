package http;

import http.parameter.Parameter;


/**
 * @author Karl Bennett
 */
public abstract class AbstractClientRequestParameterTest extends AbstractMessageAttributeTest<Request<Object>, Parameter> {


    public AbstractClientRequestParameterTest(MessageExecutor<Request<Object>, Parameter> messageExecutor) {
        super(new PropertyExecutor<Parameter>() {
            @Override
            public <T> Parameter newProperty(String name, T value) {

                return new Parameter<T>(name, value);
            }

            @Override
            public String getName(Parameter property) {

                return property.getName();
            }

            @Override
            @SuppressWarnings("unchecked")
            public <T> T getValue(Parameter property) {

                return (T) property.getValue();
            }
        }, messageExecutor);
    }
}
