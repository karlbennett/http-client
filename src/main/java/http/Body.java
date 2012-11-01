package http;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Karl Bennett
 */
public class Body<S> {

    public Collection<Parameter> getParameters() {

        return null;
    }

    public Parameter getParameter(String name) {

        return null;
    }

    public void setParameters(Collection<Parameter> parameters) {

    }

    public void addParameter(String name, Object value) {

        addParameter(new Parameter(name, Collections.singletonList(value)));
    }

    public void addParameter(Parameter parameter) {

    }

    public S getContent() {

        return null;
    }

    public void setContent(S stream) {

    }
}
