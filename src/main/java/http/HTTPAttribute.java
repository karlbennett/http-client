package http;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class HTTPAttribute {

    private final String name;
    private final List<Object> values;


    public HTTPAttribute(String name, List<Object> values) {

        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Object> getValues() {
        return values;
    }
}
