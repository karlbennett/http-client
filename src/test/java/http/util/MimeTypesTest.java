package http.util;

import org.junit.Test;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import static http.util.MimeTypes.*;
import static org.junit.Assert.assertTrue;

/**
 * @author Karl Bennett
 */
public class MimeTypesTest {

    private static final String APPLICATION_X_WWW_FORM_URL_ENCODED_STRING = APPLICATION + '/' + X_WWW_FORM_URL_ENCODED;
    private static final String APPLICATION_JSON_STRING = APPLICATION + '/' + JSON;
    private static final String MULTIPART_FORM_DATA_STRING = MULTIPART + '/' + FORM_DATA;
    private static final String APPLICATION_XML_STRING = APPLICATION + '/' + XML;

    private static final MimeType APPLICATION_X_WWW_FORM_URL_ENCODED;
    private static final MimeType APPLICATION_JSON;
    private static final MimeType MULTIPART_FORM_DATA;
    private static final MimeType APPLICATION_XML;

    static {

        MimeType applicationXWwwFormUrlEncoded = null;
        MimeType applicationJson = null;
        MimeType multipartFormData = null;
        MimeType applicationXml = null;

        try {

            applicationXWwwFormUrlEncoded = new MimeType(APPLICATION, X_WWW_FORM_URL_ENCODED);
            applicationJson = new MimeType(APPLICATION, JSON);
            multipartFormData = new MimeType(MULTIPART, FORM_DATA);
            applicationXml = new MimeType(APPLICATION, XML);

        } catch (MimeTypeParseException e) {

            throw new IllegalStateException(e);
        }

        APPLICATION_X_WWW_FORM_URL_ENCODED = applicationXWwwFormUrlEncoded;
        APPLICATION_JSON = applicationJson;
        MULTIPART_FORM_DATA = multipartFormData;
        APPLICATION_XML = applicationXml;
    }

    @Test
    public void testQuietMimeTypeWithRawData() throws Exception {

        assertTrue("application/x-www-form-urlencoded mimetype should be created correctly.",
                APPLICATION_X_WWW_FORM_URL_ENCODED.match(quietMimeType(APPLICATION_X_WWW_FORM_URL_ENCODED_STRING)));

        assertTrue("application/json mimetype should be created correctly.",
                APPLICATION_JSON.match(quietMimeType(APPLICATION_JSON_STRING)));

        assertTrue("multipart/form-data mimetype should be created correctly.",
                MULTIPART_FORM_DATA.match(quietMimeType(MULTIPART_FORM_DATA_STRING)));

        assertTrue("application/xml mimetype should be created correctly.",
                APPLICATION_XML.match(quietMimeType(APPLICATION_XML_STRING)));
    }

    @Test
    public void testQuietMimeTypeWithPrimaryAndSub() throws Exception {

        assertTrue("application/x-www-form-urlencoded mimetype should be created correctly.",
                APPLICATION_X_WWW_FORM_URL_ENCODED.match(quietMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED)));

        assertTrue("application/json mimetype should be created correctly.",
                APPLICATION_JSON.match(quietMimeType(APPLICATION, JSON)));

        assertTrue("multipart/form-data mimetype should be created correctly.",
                MULTIPART_FORM_DATA.match(quietMimeType(MULTIPART, FORM_DATA)));

        assertTrue("application/xml mimetype should be created correctly.",
                APPLICATION_XML.match(quietMimeType(APPLICATION, XML)));
    }
}
