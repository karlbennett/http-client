package http.header.util;

import http.header.Accept;
import http.header.ContentType;
import http.header.Header;
import org.junit.Test;

import static http.util.MimeTypes.*;
import static org.junit.Assert.*;

/**
 * @author Karl Bennett
 */
public class HeaderToMimeTypeConversionTest {

    private static final Header MIME_TYPE_STRING_HEADER =
            new Header<String>("mime_type_string_header", APPLICATION + '/' + JSON);
    private static final Header ACCEPT_STRING_HEADER = new Header<String>(Accept.ACCEPT, APPLICATION + '/' + JSON);
    private static final Header CONTENT_TYPE_STRING_HEADER =
            new Header<String>(ContentType.CONTENT_TYPE, APPLICATION + '/' + JSON);
    private static final Header INVALID_MIME_TYPE_STRING_HEADER =
            new Header<String>(Accept.ACCEPT, APPLICATION + "fail");

    private static final Header ACCEPT = new Accept(APPLICATION_JSON);
    private static final Header CONTENT_TYPE = new ContentType(APPLICATION_JSON);

    @Test
    public void testCreateHeaderToMimeTypeConversion() throws Exception {

        new HeaderToMimeTypeConversion<Header>(ACCEPT, Accept.ACCEPT);
        new HeaderToMimeTypeConversion<Header>(CONTENT_TYPE, ContentType.CONTENT_TYPE);
        new HeaderToMimeTypeConversion<Header>(ACCEPT_STRING_HEADER, Accept.ACCEPT);
        new HeaderToMimeTypeConversion<Header>(CONTENT_TYPE_STRING_HEADER, ContentType.CONTENT_TYPE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeaderToMimeTypeConversionWithMissNamedHeader() throws Exception {

        new HeaderToMimeTypeConversion<Header>(MIME_TYPE_STRING_HEADER, Accept.ACCEPT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeaderToMimeTypeConversionWithNullHeader() throws Exception {

        new HeaderToMimeTypeConversion<Header>(null, Accept.ACCEPT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeaderToMimeTypeConversionWithNullName() throws Exception {

        new HeaderToMimeTypeConversion<Header>(ACCEPT, null);
    }

    @Test
    public void testConvert() throws Exception {

        assertEquals("an Accept header object should be converted correctly.", APPLICATION_JSON,
                new HeaderToMimeTypeConversion<Header>(ACCEPT, Accept.ACCEPT).convert());
        assertEquals("a ContentType header object should be converted correctly.", APPLICATION_JSON,
                new HeaderToMimeTypeConversion<Header>(CONTENT_TYPE, ContentType.CONTENT_TYPE).convert());
        assertEquals("a string Accept type header object should be converted correctly.", APPLICATION_JSON,
                new HeaderToMimeTypeConversion<Header>(ACCEPT_STRING_HEADER, Accept.ACCEPT).convert());
        assertEquals("a string Content-Type type header object should be converted correctly.", APPLICATION_JSON,
                new HeaderToMimeTypeConversion<Header>(CONTENT_TYPE_STRING_HEADER, ContentType.CONTENT_TYPE).convert());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithInvalidMimeTypeHeader() throws Exception {

        new HeaderToMimeTypeConversion<Header>(INVALID_MIME_TYPE_STRING_HEADER, Accept.ACCEPT).convert();
    }
}
