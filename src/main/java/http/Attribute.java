package http;

import java.util.List;

/**
 * Represents a generic HTTP attribute with a name and multiple values.
 *
 * @author Karl Bennett
 */
public class Attribute {

    private final String name;
    private final List<Object> values;


    /**
     * Create an {@code Attribute} with a name and values.
     *
     * @param name the name of the attribute.
     * @param values the values for the attribute.
     */
    public Attribute(String name, List<Object> values) {

        this.name = name;
        this.values = values;
    }

    /**
     * @return the attributes name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the attributes values.
     */
    public List<Object> getValues() {
        return values;
    }
}
