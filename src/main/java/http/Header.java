package http;

import java.util.List;

/**
 * Represent an HTTP header which is made up of a name and possibly multiple values.
 *
 * @author Karl Bennett
 */
public class Header extends Attribute {

    /**
     * Create a new {@code Header} with the supplied name and values.
     *
     * @param name the name of the header.
     * @param values the values for the header.
     */
    public Header(String name, List<Object> values) {

        super(name, values);
    }
}