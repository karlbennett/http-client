package http;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * A utility class containing constant header values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Headers {

    private Headers() {
    }

    public static final String HEADER_NAME_ONE = "header_name_one";
    public static final String HEADER_VALUE_ONE = "header_value_one";
    public static final String HEADER_NAME_TWO = "header_name_two";
    public static final String HEADER_VALUE_TWO = "header_value_two";
    public static final String HEADER_NAME_THREE = "header_name_three";
    public static final String HEADER_VALUE_THREE = "header_value_three";

    public static final Header<String> HEADER_ONE = new Header<String>(HEADER_NAME_ONE, HEADER_VALUE_ONE);
    public static final Header<String> HEADER_TWO = new Header<String>(HEADER_NAME_TWO, HEADER_VALUE_TWO);
    public static final Header<String> HEADER_THREE = new Header<String>(HEADER_NAME_THREE, HEADER_VALUE_THREE);

    public static final Collection<Header> HEADERS = Collections.unmodifiableCollection(
            Arrays.<Header>asList((Header) HEADER_ONE, (Header) HEADER_TWO, (Header) HEADER_THREE));
}
