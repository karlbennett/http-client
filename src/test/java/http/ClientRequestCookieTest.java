package http;

import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

import static http.Client.Request;
import static http.Cookies.*;
import static http.Parameters.*;
import static http.Urls.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Karl Bennett
 */
public class ClientRequestCookieTest extends AbstractMessageCookieTest<Object, Request<Object>> {

    public ClientRequestCookieTest() {
        super(COOKIE, new RequestCreator<Object, Request<Object>>() {

            @Override
            public Request<Object> newRequest() {

                return new Request<Object>(TEST_URL);
            }
        });
    }

    @Test
    public void testCreateRequestWithUrlString() throws Exception {

        assertEquals("the request URL should be correct.", TEST_URL, new Request(TEST_URL.toString()).getUrl());
    }

    @Test
    public void testCreateRequestWithUrlStringAndQueryString() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL_STRING + TEST_QUERY_STRING);

        URL url = new URI(TEST_URL_STRING_WITH_QUERY_STRING).toURL();

        assertEquals("the request URL should be correct.", url, request.getUrl());

        Collection<Parameter> parameters = request.getParameters();

        assertEquals("the correct parameters are included in the request.", PARAMETERS, parameters);
    }



    @Test
    public void testGetParameterDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("parameter one is retrieved correctly.", PARAMETER_ONE, request.getParameter(PARAMETER_NAME_ONE));
        assertEquals("parameter two is retrieved correctly.", PARAMETER_TWO, request.getParameter(PARAMETER_NAME_TWO));
        assertEquals("parameter three is retrieved correctly.", PARAMETER_THREE,
                request.getParameter(PARAMETER_NAME_THREE));
    }

    @Test
    public void testGetParameterThatDoesNotExistDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL);

        assertNull("retrieving a parameter when no parameters exist should return null.",
                request.getParameter(PARAMETER_NAME_ONE));

        request.setParameters(new HashSet<Parameter>(PARAMETERS));

        assertNull("retrieving a parameter that does not exist should return null.", request.getParameter("not here"));
    }

    @Test
    public void testAddParameterWithNameAndValueDUPE() throws Exception {

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
    public void testAddParameterWithNameAndEmptyValueDUPE() throws Exception {

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
    public void testAddParameterWithEmptyNameAndValueDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter("", PARAMETER_VALUE_ONE);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullNameAndValueDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(null, PARAMETER_VALUE_ONE);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithEmptyNameAndEmptyValueDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter("", "");

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullNameAndNullValueDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(null, null);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterDUPE() throws Exception {

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
    public void testAddParameterWithEmptyValueDUPE() throws Exception {

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
    public void testAddParameterWithNullValueDUPE() throws Exception {

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
    public void testAddParameterWithEmptyNameDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>("", PARAMETER_VALUE_ONE));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullNameDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>(null, PARAMETER_VALUE_ONE));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithEmptyValuesDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<String>("", ""));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullValuesDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(new Parameter<Object>(null, null));

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }

    @Test
    public void testAddParameterWithNullParameterDUPE() throws Exception {

        Request<Object> request = new Request<Object>(TEST_URL, new HashSet<Parameter>(PARAMETERS));

        assertEquals("no parameters should exist", 0, request.getParameters().size());

        request.addParameter(null);

        assertEquals("no parameters should have been added.", 0, request.getParameters().size());
    }
}