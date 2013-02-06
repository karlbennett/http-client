package http;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static http.parameter.Parameters.*;

/**
 * A utility class containing constant URL values that can be used within tests.
 *
 * @author Karl Bennett
 */
public final class Urls {

    private Urls() {
    }

    public static final String TEST_URL_STRING = "http://test.com";

    public static final URL TEST_URL;

    static {

        URL url;

        try {

            url = new URI(TEST_URL_STRING).toURL();

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        } catch (URISyntaxException e) {

            throw new RuntimeException(e);
        }

        TEST_URL = url;
    }

    public static final String TEST_QUERY_STRING = PARAMETER_NAME_ONE + "=" + PARAMETER_VALUE_ONE + "&" +
            PARAMETER_NAME_TWO + "=" + PARAMETER_VALUE_TWO + "&" + PARAMETER_NAME_THREE + "=" + PARAMETER_VALUE_THREE;

    public static final String TEST_URL_STRING_WITH_QUERY_STRING = TEST_URL_STRING + "?" + TEST_QUERY_STRING;

    public static final URL TEST_URL_WITH_QUERY;

    static {

        URL url;

        try {

            url = new URI(TEST_URL_STRING + '?' + TEST_QUERY_STRING).toURL();

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        } catch (URISyntaxException e) {

            throw new RuntimeException(e);
        }

        TEST_URL_WITH_QUERY = url;
    }
}
