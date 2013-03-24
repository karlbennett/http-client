package http.util;

import org.junit.Test;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import java.util.*;

import static http.util.MimeTypes.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class MimeTypesTest {

    private static final String CHAR_SET_SUFFIX = "; charset=UTF-8";

    private static final String APPLICATION_X_WWW_FORM_URL_ENCODED_STRING = APPLICATION + '/' + X_WWW_FORM_URL_ENCODED +
            CHAR_SET_SUFFIX;
    private static final String APPLICATION_JSON_STRING = APPLICATION + '/' + JSON + CHAR_SET_SUFFIX;
    private static final String MULTIPART_FORM_DATA_STRING = MULTIPART + '/' + FORM_DATA + CHAR_SET_SUFFIX;
    private static final String APPLICATION_XML_STRING = APPLICATION + '/' + XML + CHAR_SET_SUFFIX;

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

            applicationXWwwFormUrlEncoded = new ComparableMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED);
            applicationJson = new ComparableMimeType(APPLICATION, JSON);
            multipartFormData = new ComparableMimeType(MULTIPART, FORM_DATA);
            applicationXml = new ComparableMimeType(APPLICATION, XML);

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

        assertEquals("application/x-www-form-urlencoded mime type should be created correctly.",
                APPLICATION_X_WWW_FORM_URL_ENCODED, quietMimeType(APPLICATION_X_WWW_FORM_URL_ENCODED_STRING));

        assertEquals("application/json mime type should be created correctly.",
                APPLICATION_JSON, quietMimeType(APPLICATION_JSON_STRING));

        assertEquals("multipart/form-data mime type should be created correctly.",
                MULTIPART_FORM_DATA, quietMimeType(MULTIPART_FORM_DATA_STRING));

        assertEquals("application/xml mime type should be created correctly.",
                APPLICATION_XML, quietMimeType(APPLICATION_XML_STRING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuietMimeTypeWithInvalidRawData() throws Exception {

        quietMimeType("test");
    }

    @Test
    public void testQuietMimeTypeWithPrimaryAndSub() throws Exception {

        assertEquals("application/x-www-form-urlencoded mime type should be created correctly.",
                APPLICATION_X_WWW_FORM_URL_ENCODED, quietMimeType(APPLICATION, X_WWW_FORM_URL_ENCODED));

        assertEquals("application/json mime type should be created correctly.",
                APPLICATION_JSON, quietMimeType(APPLICATION, JSON));

        assertEquals("multipart/form-data mime type should be created correctly.",
                MULTIPART_FORM_DATA, quietMimeType(MULTIPART, FORM_DATA));

        assertEquals("application/xml mime type should be created correctly.",
                APPLICATION_XML, quietMimeType(APPLICATION, XML));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuietMimeTypeWithInvalidPrimaryAndSub() throws Exception {

        quietMimeType("t3$t", ":t3$t");
    }

    @Test
    public void testCreateComparableMimeType() throws Exception {

        MimeType mimeType = new MimeType(APPLICATION, JSON);
        MimeType comparableMimeType = new ComparableMimeType(APPLICATION, JSON);

        assertEquals("the MIME type primary value should be correct.", mimeType.getPrimaryType(),
                comparableMimeType.getPrimaryType());

        assertEquals("the MIME type sub value should be correct.", mimeType.getSubType(),
                comparableMimeType.getSubType());
    }

    @Test(expected = MimeTypeParseException.class)
    public void testCreateComparableMimeTypeWithInvalidRawData() throws Exception {

        new ComparableMimeType("test");
    }

    @Test(expected = MimeTypeParseException.class)
    public void testCreateComparableMimeTypeWithInvalidPrimaryAndSub() throws Exception {

        new ComparableMimeType("t3$t", ":t3$t");
    }

    @Test
    public void testComparableMimeTypeInSet() throws Exception {

        Set<MimeType> mimeTypes = new HashSet<MimeType>(Arrays.asList(
                APPLICATION_X_WWW_FORM_URL_ENCODED,
                APPLICATION_JSON,
                MULTIPART_FORM_DATA,
                APPLICATION_XML
        ));

        assertEquals("the mime type set contains the correct number of objects", 4, mimeTypes.size());

        assertTrue("the mime type set contains the application/x-www-form-urlencoded mime type",
                mimeTypes.contains(APPLICATION_X_WWW_FORM_URL_ENCODED));

        assertTrue("the mime type set contains the application/json mime type",
                mimeTypes.contains(APPLICATION_JSON));

        assertTrue("the mime type set contains the multipart/form-data mime type",
                mimeTypes.contains(MULTIPART_FORM_DATA));

        assertTrue("the mime type set contains the application/xml mime type",
                mimeTypes.contains(APPLICATION_XML));
    }

    @Test
    public void testComparableMimeTypeInMap() throws Exception {

        Map<MimeType, Integer> mimeTypes = new HashMap<MimeType, Integer>();
        mimeTypes.put(APPLICATION_X_WWW_FORM_URL_ENCODED, 0);
        mimeTypes.put(APPLICATION_JSON, 1);
        mimeTypes.put(MULTIPART_FORM_DATA, 2);
        mimeTypes.put(APPLICATION_XML, 3);

        assertEquals("the mime type map contains the correct number of objects", 4, mimeTypes.size());

        assertEquals("the mime type map has the correct value for the application/x-www-form-urlencoded mime type",
                new Integer(0), mimeTypes.get(APPLICATION_X_WWW_FORM_URL_ENCODED));

        assertEquals("the mime type map has the correct value for the application/json mime type", new Integer(1),
                mimeTypes.get(APPLICATION_JSON));

        assertEquals("the mime type map has the correct value for the multipart/form-data mime type", new Integer(2),
                mimeTypes.get(MULTIPART_FORM_DATA));

        assertEquals("the mime type map has the correct value for the application/xml mime type", new Integer(3),
                mimeTypes.get(APPLICATION_XML));
    }

    @Test
    public void testComparableMimeTypeOrdering() throws Exception {

        final MimeType[] MIME_TYPES = {
                APPLICATION_JSON,
                APPLICATION_X_WWW_FORM_URL_ENCODED,
                APPLICATION_XML,
                MULTIPART_FORM_DATA
        };

        Set<MimeType> mimeTypes = new TreeSet<MimeType>(Arrays.asList(
                APPLICATION_X_WWW_FORM_URL_ENCODED,
                APPLICATION_JSON,
                MULTIPART_FORM_DATA,
                APPLICATION_XML
        ));

        assertArrayEquals("the mime type ordering is correct", MIME_TYPES,
                mimeTypes.toArray(new MimeType[mimeTypes.size()]));
    }
}
