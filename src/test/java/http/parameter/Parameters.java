package http.parameter;

import java.util.*;

/**
 * A utility class containing constant parameter values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Parameters {

    private Parameters() {
    }

    public static final String PARAMETER_NAME_ONE = "parameter_name_one";
    public static final String PARAMETER_VALUE_ONE = "parameter_value_one";
    public static final String PARAMETER_NAME_TWO = "parameter_name_two";
    public static final String PARAMETER_VALUE_TWO = "parameter_value_two";
    public static final String PARAMETER_NAME_THREE = "parameter_name_three";
    public static final String PARAMETER_VALUE_THREE = "parameter_value_three";

    public static final String PARAMETER_STRING = PARAMETER_NAME_ONE + '=' + PARAMETER_VALUE_ONE + '&' +
            PARAMETER_NAME_TWO + '=' + PARAMETER_VALUE_TWO + '&' + PARAMETER_NAME_THREE + '=' + PARAMETER_VALUE_THREE;

    public static final Parameter<String> PARAMETER_ONE = new Parameter<String>(PARAMETER_NAME_ONE,
            PARAMETER_VALUE_ONE);
    public static final Parameter<String> PARAMETER_TWO = new Parameter<String>(PARAMETER_NAME_TWO,
            PARAMETER_VALUE_TWO);
    public static final Parameter<String> PARAMETER_THREE = new Parameter<String>(PARAMETER_NAME_THREE,
            PARAMETER_VALUE_THREE);

    public static final Collection<Parameter<String>> PARAMETERS = Collections.unmodifiableList(
            Arrays.asList(PARAMETER_ONE, PARAMETER_TWO, PARAMETER_THREE));
}
