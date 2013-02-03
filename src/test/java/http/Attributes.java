package http;

import http.attribute.Attribute;

import java.util.*;

import static java.util.Map.Entry;
import static java.util.AbstractMap.SimpleEntry;

/**
 * A utility class containing constant attribute values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Attributes {

    private Attributes() {
    }

    public static final String TEST_ATTRIBUTE_NAME_ONE = "test_attribute_name_one";
    public static final String TEST_ATTRIBUTE_NAME_TWO = "test_attribute_name_two";
    public static final String TEST_ATTRIBUTE_NAME_THREE = "test_attribute_name_three";

    public static final Collection<String> TEST_ATTRIBUTE_NAMES = new HashSet<>(Arrays.asList(
            TEST_ATTRIBUTE_NAME_ONE,
            TEST_ATTRIBUTE_NAME_TWO,
            TEST_ATTRIBUTE_NAME_THREE
    ));

    public static final String TEST_ATTRIBUTE_VALUE_ONE = "test_attribute_value_one";
    public static final String TEST_ATTRIBUTE_VALUE_TWO = "test_attribute_value_two";
    public static final String TEST_ATTRIBUTE_VALUE_THREE = "test_attribute_value_three";

    public static final List<String> TEST_ATTRIBUTE_VALUES = Arrays.asList(
            TEST_ATTRIBUTE_VALUE_ONE,
            TEST_ATTRIBUTE_VALUE_TWO,
            TEST_ATTRIBUTE_VALUE_THREE
    );

    public static final Attribute<String> TEST_ATTRIBUTE_ONE = new Attribute<>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_VALUE_ONE);
    public static final Attribute<String> TEST_ATTRIBUTE_TWO = new Attribute<>(TEST_ATTRIBUTE_NAME_TWO, TEST_ATTRIBUTE_VALUE_TWO);
    public static final Attribute<String> TEST_ATTRIBUTE_THREE = new Attribute<>(TEST_ATTRIBUTE_NAME_THREE, TEST_ATTRIBUTE_VALUE_THREE);

    public static final Collection<Attribute<String>> TEST_ATTRIBUTES = new HashSet<>(Arrays.asList(
            TEST_ATTRIBUTE_ONE,
            TEST_ATTRIBUTE_TWO,
            TEST_ATTRIBUTE_THREE
    ));

    public static final Map<String, Attribute<String>> TEST_ATTRIBUTES_MAP;
    static {

        Map<String, Attribute<String>> testAttributesMap = new HashMap<>();

        testAttributesMap.put(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_ONE);
        testAttributesMap.put(TEST_ATTRIBUTE_NAME_TWO, TEST_ATTRIBUTE_TWO);
        testAttributesMap.put(TEST_ATTRIBUTE_NAME_THREE, TEST_ATTRIBUTE_THREE);

        TEST_ATTRIBUTES_MAP = testAttributesMap;
    }

    public static final Set<Entry<String, Attribute<String>>> TEST_ATTRIBUTE_ENTRY_SET = new HashSet<Entry<String, Attribute<String>>>(Arrays.asList(
        new SimpleEntry<>(TEST_ATTRIBUTE_NAME_ONE, TEST_ATTRIBUTE_ONE),
        new SimpleEntry<>(TEST_ATTRIBUTE_NAME_TWO, TEST_ATTRIBUTE_TWO),
        new SimpleEntry<>(TEST_ATTRIBUTE_NAME_THREE, TEST_ATTRIBUTE_THREE)
    ));
}
