package http;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static http.Client.Request;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Karl Bennett
 */
public class ClientRequestTest {

    public static final String TEST_URL_STRING = "http://test.com";

    public static final URL TEST_URL;
    static {

        URL url = null;

        try {

            url = new URI(TEST_URL_STRING).toURL();

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        } catch (URISyntaxException e) {

            throw new RuntimeException(e);
        }

        TEST_URL = url;
    }

    public static final String PARAMETER_NAME_ONE = "parameter_name_one";
    public static final String PARAMETER_VALUE_ONE = "parameter_value_one";
    public static final String PARAMETER_NAME_TWO = "parameter_name_two";
    public static final String PARAMETER_VALUE_TWO = "parameter_value_two";
    public static final String PARAMETER_NAME_THREE = "parameter_name_three";
    public static final String PARAMETER_VALUE_THREE = "parameter_value_three";

    public static final Parameter<String> PARAMETER_ONE = new Parameter<String>(PARAMETER_NAME_ONE,
            PARAMETER_VALUE_ONE);
    public static final Parameter<String> PARAMETER_TWO = new Parameter<String>(PARAMETER_NAME_TWO,
            PARAMETER_VALUE_TWO);
    public static final Parameter<String> PARAMETER_THREE = new Parameter<String>(PARAMETER_NAME_THREE,
            PARAMETER_VALUE_THREE);

    public static final Collection<Parameter> PARAMETERS = Collections.unmodifiableCollection(
            Arrays.<Parameter>asList((Parameter) PARAMETER_ONE, (Parameter) PARAMETER_TWO, (Parameter) PARAMETER_THREE));

    public static final String TEST_QUERY_STRING = "?" + PARAMETER_NAME_ONE + "=" + PARAMETER_VALUE_ONE + "&" +
            PARAMETER_NAME_TWO + "=" + PARAMETER_VALUE_TWO + "&" + PARAMETER_NAME_THREE + "=" + PARAMETER_VALUE_THREE;


    @Test
    public void testCreateRequestWithUrlString() throws Exception {

        assertEquals("the request URL should be correct.", TEST_URL, new Request(TEST_URL.toString()).getUrl());
    }

