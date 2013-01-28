package http;

import java.util.Arrays;
import java.util.Collection;

/**
 * A utility class containing constant attribute values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Attributes {

    private Attributes() {
    }

    public static final String TEST_ATTRIBUTE_NAME_ONE = "test_attribute_name_one";
    public static final String TEST_ATTRIBUTE_VALUE_ONE = "test_attribute_value_one";
    public static final String TEST_ATTRIBUTE_NAME_TWO = "test_attribute_name_two";
    public static final String TEST_ATTRIBUTE_VALUE_TWO = "test_attribute_value_two";
    public static final String TEST_ATTRIBUTE_NAME_THREE = "test_attribute_name_three";
    public static final String TEST_ATTRIBUTE_VALUE_THREE = "test_attribute_value_three";
    public static final Collection<String> TEST_ATTRIBUTE_VALUES = Arrays.asList(
            TEST_ATTRIBUTE_VALUE_ONE,
            TEST_ATTRIBUTE_VALUE_TWO,
            TEST_ATTRIBUTE_VALUE_THREE
    );
}
