package http;

import java.util.List;

/**
 * @author Karl Bennett
 */
public class Attribute {

    private final String name;
    private final List<Object> values;


    public Attribute(String name, List<Object> values) {

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
