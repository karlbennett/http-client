package http.parameter;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static http.parameter.Parameters.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class ParameterTest {

    @Test
    public void testStaticToString() throws Exception {

        assertEquals("Parameter.toString(Collection<Parameter>) should produce the correct string.", PARAMETER_STRING,
                Parameter.toString(PARAMETERS));
    }

    @Test
    public void testStaticToStringWithEmptyCollection() throws Exception {

        assertEquals("toString(Collection<Parameter>) should produce an empty string.", "",
                Parameter.toString(Collections.<Parameter<String>>emptySet()));
    }

    @Test
    public void testStaticToStringWithNull() throws Exception {

        assertEquals("toString(Collection<Parameter>) should produce an empty string.", "",
                Parameter.toString(Collections.<Parameter<String>>emptySet()));
    }

    @Test
    public void testParse() throws Exception {

        assertEquals("Parameter.parse(String) should produce the correct parameters.", new HashSet<>(PARAMETERS),
                Parameter.parse(PARAMETER_STRING));

        Collection<Parameter<String>> parameters = new HashSet<>(PARAMETERS);
        parameters.remove(PARAMETER_ONE);
        parameters.remove(PARAMETER_TWO);
        parameters.add(new Parameter<>(PARAMETER_NAME_ONE, Arrays.asList(PARAMETER_VALUE_ONE, PARAMETER_VALUE_TWO)));

        String parametersString = PARAMETER_NAME_ONE + '=' + PARAMETER_VALUE_ONE + '&' +
                PARAMETER_NAME_ONE + '=' + PARAMETER_VALUE_TWO + '&' +
                PARAMETER_NAME_THREE + '=' + PARAMETER_VALUE_THREE;

        assertEquals("Parameter.parse(String) should produce the correct parameters.", parameters,
                Parameter.parse(parametersString));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseWithInvalidString() throws Exception {

        Parameter.parse("this should not work & thus=fail.");
    }

    @Test
    public void testParseWithEmptyString() throws Exception {

        assertEquals("Parameter.parse(String) should produce an empty collection.", Collections.<Parameter>emptySet(),
                Parameter.parse(""));
    }

    @Test
    public void testParseWithNull() throws Exception {

        assertEquals("Parameter.parse(String) should produce an empty collection.", Collections.<Parameter>emptySet(),
                Parameter.parse(null));
    }

    @Test
    public void testToString() throws Exception {

        Parameter<String> parameter = new Parameter<>(PARAMETER_NAME_ONE, Arrays.asList(
                PARAMETER_VALUE_ONE,
                PARAMETER_VALUE_TWO,
                PARAMETER_VALUE_THREE
        ));

        String parameterString = PARAMETER_NAME_ONE + '=' + PARAMETER_VALUE_ONE + '&' +
                PARAMETER_NAME_ONE + '=' + PARAMETER_VALUE_TWO + '&' + PARAMETER_NAME_ONE + '=' + PARAMETER_VALUE_THREE;

        assertEquals("Parameter.toString() should produce the correct string.", parameterString, parameter.toString());
    }
}