    @Test
    public void testCreateRequestWithUrlStringAndQueryString() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL_STRING + TEST_QUERY_STRING);

        URL url = new URI(TEST_URL_STRING + TEST_QUERY_STRING).toURL();

        assertEquals("the request URL should be correct.", url, request.getUrl());

        Collection<Parameter> parameters = request.getParameters();

        assertEquals("the correct parameters are included in the request.", PARAMETERS, parameters);
    }

    @Test
    public void testGetParameter() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("parameter one is retrieved correctly.", PARAMETER_ONE, request.getParameter(PARAMETER_NAME_ONE));
        assertEquals("parameter two is retrieved correctly.", PARAMETER_TWO, request.getParameter(PARAMETER_NAME_TWO));
        assertEquals("parameter three is retrieved correctly.", PARAMETER_THREE,
                request.getParameter(PARAMETER_NAME_THREE));
    }

    @Test
    public void testGetParameterThatDoesNotExist() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL);

        assertNull("retrieving a parameter when no parameters exist should return null.", 
                request.getParameter(PARAMETER_NAME_ONE));

        request.setParameters(new HashSet<Parameter>(PARAMETERS));

        assertNull("retrieving a parameter that does not exist should return null.", request.getParameter("not here"));
    }

    @Test
    public void testAddParameterWithNameAndValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(PARAMETER_NAME_ONE, PARAMETER_VALUE_ONE);

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should have been added", PARAMETER_ONE, request.getParameter(PARAMETER_NAME_ONE));

        request.addParameter(PARAMETER_NAME_TWO, PARAMETER_VALUE_TWO);

        assertEquals("two parameters should exist", 2, request.getParameters().size());
        assertEquals("parameter two should have been added", PARAMETER_TWO, request.getParameter(PARAMETER_NAME_TWO));

        request.addParameter(PARAMETER_NAME_THREE, PARAMETER_VALUE_THREE);

        assertEquals("three parameters should exist", 3, request.getParameters().size());
        assertEquals("parameter three should have been added", PARAMETER_THREE,
                request.getParameter(PARAMETER_NAME_THREE));

        request.addParameter(PARAMETER_NAME_ONE, PARAMETER_VALUE_TWO);

        assertEquals("three parameters should exist when a value has been added to parameter one", 3,
                request.getParameters().size());
        assertEquals("parameter one should have value one.", PARAMETER_VALUE_ONE,
                request.getParameter(PARAMETER_NAME_ONE).getValue());
        assertEquals("parameter one should have value two.", PARAMETER_VALUE_TWO,
                request.getParameter(PARAMETER_NAME_ONE).getValues().get(1));
    }

    @Test
    public void testAddParameterWithNameAndEmptyValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(PARAMETER_NAME_ONE, "");

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have an empty value", "",
                request.getParameter(PARAMETER_NAME_ONE).getValue());

        request.addParameter(PARAMETER_NAME_ONE, "");

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should still only have have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have an empty value", "",
                request.getParameter(PARAMETER_NAME_ONE).getValue());
    }

    @Test
    public void testAddParameterWithNameAndNullValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(PARAMETER_NAME_ONE, null);

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have a null value", null,
                request.getParameter(PARAMETER_NAME_ONE).getValue());

        request.addParameter(PARAMETER_NAME_ONE, null);

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should still only have have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have a null value", null,
                request.getParameter(PARAMETER_NAME_ONE).getValue());
    }

    @Test
    public void testAddParameterWithEmptyNameAndValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter("", PARAMETER_VALUE_ONE);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullNameAndValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(null, PARAMETER_VALUE_ONE);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithEmptyNameAndEmptyValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter("", "");

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullNameAndNullValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(null, null);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameter() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(PARAMETER_ONE);

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should have been added", PARAMETER_ONE, request.getParameter(PARAMETER_NAME_ONE));

        request.addParameter(PARAMETER_TWO);

        assertEquals("two parameters should exist", 2, request.getParameters().size());
        assertEquals("parameter two should have been added", PARAMETER_TWO, request.getParameter(PARAMETER_NAME_TWO));

        request.addParameter(PARAMETER_THREE);

        assertEquals("three parameters should exist", 3, request.getParameters().size());
        assertEquals("parameter three should have been added", PARAMETER_THREE,
                request.getParameter(PARAMETER_NAME_THREE));

        request.addParameter(new Parameter<String>(PARAMETER_NAME_ONE, PARAMETER_VALUE_TWO));

        assertEquals("three parameters should exist when a new value has been added to parameter one", 3,
                request.getParameters().size());
        assertEquals("parameter one should have value one.", PARAMETER_VALUE_ONE,
                request.getParameter(PARAMETER_NAME_ONE).getValue());
        assertEquals("parameter one should have value two.", PARAMETER_VALUE_TWO,
                request.getParameter(PARAMETER_NAME_ONE).getValues().get(1));
    }

    @Test
    public void testAddParameterWithEmptyValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>(PARAMETER_NAME_ONE, ""));

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have an empty value", "",
                request.getParameter(PARAMETER_NAME_ONE).getValue());

        request.addParameter(new Parameter<String>(PARAMETER_NAME_ONE, ""));

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should still only have have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have an empty value", "",
                request.getParameter(PARAMETER_NAME_ONE).getValue());
    }

    @Test
    public void testAddParameterWithNullValue() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<Object>(PARAMETER_NAME_ONE, null));

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have an empty value", "",
                request.getParameter(PARAMETER_NAME_ONE).getValue());

        request.addParameter(new Parameter<Object>(PARAMETER_NAME_ONE, null));

        assertEquals("one parameter should exist", 1, request.getParameters().size());
        assertEquals("parameter one should still only have have one value", 1,
                request.getParameter(PARAMETER_NAME_ONE).getValues().size());
        assertEquals("parameter one should have an empty value", "",
                request.getParameter(PARAMETER_NAME_ONE).getValue());
    }

    @Test
    public void testAddParameterWithEmptyName() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>("", PARAMETER_VALUE_ONE));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullName() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>(null, PARAMETER_VALUE_ONE));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithEmptyValues() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>("", ""));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullValues() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<Object>(null, null));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullParameter() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(null);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }
}
