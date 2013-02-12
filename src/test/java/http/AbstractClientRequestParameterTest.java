package http;

import http.parameter.Parameter;


/**
 * @author Karl Bennett
 */
public abstract class AbstractClientRequestParameterTest extends AbstractMessageMultiValueAttributeTest<Request<Object>, Parameter<String>> {


    public AbstractClientRequestParameterTest(MessageExecutor<Request<Object>, Parameter<String>> messageExecutor) {
        super(new PropertyExecutor<Parameter<String>>() {
            @Override
            public <T> Parameter<String> newProperty(String name, T value) {

                return new Parameter<String>(name, (String) value);
            }

            @Override
            public String getName(Parameter<String> property) {

                return property.getName();
            }

            @Override
            @SuppressWarnings("unchecked")
            public <T> T getValue(Parameter<String> property) {

                return (T) property.getValue();
            }
        }, messageExecutor);
    }
}
